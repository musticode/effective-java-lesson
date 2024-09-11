package org.example.item7;

import java.util.ArrayList;

public class itemSevenTest {
    public static void main(String[] args) {
        GarbageCollection test = new GarbageCollection();
        GarbageCollection test2 = new GarbageCollection();


        test = null;
        test2 = null;

        System.gc();




    }



}
