package cn.dodo.jdk89.stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamDemo01 {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        doF01_OldAdd(nums);
        doF02_NewAdd(nums);
        doF03_map(nums);

        // 使用stream的内部迭代
        // map就是中间操作（返回stream的操作）
        // sum就是终止操作
        int sum2 = IntStream.of(nums).map(StreamDemo01::doubleNum).sum();
        System.out.println("结果为：" + sum2);

        System.out.println("惰性求值就是终止没有调用的情况下，中间操作不会执行");
        IntStream.of(nums).map(StreamDemo01::doubleNum);
    }

    private static void doF03_map(int[] nums) {
        int[] nums1 = IntStream.of(nums).map(i -> i*2).toArray();
        System.out.println("doF03_map map->nums1：" + Arrays.toString(nums1));


        int sum1 = IntStream.of(nums).sum();
        System.out.println("doF03_map 结果为：" + sum1);
    }

    /**
     * 内部迭代
     *      不关心实现
     *      用内部的方法迭代【已经有实现，只需要传入action， 以后需要修改为多线程，只需要简单修改即可】
     * @param nums
     */
    private static void doF02_NewAdd(int[] nums) {
        int sum1 = IntStream.of(nums).sum();
        System.out.println("doF02_NewAdd 结果为：" + sum1);
    }

    /**
     * 外部迭代
     *      外面自己迭代实现
     * @param nums
     */
    private static void doF01_OldAdd(int[] nums) {
        // 外部迭代
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        System.out.println("doF01_OldAdd 结果为：" + sum);
    }

    public static int doubleNum(int i) {
        System.out.println("执行了乘以2");
        return i * 2;
    }
}
