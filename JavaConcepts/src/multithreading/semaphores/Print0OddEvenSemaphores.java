package multithreading.semaphores;


import java.util.concurrent.*;
/*
The critical part of understanding how this works lies in the way the acquire and release methods of Semaphore work.

In the PrintZero method, you correctly pointed out that it can release oddSem and evenSem even if it hasn't explicitly
acquired them. This is because acquire is a blocking call. If acquire is called on a semaphore that has no permits available
(i.e., the semaphore's count is zero), the calling thread will block until a permit is available.

Here's how it works in the PrintZero method:

1) At the beginning of each iteration of the loop, zeroSem.acquire(); is called. Since zeroSem was initialized with one permit,
this call succeeds and allows the thread to continue.

2)Inside the loop, i is checked to determine if it's even or odd. If i is even, it means the next number should be odd,
so evenSem.release(); is called. This increments the count of evenSem, making it possible for another thread waiting on evenSem to acquire a permit.

3)Similarly, if i is odd, it means the next number should be even, so oddSem.release(); is called, incrementing the count of oddSem.

4)The key here is that release doesn't require the thread to have acquired the semaphore in advance. It simply increases
the available permits for that semaphore, allowing other threads that are blocked on an acquire call for that semaphore to proceed.

5)So, even though the thread initially acquired zeroSem, it can indirectly influence the availability of permits in oddSem
and evenSem based on the value of i.

When semaphore.release() is called, it increases the count of available permits by one. On the other hand, when a thread
calls semaphore.acquire(), it decreases the count of available permits by one.

This is how semaphores manage access to a shared resource or a critical section. They ensure that only a limited number
of threads (based on the number of available permits) can access the shared resource concurrently. When a thread finishes
its work and calls release(), it effectively signals that another thread can now enter the critical section, incrementing
the count of available permits. When a thread wants to enter the critical section, it calls acquire(), which will block if
there are no available permits until another thread releases a permit by calling release().
 */
public class Print0OddEvenSemaphores {
    private int n;
    private Semaphore zeroSem, oddSem, evenSem;

    public Print0OddEvenSemaphores(int n) {
        this.n = n;
        zeroSem = new Semaphore(1);
        oddSem = new Semaphore(0);
        evenSem = new Semaphore(0);
    }

    public void printZero() {
        for (int i = 0; i < n; ++i) {
            try {
                zeroSem.acquire();
            } catch (Exception e) {
            }
            System.out.print("0 ");
            // release oddSem if i is even or else release evenSem if i is odd
            (i % 2 == 0 ? oddSem : evenSem).release();
        }
    }

    public void printEven() {
        for (int i = 2; i <= n; i += 2) {
            try {
                evenSem.acquire();
            } catch (Exception e) {
            }
            System.out.print(i+" ");
            zeroSem.release();
        }
    }

    public void printOdd() {
        for (int i = 1; i <= n; i += 2) {
            try {
                oddSem.acquire();
            } catch (Exception e) {
            }
            System.out.print(i+" ");
            zeroSem.release();
        }
    }


    public static void main(String[] args) {

        Print0OddEvenSemaphores zeo = new Print0OddEvenSemaphores(5);

        Thread t1 = new Thread(() -> {
            zeo.printZero();
        });
        Thread t2 = new Thread(() -> {
            zeo.printOdd();
        });
        Thread t3 = new Thread(() -> {
            zeo.printEven();
        });

        t2.start();
        t1.start();
        t3.start();

    }
}
