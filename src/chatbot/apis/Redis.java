package chatbot.apis;

import redis.clients.jedis.Jedis;
import java.util.*;

/**
 * Wrapper class for Redis.io database.
 * Generated for re-usability
 *
 * Singleton Pattern is implemented for Redis.io
 */
public class Redis {
    private static final String redisHost = "localhost";
    private static Redis Instance=null;
    private Jedis db;

    private Redis() {
        openConnection();
    }

    /**
     * @param keyword for getting tweets from redis
     * @return list of tweets according to keyword
     */
    public List<Tweet> getTweetsByKeyword(String keyword) {
        openConnection();
        keyword = keyword.replaceAll("\\W", "");
        List<Tweet> tweetList = new ArrayList<>();
        List<String> tweetInfos;
        try {
            tweetInfos = db.lrange(keyword, 0, -1); //get all items in list
            for (String tweetInfo : tweetInfos){
                String[] parts = tweetInfo.split("♦");
                //we store with "id♦text♦user♦timespan♦reviewPoint" format in Redis
                tweetList.add(new Tweet(Long.parseLong(parts[0]), parts[1], parts[2], parts[3], Double.parseDouble(parts[4]),Double.parseDouble(parts[5])));
            }
            return tweetList;

        } catch (Exception e){
            return null;
        }
    }

    /**
     * Adds given tweets to the redis
     *
     * @param tweetList source tweet list
     */
    public void addNewTweets(String keyword, List<Tweet> tweetList) {
        openConnection();
        keyword = keyword.replaceAll("\\W", "");
        for (Tweet tweet : tweetList) {
            db.rpush(keyword, tweet.serialize());

        }
    }

    /**
     * USE WITH CAUTION! Deletes all the keys of the currently selected DB.
     *
     * @see <a href="https://redis.io/commands/flushdb">FLUSHDB</a>
     */
    public void reset() {
        db.flushDB();
    }

    /**
     * Closes database connection. Use this after you are done with object
     */
    public void close() {
        db.close();
    }

    public void openConnection(){
        db = new Jedis(redisHost);
    }

    public static Redis getInstance(){
        if (Instance == null)
            Instance = new Redis();

        return Instance;
    }
}
