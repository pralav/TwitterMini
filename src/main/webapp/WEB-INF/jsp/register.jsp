<%--
  Created by IntelliJ IDEA.
  User: prashanth.v
  Date: 7/14/12
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Register</title></head>
<body>
<H1> Login</H1>
<b><font color="red">${message}</font></b>
<form method="post" action="/user/login">
    <table border=0>
        <tr>
            <td>UserName </td>
            <td>: <input type="text" name="email" /></td>
        </tr>
        <tr>
            <td>Password </td>
            <td>: <input type="password" name="password" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Login" /></td>
            <td><input type="button" value="Forgot Password" /></td>
        </tr>
    </table>
</form>
<h1> Register </h1>   <font color="red">${status}</font>
<form method="post" action="/user/register">
    <table border=0>
        <tr>
            <td>Full Name </td>
            <td>: <input type="text" name="name" /></td>
        </tr>
        <tr>
            <td>E-mail </td>
            <td>: <input type="test" name="email" /></td>
        </tr>
        <tr>
            <td>Password </td>
            <td>: <input type="password" name="password" /></td>
        </tr>
        <tr>
                    <td>Re-type Password </td>
                    <td>: <input type="password" name="confirmpassword" /></td>
        </tr>

        <tr>
            <td><input type="submit" value="Register" /></td>
            <td><input type="reset" value="Clear" /></td>
        </tr>
    </table>
</form>
</body>
</html>