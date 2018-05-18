package cn.dodo.jdk89.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class StreamDemo05 {

    public static void main(String[] args) {
//        do01ParalleOrlPeek();
//        do02ParalleAndPeek();
//        do03ChangeConcurrentParallelism();
//        do04ForkJoinPool();
        do05ForkJoinPool2();

    }

    /**
     * 网上找的写法， 还没有确切测试
     */
    private static void do05ForkJoinPool2() {
        // 使用自己的线程池, 不使用默认线程池, 防止任务被阻塞
        // 线程名字 : ForkJoinPool-1
        ForkJoinPool pool = new ForkJoinPool(20);
        ForkJoinTask task = pool.submit(() -> IntStream.range(1, 100).parallel()
                .peek(StreamDemo05::debug).count());
        do {
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());
        pool.shutdown();

    }

    private static void do04ForkJoinPool() {
        // 使用自己的线程池, 不使用默认线程池, 防止任务被阻塞
        // 线程名字 : ForkJoinPool-1
        ForkJoinPool pool = new ForkJoinPool(20);
        pool.submit(() -> IntStream.range(1, 100).parallel()
                .peek(StreamDemo05::debug).count());
        pool.shutdown();    //不加入新的

        // 这里堵塞 主线程， 不让整个线程池退出
        synchronized (pool) {
            try {
                pool.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用这个属性可以修改默认的线程数
     */
    private static void do03ChangeConcurrentParallelism() {
        // 并行流使用的线程池: ForkJoinPool.commonPool
        // 默认的线程数是 当前机器的cpu个数
        // 使用这个属性可以修改默认的线程数
//         System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
//         "20");
//         IntStream.range(1, 100).parallel().peek(StreamDemo05::debug).count();
    }

    /**
     * 先并行， 再 sequential 串行【 不能实现】
     * 【多次调用， 以最后一个为准】
     */
    private static void do02ParalleAndPeek() {
        // 现在要实现一个这样的效果: 先并行,再串行
        // 多次调用 parallel / sequential, 以最后一次调用为准.
        IntStream.range(1, 100)
                // 调用parallel产生并行流
                .parallel().peek(StreamDemo05::debug)
                // 调用sequential 产生串行流
//        .sequential()
                .peek(StreamDemo05::debug2)
                .count();
    }

    /**
     * 直接 peek 是串行的
     * parallel 后， peek， 是并行的
     */
    private static void do01ParalleOrlPeek() {
        //直接peek ，是串行的
//        IntStream.range(1, 100).peek(StreamDemo05::debug).count();

        // 调用parallel 产生一个并行流
//        IntStream.range(1, 100).parallel().peek(StreamDemo05::debug).count();
    }

    static long time01 = 0;
    static long time02 = 0;


    /**
     * debug , 多线程的环节， 打印当前线程名字
     * 同时执行的个数， 和cpu核心数有关（几核，就同时运行几个线程）
     * 可以通过 System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","20"); 修改
     *
     * @param i
     */
    public static void debug(int i) {
        System.out.println(Thread.currentThread().getName() + " debug " + i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void debug2(int i) {
        System.err.println("debug2 " + i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
