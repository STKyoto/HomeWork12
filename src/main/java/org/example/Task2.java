package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Task2 {
    int n;
    AtomicInteger current = new AtomicInteger(1);
    BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public Task2(int n) {
        this.n = n;
    }

    public void fizz() {
        while (true) {
            int number = current.get();
            if (number > n) {
                break;
            }
            if ((number % 3 == 0) && (number % 5 != 0)) {
                    try {
                    queue.put("fizz");
                    current.incrementAndGet();
                    synchronized (this){
                        notifyAll();
                    }
                        } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                        }
                }
            }
        }

    public void buzz() {
            while (true) {
                int number = current.get();
                if (number > n) {
                    break;
                }
                if ((number % 3 != 0) && (number % 5 == 0)) {
                    try {
                        queue.put("buzz");
                        current.incrementAndGet();
                        synchronized (this){
                            notifyAll();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
    }

    public void fizzBuzz() {
            while (true) {
                int number = current.get();
                if (number > n) {
                    break;
                }
                if ((number % 3 == 0) && (number % 5 == 0)) {
                    try {
                        queue.put("fizzbuzz");
                        current.incrementAndGet();
                        synchronized (this){
                            notifyAll();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
    }

    public void number() {
            while (true) {
                int number = current.get();
                if (number > n) {
                    break;
                }
                if ((number % 3 != 0) && (number % 5 != 0)) {
                    try {
                        queue.put(String.valueOf(number));
                        current.incrementAndGet();
                        synchronized (this){
                            notifyAll();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
    }
    public void print(){
        while (true) {
            synchronized (this) {
                while (queue.isEmpty() && current.get() <= n) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (queue.isEmpty() && current.get() > n) {
                    break;
                }
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

