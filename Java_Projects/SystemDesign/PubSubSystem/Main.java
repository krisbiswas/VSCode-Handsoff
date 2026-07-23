import Publishers.NewsPublisher;
import Publishers.Publisher;
import Publishers.SportsPublisher;
import Service.PublishSubscribeService;
import Subscribers.NewsSubscriber;
import Subscribers.SportsSubscriber;
import Subscribers.Subscriber;
import Topic.TopicNames;

class Main {
    public static void main(String[] args) {
        PublishSubscribeService pubSubService = new PublishSubscribeService();
        Publisher newsPublisher = new NewsPublisher("ABP", pubSubService);
        Publisher sportsPublisher = new SportsPublisher("ESPN", pubSubService);
        Publisher sportsPublisher2 = new SportsPublisher("Fox Sports", pubSubService);
        
        Subscriber newsSubscriber = new NewsSubscriber(pubSubService);
        pubSubService.subscribe(TopicNames.NEWS.getName(), newsSubscriber);

        newsPublisher.publish("Breaking news: Java 17 released!");

        sportsPublisher.publish("Sports update: Team A wins the championship!");
        
        Subscriber sportsSubscriber = new SportsSubscriber(pubSubService);
        
        sportsPublisher2.publish("Sports update: Team B wins the championship!");
        
        // pubSubService.publish("Author3", TopicNames.ENTERTAINMENT.getName(), "Entertainment news: New movie released!");
        sportsPublisher.publish("Sports update: Team C wins the championship!");
        
        Subscriber sportsSubscriber2 = new SportsSubscriber(pubSubService);
        sportsSubscriber.close();
        
        sportsPublisher.publish("Sports update: Team Z lost");
        sportsSubscriber2.close();
        
        Subscriber sportsSubscriber3 = new SportsSubscriber(pubSubService);
        sportsPublisher2.publish("Sports update: Match postponed due to weather conditions!");
        sportsPublisher.publish("Sports update: Match postponed due to weather conditions!");
        sportsSubscriber3.close();
        
        pubSubService.close();
    }
}