package org.example.enumannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class EnumTest {
    public static final int APPLE_FUJI = 0;
    public static final int APPLE_PIPPIN = 1;
    public static final int APPLE_GRANNY_SMITH = 2;

    public static final int ORANGE_NAVEL = 0;
    public static final int ORANGE_TEMPLE = 1;
    public static final int ORANGE_BLOOD = 2;


    public static void main(String[] args) {

        System.out.println(Planet.EARTH.mass());

        System.out.println(Color.RED.ordinal());

        EnumSet<Ensemble> set = EnumSet.noneOf(Ensemble.class);
        set.add(Ensemble.DECTET);
        set.add(Ensemble.OCTET);
        set.add(Ensemble.DECTET);
        set.add(Ensemble.DECTET);

        set.forEach(System.out::println);

        List<Plant> garden = new ArrayList<>();
        Map<Plant.LifeCycle, Set<Plant>> plantsLifeCycle = new EnumMap<>(Plant.LifeCycle.class);
        for (Plant.LifeCycle lifeCycle : Plant.LifeCycle.values()) {
            plantsLifeCycle.put(lifeCycle, new HashSet<>());
        }

        for (Plant plant : garden) {
            plantsLifeCycle.get(plant.lifeCycle).add(plant);
        }

        System.out.println(plantsLifeCycle);


        System.out.println("BIGRAM");

        Set<Bigram> s = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                s.add(new Bigram(ch, ch));
            }
        }
        System.out.println(s.size());
    }

    private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
        for (Operation op : opEnumType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m2() {
        int i = 0;
        i = i / i;
    }

}


class Bigram {
    private final char first;
    private final char second;

    Bigram(char first, char second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Bigram)) {
            return false;
        }

        Bigram b = (Bigram) o;
        return b.first == first && b.second == second;

    }

    @Override
    public int hashCode() {
        return 31 * first + second;
    }
}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ExceptionTestContainer {
    ExceptionTest[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ExceptionTest {
    Class<? extends Throwable> value();
}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Test {

}

interface Operation {
    double apply(double x, double y);
}

enum ExtendedOperation implements Operation {
    EXP("^") {
        @Override
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        @Override
        public double apply(double x, double y) {
            return x % y;
        }
    };
    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "ExtendedOperation{" +
                "symbol='" + symbol + '\'' +
                '}';
    }
}


enum BasicOperation implements Operation {
    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },

    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    },

    ;

    private final String symbol;

    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}


enum Phase {
    SOLID, LIQUID, GAS, PLASMA;

    enum Transition {
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID),
        IONIZE(GAS, PLASMA), DEIONIZE(PLASMA, GAS);

        private final Phase from;
        private final Phase to;

        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        private static final Map<Phase, Map<Phase, Transition>> m =
                Stream.of(values()).collect(
                        groupingBy(t -> t.from, () ->
                                        new EnumMap<>(Phase.class),
                                toMap(t -> t.to, t -> t,
                                        (x, y) -> y, () -> new EnumMap<>(Phase.class))));

        public static Transition from(Phase from, Phase to) {
            return m.get(from).get(to);
        }

    }


// hatalı kod
//    enum Transition {
//        MELT, FREEZE, BOIL, CONDENSE, SUBLIME, DEPOSIT;
//        private static final Transition[][] TRANSITIONS = {
//                {null, MELT, SUBLIME},
//                {FREEZE, null, BOIL},
//                {DEPOSIT, CONDENSE, null}
//        };
//
//        public static Transition from(Phase from, Phase to) {
//            return TRANSITIONS[from.ordinal()][to.ordinal()];
//        }
//
//    }
}


class Plant {
    enum LifeCycle {ANNUAL, BIENNIAL, PERENNIAL}

    final String name;
    final LifeCycle lifeCycle;

    Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }
}


class Text {
    enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

    public void applySet(Set<Style> styles) {

    }
}

// orbital metodundan faydalanarak int değerleri türetme
// BUNU YAPMAYIN!!
enum Ensemble {
    SOLO, DUET, TRIO, QUARTET, QUINTET,
    SEXTET, SEPTET, OCTET, NONET, DECTET;

    public int numberOfMusicians() {
        return ordinal() + 1;
    }
}

enum Color {
    RED,
    BLACK,
    BLUE
}


enum Planet {
    MERCURY(3.302e+23, 2.439e6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6),
    MARS(6.419e+23, 3.393e6),
    JUPITER(1.899e+27, 7.149e7),
    SATURN(5.685e+26, 6.027e7),
    URANUS(8.683e+25, 2.556e7),
    NEPTUNE(1.024e+26, 2.477e7);


    private final double mass;
    private final double radius;
    private final double surfaceGravity;

    // yerçekimi sabiti
    private static final double G = 6.67300E-11;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass / (radius * radius);
    }

    public double mass() {
        return mass;
    }

    public double radius() {
        return radius;
    }

    public double surfaceGravity() {
        return surfaceGravity;
    }

    public double surfaceWeight(double mass) {
        return mass * surfaceGravity;  // F = ma
    }

}
