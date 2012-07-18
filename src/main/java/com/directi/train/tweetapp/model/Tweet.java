package com.directi.train.tweetapp.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: prashanth.v
 * Date: 7/15/12
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tweet {
    int tweetid;
    String tweets;
    int user_id;
    String name;
    int parentReplyId;

    Timestamp time;
    public static final RowMapper<Tweet> rowMapper = new RowMapper<Tweet>() {
        @Override public Tweet mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Tweet(resultSet);
        }
    };
    public Tweet(ResultSet rs) throws SQLException{
        tweetid =rs.getInt("tweetid");
        tweets =rs.getString("tweets");
        user_id=rs.getInt("user_id");
        time=rs.getTimestamp("time");
        parentReplyId=rs.getInt("parentReplyId");
        name=rs.getString("name");
    }
     public Tweet() { }

    public int getTweetid() {
        return tweetid;
    }
    public void setName(String name){
        this.name=name;

    }
    public String getName(){
        return name;
    }
    public void setParentReplyId(int id){
        this.parentReplyId=id;
    }
    public int getParentReplyId(){
        return parentReplyId;
    }

    public void setTweetid(int tweetid) {
        this.tweetid = tweetid;
    }
    public void setTweets(String tweets){
        this.tweets = tweets;
    }

    public String getTweets() {
        return tweets;
    }
    public int getUser_id(){
        return user_id;
    }

    public void setUser_id(int id) {
        this.user_id = id;
    }
    public void setTime(Timestamp time){
        this.time=time;
    }
    public Timestamp getTime(){
        return time;
    }

}
