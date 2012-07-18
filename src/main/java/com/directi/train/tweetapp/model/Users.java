package com.directi.train.tweetapp.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: prashanth.v
 * Date: 7/15/12
 * Time: 12:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class Users {
    long id;
    String email;
    String password;
    String name;
    Timestamp time;
    public static final RowMapper<Users> rowMapper = new RowMapper<Users>() {
            @Override public Users mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Users(resultSet);
            }
        };
    public Users(ResultSet rs) throws SQLException{
            id =rs.getInt("id");
            email =rs.getString("email");
            password=rs.getString("password");
            name=rs.getString("name");
            time=rs.getTimestamp("time");
    }
    public Users() { }
    public long getId(){
        return this.id;
    }
    public void setId(long id){
        this.id=id;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
         this.password=password;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public Timestamp getTime(){
        return this.time;
    }
    public void setTime(Timestamp time){
        this.time=time;
    }

}
