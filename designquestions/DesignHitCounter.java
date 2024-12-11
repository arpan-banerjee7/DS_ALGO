package designQuestions;



import java.util.*;

public class DesignHitCounter {

    private LinkedList<Pair> list;
    private int sum;

    /**
     * Initialize your data structure here.
     */
    public DesignHitCounter() {
        list = new LinkedList<>();
    }

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        evict(timestamp);
        if (list.isEmpty() || list.getLast().key != timestamp)
            list.add(new Pair(timestamp));
        else
            list.getLast().val++;
        sum++;
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        evict(timestamp);
        return sum;
    }

    private void evict(int timestamp) {
        while (!list.isEmpty() && list.getFirst().key <= timestamp - 300) {
            sum -= list.removeFirst().val;
        }
    }

    /*
      while (!this.hits.isEmpty()) {
                int diff = timestamp - this.hits.getFirst().getKey();
                if (diff >= 300) {
                    // Decrement total by the count of the oldest timestamp
                    this.total -= this.hits.getFirst().getValue();
                    this.hits.removeFirst();
                }
                else
     */
    private static final class Pair {
        int key;
        int val;

        Pair(int key) {
            this.key = key;
            this.val = 1;
        }
    }


    public static void main(String[] args) {
        DesignHitCounter counter = new DesignHitCounter();
        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        int hits1 = counter.getHits(4); // should return 3
        counter.hit(300);
        int hits2 = counter.getHits(300); // should return 4
        int hits3 = counter.getHits(301); // should return 3
        System.out.println(hits1);
        System.out.println(hits2);
        System.out.println(hits3);
    }
}

/*
class HitCounter {
    private Queue<Integer> hits;


public HitCounter() {
    this.hits = new LinkedList<Integer>();
}

    public void hit(int timestamp) {
        this.hits.add(timestamp);


    public int getHits(int timestamp) {
        while (!this.hits.isEmpty()) {
            int diff = timestamp - this.hits.peek();
            if (diff >= 300) this.hits.remove();
            else break;
        }
        return this.hits.size();
    }
}
 */