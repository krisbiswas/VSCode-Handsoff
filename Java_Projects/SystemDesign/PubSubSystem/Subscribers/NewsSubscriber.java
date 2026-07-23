package Subscribers;

import Service.PublishSubscribeService;
import Topic.TopicNames;

public class NewsSubscriber implements Subscriber {
    private String id;
    private PublishSubscribeService service;

    public NewsSubscriber(PublishSubscribeService service) {
        this.id = java.util.UUID.randomUUID().toString();
        this.service = service;
        service.subscribe(TopicNames.NEWS.getName(), this);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("NewsSubscriber " + id + " received message: " + message);
    }
    
    @Override
    public void close() {
        service.unsubscribe(TopicNames.NEWS.getName(), this);
    }
}
