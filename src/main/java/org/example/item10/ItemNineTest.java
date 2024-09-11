package org.example.item10;

import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ItemNineTest {
    public static void main(String[] args) {
        String a = new String("polish");
        String b = "polish";

        System.out.println(a.equals(b));


        Point p = new Point(1, 2);
        ColorPoint cp = new ColorPoint(1,2, Color.GREEN);

        System.out.println(p.equals(cp)); // true dönüyor. yanlış override

        // integer type
        System.out.println('a' == 97); // true

        System.out.println('a' + 'b' == 195); // true



        SecuredLoan securedLoan = new SecuredLoan();
        securedLoan.test();

        System.out.println(securedLoan.equals(securedLoan));


        ColorPoint green = new ColorPoint(1,1, Color.GREEN);


        Map<ColorPoint, String> map = new HashMap<>();
        map.put(green, "firstElement");

        System.out.println(map.get(green)); // firstElement

        // 13.video




    }
}

interface Loan{
    void test();

}

class SecuredLoan implements Loan{

    @Override
    public void test() {
        System.out.println("Test "+ getClass().getName());
    }
}

class NoOpLoan implements Loan{

    @Override
    public void test() {
        System.out.println("Test "+ getClass().getName());
    }
}
final class TestLoan extends SecuredLoan{
    private float interest;

}





