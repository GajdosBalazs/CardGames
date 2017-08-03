<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="games.HandTutorial" %>
<%@page import="pojo.*" %>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            html,body{
                height:100%;
                width: 100%;
            }
            body{
                background-image: url(resources/img/poker_table.png);
                background-size: 100% 100%;
                background-attachment: fixed;
                text-align: center;
            }
            form{
                width: 10px;
                height: 0px;
            }
            .card{
                background-color: white;
                border: 1px solid;
                border-radius: 5%;
                margin: 10px;
                height: 15%;
            }
            .container{
                height: inherit;
                width: inherit;
                display:flex;
                flex-direction: column;
                align-content:  center;
                justify-content: center;
            }
            div.row:nth-of-type(1) {flex-grow: 1;}
            div.row:nth-of-type(2) {flex-grow: 3;}
            div.row:nth-of-type(3) {flex-grow: 1;}
            div.col:nth-of-type(1) {flex-grow: 1;}
            div.col:nth-of-type(2) {flex-grow: 3;}
            div.col:nth-of-type(3) {flex-grow: 1;}
            .row{
                display:flex;
                flex-direction: row;
                align-content:  center;
                justify-content: center;
            }
            .col{
                flex-grow: 1;
                align-content:  center;
                justify-content: center;
            }
            .container,.row,.col{
                border-style: dotted;
            }
            form{
                border-style: dotted;
            }
        </style>
    <body>
        <c:if test="${param.start ne 'Start' || param.newGame eq 'New Game'}">
            <c:set scope="session" var="gameStarted" value="false"/>
        </c:if>

        <c:if test="${param.start eq 'Start'}">
            <c:set scope="session" var="gameStarted" value="true"/>

            <jsp:useBean id="deck" scope="session" class="pojo.Deck"/>
            <jsp:useBean id="dealerHand" scope="session" class="pojo.Hand"/>
            <jsp:useBean id="playerHand" scope="session" class="pojo.Hand"/>

            ${deck.fillCardsInDeck(1)}
        </c:if>

        <div class="container">
            <div class="row">
                <div class="col">
                    <form method="POST">
                        <input ${gameStarted eq 'true' ? '' : 'style="display:none"'} type="submit" value="New Game" name="newGame"/>
                    </form>
                </div>
                <div class="col">
                    <form method="POST">
                        <input ${gameStarted eq 'false' ? '' : 'style="display:none"'} type="submit" value="Start" name="start"/>
                    </form>
                </div>
                <div class="col">
                    2
                </div>
            </div>
            <div class="row">
                <div class="col">
                    3
                </div>
                <div class="col">
                    4
                </div>
                <div class="col">
                    2
                </div>
            </div>
            <div class="row">
                <div class="col">
                    5
                </div>
                <div class="col">
                    6
                </div>
                <div class="col">
                    2
                </div>
            </div>
        </div>

    </body>