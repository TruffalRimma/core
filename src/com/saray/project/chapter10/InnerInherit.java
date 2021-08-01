package com.saray.project.chapter10;

class Outer00 {

    private String outstr = "Outer";
    private String str = "strOuter00";

    class Inner01 extends Outer00 {

        private String strInn01 = "strInn01";
        private String str = "strInner01";

        String getOutStr() { return outstr; }

        void printStr() {
            System.out.println("Inner01>" + str);
            System.out.println("Inner01>" + Inner01.super.str);
        }

        class Inner02 extends Outer00.Inner01 {
            // конструктор
//            public Inner02(Outer00.Inner01 o) {
//                o.super();
//            }

            private String str = "strInner02";

            String getOutStr() { return outstr; }
            String getStrInn01() { return strInn01; }

            void printStr() {
                System.out.println("Inner02>" + str);
                System.out.println("Inner02>" + Inner02.super.str);
                System.out.println("Inner02>" + Inner01.super.str);
            }
        }
    }

    void printStr() {
        System.out.println(str);
    }
}

public class InnerInherit {
    public static void main(String[] args) {
        Outer00 outer = new Outer00();
        Outer00.Inner01 inner01 = outer.new Inner01();
//        Outer00.Inner01.Inner02 inner02 = inner01.new Inner02(inner01);
        Outer00.Inner01.Inner02 inner021 = new Outer00().new Inner01().new Inner02();
        System.out.println(inner01.getOutStr());
//        System.out.println(inner02.getOutStr());
//        System.out.println(inner02.getStrInn01());
        inner01.printStr();
//        inner02.printStr();
        System.out.println("-------");
        inner021.printStr();
    }
}
