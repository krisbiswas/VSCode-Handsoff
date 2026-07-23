package Topic;

public enum TopicNames{
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