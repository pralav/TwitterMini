package com.directi.train.tweetapp.services;

import com.directi.train.tweetapp.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: prashanth.v
 * Date: 7/18/12
 * Time: 12:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class LoginStore {
    private SimpleJdbcTemplate db;
    private final ThreadLocal<Long> userID;
    private UserStore userStore;
    @Autowired
    public LoginStore(@Qualifier("userID") ThreadLocal<Long> userID,SimpleJdbcTemplate db,UserStore userStore){
        this.userID=userID;
        this.userStore=userStore;
        this.db=db;
    }

    public HashMap<String,Object> loginCheck(Users users){

        Map<String,Object> userDetails=null;
        HashMap<String,Object> status=new HashMap<String,Object>();
        try{
              userDetails=db.queryForMap("select * from users where email=?",
                                         users.getEmail());
              if(userDetails.get("password").equals(users.getPassword())){
                  status.put("status","Success");  System.out.println(userDetails.get("id")+"plss va");
                  status.put("id", userDetails.get("id"));
                  status.put("name",userDetails.get("name"));
              }


              else
                  status.put("status","Password Mismatch");

        }
        catch(EmptyResultDataAccessException newUser){

            status.put("status","New User");
        }
        return status;

    }
    public Users registerUser(Users users){
        Map<String,Object> userDetails=null;
        HashMap<String,Object> status=new HashMap<String,Object>();
          return userStore.add(users);

        }

}
