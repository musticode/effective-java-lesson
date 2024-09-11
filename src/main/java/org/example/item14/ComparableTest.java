package org.example.item14;

import java.util.ArrayList;
import java.util.Collections;

public class ComparableTest {
    public static void main(String[] args) {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        bookArrayList.add(new Book("Mustafa", 9));
        bookArrayList.add(new Book("Mustafa2", 7));
        bookArrayList.add(new Book("Mustafa3", 11));

        Collections.sort(bookArrayList);

        System.out.println("Printing SORTED 1");

        for (Book book : bookArrayList) {
            System.out.println("Book name " + book.getName() + " , page : " + book.getPage());
        }

        // 16.videoq
        System.out.println("Printing SORTED 2");
        bookArrayList.forEach(item -> {
            System.out.println(item.getName());
            System.out.println(item.getPage());
        });
    }



}

class Book implements Comparable<Book> {
    private String name;
    private int page;

    Book(String name, int page) {
        this.name = name;
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public String getName() {
        return name;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Book o) {
        return this.page - o.page;
    }
}
