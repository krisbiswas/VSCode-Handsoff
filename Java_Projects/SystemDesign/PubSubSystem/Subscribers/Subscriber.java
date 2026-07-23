package Subscribers;

public interface Subscriber {
    void onMessage(String message);

    void close();
}
