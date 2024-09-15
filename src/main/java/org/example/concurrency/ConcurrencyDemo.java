package org.example.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ConcurrencyDemo {
    public static void main(String[] args) throws InterruptedException {

        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();

        // functional programming
        new Thread(() -> {
            System.out.println("Lambda expression implement runnable");
        }).start();


        // callable
        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            Integer result = futureTask.get();
            System.out.println(result);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


        // thread state
        Thread thread1 = new Thread(() -> {
        });
        System.out.println(thread1.getState());


        // future interface
//        Thread th = new Thread(()-> {});
//        System.out.println(th.getState());
//
//        th.start();
//        System.out.println(th.getState());
//
//        th.start();


        // block case
        BlockCase blockCase = new BlockCase();
        Thread A = new Thread(blockCase::businessProcessing, "A");
        Thread B = new Thread(blockCase::businessProcessing, "B");

        A.start();
        Thread.sleep(1000);
        B.start();

        System.out.println("Thread[" + A.getName() + "] state:" + A.getState());
        System.out.println("Thread[" + B.getName() + "] state:" + B.getState());




        // waiting case
        System.out.println("----WAITING CASE---- ");
        WaitingCase waitingCase = new WaitingCase();
        Thread waitA = new Thread(waitingCase::businessProcessing);
        Thread waitB = new Thread(waitingCase::repairComputer);

        waitA.start();
        Thread.sleep(500);
        waitB.start();

        System.out.println("Thread[" + waitA.getName() + "] state:" + waitA.getState());
        System.out.println("Thread[" + waitB.getName() + "] state:" + waitB.getState());

        //https://towardsdev.com/mastering-java-multithreading-3-java-thread-states-and-transition-methods-2de16b72d1bb
    }
}

class WaitingCase {
    synchronized void businessProcessing() {
        try {
            System.out.println("Thread[" + Thread.currentThread().getName() + "] expects to process business, but the computer is broken");
            // release the monitor - lock
            wait();
            // business processing
            System.out.println("Thread[" + Thread.currentThread().getName() + "] continues to process business");

            Thread.sleep(2000L);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void repairComputer() {
        System.out.println("Thread[" + Thread.currentThread().getName() + "] comes to repair the computer");
        try {
            // simulated repair
            Thread.sleep(1000L);

            System.out.println("Thread[" + Thread.currentThread().getName() + "] has completed the repair.");
            notify();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}


class BlockCase {
    synchronized void businessProcessing() {
        try {
            System.out.println("Thread [" + Thread.currentThread().getName() + "] performs business processing");
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() {

        try {
            int result = 0;
            for (int i = 0; i < 5; i++) {
                result += i;
            }

            return result;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}


class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello Runnable...!");
    }
}
