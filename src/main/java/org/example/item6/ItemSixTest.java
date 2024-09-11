package org.example.item6;

import java.util.ArrayList;
import java.util.List;

public class ItemSixTest {
    public static void main(String[] args) {

        String word = new String("bikini");
        String word2 = "bikini";
        String word3 = "bikini";

        System.out.println(word == word3); // false
        System.out.println(word2 == word3); // true --> same reference


        long start = System.nanoTime();
        summation();

        long end = System.nanoTime();
        long totalTime = end - start;
        System.out.println("Total time " + totalTime);

        // Total time 253167 when Long(wrapper class is used)

        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers.add(Integer.valueOf(i));
        }

        List<Integer> li = new ArrayList<>();
        int sum2 = 0;
        for (Integer i : li){
            if (i % 2 == 0){ // i.intValue
                System.out.println("2 ile bölümden kalan 0 mı?");
            }
        }


    }

    private static long summation(){
        Long sum = 0L;
        for (int i = 0; i < 150; i++ ){
            sum += i;
        }
        return sum;
    }

}
