package org.example;

import java.util.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.UnaryOperator;


class Favorites{
    private Map<Class<?>,Object> favorites = new HashMap<>();
    public <T> void putFavorite(Class<T> type, T instance){
        favorites.put(Objects.requireNonNull(type),instance);
    }
    public <T> T getFavorite(Class<T> type){
        return type.cast(favorites.get(type));
    }
}

class GenericStack<E>{
    private E [] elements;
    private int size;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public GenericStack(){
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e){
        elements[size++] = e;
    }

    public E pop(){
        if (size == 0){
            throw new EmptyStackException();
        }
        E result = (E) elements[--size];
        elements[size] = null;
        return result;
    }


    // Generic method
    public static <E> Set<E> unionSet(Set<E> s1, Set<E> s2){
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    // yeni method
    public void pushAll(Iterable<? extends E> src) {
        for (E e : src){
            push(e);
        }
    }

    public void popAll(Collection<? super E> dst){
        while (!dst.isEmpty()){
            dst.add(pop());
        }
    }



}

class ListChooser<T>{
    private final List<T> choiceList;

    ListChooser(List<T> choiceList) {
        this.choiceList = new ArrayList<>(choiceList);
    }

    public T choose(){
        Random rnd = ThreadLocalRandom.current();
        return choiceList.get(rnd.nextInt(choiceList.size()));
    }
}

class Chooser{
    private final Object[] choiceArray;

    Chooser(Object[] choiceArray) {
        this.choiceArray = choiceArray;
    }

    public Object choose(){
        Random rnd = ThreadLocalRandom.current();
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }

}

public class GenericsTest {


    // generic tür sınırlaması kullanarak collectin'daki en büyük değer bulunur
    public static <E extends Comparable<E>> E max(Collection<E> c){
        if (c.isEmpty()){
            throw  new IllegalArgumentException("Empty collection");
        }

        E result = null;
        for (E e : c){
            if (result == null || e.compareTo(result) > 0){
                result = Objects.requireNonNull(e);
            }
        }

        return result;

    }

    private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    public static  <T> UnaryOperator<T> identityFunction(){
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    public static void main(String[] args) {


        // usage of generic singleton factory method
        String [] strings = {"jute", "hemp", "nylon"};
        UnaryOperator<String> sameString = identityFunction();
        for (String s : strings){
            System.out.println(sameString.apply(s));
        }

        Number [] numbers = {1, 2.0, 3L};
        UnaryOperator<Number> sameNumber = identityFunction();
        for (Number n : numbers){
            System.out.println(sameNumber.apply(n));
        }



        GenericStack<String> stringGenericStack = new GenericStack<>();
        for (String arg : args){
            stringGenericStack.push("test");
            System.out.println(arg);
        }


        // addAll test

        GenericStack<Number> numberStack = new GenericStack<>();
        Iterable<Integer> integers = Arrays.asList(1,2,3,4);
        numberStack.pushAll(integers);



//        Set<String> gust = Set.of("Tom", "Harry");
        // favorites test
        Favorites f = new Favorites();
        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, Favorites.class);

        String favString = f.getFavorite(String.class);
        Integer favInteger = f.getFavorite(Integer.class);
        Class<?> favClass = f.getFavorite(Class.class);

        System.out.printf("%s %x %s%n", favString, favInteger, favClass.getName());

    }



    @SafeVarargs
    static <T> List<T> flatten(List<? extends T>... lists){
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists){
            result.addAll(list);
        }

        return result;
    }

//    static <T> List<T> pickTwo(T a, T b, T c){
//        switch (ThreadLocalRandom.current().nextInt(3)){
//            case 0 : return List.of(a, b);
//            case 1 : return List.of(a, b);
//            case 2 : return List.of(a, b);
//        }
//        throw new AssertionError();
//    }
}


