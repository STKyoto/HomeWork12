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
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                current.incrementAndGet();
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
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                current.incrementAndGet();
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
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                current.incrementAndGet();
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
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                current.incrementAndGet();
            }
        }
        while (!queue.isEmpty()) {
            try {
                Thread.sleep(500);
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

