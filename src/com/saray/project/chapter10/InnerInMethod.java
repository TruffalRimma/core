package com.saray.project.chapter10;

/*
ЛОКАЛЬНЫЙ ВНУТРЕННИЙ КЛАСС / Method-local inner class
создание всего класса в области действия метода (вместо области действия другого класса)
 */

interface Destination {
    String readLabel();
}

interface Contents {
    int value();
}

public class InnerInMethod {
    public Destination destination(String s) {

        // класс PDestination является частью destination(String s), а не InnerInMethod
        // следовательно к этому классу нельзя обратится за пределелами метода
        class PDestination implements Destination {
            private String label;

            private PDestination(String whereTo) {
                label = whereTo;
            }

            @Override
            public String readLabel() {
                return label;
            }
        }

        return new PDestination(s);
    }

    /*  идентификатор PDestination можно использовать для внутреннего класса
        внутри всех классов еще раз, назодящихся в одном подкаталоге и это не
        вызовет конфликта имён */
//!    class PDestination {}

    public static void main(String[] args) {
        InnerInMethod inMethod = new InnerInMethod();
        Destination destination = inMethod.destination("Yakutsk");
    }
}

/*
ВНУТРЕННИЙ КЛАСС В ПРОИЗВОЛЬНОЙ ОБЛАСТИ ДЕЙСТВИЯ / один из видов Method-local inner class
 */

class InnerInScope {
    private void internalTracking(boolean b) {
        if (b) {

            // класс TrackingSlip вложен в область действия команды if
            class TrackingSlip {
                private String id;

                TrackingSlip(String s) {
                    id = s;
                }

                String getSlip() {
                    return id;
                }
            }

            TrackingSlip trackingSlip = new TrackingSlip("slip");
            String s = trackingSlip.getSlip();
        }
        // Здесь использовать нельзя - вне области действия
//!        TrackingSlip trackingSlip = new TrackingSlip("WRONG");
    }

    public void track() {
        internalTracking(true);
    }

    public static void main(String[] args) {
        InnerInScope inScope = new InnerInScope();
        inScope.track();
    }
}

/*
АНОНИМНЫЕ ВНУТРЕННИЕ КЛАССЫ
 */

// возвращение экземпляра анонимного внутреннего класса, созданного на основе интерфейса
// с конструктором по умолчанию
class AnonymousReturn {

    // метод объединяет создание возвращаемого значения
    // с определением класса, представляющего само это значение
    public Contents contents() {

        // ссылка, возвращаемая выражением, автоматически преобразуется в ссылку на Contents
        return new Contents() {
            // вставка определения класса
            // класс анонимен - нет имени
            private int i = 11;

            public int value() {
                return i;
            }

            // + Contents создается конструктором по умолчанию
        }; // точка с запятой необходима - отмечает конец выражения, содержащего анонимный класс

    }

    /*

    РАСШИРЕННАЯ ВЕРСИЯ АНОНИМНОГО КЛАССА ВЫШЕ

    class MyContents implements Contents {
        private int i = 11;
        public int value() { return i; }
        public Contents contents() { return new MyContents(); }

        */

    public static void main(String[] args) {
        AnonymousReturn anonymousReturn = new AnonymousReturn();
        Contents contents = anonymousReturn.contents();
    }
}

/*
АНОНИМНЫЙ КЛАСС, РАСШИРЯЮЩИЙ КЛАСС С КОНСТРУКТОРОМ, КОТОРЫЙ НЕ ЯВЛЯЕТСЯ КОНСТРУКТОРОМ ПО УМОЛЧАНИЮ
 */

// анонимный класс на основе обычного класса (не интерфейса)
class Wrapping {
    private int i;

    public Wrapping(int i) { this.i = i; }

    public int value() { return i; }
}

class AnonymousNoArgConstructor {
    // аргумент для передачи конструктору базового класса
    public Wrapping wrapping(int x) {
        // Вызов конструктора базового класса
        return new Wrapping(x) { // передача аргумента конструктору
            // + переопределение одного из методов
            public int value() {
                return super.value() * 47;
            }
        };
    }

    public static void main(String[] args) {
        AnonymousNoArgConstructor constructor = new AnonymousNoArgConstructor();
        Wrapping wrapping = constructor.wrapping(10);
    }
}

/*
АНОНИМНЫЙ ВНУТРЕННИЙ КЛАСС, ВЫПОЛНЯЮЩИЙ ИНИЦИАЛИЗАЦИЮ ПОЛЯ
 */

class AnonymousField {

    // Для использования в анонимном внутреннем классе
    // аргумент должен быть объявлен как final - до Java 8
    /*
    В Java 7 локальный класс может получить доступ к локальной переменной или параметру метода,
    только если они объявлены в методе как final. В Java 8 поведение локальных классов было изменено.
    В этой версии языка локальный класс имеет доступ не только к final локальным переменным и параметрам,
    но и к effective-final - так называют переменную, значение которой не менялось после инициализации.
     */
    public Destination destination(final String dest) {
        return new Destination() {
          private String label = dest;

            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        AnonymousField field = new AnonymousField();
        Destination destination = field.destination("Moscow");
    }
}

/*
ИМИТАЦИЯ СОЗДАНИЯ КОНСТРУКТОРА ДЛЯ АНОНИМНОГО КЛАССА с помощью {}
вообще анонимный класс не может содержать конструктор
 */

abstract class Base {
    public Base(int i) {
        System.out.println("Базовый конструктор, i = " + i);
    }

    public abstract void f();
}

class ConstructorForAnonymous {

    // i может не быть final, так как не используется напрямую в анонимном классе
    public static Base getBase(int i) {
        return new Base(i) {
            {
                System.out.println("В инициализаторе экземпляра");
            }

            @Override
            public void f() {
                System.out.println("В анонимном f()");
            }
        };
    }

    public static void main(String[] args) {
        Base base = getBase(47);
        base.f();
    }
}