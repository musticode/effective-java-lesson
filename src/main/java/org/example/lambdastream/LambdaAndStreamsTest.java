package org.example.lambdastream;




import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.math.BigInteger.*;
import static java.util.stream.Collectors.toList;

public class LambdaAndStreamsTest {
    public static void main(String[] args) {

        List<String> words = new ArrayList<>();
        words.add("Test");
        words.add("Test2");
        words.add("Test3");
        words.add("Test4");

        Collections.sort(words, new Comparator<String>() {
            public int compare(String s1, String s2){
              return Integer.compare(s1.length(), s2.length());
            }
        });

        Collections.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));


        Map<String, Integer> integerMap = new HashMap<>();
        // gereksiz yer kaplayan metot
        integerMap.merge("key", 1, (count, incr) -> count + incr);

        integerMap.merge("key", 1, Integer::sum);


        primes()
                .map(p -> TEN.pow(p.intValueExact()).subtract(ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .forEach(System.out::println);


        // ----------
//        Map<String, Long> freq = new HashMap<>();
//        try (Stream<String> word = new Scanner(new File("test")).tokens()){
//            word.forEach(w -> freq.merge(w.toLowerCase(), 1L, Long::sum));
//
//        }
        Map<String, Long> freq = new HashMap<>();
        List<String> topTen = freq.keySet().stream()
                .sorted(Comparator.comparing(freq::get).reversed())
                .limit(10)
                .collect(toList());






    }
    // Stream'i iterable'a döndüren metot
    public static <E> Iterable<E> iterableOf(Stream<E> stream){
        return stream::iterator;
    }

    // Iterable<E> yi Stream<E>ye döndüren metot
    public static <E> Stream<E> streamOf(Iterable<E> iterable){
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    // Stream tabanlı yaklaşım
    private static List<Card> newDeckStream() {
        return Stream.of(Suit.values())
                .flatMap(suit ->
                        Stream.of(Rank.values())
                                .map(rank -> new Card(suit, rank)))
                .collect(toList());
    }


    // yinelemeli (iterative) yaklaşım
    private static List<Card> newDeck() {
        List<Card> result = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                result.add(new Card(suit, rank));
            }
        }
        return result;
    }

    static Stream<BigInteger> primes(){
        return Stream.iterate(TEN, BigInteger::nextProbablePrime);
    }

    // verilen listenin bütün alt listelerini stream olarak döndürür
    public static <E> Stream<List<E>> of(List<E> list) {
        return IntStream.range(0, list.size())
                .mapToObj(start ->
                        IntStream.rangeClosed(start + 1, list.size())
                                .mapToObj(end -> list.subList(start, end))
                ).flatMap(x -> x);
    }

    // paralele stream'lerin faydalı oldğu bir hesaplama
    static long primeCount(long n){
        return LongStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

}

class SubLists{
    // verilen listenin bütün alt listelerini stream olarak döndürür
    public static <E> Stream<List<E>> of(List<E> list) {
        return Stream.concat(Stream.of( Collections.emptyList()),
                prefixes(list).flatMap(SubLists::suffixes));
    }

    public static <E> Stream<List<E>> prefixes(List<E> list){
        return IntStream.rangeClosed(1, list.size()).mapToObj(end -> list.subList(0, end));
    }

    public static <E> Stream<List<E>> suffixes(List<E> list){
        return IntStream.range(0, list.size()).mapToObj(start -> list.subList(start, list.size()));
    }
}

class PowerSet{
    public static final <E> Collection<Set<E>> of(Set<E> s){
        List<E> src = new ArrayList<>(s);
        if (src.size() > 30){
            throw new IllegalArgumentException("Set too big " + s);
        }

        return new AbstractList<Set<E>>() {

            @Override
            public int size() {
                return 1 << src.size();
            }

            @Override
            public boolean contains(Object o) {
                return o instanceof Set && src.containsAll((Set) o);
            }

            @Override
            public Set<E> get(int index) {
                Set<E> result = new HashSet<>();
                for (int i = 0; index != 0; i++, index >>=1 ){
                    if ((index & 1) == 1){
                        result.add(src.get(i));
                    }
                }
                return result;
            }
        };
    }
}

class Card{
    private final Suit suit;
    private final Rank rank;


    Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
}

class Rank{
    private final String name;

    Rank(String name) {
        this.name = name;
    }

    public static Rank[] values() {
        return new Rank[0];
    }
}

class Suit{
    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public static Suit[] values() {
        return new Suit[0];
    }
}

