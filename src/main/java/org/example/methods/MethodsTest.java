package org.example.methods;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MethodsTest {
    public static void main(String[] args) {

        // date test
        Date start = new Date();
        Date end = new Date();
        Period p = new Period(start, end);
        end.setYear(6112313);


        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };

        for (Collection<?> c : collections) {
            System.out.println(CollectionClassifier.classify(c));
        }

        new Thread(System.out::println).start();

        ExecutorService executorService = Executors.newCachedThreadPool();
        //executorService.submit(System.out::println);





    }

    public static <E extends Comparable<E>> Optional<E> maxOptionalStream(Collection<E> c){
        return c.stream().max(Comparator.naturalOrder());
    }

    public static <E extends Comparable<E>> Optional<E> maxOptional(Collection<E> c){
        if (c.isEmpty()){
            return Optional.empty();
        }

        E result = null;
        for (E e : c){
            if (result == null || e.compareTo(result) > 0){
                result = Objects.requireNonNull(e);
            }
        }
        return Optional.of(result);
    }

    // koleksiyondaki maximum değeri bulur
    // eğer eleman yoksa exception fırlatır
    public static <E extends Comparable<E>> E max(Collection<E> c){
        if (c.isEmpty()){
            throw new IllegalArgumentException("Empty collection ");
        }

        E result = null;
        for (E e : c){
            if (result == null || e.compareTo(result) > 0){
                result = Objects.requireNonNull(e);
            }
        }
        return result;
    }

    public List<String> getCheeses(){
        List<String> cheesesInStock = new ArrayList<>();
        return cheesesInStock.isEmpty() ? Collections.emptyList() : new ArrayList<>(cheesesInStock);
    }

    public String[] getCheeseArr(){
        List<String> cheesesInStock = new ArrayList<>();
        return cheesesInStock.toArray(new String[0]);
    }



    static int min(int firstArg, int... remainingArgs){
        int min = firstArg;
        for (int arg : remainingArgs){
            if (arg < min){
                min = arg;
            }
        }

        return min;
    }

    static int sum(int... args){
        int sum = 0;
        for (int arg : args){
            sum += arg;
        }
        return sum;
    }


    /**
     * @param m (modulus) pozitif olmalı
     * @return this mod m
     * @throws ArithmeticException m sıfıra eşitse ya da küçükse
     */
    public BigInteger mod(BigInteger m) {

        Objects.requireNonNull(m, "m");

        if (m.signum() <= 0) {
            throw new ArithmeticException("Modulus <= 0" + m);
        }

        // hesaplamalar...
        return BigInteger.ONE;
    }


}

class Period {
    private final Date start;
    private final Date end;

    Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        if (this.start.compareTo(this.end) > 0) {
            throw new IllegalArgumentException(this.start + " after " + this.end);
        }
    }

    // problemli
//    Period(Date start, Date end) {
//        if (start.compareTo(end) > 0){
//            throw new IllegalArgumentException(start + " after "+ end);
//        }
//        this.start = start;
//        this.end = end;
//    }

    // Düzeltilmiş erişim metotları - koruyucu kopya döndürüyoruz
    public Date start() {
        return new Date(start.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }


}

// Bozuk! Bu programın ne sonuç üreteceğini biliyor musunuz?
class CollectionClassifier {
    public static String classify(Set<?> s) {
        return "Set";
    }

    public static String classify(List<?> lst) {
        return "List";
    }

    public static String classify(Collection<?> c) {
        return c instanceof Set  ? "Set" :
                c instanceof List ? "List" : "Unknown Collection";
    }
}

