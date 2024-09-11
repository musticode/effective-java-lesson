package org.example.item10;

public class Point {

    /**
     * immutable two-dimensional integer point class
     * */
    private final int x;
    private final int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o){
        if (!(o instanceof Point))
            return false;

        Point p = (Point) o;
        return p.x == x && p.y == y;
    }


}
