package com.saray.project.multythreading;

public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }
}

class Operations {
    public static void main(String[] args) throws Exception {
        Account a = new Account(1000);
        Account b = new Account(2000);

        new Thread(() -> {
            try {
                transfer(a, b, 500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        transfer(b, a, 300);
    }

    static void transfer(Account a1, Account a2, int amount) throws Exception {
        if (a1.getBalance() < amount) {
            System.out.println("failed");
            throw new Exception();
        }

        synchronized (a1) {
            Thread.sleep(1000);
            synchronized (a2) {
                a1.withdraw(amount);
                a2.deposit(amount);
            }
        }

        System.out.println("succeeded");
    }
}