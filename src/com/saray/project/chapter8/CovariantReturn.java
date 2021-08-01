package com.saray.project.chapter8;
/*
КОВАРИАНТНОСТЬ ВОЗВРАЩАЕМЫХ ТИПОВ
переопределенный метод производного класса может вернуть тип,
производный от типа, возвращаемого методом базового класса
 */

class Grain {
    public String toString() {return "Grain";}
}

class Wheat extends Grain {
    @Override
    public String toString() {
        return "Wheat";
    }
}

class Mill {
    Grain process() {
        return new Grain();
    }
}

class WheatMill extends Mill {
    Wheat process() {
        return new Wheat();
    }
}

public class CovariantReturn {
    public static void main(String[] args) {
        Mill mill = new Mill();
        Grain grain = mill.process();
        System.out.println(grain);

        mill = new WheatMill();
        grain = mill.process();
        System.out.println(grain);
    }
}
