package com.comppractice.july.threadpractice;

class Main {
    public static void main(String... args) throws Exception {
        Object lock1 = new Object();
        Object lock2 = new Object();
        Object lock3 = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                for (int i = 0; i < 100; i += 3) {
                    try {
                        lock1.wait();
                        System.out.println(i);
                        synchronized (lock2) {
                            lock2.notify();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                for (int i = 1; i < 100; i += 3) {
                    try {
                        lock2.wait();
                        System.out.println(i);
                        synchronized (lock3) {
                            lock3.notify();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread t3 = new Thread(() -> {
            synchronized (lock3) {
                for (int i = 2; i < 100; i += 3) {
                    try {
                        lock3.wait();
                        System.out.println(i);
                        synchronized (lock1) {
                            lock1.notify();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

        synchronized (lock1) {
            lock1.notify();
        }

    }
}