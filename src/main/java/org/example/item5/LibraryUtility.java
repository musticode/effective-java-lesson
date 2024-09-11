package org.example.item5;

/**
 * utility class
 * */
public class LibraryUtility {

    private static final Library turkish = new Library("Turkish");


    /**
     * utility class -> shouldn't be initialized
     * */
    private LibraryUtility(){

    }

    public static boolean isValid(){
        System.out.println("Is Valid for TR");
        return true;
    }


    public static String testLongMethodName(){
        return "Hello";
    }

}
