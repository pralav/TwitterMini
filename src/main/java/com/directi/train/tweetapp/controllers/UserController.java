package com.directi.train.tweetapp.controllers;

import com.directi.train.tweetapp.model.Users;
import com.directi.train.tweetapp.services.LoginStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class UserController {
    //public final SimpleJdbcTemplate db;
    private LoginStore loginStore;


    @Autowired
    public UserController(LoginStore loginStore) {
        this.loginStore = loginStore;
    }

    @RequestMapping("/")
    public String index() {
        return "register";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String loginForm() {
        return "register";
    }
    @RequestMapping(value="/user/register",method= RequestMethod.GET)
    public String registrationForm(){
        return "register";
    }
    @RequestMapping("/user/register")
    public ModelAndView register(Users users){
        ModelAndView mv=new ModelAndView("register");
        Users newUser=loginStore.registerUser(users);
        if(newUser!=null){
            mv.addObject("message","Registration Successful!! Login now");
            //mv.addObject("status","Success");
        }
        else{

            mv.addObject("status", "Username Exists");
        }

        return mv;

    }
    @RequestMapping("/user/login")
    public ModelAndView login(Users users,
                              HttpSession session) {
        ModelAndView mv = new ModelAndView("register");
        long userID;
        HashMap<String,Object> statusMap=loginStore.loginCheck(users);
        String status=(String)statusMap.get("status");

        if(status.equals("Success") ){
            userID = (Integer)statusMap.get("id");
            System.out.println(userID+"here");
            String fullname=(String) statusMap.get("name");
            session.setAttribute("userName", fullname);
            session.setAttribute("userID", userID);
            mv.addObject("name",fullname);
            mv.setViewName("redirect:/tweet/home");
        }
        else if(status.equals("Password Mismatch")){
            mv.addObject("message", "Invalid password.");

            return mv;
        }
        else{
            mv.addObject("message","Register First !!");

        }
        return mv;
    }

    @RequestMapping(value = "/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}