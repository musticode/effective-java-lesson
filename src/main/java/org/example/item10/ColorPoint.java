package org.example.item10;

import java.awt.*;
import java.util.Objects;

public class ColorPoint extends Point {

    int x,y;
    private final Color color;
    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }



    // Broken -- violates symmetry!
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof  ColorPoint))
            return false;

        return super.equals(o) && ((ColorPoint) o).color == color;
    }


    // iyi yöntem -> belirli bir sabit ile çarp ve result'a çevir
    @Override
    public int hashCode() {
        int result = Integer.hashCode(y);

        result = 31 * result + Integer.hashCode(x);
        return result;
    }

    /*
    // slow and problematic
    public int hashCode(){
        return Objects.hash(x,y);
    }

     */



}
