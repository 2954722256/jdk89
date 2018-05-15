package cn.dodo.jdk89.lambda;

import java.util.stream.IntStream;

public class ThreadDemo {

	public static void main(String[] args) {

	}


	public static void doOld(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        }).start();

	}

	// jdk8
	public static void doNew(){
	    new Thread(()-> System.out.println("run new!!")).start();
	}

}
