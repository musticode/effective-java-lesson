package org.example.serialization;

import sun.tools.jstat.Jstat;

import java.io.*;
import java.time.Period;
import java.util.Date;
import java.util.EnumSet;
import java.util.Map;

/**
 * @serial Data test
 * @author musti
 * */

public class SerializationTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Serializable

        MutablePeriod mp = new MutablePeriod();
        Period p = mp.period;
        Date pEnd = mp.end;


        // Let's turn back the clock
        pEnd.setYear(78);
        System.out.println(p);
        // Bring back the 60s!
        pEnd.setYear(69);
        System.out.println(p);
    }

}
// EnumSet's serialization proxy
class SerializationProxy <E extends Enum<E>>
        implements Serializable {
    // The element type of this enum set.
    private final Class<E> elementType;
    // The elements contained in this enum set.
    private final Enum<?>[] elements;
    SerializationProxy(EnumSet<E> set) {
        this.elementType = set.elementType;
        this.elements = set.toArray(new Enum<?>[0]);
    }
    private Object readResolve() {
        EnumSet<E> result = EnumSet.noneOf(elementType);
        for (Enum<?> e : elements)
            result.add((E)e);
        return result;
    }
    private static final long serialVersionUID =
            362491234563181265L;
}

class SerializationProxy2 implements Serializable{
    private final Date start;
    private final Date end;

    SerializationProxy2(Date start, Date end) {
        this.start = start;
        this.end = end;
    }


    // writeReplace method for the serialization proxy pattern
    private Object writeReplace() {
        return new SerializationProxy(this);
    }
}



class MutablePeriod{
    public final Period period;
    public final Date start;
    public final Date end;

    MutablePeriod() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos =
                new ByteArrayOutputStream();
        ObjectOutputStream out =
                new ObjectOutputStream(bos);
// Serialize a valid Period instance out.writeObject(new Period(new Date(), new Date()));
        /*
         * Append rogue "previous object refs" for internal * Date fields in Period. For details, see "Java
         * Object Serialization Specification," Section 6.4. */
        byte[] ref = { 0x71, 0, 0x7e, 0, 5 }; // Ref #5
        bos.write(ref); // The start field
        ref[4] = 4;     // Ref # 4
        bos.write(ref); // The end field
        // Deserialize Period and "stolen" Date references
        ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(bos.toByteArray()));
        this.period = (Period) in.readObject();
        this.start  = (Date)   in.readObject();
        this.end    = (Date)   in.readObject();
    }


//    private void readObject(ObjectInputStream s)
//            throws IOException, ClassNotFoundException {
//        s.defaultReadObject();
//        // Defensively copy our mutable components
//        start = new Date(start.getTime());
//        end   = new Date(end.getTime());
//        // Check that our invariants are satisfied
//        if (start.compareTo(end) > 0)
//            throw new InvalidObjectException(start +" after "+ end);
//    }


}







final class StringList implements Serializable{

    private transient int size = 0;


}

enum Entry{

}
