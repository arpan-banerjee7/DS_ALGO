package multithreading.producerconsumer;


import java.util.LinkedList;


public class ProducerConsumerWIthSync {
    static class SharedBuffer {
        private LinkedList<Integer> buffer = new LinkedList<>();
        private int capacity = 10;

        public void produce(int item) throws InterruptedException {
            synchronized (this) {
                while (buffer.size() == capacity) {
                    wait();
                }
                buffer.add(item);
                System.out.println("Produced: " + item);
                notify();
            }
        }

        public void consume() throws InterruptedException {
            synchronized (this) {
                while (buffer.isEmpty()) {
                    wait();
                }
                int item = buffer.removeFirst();
                System.out.println("Consumed: " + item);
                notify();
            }
        }


    }

    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();

        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    buffer.produce(i);
                    Thread.sleep(100); // Simulate some work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    buffer.consume();
                    Thread.sleep(200); // Simulate some work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}