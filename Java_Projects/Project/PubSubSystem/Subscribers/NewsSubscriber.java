package Subscribers;

public class NewsSubscriber implements Subscriber {
    private String id;

    public NewsSubscriber() {
        this.id = java.util.UUID.randomUUID().toString();
    }

    @Override
    public void onMessage(String message) {
        System.out.println("NewsSubscriber " + id + " received message: " + message);
    }
    
}
