package cn.dodo.jdk89.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections4.MapUtils;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 收集器 的demo
 *      collect
 */
public class CollectDemo {

    public static void main(String[] args) {
        // 测试数据
        List<Student> students = getStudents();

        do01CollectorsToList(students);
        do02ChooseImp(students);
        do03IntSummary(students);
        do04partitioningBy(students);
        do05groupingBy(students);
        do06groupingBySelf(students);


    }

    private static void do06groupingBySelf(List<Student> students) {
        //        // 得到所有班级学生的个数
        Map<Grade, Long> gradesCount = students.stream().collect(Collectors
                .groupingBy(Student::getGrade, Collectors.counting()));
        MapUtils.verbosePrint(System.out, "do06groupingBySelf 班级学生个数列表", gradesCount);
    }

    private static void do05groupingBy(List<Student> students) {
        //        // 分组
        Map<Grade, List<Student>> grades = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade));
        MapUtils.verbosePrint(System.out, "do05groupingBy 学生班级列表", grades);
    }

    /**
     *  分块
     *  Collectors.partitioningBy
     * @param students
     */
    private static void do04partitioningBy(List<Student> students) {
        // 分块
        Map<Boolean, List<Student>> genders = students.stream().collect(
                Collectors.partitioningBy(s -> s.getGender() == Gender.MALE));
        // 这里 打印map不直观， 用 MapUtils 打印
        // System.out.println("男女学生列表:" + genders);
        MapUtils.verbosePrint(System.out, "do04partitioningBy 男女学生列表", genders);
    }

    /**
     * 汇总统计 IntSummaryStatistics
     *      {count=XXX, sum=XXX, min=XXX, average=XXX, max=XXX}
     *
     * @param students
     */
    private static void do03IntSummary(List<Student> students) {
        // 统计汇总信息
        IntSummaryStatistics agesSummaryStatistics = students.stream()
                .collect(Collectors.summarizingInt(Student::getAge));
        System.out.println("do03IntSummary summarizingInt 年龄汇总信息:" + agesSummaryStatistics);
    }

    /**
     *  和 toSet 不同， 这里 选择了Set的实现类
     *      用 Collectors.toCollection(TreeSet::new) 选择实现类
     * @param students
     */
    private static void do02ChooseImp(List<Student> students) {
        // 得到所有学生的年龄列表
        // s -> s.getAge() --> Student::getAge , 不会多生成一个类似 lambda$0这样的函数
        Set<Integer> ages = students.stream().map(Student::getAge)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("do02ChooseImp TreeSet 所有学生的年龄:" + ages);
    }

    /**
     *  s -> s.getAge() --> Student::getAge ,
     *      不会多生成一个类似 lambda$0这样的函数
     *  默认类型  这里 List  （也可以Set）
     *      List 是接口， 不管是什么实现
     * @param students
     */
    private static void do01CollectorsToList(List<Student> students) {
        // 得到所有学生的年龄列表
        // s -> s.getAge() --> Student::getAge , 不会多生成一个类似 lambda$0这样的函数
        List<Integer> ages = students.stream().map(Student::getAge)
                .collect(Collectors.toList());
        System.out.println("do01CollectorsToList 所有学生的年龄:" + ages);
    }

    private static List<Student> getStudents() {
        return Arrays.asList(
                    new Student("小明", 10, Gender.MALE, Grade.ONE),
                    new Student("大明", 9, Gender.MALE, Grade.THREE),
                    new Student("小白", 8, Gender.FEMALE, Grade.TWO),
                    new Student("小黑", 13, Gender.FEMALE, Grade.FOUR),
                    new Student("小红", 7, Gender.FEMALE, Grade.THREE),
                    new Student("小黄", 13, Gender.MALE, Grade.ONE),
                    new Student("小青", 13, Gender.FEMALE, Grade.THREE),
                    new Student("小紫", 9, Gender.FEMALE, Grade.TWO),
                    new Student("小王", 6, Gender.MALE, Grade.ONE),
                    new Student("小李", 6, Gender.MALE, Grade.ONE),
                    new Student("小马", 14, Gender.FEMALE, Grade.FOUR),
                    new Student("小刘", 13, Gender.MALE, Grade.FOUR));
    }

}


/**
 * 学生 对象
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
class Student {
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 班级
     */
    private Grade grade;

}

/**
 * 性别
 */
enum Gender {
    MALE, FEMALE
}

/**
 * 班级
 */
enum Grade {
    ONE, TWO, THREE, FOUR;
}
