package com.saray.project.chapter7;

class Villain {
    private String name;

    protected void set(String nm) {
        name = nm;
    }

    public Villain(String name) {
        this.name = name;
    }

    public String toString() {
        return "Я объект Villain и мое имя " + name;
    }
}
/*
Ключевое слово protected — дань прагматизму. Оно означает; «Член класса является
закрытым (private) для пользователя класса, но для всех, кто наследует от класса,
и для соседей по пакету он доступен». (B Java protected автоматически предоставляет
доступ в пределах пакета.)
Лучше всего, конечно, объявлять поля класса как private — всегда стоит оставить за
собой право изменять лежащую в основе реализацию. Управляемый доступ наследникам
класса предоставляется через методы protected;
 */
public class OrcProtected extends Villain {
    private int orcNumber;

    public OrcProtected(String name, int orcNumber) {
        super(name);
        this.orcNumber = orcNumber;
    }

    public void change(String name, int orcNumber) {
        set(name); // Доступно, так как объявлено protected
        this.orcNumber = orcNumber;
    }

    public String toString() {
        return "Orc " + orcNumber + " :" + super.toString();
    }

    public static void main(String[] args) {
        OrcProtected orc = new OrcProtected("Лимбургер", 12);
        System.out.println(orc);
        orc.change("6o6", 19);
        System.out.println(orc);
    }
}