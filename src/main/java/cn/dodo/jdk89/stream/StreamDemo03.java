package cn.dodo.jdk89.stream;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class StreamDemo03 {

    public static void main(String[] args) {
        String str = "my name is 007";
        do01map(str);
        do02filter(str);
        do03flatmap(str);
        do04peek(str);
        do05limit();


    }

    private static void do05limit() {
        // limit 使用, 主要用于无限流
        new Random().ints().filter(i -> i > 100 && i < 1000).limit(10)
                .forEach(System.out::println);
    }

    private static void do04peek(String str) {
        // peek 用于debug. 是个中间操作,和 forEach 是终止操作
        System.out.println("--------------peek------------");
        Stream.of(str.split(" ")).peek(System.out::println)
                .forEach(System.out::println);
    }

    private static void do03flatmap(String str) {
        // flatMap A->B属性(是个集合), 最终得到所有的A元素里面的所有B属性集合
        // intStream/longStream 并不是Stream的子类, 所以要进行装箱 boxed
//        Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed())
//                .forEach(i -> System.out.println((char) i.intValue()));

        Stream<Integer> integerStream = Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed());
        System.out.println("do03flatmap " + Arrays.toString(integerStream.toArray()));

        Stream<Object> objectStream = Stream.of(str.split(" ")).flatMap(s -> s.chars().mapToObj(a->(char) a));
        System.out.println("do03flatmap " + Arrays.toString(objectStream.toArray()));
    }

    private static void do02filter(String str) {
        // 把每个单词的长度调用出来
//        Stream.of(str.split(" ")).filter(s -> s.length() > 2)
//                .map(s -> s.length()).forEach(System.out::println);

        Stream<Integer> integerStream = Stream.of(str.split(" ")).filter(s -> s.length() > 2)
                .map(s -> s.length());
        System.out.println("do02filter " + Arrays.toString(integerStream.toArray()));
    }

    private static void do01map(String str) {
        // 把每个单词的长度调用出来
//        Stream.of(str.split(" ")).filter(s -> s.length() > 2)
//                .map(s -> s.length()).forEach(System.out::println);

        Stream<Integer> integerStream = Stream.of(str.split(" "))
                .map(s -> s.length());
        System.out.println("do01map " + Arrays.toString(integerStream.toArray()));

    }

}
