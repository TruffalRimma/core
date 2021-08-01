package com.saray.project.chapter7;

class Art {
    Art() {
        System.out.println("KoHCTpyKTop Art");
    }
}

class Drawing extends Art {
    Drawing() {
        System.out.println("KoHCTpyKTop Drawing");
    }
}

public class Cartoon extends Drawing {
    public Cartoon() {
        System.out.println("KoHCTpyKTop Cartoon");
    }

    public static void main(String[] args) {
        // мультик содержит подобъекты родителей
        Cartoon x = new Cartoon();
    }
}
