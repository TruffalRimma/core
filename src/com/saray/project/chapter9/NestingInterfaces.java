package com.saray.project.chapter9;

/*
ВЛОЖЕННЫЕ ИНТЕРФЕЙСЫ
 */

class A {

    // PACKAGE
    interface B {
        void f();
    }

    // имплементация вложенными классами вложенного интерфеса
    public class BImpl implements B {
        @Override
        public void f() {
            System.out.println("BImpl.f()");
        }
    }

    private class BImpl2 implements B {
        @Override
        public void f() {
            System.out.println("BImpl2.f()");
        }
    }

    // PUBLIC
    public interface C {
        void f();
    }

    class CImpl implements C {
        @Override
        public void f() {
            System.out.println("CImpl.f()");
        }
    }

    private class CImpl2 implements C {
        @Override
        public void f() {
            System.out.println("CImpl2.f()");
        }
    }

    // PRIVATE
    // восходящее преобразование запрещенно
    private interface D {
        void f();
    }

    private class DImpl implements D {
        @Override
        public void f() {
            System.out.println("DImpl.f()");
        }
    }

    // замкнут сам на себя
    public class DImpl2 implements D {
        @Override
        public void f() {
            System.out.println("DImpl2.f()");
        }
    }

    public D getD() {
        return new DImpl2();
    }

    private D dRef;

    public void receiveD(D d) {
        dRef = d;
        dRef.f();
    }
}

interface E {
    interface G {
        void f();
    }

    // Избыточное объявление public
    public interface H {
        void f();
    }

    void g();

    // Не может быть ПРАЙВАТ внутри интерфеса:
    //! private interface I {}

    // любой класс, помещенный в интерфейс автоматически объявляется как открытый и статический
    public static class MyClass {}
}

public class NestingInterfaces {
    public class BImpl implements A.B {
        @Override
        public void f() {
            System.out.println("ABImpl.f() in main class");
        }
    }

    class CImpl implements A.C {
        @Override
        public void f() {
            System.out.println("ACImpl.f() in main class");
        }
    }

    // НЕЛЬЗЯ РЕАЛИЗОВАТЬ PRIVATE-ИНТЕРФЕЙС, КРОМЕ КАК ВНУТРИ КЛАССА, ГДЕ ОН БЫЛ ОПРЕДЕЛЕН
/*    class DImpl implements A.D {
        @Override
        public void f() {
            System.out.println("WRONG");
        }
    } */

    class EImpl implements E {
        @Override
        public void g() {
            System.out.println("EImpl.g() in main class");
        }
    }

    class EGImpl implements E.G {
        @Override
        public void f() {
            System.out.println("EGImpl.f() in main class");
        }
    }

    public static void main(String[] args) {
        A a = new A();
        // нет доступа к D
//!        A.D ad = a.getD();

        // не возвращает ничего кроме A.D
//!        A.DImpl2 dImpl2 = a.getD();

        // нельзя получить доступ к члену интерфейса
//!        a.getD().f();

        // только другой класс A может использовать getD()
        A a2 = new A();
        a2.receiveD(a.getD());
    }

    {
        E.G eg = new EGImpl();
    }
}
