package cn.dodo.jdk89.lambda;

import java.util.stream.IntStream;

/**
 * 理解函数式编程
 *  理解 流式编程 IntStream
 *  理解 parallel() 多线程优化
 */
public class C01MinDemo {

    public static void main(String[] args) {
        int[] nums = {33,55,-55,90,-666,90};

//        miniOld(nums);

        miniLambda(nums);
    }

    public static void miniOld(int[] nums){
        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            if(i < min) {
                min = i;
            }
        }

        System.out.println(min);
    }

    // jdk8
    public static void miniLambda(int[] nums){
        int min2 = IntStream.of(nums).parallel().min().getAsInt();
        System.out.println(min2);
    }

}
