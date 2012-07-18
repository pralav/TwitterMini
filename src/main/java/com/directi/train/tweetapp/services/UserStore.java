package com.directi.train.tweetapp.services;

import com.directi.train.tweetapp.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: prashanth.v
 * Date: 7/17/12
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserStore {
    private final ThreadLocal<Long> userID;
    public SimpleJdbcTemplate db;

    @Autowired
    public UserStore(@Qualifier("userID") ThreadLocal<Long> userID, SimpleJdbcTemplate template) {
        this.userID = userID;
        db = template;
    }


    public Users add(Users users) {
        try{
        db.update("insert into users (email,password,name) values(?,?,?)",
                    users.getEmail(),
                    users.getPassword(),
                    users.getName());
        int id = db.queryForInt("SELECT LAST_INSERT_ID()");
        return db.queryForObject("select * from users where id=?",
                                 Users.rowMapper,
                                 id);
        }
        catch(DataIntegrityViolationException userExists){
            return null;
        }
    }

    public void delete() {
        db.update("delete from users where id=?", userID.get());
    }

    public void updateName(Users updated) {
        db.update("update users set name=? where id=?",
                  updated.getName(),
                  userID.get());

    }
    public void updatePassword(Users updated){
        db.update("update users set password=? where id=?",
                updated.getPassword(),
                userID.get());
    }

}
