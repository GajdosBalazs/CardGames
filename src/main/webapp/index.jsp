<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pojo.Game" %>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            body{
/*                background-image: url(resources/img/home_page.jpg);*/
                background-size:100% auto;
             }
        </style>
    </head>
    <body>
        <form>
            <input type="submit" value="test" name="btn"/>
        </form>
        
        <c:if var="e" test="${param.btn eq 'test'}">
            <%= pojo.Game.k() %>
            
          
        </c:if>
    </body>
</html>