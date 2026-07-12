package Topic;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import Subscribers.Subscriber;

public class Topic{
    private String name;
    private ExecutorService executorService;
    private Set<Subscriber> subscribers;

    public Topic(String name) {
        this.name = name;
        executorService = java.util.concurrent.Executors.newFixedThreadPool(10);
        subscribers = new java.util.HashSet<>();
    }

    public void broadcast(String message) {
        if(subscribers.isEmpty()) {
            System.out.println("No subscribers for topic: " + name);
            return;
        }
        System.out.println("Publishing message: " + message);
        for (Subscriber subscriber : subscribers) {
            executorService.submit(() -> subscriber.onMessage(message));
        }
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void close() {
        subscribers.clear();
        executorService.shutdown();
    }
}