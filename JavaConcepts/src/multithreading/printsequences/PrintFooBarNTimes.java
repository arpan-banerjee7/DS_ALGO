package multithreading.printsequences;


import java.util.concurrent.Semaphore;

public class PrintFooBarNTimes {
    boolean flag;

    public void printFoo(int n) {
        for (int i = 1; i <= n; i++) {
            synchronized (this) {
                while (!flag) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Foo");
                flag = false;
                notifyAll();
            }

        }
    }
}