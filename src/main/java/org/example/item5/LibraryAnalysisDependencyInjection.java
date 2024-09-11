package org.example.item5;

public class LibraryAnalysisDependencyInjection {
    private final Library library;

    public LibraryAnalysisDependencyInjection(Library library) {
        this.library = library;
    }

    public boolean isValid() {
        System.out.println("Is Valid for TR");
        return true;
    }


}
