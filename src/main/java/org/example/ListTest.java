package org.example;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ListTest {
    public static void main(String[] args) {


            Set<Integer> intergers = Set.of(1,3,4);
            Set<Double> doubles = Set.of(1.2, 3.5, 6.6);

        System.out.println(union(intergers, doubles));


    }
    public static <E> Set<E> union(Set<? extends E> set1, Set<? extends E> set2){
        Set<E> resultSet = new HashSet<>(set1);
        resultSet.addAll(set2);
        return resultSet;
    }




}
