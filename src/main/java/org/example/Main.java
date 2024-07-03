package org.example;


public class Main {
    public static void main(String[] args) {
        Task2 task2 = new Task2(15);

        Thread threadA = new Thread(task2::fizz);
        Thread threadB = new Thread(task2::buzz);
        Thread threadC = new Thread(task2::fizzBuzz);
        Thread threadD = new Thread(() ->{
            task2.number();
            task2.print();
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();



    }
}