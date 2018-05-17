package cn.dodo.jdk89.lambda;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 *  方法引用的一些写法
 *      一些 名字
 */
public class C05FunctionDemo {

    public static void main(String[] args) {
        // 断言函数接口
        Predicate<Integer> predicate1 = i -> i > 0;
        // 名字带 传入参数
        // IntPredicate predicate2 = i -> i > 0;
        System.out.println(predicate1.test(-9));


        // 消费函数接口,  直接使用对应的函数
        Consumer<String> consumer = s -> System.out.println(s);
        // 名字带 传入参数
//        IntConsumer consumer2 = s-> System.out.println(s);

        consumer.accept("输入的数据");
    }
}
