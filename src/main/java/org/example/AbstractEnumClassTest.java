package org.example;

public class AbstractEnumClassTest {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++ ){
            System.out.println(String.format("i : %s", i));
            if (i == 5){
                break;
            }

            if (i == 3){
                System.out.println("3 oldu");
            }

            if (i== 6){
                System.out.println("6 oldu");
            }

        }
        System.out.println("A");
        int a = 0;
        while(a < 10){
            System.out.println(a);
            a++;
        }


    }
}

// özel dikdörtgen class'ı
class Square extends Rectangle{
    Square(double side) {
        super(side, side);
    }
}


// işaretli sınıfın tür hiyerarşisi ile yazılışı
abstract class Figure{
    abstract double area();
}

class Circle extends Figure{
    final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * (radius * radius);
    }
}

class Rectangle extends Figure{

    final double length;
    final double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length * width;
    }

}




/**
 * problemli!
 * Bunun yerine çok daha iyi bir alternatif var, tür hiyerarşisi kurmak.
 * İşaretli sınfılar aslında sınıfı hiyerarşilerinin başarısız bir taklididir.
 *
 *
 * */
class Figure1 {
    enum Shape {RECTANGLE, CIRCLE}
    final Shape shape;
    double length;
    double width;
    double radius;

    Figure1(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    double area(){
        switch (shape){
            case RECTANGLE:
                return length * width;
            case CIRCLE:
                return Math.PI *(radius*radius);
            default:
                throw new AssertionError(shape);
        }
    }

}

