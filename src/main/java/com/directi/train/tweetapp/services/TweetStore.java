package com.directi.train.tweetapp.services;

import com.directi.train.tweetapp.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: prashanth.v
 * Date: 7/15/12
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TweetStore {
    private final ThreadLocal<Long> userID;
    public SimpleJdbcTemplate db;

    @Autowired
    public TweetStore(@Qualifier("userID") ThreadLocal<Long> userID, SimpleJdbcTemplate template) {
        this.userID = userID;
        db = template;
    }

    public List<Tweet> list() {

        return db.query("SELECT tweetid,user_id,tweets,tweets.time,parentReplyId," +
                        "users.name " +
                        "from tweets,users " +
                        " where (user_id in(Select followerid from follower where userid=?)or user_id=? )"+
                        " and user_id=users.id"+
                        " order by time desc",
                        Tweet.rowMapper,
                        userID.get(),
                        userID.get());
    }

    public Tweet add(Tweet tweet) {
        db.update("insert into tweets (user_id, tweets) values(?,?)", userID.get(), tweet.getTweets());
        int id = db.queryForInt("SELECT LAST_INSERT_ID()");
        return db.queryForObject("select * from tweets where tweetid=?",
                                 Tweet.rowMapper,
                                 id);
    }

    public void delete(int id) {
        db.update("delete from tweets where tweetid=? and user_id=?", id, userID.get());
    }

    public void update(Tweet updated) {
        db.update("update tweets set tweets=? where tweetid=? and user_id=?",
                  updated.getTweets(),
                  updated.getTweetid(),
                  userID.get());
    }
}
