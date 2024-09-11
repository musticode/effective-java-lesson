package org.example;

import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;

public class ImmutableClassTest {

    // https://www.seckintozlu.com/1526-effective-java-madde-18-kalitim-yerine-komposizyonu-tercih-edin.html

}

class Complex{
    private final double re;
    private final double im;

    private Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static Complex valueOf(double real, double imaginary){
        return new Complex(real, imaginary);
    }

    public static Complex valueOfPolar(double real, double theta){
        return new Complex(real * Math.cos(theta), real * Math.sin(theta));
    }

}
