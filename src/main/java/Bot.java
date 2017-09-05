import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.UserStreamListener;

/**
 * Created by kbuild on 16. 10. 14.
 */
public class Bot {
    private UserStreamListener listner;
    private TwitterStream twitterStream;

    public Bot(TwitterStreamLoader listner) {
        this.listner = listner;
    }

    public void init() {
        try {
            twitterStream.clearListeners();
            twitterStream.cleanUp();
        } catch (Exception e) {
            // nothing to do
        }

        twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.setOAuthConsumer(GlobalConfig.getConsumerKey(),
                                       GlobalConfig.getConsumerSecret());
        twitterStream.setOAuthAccessToken(GlobalConfig.getRawAccessToken());
        twitterStream.addListener(listner);
    }

    public void run() {
        twitterStream.user();
    }
}
