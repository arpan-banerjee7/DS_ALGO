package multithreading.semaphores;


import java.util.concurrent.Semaphore;

public class SemaphoreBasicExample {
    public static void main(String[] args) {
        // Create a semaphore with 2 permits
        Semaphore semaphore = new Semaphore(2);

        // Define three threads
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("Thread 1: Trying to acquire a permit.");
                semaphore.acquire(); // Acquire a permit
                System.out.println("Thread 1: Permit acquired. Doing some work.");
                Thread.sleep(2000); // Simulating some work
                System.out.println("Thread 1: Work done. Releasing permit.");
                semaphore.release(); // Release the permit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("Thread 2: Trying to acquire a permit.");
                semaphore.acquire(); // Acquire a permit
                System.out.println("Thread 2: Permit acquired. Doing some work.");
                Thread.sleep(1000); // Simulating some work
                System.out.println("Thread 2: Work done. Releasing permit.");
                semaphore.release(); // Release the permit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                System.out.println("Thread 3: Trying to acquire a permit.");
                semaphore.acquire(); // Acquire a permit
                System.out.println("Thread 3: Permit acquired. Doing some work.");
                Thread.sleep(1500); // Simulating some work
                System.out.println("Thread 3: Work done. Releasing permit.");
                semaphore.release(); // Release the permit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
}






