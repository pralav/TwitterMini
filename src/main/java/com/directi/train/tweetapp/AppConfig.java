package com.directi.train.tweetapp;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

@Configuration
public class AppConfig {
    @Bean
    public SimpleJdbcTemplate simpleJdbcTemplate() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/twitter");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        /*dataSource.setUrl("jdbc:hsqldb:mem:todo");
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver"); */
        SimpleJdbcTemplate db = new SimpleJdbcTemplate(dataSource);

        /*db.update("CREATE TABLE users (" +
                  "id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY," +
                  "username varchar(128) UNIQUE," +
                  "password varchar(128) NOT NULL," +
                  "email varchar(128) UNIQUE," +
                  "fullname varchar(128) NOT NULL," +
                  "time TIMESTAMP DEFAULT NOW"+
                  ")");
        db.update("CREATE TABLE todos (" +
                  "id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY," +
                  "user_id INTEGER NOT NULL," +
                  "description varchar(128) DEFAULT '' NOT NULL" +
                  ")");
        db.update("CREATE TABLE feeds (" +
                  "feedid INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY," +
                  "user_id INTEGER NOT NULL," +
                  "feeds varchar(1024) DEFAULT '' NOT NULL," +
                  "time TIMESTAMP DEFAULT NOW," +
                  "FOREIGN KEY(user_id) REFERENCES users(id)" +
                  ")");
        db.update("CREATE TABLE comments (" +
                   "commentid INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY," +
                   "user_id INTEGER NOT NULL," +
                   "feedid varchar(128) NOT NULL," +
                   "comment varchar(128) NOT NULL," +
                   "time TIMESTAMP DEFAULT NOW,"+
                   "FOREIGN KEY(user_id) REFERENCES users(id)" +
                   ")");
        db.update("CREATE TABLE followers (" +
                   "follower_id INTEGER NOT NULL," +
                   "user_id INTEGER NOT NULL," +
                   "FOREIGN KEY(user_id) REFERENCES users(id)," +
                   "FOREIGN KEY(follower_id) REFERENCES users(id)" +
                   ")");

          */
        return db;
    }

    @Bean
    public ThreadLocal<Long> userID() {
        return new ThreadLocal<Long>();
    }
}
