package cn.dodo.jdk89.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo04 {

    public static void main(String[] args) {
        String str = "my name is 007";
        do01ForEach(str);
        do02StreamToList(str);
        do03Reduce(str);
        do04MapReduce(str);
        do05Max(str);
        do06FindFirst();
    }

    private static void do06FindFirst() {
        System.out.println();
        // 使用 findFirst 短路操作
//        OptionalInt findFirst = new Random().ints().findFirst();
//        System.out.println(findFirst.getAsInt());

        Random random = new Random();
        IntStream ints = random.ints().filter(i -> i>0 && i<= 100).limit(10);
//        System.out.println("do06FindFirst random: " + Arrays.toString(ints.toArray()));
        System.out.println(ints.findFirst().getAsInt());

    }

    /**
     * max 的使用
     *      相当于， 实现
     * @param str
     */
    private static void do05Max(String str) {
        System.out.println();
        // max 的使用
        Optional<String> max = Stream.of(str.split(" "))
                .max((s1, s2) -> s1.length() - s2.length());
        System.out.println("do05Max max: " + max.get());
    }

    /**
     * 类似 hadoop的 MR
     * @param str
     */
    private static void do04MapReduce(String str) {
        System.out.println();
        // 计算所有单词总长度
        Integer length = Stream.of(str.split(" ")).map(s -> s.length())
                .reduce(0, (s1, s2) -> s1 + s2);
        System.out.println("do04MapReduce length: " + length);
    }

    /**
     *  readuce操作
     *      会返回对应的 Optional  直接get 需要catch异常
     *                             或者  orElse 直接写处理
     *  readuce(doSth).orElse("xxx")  ==  reduce("xxx",  doSth)
     * @param str
     */
    private static void do03Reduce(String str) {
        System.out.println();
        // 使用 reduce 拼接字符串
        Optional<String> letters = Stream.of(str.split(" "))
                .reduce((s1, s2) -> s1 + "|" + s2);
        System.out.println("do03Reduce  orElse: " + letters.orElse(""));

        // 带初始化值的reduce
        String reduce = Stream.of(str.split(" ")).reduce("",
                (s1, s2) -> s1 + "|" + s2);
        System.out.println("do03Reduce init: " + reduce);
    }

    /**
     *  collect操作
     *      也就是， 把Stream 直接 转换成 List， Set 等等 容器类
     * @param str
     */
    private static void do02StreamToList(String str) {
        System.out.println();
        // 收集到list
        List<String> list = Stream.of(str.split(" "))
                .collect(Collectors.toList());
        System.out.println("do02StreamToList: " + list);
    }

    /**
     *  forEach, forEachOrdered
     *      非短路操作
     *          forEach     如果是并行操作， 会乱（没有顺序）
     *          forEachOrdered  不会乱
     * @param str
     */
    private static void do01ForEach(String str) {

        System.out.print("do01ForEach: ");
        // 使用并行流
        str.chars().parallel().forEach(i -> System.out.print((char) i));
        System.out.println();

        System.out.print("do01forEachOrdered: ");
        // 使用 forEachOrdered 保证顺序
        str.chars().parallel().forEachOrdered(i -> System.out.print((char) i));
        System.out.println();
    }


}
