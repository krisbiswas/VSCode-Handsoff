package Publishers;

import Topic.TopicNames;

public class SportsPublisher implements Publisher {
    private String name;
    private final TopicNames topicName = TopicNames.SPORTS;
    private Service.PublishSubscribeService pubSubService;

    public SportsPublisher(String name, Service.PublishSubscribeService pubSubService) {
        this.name = name;
        this.pubSubService = pubSubService;
        pubSubService.createTopic(topicName.getName());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void publish(String message) {
        pubSubService.publish(name, topicName.getName(), message);
    }
}
