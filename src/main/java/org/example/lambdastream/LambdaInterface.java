package org.example.lambdastream;
@FunctionalInterface
public interface LambdaInterface {

    /**
     * In java 8, All lambda types are an interface and the lambda expression itself
     * that is the piece of code, needs to be an implementation of this interface.
     * This is one of the keys to understanding lambda in my opinion.
     * In short, the lambda expression itself is the implementation of an interface. It might still be a bit confusing
     *
     * */
    void doSomething(String s);


}
