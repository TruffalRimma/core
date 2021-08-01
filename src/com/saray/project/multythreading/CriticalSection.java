package com.saray.project.multythreading;

/*
СИНХРОНИЗАЦИЯ БЛОКОВ КОДА ВМЕСТО ЦЕЛЫХ МЕТОДОВ
Также демонстрирует защиту класса, небезопасного
по отношению к потокам, другим защищенным классом
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// ПОТОКО-НЕБЕЗОПАСНЫЙ КЛАСС
// т.к. требует равенства 2х своих полей
class Pair {

    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // инкрементация небезопасна в условиях многопоточности
    public void incrementX() {
        x++;
    }

    public void incrementY() {
        y++;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public class PairValuesNotEqualException extends RuntimeException {
        public PairValuesNotEqualException() {
            super("Pair values not equal: " + Pair.this);
        }
    }

    // ПЕРЕМЕННЫЕ ДОЛЖНЫ БЫТЬ РАВНЫ
    public void checkState() {
        if (x != y) {
            throw new PairValuesNotEqualException();
        }
    }
}

// ЗАШИТА Pair В ПОТОКО-БЕЗОПАСНОМ КЛАССЕ
abstract class PairManager {

    // атомарный класс, предназначенный для выполнения атомарных операций (в многопточной среде)
    // которые таковыми не являются (в нашем случае счетчик инкрементируется)
    AtomicInteger checkCounter = new AtomicInteger(0);
    /*
    Блокировка подразумевает пессимистический подход, разрешая только одному потоку выполнять определенный код,
    связанный с изменением значения некоторой «общей» переменной.
    Таким образом, никакой другой поток не имеет доступа к определенным переменным.
    Но можно использовать и оптимистический подход.
    В этом случае блокировки не происходит, и если поток обнаруживает,
    что значение переменной изменилось другим потоком,
    то он повторяет операцию снова, но уже с новым значением переменной.
    Так работают атомарные классы.
     */

    protected Pair p = new Pair();

    private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());

    public synchronized Pair getPair() {
        // Создание копии для защиты оригинала
        return new Pair(p.getX(), p.getY());
    }

    // предполагается, что эта операция занимает много времени
    protected void store(Pair p) {
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException ignore) {
        }
    }

    // ключевое слово synchronized не является частью сигнатуры метода,
    // поэтому может быть добавлено в переопределенном методе
    public abstract void increment();
}

// Синхронизация целого метода
class PairManager1 extends PairManager {
    @Override
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
}

// Использование КРИТИЧЕСКОЙ СЕКЦИИ (быстрее НАМНОГО)
class PairManager2 extends PairManager {
    @Override
    public void increment() {
        Pair temp;
        synchronized (this) {
            p.incrementX();
            p.incrementY();
            temp = getPair();
        }
        store(temp);
    }
}

// ПОТОК ИНКРЕМЕНТАТОР САМОЙ ПАРЫ
class PairManipulator implements Runnable {
    private PairManager pm;

    public PairManipulator(PairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true) {
            pm.increment();
        }
    }

    public String toString() {
        return "Pair: " + pm.getPair() +
                " checkCounter = " + pm.checkCounter.get();
    }
}

// ПОТОК ИНКРЕМЕНТАТОР САМОГО СЧЕТЧИКА ПРОВЕРОК + САМ ПРОВЕРЯЛЬЩИК
class PairChecker implements Runnable {
    private PairManager pm;

    public PairChecker(PairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true) {
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}

public class CriticalSection {
    // Тестирование двух разных подходов
    static void testApproaches(PairManager pm1, PairManager pm2) {
        ExecutorService service = Executors.newCachedThreadPool();

        PairManipulator pma1 = new PairManipulator(pm1);
        PairManipulator pma2 = new PairManipulator(pm2);

        PairChecker pcheck1 = new PairChecker(pm1);
        PairChecker pcheck2 = new PairChecker(pm2);

        service.execute(pma1);
        service.execute(pma2);

        service.execute(pcheck1);
        service.execute(pcheck2);

        try {
            TimeUnit.MILLISECONDS.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }

        System.out.println("pm1: " + pma1 + "\npm2: " + pma2);
        System.exit(0);
    }

    public static void main(String[] args) {
        /*
        PairManager1 предоставляет PairChecker существенно меньше доступа,
        чем PairManager2, который содержит синхронизированный блок, а следовательно,
        предоставляет больше незаблокированного времени
        т.е. ПОВЫШАЕТСЯ ДОСТУПНОСТЬ КОДА ДЛЯ ДРУГИХ ЗАДАЧ
        (при условии что это безопасно - в нашем случае store.add - потокобезопасен)
         */
        PairManager pairManager1 = new PairManager1();
        PairManager pairManager2 = new PairManager2();
        testApproaches(pairManager1, pairManager2);
    }
}
