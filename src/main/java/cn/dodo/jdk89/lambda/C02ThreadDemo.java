package cn.dodo.jdk89.lambda;

/**
 * 理解 () ->
 */
public class C02ThreadDemo {

	public static void main(String[] args) {

	}


	public static void doOld(){
        final Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        };
        new Thread(run).start();

	}

	// jdk8
	public static void doNew(){
        final Runnable runnable = () -> System.out.println("run new!!");
        new Thread(runnable).start();
	}


    public static void doOldChange(){
        final Object run = new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        };
        new Thread((Runnable) run).start();

    }

    // jdk8
    public static void doNewChange(){
        final Object runnable1 = (Runnable)() -> System.out.println("run new!!");
        final Runnable runnable2 = () -> System.out.println("run new!!");


        new Thread((Runnable)runnable1).start();
    }

}
