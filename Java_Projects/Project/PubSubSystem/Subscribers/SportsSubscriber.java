package Subscribers;

public class SportsSubscriber implements Subscriber {
    private String id;

    public SportsSubscriber() {
        this.id = java.util.UUID.randomUUID().toString();
    }

    @Override
    public void onMessage(String message) {
        System.out.println("SportsSubscriber " + id + " received message: " + message);
    }
}
