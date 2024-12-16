package designpatterns.observer.observer;

import designpatterns.observer.subject.Subject;

public class EmailObserver implements Observer {
    String emailId;
    Subject subject;

    public EmailObserver(String emailId, Subject subject) {
        this.emailId = emailId;
        this.subject = subject;
    }

    @Override
    public void update() {
        sendMail(emailId, "Back in stock");
    }

    private void sendMail(String emailId, String body) {
        // dummy implementation
        System.out.println(emailId + " " + body);
    }
}
