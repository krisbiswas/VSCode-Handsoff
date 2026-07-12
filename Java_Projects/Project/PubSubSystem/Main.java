import Subscribers.NewsSubscriber;
import Subscribers.SportsSubscriber;
import Subscribers.Subscriber;

class Main {
    public static void main(String[] args) {
        PublishSubscribeService pubSubService = new PublishSubscribeService();
        Subscriber newsSubscriber = new NewsSubscriber();
        pubSubService.createTopic(null);
        pubSubService.createTopic(TopicNames.NEWS.getName());
        pubSubService.subscribe(TopicNames.NEWS.getName(), newsSubscriber);
        pubSubService.publish("Author1", TopicNames.NEWS.getName(), "Breaking news: Java 17 released!");
        pubSubService.publish("Author2", TopicNames.SPORTS.getName(), "Sports update: Team A wins the championship!");
        Subscriber sportsSubscriber = new SportsSubscriber();
        pubSubService.createTopic(TopicNames.SPORTS.getName());
        pubSubService.publish("Author3", TopicNames.ENTERTAINMENT.getName(), "Entertainment news: New movie released!");
        pubSubService.subscribe(TopicNames.SPORTS.getName(), sportsSubscriber);
        pubSubService.publish("Author2", TopicNames.SPORTS.getName(), "Sports update: Team C wins the championship!");
        Subscriber sportsSubscriber2 = new SportsSubscriber();
        pubSubService.subscribe(TopicNames.SPORTS.getName(), sportsSubscriber2);
        pubSubService.publish("Author3", TopicNames.SPORTS.getName(), "Sports update: Team Z lost");
        Subscriber sportsSubscriber3 = new SportsSubscriber();
        pubSubService.subscribe(TopicNames.SPORTS.getName(), sportsSubscriber3);
        pubSubService.subscribe(TopicNames.SPORTS.getName(), sportsSubscriber2);
        pubSubService.publish("Author2", TopicNames.SPORTS.getName(), "Sports update: Match postponed due to weather conditions!");
    }
}