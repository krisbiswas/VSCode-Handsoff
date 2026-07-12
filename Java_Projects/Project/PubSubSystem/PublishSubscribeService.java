import java.util.Map;
import java.util.HashMap;
import Subscribers.Subscriber;

public class PublishSubscribeService {
    Map<String, Topic> subscribersMap = new HashMap<>();

    public Topic createTopic(String name) {
        Topic topic = new Topic(name);
        subscribersMap.put(name, topic);
        return topic;
    }

    public void publish(String author, String topicName, String message) {
        Topic topic = subscribersMap.get(topicName);
        if(topic == null){
            System.out.println("Topic does not exist: " + topicName);
            return;
        }
        topic.broadcast(message);
    }

    public void subscribe(String topicName, Subscriber subscriber) {
        if(topicName == null || subscriber == null) {
            System.out.println("Topic or Subscriber cannot be null");
            return;
        }
        Topic topic = subscribersMap.get(topicName);
        if(topic == null){
            System.out.println("Topic does not exist: " + topicName);
            return;
        }
        subscribersMap.get(topicName).subscribe(subscriber);
    }

    public void unsubscribe(String topicName, Subscriber subscriber) {
        if(topicName == null || subscriber == null) {
            System.out.println("Topic or Subscriber cannot be null");
            return;
        }
        Topic topic = subscribersMap.get(topicName);
        if(topic == null){
            System.out.println("Topic does not exist: " + topicName);
            return;
        }
        topic.unsubscribe(subscriber);
    }
}
