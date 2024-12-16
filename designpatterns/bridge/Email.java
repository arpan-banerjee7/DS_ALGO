package designpatterns.bridge;

public class Email implements NotificationSender {
    @Override
    public void sendNotification() {
        System.out.println("Sending an Email");
    }
}
