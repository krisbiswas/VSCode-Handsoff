import java.util.Set;
import java.util.concurrent.ExecutorService;
import Subscribers.Subscriber;

public class Topic{
    private String name;
    private ExecutorService executorService;
    private Set<Subscriber> subscribers;

    Topic(String name) {
        this.name = name;
        executorService = java.util.concurrent.Executors.newFixedThreadPool(10);
        subscribers = new java.util.HashSet<>();
    }

    void broadcast(String message) {
        if(subscribers.isEmpty()) {
            System.out.println("No subscribers for topic: " + name);
            return;
        }
        System.out.println("Publishing message: " + message);
        for (Subscriber subscriber : subscribers) {
            executorService.submit(() -> subscriber.onMessage(message));
        }
    }

    void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }
}

enum TopicNames{
    NEWS("News"),
    SPORTS("Sports"),
    ENTERTAINMENT("Entertainment");

    private final String name;

    TopicNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}