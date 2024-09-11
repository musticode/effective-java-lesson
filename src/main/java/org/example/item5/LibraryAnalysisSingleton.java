package org.example.item5;

public class LibraryAnalysisSingleton {
    private static final Library turkish = new Library("Turkish");
    public static LibraryAnalysisSingleton INSTANCE = new LibraryAnalysisSingleton();

    private LibraryAnalysisSingleton() {
    }

    public static boolean isValid() {
        System.out.println("Is Valid for TR");
        return true;
    }

}
