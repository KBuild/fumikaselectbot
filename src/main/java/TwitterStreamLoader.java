/**
 * Created by kbuild on 16. 10. 14.
 */

import twitter4j.*;
import com.twitter.Extractor;

public class TwitterStreamLoader implements UserStreamListener {

    private static String botName = "fumika_select";
    private Extractor ex;
    private TweetShooter tweetShooter;

    public static boolean isAlive = false;

    public TwitterStreamLoader() {
        this.tweetShooter = new TweetShooter();
        this.ex = new Extractor();
    }

    public void onStatus(Status status) {
        //check on timeline and rules will be called as Timeline or Mention
        if(status.isRetweet()) {
            //pass if tweet is retweet object
            return;
        }

        if(null == status || "".equals(status.getText())) {
            System.out.println("There is no contents");
        }

        if(ex.extractReplyScreenname(status.getText()).matches(botName)) {
            onMentionText(status.getText().replace("@"+botName, "").trim(),
                          status.getId(),
                          status.getUser().getScreenName());
        }
    }

    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

    }

    public void onTrackLimitationNotice(int i) {
    }

    public void onScrubGeo(long l, long l1) {

    }

    public void onStallWarning(StallWarning stallWarning) {
        System.out.println(stallWarning.getMessage());
    }

    public void onException(Exception e) {
        e.printStackTrace();
    }

    private void onTimelineText(String text, long id) {
    }

    private void onMentionText(String text, long id, String userName) {
        tweetShooter.send(text, id, userName);
    }

    @Override
    public void onDeletionNotice(long l, long l1) {

    }

    @Override
    public void onFriendList(long[] longs) {

    }

    @Override
    public void onFavorite(User user, User user1, Status status) {

    }

    @Override
    public void onUnfavorite(User user, User user1, Status status) {

    }

    @Override
    public void onFollow(User user, User user1) {

    }

    @Override
    public void onUnfollow(User user, User user1) {

    }

    @Override
    public void onDirectMessage(DirectMessage directMessage) {

    }

    @Override
    public void onUserListMemberAddition(User user, User user1, UserList userList) {

    }

    @Override
    public void onUserListMemberDeletion(User user, User user1, UserList userList) {

    }

    @Override
    public void onUserListSubscription(User user, User user1, UserList userList) {

    }

    @Override
    public void onUserListUnsubscription(User user, User user1, UserList userList) {

    }

    @Override
    public void onUserListCreation(User user, UserList userList) {

    }

    @Override
    public void onUserListUpdate(User user, UserList userList) {

    }

    @Override
    public void onUserListDeletion(User user, UserList userList) {

    }

    @Override
    public void onUserProfileUpdate(User user) {

    }

    @Override
    public void onUserSuspension(long l) {

    }

    @Override
    public void onUserDeletion(long l) {

    }

    @Override
    public void onBlock(User user, User user1) {

    }

    @Override
    public void onUnblock(User user, User user1) {

    }

    @Override
    public void onRetweetedRetweet(User user, User user1, Status status) {

    }

    @Override
    public void onFavoritedRetweet(User user, User user1, Status status) {

    }

    @Override
    public void onQuotedTweet(User user, User user1, Status status) {

    }
}
