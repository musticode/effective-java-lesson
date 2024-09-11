package org.example.item5;

import java.util.function.Supplier;

public class ItemFiveTest {
    public static void main(String[] args) {

        LibraryUtility.isValid();

        LibraryAnalysisSingleton.INSTANCE.isValid();

        /**
         * This architecture is flexible to use
         * */
        LibraryAnalysisDependencyInjection test = new LibraryAnalysisDependencyInjection(new Library("Turkish"));
        test.isValid();

        /*
        Supplier<Library> turkishLibrary = () -> new Library("Turkish");
        LibraryAnalysisDependencyInjection testWithSupplier = new LibraryAnalysisDependencyInjection(turkishLibrary);
        test.isValid();
        // library class should be updated
         */

        // todo LAMBDA FUNCTIONS
        Supplier<String> fs = () -> "Mustafa";
        fs.get();


        LibraryUtility.testLongMethodName();
        // if method name is too large, we can use supplier methods

        Supplier<String> t = () -> LibraryUtility.testLongMethodName();
        t.get();


        double randomNumber = Math.random();
        System.out.println("Random number : " + randomNumber);

        Supplier<Double> randomValue = () -> Math.random();
        System.out.println("Random value for supplier " + randomValue.get());
    }
}
