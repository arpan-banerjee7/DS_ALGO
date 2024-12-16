package designpatterns.bridge;

public class SMS implements NotificationSender {
    @Override
    public void sendNotification() {
        System.out.println("We are going to send SMS");
    }
}
