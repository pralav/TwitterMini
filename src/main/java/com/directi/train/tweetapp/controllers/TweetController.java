package com.directi.train.tweetapp.controllers;

import com.directi.train.tweetapp.model.Tweet;
import com.directi.train.tweetapp.services.TweetStore;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("tweet")
public class TweetController {
    //private final TodoStore todoStore;

    private final TweetStore tweetStore;

    @Autowired
    public TweetController(TweetStore tweetStore){
        this.tweetStore=tweetStore;
    }

    @RequestMapping
    public ModelAndView tweet() {
        return new ModelAndView() {{
            addObject(tweetStore.list());
        }};
    }

    @RequestMapping(value="/home", method= RequestMethod.GET)
    public ModelAndView homePage(@RequestParam String name){
        ModelAndView mv=new ModelAndView("/home");

        mv.addObject("tweetList",tweetStore.list());
        mv.addObject("fullname",name);
        return mv;
    }
    @RequestMapping("list")
    @ResponseBody
    public List<Tweet> list() {
        return tweetStore.list();
    }

    @RequestMapping("create")
    @ResponseBody
    public Tweet create(Tweet tweet) {
        return tweetStore.add(tweet);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map<String, String> delete(@RequestParam("id") int id) {
        tweetStore.delete(id);
        return ImmutableMap.of("status", "success");
    }

    @RequestMapping("update")
    @ResponseBody
    public ImmutableMap<String, String> update(Tweet updated) {
        tweetStore.update(updated);
        return ImmutableMap.of("status", "success");
    }
}
