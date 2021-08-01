package com.saray.project.chapter8;

// Переключаемся на другую ветку наследования

class Actor {
    public void act() {}
}

class HappyActor extends Actor {
    @Override
    public void act() {
        System.out.println("HappyActor");
    }
}

class SadActor extends Actor {
    @Override
    public void act() {
        System.out.println("SadActor");
    }
}

class Stage {
    private Actor actor = new HappyActor();

    public void change() {
        actor = new SadActor();
    }

    public void perform() {
        actor.act();
    }
}

public class ChangeHierarchyTree {
    public static void main(String[] args) {
        Stage stage = new Stage();
        stage.perform();
        stage.change();
        stage.perform();
    }
}
