package Subscribers;

import Service.PublishSubscribeService;
import Topic.TopicNames;

public class SportsSubscriber implements Subscriber {
    private String id;
    private PublishSubscribeService service;

    public SportsSubscriber(PublishSubscribeService service) {
        this.id = java.util.UUID.randomUUID().toString();
        this.service = service;
        service.subscribe(TopicNames.SPORTS.getName(), this);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("SportsSubscriber " + id + " received message: " + message);
    }

    @Override
    public void close() {
        service.unsubscribe(TopicNames.SPORTS.getName(), this);
    }
}
