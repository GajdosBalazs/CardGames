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
                background-image: url(resources/img/background.jpg);
            }

            .card{
                background-color: white;
                border: 1px solid;
                border-radius: 5%;
                margin: 10px;
                height: 100px;
            }
            .middle{
                background-color: rgba(100, 100, 100, 0.6);
                text-align: center;
                width: 30%;
                margin: 10px auto;
                padding: 20px;
                
            }
        </style>
    </head>
    <body>
        <form class="middle">
            
            <input  type="submit" value="Generate" name="btn"/>
        </form>

        <jsp:useBean id="hand" scope="session" class="pojo.Game"/>

        <c:if var="e" test="${param.btn eq 'Generate'}">
            <div class="middle">
                ${hand.k()}
            </div>
        </c:if>
    </body>
</html>