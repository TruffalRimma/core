package com.saray.project.chapter10;

/*
ВЛОЖЕННЫЕ СТАТИЧЕСКИЕ КЛАССЫ КАК ЧАСТЬ ИНТЕРФЕЙСА
 */

interface ClassInInterface {
    void howdy();

    class Test implements ClassInInterface {
        @Override
        public void howdy() {
            System.out.println("HI");
        }

        public static void main(String[] args) {
            new Test().howdy();
        }
    }
}

/*
МНОГОКРАТНО ВЛОЖЕННЫЙ КЛАСС
 */

class MultiNestingAccess {
    private void f() {}

    class A {
        private void g() {}

        public class B {
            void h() {
                g();
                f();
            }
        }
    }
}

public class StaticInner {
    public static void main(String[] args) {
        MultiNestingAccess multiNestingAccess = new MultiNestingAccess();
        MultiNestingAccess.A a = multiNestingAccess.new A();
        MultiNestingAccess.A.B b = a.new B();
        b.h();
    }
}
