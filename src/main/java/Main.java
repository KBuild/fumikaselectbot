/**
 * Created by kbuild on 16. 10. 14.
 */
public class Main {
    public static void main(String[] args) {
        TwitterStreamLoader tsl = new TwitterStreamLoader();
        Bot bot = new Bot(tsl);
        bot.init();
        bot.run();
    }
}
