package Service;
import java.util.Map;
import java.util.HashMap;
import Subscribers.Subscriber;
import Topic.Topic;

public class PublishSubscribeService {
    Map<String, Topic> subscribersMap = new HashMap<>();

    public void createTopic(String name) {
        if(subscribersMap.containsKey(name)){
            System.out.println("Topic already exists: " + name);
            return ;
        }
        Topic topic = new Topic(name);
        subscribersMap.put(name, topic);
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

    public void close() {
        subscribersMap.values().forEach(topic -> topic.close());
        subscribersMap.clear();
    }
}
