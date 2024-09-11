package org.example;

import java.util.concurrent.CompletableFuture;


public class ConcurrencyTest {

    // https://medium.com/javarevisited/java-completablefuture-c47ca8c885af

    public static void main(String[] args) {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
           try {
               Thread.sleep(5000);
           }catch (InterruptedException e){
               e.printStackTrace();
           }

           return "Hello world";
        });

        future.thenAccept(res -> System.out.println(res));


        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(()-> 10)
                .thenApply(result -> result * 2)
                .thenApply(result -> result + 5);
        integerCompletableFuture.thenAccept( res -> System.out.println(res));


        // another example
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("So long running operation");
            return "test";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("So long running operation");
            return "test2";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("So long running operation");
            return "test3";
        });

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3);
        allFutures.thenRun(() -> {
            String result1 = future1.join();
            String result2 = future2.join();
            String result3 = future3.join();
            System.out.println(String.format("Result 1 : %s , Result 2 : %s, Result 3 : %s", result1, result2, result3));
        });


        // another example
        /**
         * Creating a CompletableFuture that will throw an ArithmeticException, because we're dividing by zero.
         * Then calling the exceptionally() method to specify what to do if there's an exception.
         * In this case, we're printing the exception message
         * */
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
           int result = 10 / 0;
           return result;
        });

        completableFuture.exceptionally( ex -> {
            System.out.println("Exception occured : " +  ex.getMessage());
            return 0;
        }).thenAccept( res -> {
            System.out.println("Result : " + res);
        });


        /**
         * Future : Blocking
         * CompletableFuture : Non blocking
         * */

        // handling errors in multiple CompletableFuture objects

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync( ()-> {
           return 10;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync( ()-> {
            int result = 10/0;
            return result;
        });

        CompletableFuture<Integer> f3 = CompletableFuture.supplyAsync( ()-> {
            return 20;
        });

        CompletableFuture<Void> allFeat = CompletableFuture.allOf(f1, f2, f3);

        allFeat.exceptionally( ex -> {
            System.out.println("Exception occured: " + ex.getMessage());
            return null;
        }).thenRun(()-> {
            int res1 = f1.join();
            int res2 = f2.join();
            int res3 = f3.join();

            System.out.println("Multiple exception : ");
            System.out.println(String.format("Result 1 : %s , Result 2 : %s, Result 3 : %s", res1, res2, res3));
        });


        // Async Methods of CompletableFuture
        CompletableFuture<String> fut4 = CompletableFuture.supplyAsync( () -> "Hello");

        CompletableFuture<Integer> transformedFuture = fut4.thenApplyAsync(s -> {
            System.out.println("Thread : " + Thread.currentThread().getName());
            return s.length();
        });

        transformedFuture.thenAccept(length -> {
            System.out.println("Thread : "+ Thread.currentThread().getName());
            System.out.println("Length of hello : " + length);
        });

        CompletableFuture<Void> future4 = CompletableFuture.runAsync(()-> {
            System.out.println("Thread : " + Thread.currentThread().getName());
            System.out.println("Hello from async task");
        });


        /**
         * thenComposeAsync() is a method in CompletableFuture that allows you to chain multiple async tasks together in
         * a non-blocking way
         * This method is used when you have one CompletableFuture object returns another CompletableFuture object as its result, and you want to execute
         * the second task after the first one has completed
         * */
        // Here is an example of using thenComposeAsync():

        CompletableFuture<String> sFut1 = CompletableFuture.supplyAsync(()-> "Hello");
        CompletableFuture<String> sFut2 = sFut1.thenComposeAsync(s -> CompletableFuture.supplyAsync(()-> s + " world"));
        System.out.println("SFUT2 : " + sFut2); // SFUT2 : java.util.concurrent.CompletableFuture@7229724f[Not completed]
        sFut2.thenAccept( result -> System.out.println(result));





    }
}
