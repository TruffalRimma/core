package com.saray.project.chapter7;

// класс SpaceShipControls имитирует модуль управления космическим кораблем:
class SpaceShipControls {
    void up(int velocity) {
    }

    void down(int velocity) {
    }

    void left(int velocity) {
    }

    void right(int velocity) {
    }

    void forward(int velocity) {
    }

    void back(int velocity) {
    }

    void turboBoost() {
    }
}

// Для построения космического корабля можно воспользоваться наследованием:
class SpaceShip extends SpaceShipControls {
    private String name;

    public SpaceShip(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        SpaceShip protector = new SpaceShip("NSEA Protector");
        protector.forward(100);
    }
}

/*
Однако космический корабль не может рассматриваться как частный случай своего
управляющего модуля — несмотря на то, что ему, к примеру, можно приказать двигаться вперед (forward()).
Точнее сказать, что SpaceShip содержит SpaceShipControls,
и в то же время все методы последнего предоставляются классом SpaceShip.
Проблема решается при помощи делегирования:
 */
public class SpaceShipDelegation {
    private String name;
    private SpaceShipControls controls =
            new SpaceShipControls();

    public SpaceShipDelegation(String name) {
        this.name = name;
    }

    // Делегированные методы:
    public void back(int velocity) {
        controls.back(velocity);
    }

    public void down(int velocity) {
        controls.down(velocity);
    }

    public void forward(int velocity) {
        controls.forward(velocity);
    }

    public void left(int velocity) {
        controls.left(velocity);
    }

    public void right(int velocity) {
        controls.right(velocity);
    }

    public void turboBoost() {
        controls.turboBoost();
    }

    public void up(int velocity) {
        controls.up(velocity);
    }

    /*
    вызовы методов переадресуются встроенному объекту controls, а интерфейс остается таким же,
    как и при наследовании. С другой стороны, делегирование позволяет лучше управлять происходящим,
    потому что вы можете ограничиться небольшим подмножеством методов встроенного объекта
     */
    public static void main(String[] args) {
        SpaceShipDelegation protector =
                new SpaceShipDelegation("NSEA Protector");
        protector.forward(100);
    }
}

