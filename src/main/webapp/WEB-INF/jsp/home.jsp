<%--
  Created by IntelliJ IDEA.
  User: prashanth.v
  Date: 7/15/12
  Time: 7:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Home</title>
    <link href="/static/css/bootstrap.css" rel="stylesheet" >
    <link href="/static/css/bootstrap-responsive.css" rel="stylesheet">
    <style>
        .body{
            padding-top: 60;
            padding-bottom: 40;
        }
    </style>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
       <script type="text/javascript" src="/static/js/ejs_production.js"></script>

       <script type="text/javascript">
            function addTweet(form){
                $.post('/todo/create.json', $(form).serialize(), function(data){
                      var newTweet=$(new EJS({url: '/static/ejs/addTweet.ejs'}).render(data)).data("tweets",data.tweets);
                      $('#newTweet').append(newTweet);
                });
            }
       </script>

</head>
<body>
<div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">Min-Twitter</a>
          <div class="btn-group pull-right">
            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
              <i class="icon-user"></i> ${fullname}
              <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
              <li><a href="#">Profile</a></li>
              <li class="divider"></li>
              <li><a href="#">Sign Out</a></li>
            </ul>
          </div>
          <div class="nav-collapse">
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
</div>
<div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">Sidebar</li>
              <li class="active"><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li class="nav-header">Sidebar</li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li class="nav-header">Sidebar</li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span9">
          <div class="hero-unit">
            <h1>Welcome ${fullname}</h1>
            <p>
                <br />
                <form method="post" action="/todo/create" onsubmit="addTweet(this);return false;">
                    <textarea name="tweets" cols="150" ></textarea>
                    <br />
                    <input type="submit" value="Tweet" />
                </form>
                <span id="newTweet"></span>
            </p>
            <c:forEach var='tweet' items='${tweetList}'>
                <li><a href="${tweet.user_id}">${tweet.name}</a>${tweet.tweets}</li>
            </c:forEach>
            <p><a class="btn btn-primary btn-large">Learn more &raquo;</a></p>
          </div>
        </div>
      </div>
    </div>
</div>
</body>
</html>