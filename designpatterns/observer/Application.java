package designpatterns.observer;

import designpatterns.observer.observer.EmailObserver;
import designpatterns.observer.observer.SmsObserver;
import designpatterns.observer.subject.IphoneSubject;
import designpatterns.observer.subject.Subject;

public class Application {
    public static void main(String[] args) {
        Subject iphoneSubject = new IphoneSubject();

        EmailObserver emailObserver = new EmailObserver("abc@gmail.com", iphoneSubject);
        EmailObserver emailObserver1 = new EmailObserver("xyz@gmail.com", iphoneSubject);
        SmsObserver smsObserver = new SmsObserver(12, iphoneSubject);

        iphoneSubject.add(emailObserver);
        iphoneSubject.add(emailObserver1);
        iphoneSubject.add(smsObserver);

        iphoneSubject.set(10);


    }
}
