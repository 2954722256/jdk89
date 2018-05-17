package cn.dodo.jdk89.lambda;

import java.util.function.Function;
import java.util.function.UnaryOperator;


/**
 * 级联表达式和柯里化
 * 柯里化:把多个参数的函数转换为只有一个参数的函数
 * 柯里化的目的：函数标准化
 * 高阶函数：就是返回函数的函数
 */
public class C09CurryDemo {
    public static void main(String[] args) {

        doFunc01();
        doFunc02();


        doFunc03();
    }

    private static void doFunc03() {
        Function<Integer, Function<Integer, Function<Integer, Integer>>> fun2 = x -> y -> z -> x + y + z;
        System.out.println(fun2.apply(2).apply(3).apply(4));

        int[] nums = {2, 3, 4};
        Function f = fun2;

        for (int i = 0; i < nums.length; i++) {
            if (f instanceof Function) {
                Object obj = f.apply(nums[i]);
                if (obj instanceof Function) {
                    f = (Function) obj;
                } else {
                    System.out.println("调用结束：结果为" + obj);
                }
            }
        }
    }

    /**
     * 理解， 传入 Integer，  返回  一个 Function<Integer, Integer>
     *     x ->  【这里是对应的参数】
     *           y -> x + y  【对应的函数表达式 Function<Integer, Integer>】
     *                  这里，输入 y 是 Integer，  返回 x + y 也是 Integer
     *                      【因为相同，可以用 UnaryOperator】
     */
    private static void doFunc01() {
        // 实现了x+y的级联表达式
        Function<Integer, Function<Integer, Integer>> f = x -> y -> x + y;

        Function<Integer, UnaryOperator<Integer>> f2 = x -> y -> x + y;

        //  返回一个函数  y -> x + y，  也就是  y -> 2+y
        System.out.println(f2.apply(2));

    }

    private static void doFunc02() {
        Function<Integer, Function<Integer, Integer>> fun = x -> y -> x + y;
        System.out.println(fun.apply(2).apply(3));
    }
}
