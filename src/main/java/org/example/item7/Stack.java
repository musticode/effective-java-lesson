package org.example.item7;

import java.util.Arrays;
import java.util.EmptyStackException;


public class Stack {
    public Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public void finalize(){
        System.out.println("Çöp toplandı");
    }

    public Stack(){
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e){
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop(){
        if (size == 0){
            throw new EmptyStackException();
        }
        // eğer çıkarabiliyosam sonuncu eleman harici veriyor
        // referans eski kalıyor. en üstteki eleman boş
        return elements[--size];
    }

    public Object popWell(){
        if (size == 0){
            throw new EmptyStackException();
        }

        Object result = elements[--size];
        elements[size] = null; // eski objeyi null'a set ettim

        return result;
    }


    // eğer yer yoksa, eski array'i kopyala ve yeni size ile oluştur
    private void ensureCapacity(){
        if (elements.length == size){
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }


}
