package org.example.serialization;

import java.io.Serializable;
import java.util.Map;

/**
 * @serial Data test
 * @author musti
 * */

public class SerializationTest {
    public static void main(String[] args) {

        //Serializable
    }

}

final class StringList implements Serializable{

    private transient int size = 0;


}

enum Entry{

}
