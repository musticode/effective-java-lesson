package org.example;

import java.util.*;

public class CompositionTest {
    public static void main(String[] args) {
        // test
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        boolean aBoolean = s.addAll(Arrays.asList("test", "test2", "test3"));

        System.out.println(aBoolean);
        System.out.println(s.getAddCount());
        /**
         * true
         * 6
         * Bu kısımda normalde 3 tane beklenirken 6 döndü addCount
         *
         * */
    }

}

/**
 * Sonuç olarak ortaya çıkan sınıf sapasağlam ve içerdiği sınıfın gerçekleştirim detaylarından tamamen bağımsız olacaktır.
 * Referans olarak eklenen sınıfa yeni metotlar eklense bile sizi etkilemeyecektir.
 * */
class ForwardingSet1<E> implements Set<E> {



    private final Set<E> s;
    public ForwardingSet1(Set<E> s) {
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
}



class InstrumentedHashSet<E> extends HashSet<E>{
    private int addCount = 0;
    InstrumentedHashSet(){

    }

    public InstrumentedHashSet(int initCap, int loadFactor){
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }

}
