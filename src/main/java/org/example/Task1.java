package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    public static void main(String[] args) {
        AtomicInteger seconds = new AtomicInteger();
        AtomicInteger minutes = new AtomicInteger();
        AtomicInteger hours = new AtomicInteger();
        Thread thread1 = new Thread(() -> {
            try {
                seconds.getAndIncrement();
                while(true) {
                    System.out.println(hours + " hours, " + minutes + " minutes, " + seconds + " seconds.");
                    Thread.sleep(1000);
                    if(seconds.incrementAndGet() == 60){
                        minutes.incrementAndGet();
                        seconds.set(0);
                    }
                    if (minutes.get() == 60){
                        hours.incrementAndGet();
                        minutes.set(0);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(5000);
                    System.out.println("Минуло 5 секунд");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start();
        thread2.start();
    }


}
