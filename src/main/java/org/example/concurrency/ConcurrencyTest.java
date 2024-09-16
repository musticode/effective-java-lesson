package org.example.concurrency;



import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrencyTest {
    public static void main(String[] args) throws InterruptedException {



        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new TestRunnable());
        executorService.shutdown();






/*

        Thread backgroundThread = new Thread(()-> {
           int i = 0;
           while(!stopRequested)
               i++;
        });

        backgroundThread.start();
        Thread.sleep(1000L);
        requestStop();


 */
    }

    private static long time(Executor executor, int concurrency, Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(concurrency);

        for (int i = 0; i < concurrency; i++ ){
            executor.execute(()-> {
                ready.countDown();
                try {
                    start.await();
                    action.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                   done.countDown();
                }
            });
        }

        ready.await();
        long startNanos = System.nanoTime();
        start.countDown();
        done.await();
        return System.nanoTime() - startNanos;

        // java.util.concurrent.locks.Lock
    }

    private static String intern2(String s){
        final ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

        String result = concurrentHashMap.get(s);
        if (result == null){
            result = concurrentHashMap.putIfAbsent(s,s);
            if (result == null){
                result = s;
            }
        }

        return result;
    }

    private static String intern(String s){
        final ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        String previousVal = concurrentHashMap.putIfAbsent(s, s);
        return previousVal == null ? s : previousVal;
    }

    // Lock-free synchronization with java.util.concurrent.atomic
    private static final AtomicLong nextSerialNum = new AtomicLong();
    public static long generateSerialNumber() {
        return nextSerialNum.getAndIncrement();
    }


    /*
    // Broken - requires synchronization!
    private static volatile int nextSerialNumber = 0;

    public static int generateSerialNumber() {
        return nextSerialNumber++;
    }

     */


    private static boolean stopRequested;

    private static synchronized void requestStop(){
        stopRequested = true;
    }

    private static synchronized boolean stopRequested(){
        return stopRequested;
    }

}

class TestRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Run..!!" + this.getClass().getName());
    }
}

class ForwardingSet<E> implements Set<E> {
    private final Set<E> s;
    public ForwardingSet(Set<E> s) {
        this.s = s;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

class SetObservable<E>{

}

class Observable<E> extends ForwardingSet<E>{

    private final List<SetObservable<E>> observers = new ArrayList<>();


    public Observable(Set<E> s) {
        super(s);
    }
}
