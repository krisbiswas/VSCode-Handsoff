package Publishers;

import Service.PublishSubscribeService;
import Topic.TopicNames;

public class NewsPublisher implements Publisher {
    private String name;
    private final TopicNames topicName = TopicNames.NEWS;
    private PublishSubscribeService pubSubService;

    public NewsPublisher(String name, PublishSubscribeService pubSubService) {
        this.name = name;
        this.pubSubService = pubSubService;
        pubSubService.createTopic(TopicNames.NEWS.getName());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void publish(String message) {
        // System.out.println("NewsPublisher " + name + " published: " + message);
        pubSubService.publish(name, topicName.getName(), message);
    }
}
