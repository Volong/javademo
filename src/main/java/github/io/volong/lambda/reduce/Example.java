package github.io.volong.lambda.reduce;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @since 2019-07-19 16:59
 */
public class Example {

    /**
     * Optional<T> reduce(BinaryOperator<T> accumulator);
     *
     * 求和
     */
    @Test
    public void reduceFirstSign() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        Integer integer = list.stream().reduce((a, b) -> a + b).get();
        System.out.println(integer); // 21
    }

    /**
     * T reduce(T identity, BinaryOperator<T> accumulator)
     *
     * 所有乘积的2倍
     */
    @Test
    public void reduceSecondSign() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        Integer reduce = list.stream().reduce(2, (a, b) -> a * b);
        System.out.println(reduce); // 1440
    }

    /**
     * <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);
     *
     */
    @Test
    public void reduceThirdSign() {
        List<Integer> list = Arrays.asList(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Long reduce = list.stream().reduce(0L, (a, b) -> a + b, (a, b) -> 0L);
        System.out.println(reduce);
    }

    /**
     * 统计一个班上所有及格同学的分数总和
     */
    @Test
    public void answer() {
        List<Student> students = initData();
        Double aDouble = students.stream().filter(s -> s.getScore() >= 60)
                .map(Student::getScore)
                .reduce(0d, (a, b) -> a + b);

        System.out.println(aDouble);
    }


    public static List<Student> initData() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("张三", 60));
        students.add(new Student("李四", 80));
        students.add(new Student("王五", 50));
        students.add(new Student("赵六", 70));
        students.add(new Student("孙七", 90));
        students.add(new Student("周八", 30));
        return students;
    }


}

class Student {

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生分数
     */
    private double score;

    /**
     * 所学课程
     */
    List<String> course;

    public Student() {}

    public Student(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public Student(String name, double score, List<String> course) {
        this.name = name;
        this.score = score;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCourse() {
        return course;
    }

    public void setCourse(List<String> course) {
        this.course = course;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}