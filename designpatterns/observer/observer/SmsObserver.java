package designpatterns.observer.observer;

import designpatterns.observer.subject.Subject;

public class SmsObserver implements Observer {
    int userId;
    Subject subject;

    public SmsObserver(int userId, Subject subject) {
        this.userId = userId;
        this.subject = subject;
    }

    @Override
    public void update() {
        sendMail(userId, "Back in stock");
    }

    private void sendMail(int userId, String body) {
        // dummy implementation
        System.out.println(userId + " " + body);
    }
}
