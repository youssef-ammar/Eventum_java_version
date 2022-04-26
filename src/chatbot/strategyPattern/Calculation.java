package chatbot.strategyPattern;

import twitter4j.Status;

public interface Calculation {

    public double calculate(Status tweet);

    public double calculate(String tweetText);
}
