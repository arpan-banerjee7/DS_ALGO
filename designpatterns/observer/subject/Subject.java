package designpatterns.observer.subject;

import designpatterns.observer.observer.Observer;

public interface Subject {
    public void add(Observer observer);

    public void remove(Observer observer);

    public void notifySubscribers();

    public void set(int data);

    public int get();
}
