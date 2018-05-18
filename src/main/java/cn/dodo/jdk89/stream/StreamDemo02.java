package cn.dodo.jdk89.stream;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo02 {

    public static void main(String[] args) {
        do01FromList();
        do02FromArrays();
        do03FromXXXStream();
        do04FromStreamRandom();


    }

    private static void do04FromStreamRandom() {
        // 使用random创建一个无限流    limit(n) 终止
        new Random().ints().limit(10);
        Random random = new Random();

        // 自己产生流
        Stream<Integer> limit = Stream.generate(() -> random.nextInt(100)).limit(20);
        Object[] ints = limit.toArray();
        System.out.println("do04FromStreamRandom " + ints.length +" --> "+ Arrays.toString(ints));
    }

    private static void do03FromXXXStream() {
        // 创建数字流
        IntStream intStream01 = IntStream.of(1, 2, 3);
        IntStream intStream02 = IntStream.rangeClosed(1, 10);

        System.out.println("do03FromXXXStream " + Arrays.toString(intStream01.toArray()));
        System.out.println("do03FromXXXStream " + Arrays.toString(intStream02.toArray()));
    }

    private static void do02FromArrays() {
        // 从数组创建
        IntStream stream = Arrays.stream(new int[]{2, 3, 5});
        ;
        System.out.println("do02FromArrays " + Arrays.toString(stream.toArray()));
    }

    private static void do01FromList() {
        List<String> list = new ArrayList<>();

        for(int i=0; i<100; i++){
            list.add(i+" todo!!");
        }

        List<String> list02 = new ArrayList<>();
        list02.addAll(list);

        // 从集合创建
        list.stream();
        list.parallelStream();
    }


}
