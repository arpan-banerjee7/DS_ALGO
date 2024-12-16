package designpatterns.observer.subject;

import designpatterns.observer.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class IphoneSubject implements Subject {

    private final List<Observer> observerList = new ArrayList<>();
    private int stockCount = 0;

    @Override
    public void add(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifySubscribers() {
        for (Observer observer : observerList) {
            observer.update();
        }
    }

    @Override
    public void set(int data) {
        if (stockCount == 0) {
            notifySubscribers();
        }
        stockCount = stockCount + data;
    }

    @Override
    public int get() {
        return stockCount;
    }
}
