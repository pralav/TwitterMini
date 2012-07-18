<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty sessionScope.userName}">
    <script type="text/javascript">
        window.location = "/todo"
    </script>
</c:if>

<c:if test="${not empty message}">
    ${message}</br>
</c:if>

Login:  ${status}
<form action="/user/login" method="post">
    E-mail: <input type="text" name="email"></br>
    Password: <input type="password" name="password"></br>
    <input type="submit">
</form>
