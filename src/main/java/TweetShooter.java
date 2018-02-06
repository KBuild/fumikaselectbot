import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.security.SecureRandom;

/**
 * Created by kbuild on 16. 10. 14.
 */
public class TweetShooter {
    public static Twitter twitter = null;
    private SecureRandom rnd;

    public TweetShooter() {
        if (twitter == null) {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setOAuthConsumerKey(GlobalConfig.getConsumerKey())
              .setOAuthConsumerSecret(GlobalConfig.getConsumerSecret())
              .setOAuthAccessToken(GlobalConfig.getRawAccessToken().getToken())
              .setOAuthAccessTokenSecret(GlobalConfig.getRawAccessToken().getTokenSecret());
            TwitterFactory factory = new TwitterFactory(cb.build());
            twitter = factory.getInstance();
        }
        rnd = new SecureRandom();
        rnd.setSeed(System.currentTimeMillis());
    }

    public void send(String text, long i, String userName) {
        try {
            String[] candidates = text.split("\\s+");
            if(candidates.length <= 1) {
                //single candidate matched
                return;
            }

            String reply = "@" + userName + " " +
                           candidates[rnd.nextInt(candidates.length)];
            if (i < 1) {
                twitter.tweets().updateStatus(reply);
            } else {
                StatusUpdate su = new StatusUpdate(reply);
                su.setInReplyToStatusId(i);
                twitter.tweets().updateStatus(su);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
