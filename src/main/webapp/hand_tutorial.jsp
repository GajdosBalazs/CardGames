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
                background-size: 100% 100%;
                background-attachment: fixed;
                text-align: center;
            }

            .card{
                background-color: white;
                border: 1px solid;
                border-radius: 5%;
                margin: 10px;
                height: 15%;
            }
            .middle{
                background-color: rgba(100, 100, 100, 0.6);
                text-align: center;
                width: 40%;
                margin: 10px auto;
                padding: 20px;
            }
            .middle-table{
                margin: 0 auto;
                text-align: center;
            }
            .middle-elem{
                width: 75%;
                margin-bottom: 10px;
            }
            .font{
                color: honeydew;
            }
            input{
                color: black;
            }
        </style>
    </head>
    <body>
        <form class="middle" method="POST">
            <table class="middle-table">
                <tr>
                    <td class="font">Deal hands until:</td>
                    <td><select class="middle-elem" name="runUntil">
                            <option value="e">Once</option>
                            <option value="1">One Pair</option>
                            <option value="2">Two Pairs</option>
                            <option value="3">Drill</option>
                            <option value="4">Straight</option>
                            <option value="5">Flush</option>
                            <option value="6">Full House</option>
                            <option value="7">Straight Flush</option>
                            <option value="8">Poker</option>
                            <option value="9">Royal Flush</option>
                        </select></td>
                </tr>
                <tr>
                    <td class="font">Number of decks to use:</td>
                    <td><input class="middle-elem" type="number" name="nrDecks" value="1"/></td>
                </tr>
            </table>
            <input type="submit" value="Generate" name="generate"/>
            <a style="text-decoration: none" href="index.jsp">
                <input type="button" value="Back" name="back"/>
            </a>
        </form>

        <jsp:useBean id="hand" scope="session" class="pojo.Game"/>

        <c:if var="e" test="${param.generate eq 'Generate'}">
            <div class="font middle">
                ${hand.k(param.runUntil,param.nrDecks)}
            </div>
        </c:if>
    </body>
</html>