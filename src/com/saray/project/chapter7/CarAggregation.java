package com.saray.project.chapter7;

class Engine {
    int power;

    public Engine(int p) {
        power = p;
    }
}

class Car {
    String model = "Porshe";
    Engine engine;

    public Car(Engine someEngine) {
        this.engine = someEngine;
    }
}

public class CarAggregation {
    public static void main(String[] args) {
        /*
        АГРЕГАЦИЯ – это когда экземпляр двигателя создается где-то в другом месте кода,
        и передается в конструктор автомобиля в качестве параметра.
         */
        Engine goodEngine = new Engine(360);
        Car porshe = new Car(goodEngine);
    }
}