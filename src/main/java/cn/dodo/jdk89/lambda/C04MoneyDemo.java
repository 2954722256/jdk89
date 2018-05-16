package cn.dodo.jdk89.lambda;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * 第1种写法
 *      定义接口 （是函数式接口， 只有一个 未实现方法）
 *      传入 int， 返回 String
 */
interface IMoneyFormat{
    String format(int i);
}

/**
 * 第1种写法
 *      传入接口实现即可
 */
class MyMoney1 {
    private final int money;

    public MyMoney1(int money) {
        this.money = money;
    }

    public void printMoney(IMoneyFormat moneyFormat) {
        System.out.println("我的存款：" + moneyFormat.format(this.money));
    }
}

/**
 * 函数式
 */
class MyMoney2 {
    private final int money;

    public MyMoney2(int money) {
        this.money = money;
    }

    public void printMoney(Function<Integer, String> moneyFormat) {
        System.out.println("我的存款：" + moneyFormat.apply(this.money));
    }
}

/**
 *  理解 函数接口链式操作
 *      函数接口链式操作
 */
public class C04MoneyDemo {

    public static void main(String[] args) {
//        do01();
        do02();
    }

    private static void do02() {
        MyMoney2 me = new MyMoney2(99999999);
        //jdk8 后， 对应的
        // 1) Integer 输入类型   （i  也是 ）
        // 2) String 输出类型
        Function<Integer, String> moneyFormat = i -> new DecimalFormat("#,###")
                .format(i);

//        me.printMoney(moneyFormat);
        // 函数接口链式操作
        me.printMoney(moneyFormat.andThen(s -> "人民币 " + s));
    }


    private static void do01() {
        MyMoney1 me = new MyMoney1(99999999);
        // 接口的写法
        me.printMoney(i -> new DecimalFormat("#,###").format(i));

//        me.printMoney(new IMoneyFormat(){
//            @Override
//            public String format(int i) {
//                return new DecimalFormat("#,###").format(i);
//            }
//        });
    }

}
