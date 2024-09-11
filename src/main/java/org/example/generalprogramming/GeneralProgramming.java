package org.example.generalprogramming;

import java.text.SimpleDateFormat;
import java.util.Random;

public class GeneralProgramming {
    public static void main(String[] args) {
        ThreadLocal<SimpleDateFormat> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


        SimpleDateFormat dateFormat = threadLocal.get();
        System.out.println(dateFormat.toString());
    }


    static int random(int n){
        Random rnd = new Random();

        return Math.abs(rnd.nextInt() % n);
    }

    /*
    // Yanlış string birleştirme kullanımı - düşük performans
    public String statement() {
        String result = "";
        for (int i = 0; i < numItems(); i++)
            result += lineForItem(i);     // String birleştirme
        return result;
    }

     */

}
