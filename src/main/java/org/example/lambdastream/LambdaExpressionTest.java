package org.example.lambdastream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.lang.System.out;


// article : [https://medium.com/javarevisited/lambda-expression-in-java-439268b0ab97]
public class LambdaExpressionTest {
    public static void main(String[] args) {


        LambdaInterface codeBlock = (s)-> out.println(s);

        LambdaInterface lambdaInterface = new LambdaInterfaceImpl();


        List<Student> students = Arrays.asList(
                new Student("Bob", 18),
                new Student("Ted", 17),
                new Student("Zeka", 18));
 // Now you need to print out the names of all 18-year-old students in students.

        //matchAndExecute(students, stu -> stu.getAge() == 18, stu -> stu.getName().contains("B"));
        matchAndExecute(students, s-> s.getAge() == 18, s -> out.println(s));


        // simplified version ->
        out.println("simplified version ->");
        students.stream()
                .filter(s -> s.getAge() == 18)
                .map(Student::getName)
                .forEach(out::println);



    }

    public static void matchAndExecute(List<Student> students, Predicate<Student> predicate, Consumer<String> consumer){
        for (Student student : students){
            if (predicate.test(student)){
                consumer.accept(student.getName());
            }
        }

        out.println("Second way...!");
        // second and concise way
        students.forEach(s -> {
            if (predicate.test(s)) {
                consumer.accept(s.getName());
            }
        });
    }

    public static void matchAndExecute2(List<Student> students, AgeMatcher matcher, Executor executor) {
        for (Student student : students) {
            if (matcher.match(student)) {
                executor.execute(student);
            }
        }
    }

}

@FunctionalInterface
interface AgeMatcher{
    boolean match(Student student);
}

@FunctionalInterface
interface Executor{
    boolean execute(Student student);
}




class Student {
    private String name;
    private Integer age;

    Student(String name, Integer age){
        this.name = name;
        this.age = age;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }


}


class LambdaInterfaceImpl implements LambdaInterface{

    @Override
    public void doSomething(String s) {
        out.println(s);
    }
}