package designpatterns.bridge;

public class Application {
    public static void main(String[] args) {
        QRMessage qrMessage = new QRMessage(new SMS());
        qrMessage.sendMessage();
    }
}
