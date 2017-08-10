<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="games.BlackJack" %>
<%@page import="pojo.*" %>
<html>
    <head>
        <!--        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->

        <script src="resources/js/maxcdn.bootstrapcdn.com_bootstrap_3.3.7_js_bootstrap.min.js" type="text/javascript"></script>
        <script src="resources/js/ajax.googleapis.com_ajax_libs_jquery_3.2.1_jquery.min.js" type="text/javascript"></script>
        <link href="resources/css/maxcdn.bootstrapcdn.com_bootstrap_3.3.7_css_bootstrap.min.css" rel="stylesheet" type="text/css"/>

        <title>BlackJack</title>
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
            .card{
                background-color: white;
                border: 1px solid;
                border-radius: 5%;
                margin: 10px;
                height: 15%;
            }
            .dealer{
                width: 25%;
                background-color: #c0a16b;
                border-radius: 5em;
            }
            .row{
                display: flex;
                align-items: center;
            }
            .row1{
                height:15%;
                margin-bottom: 30px;
            }
            .row2{
                margin-bottom: 15px;
            }
            .row3{
                min-height: 115px;
                margin-bottom: 15px;
                font-size: 20px;
                padding-bottom: 25px;
            }
            .result{
                
            }
            .row4{
                margin-bottom: 80px;
            }
            .row5{

            }
        </style>
    </head>
    <body>

        <!--If the game has started, but dealer/player hand was not created yet,
                the deck/two hands/BlackJack class (this contains game logic) are instantiated.
            Then the deck is filled by cards, and 2-2 cards are given to each hand.-->
        <c:if test="${gameStatus eq 'started' && dealerHand eq null}">

            <jsp:useBean id="deck" scope="session" class="pojo.Deck"/>
            <jsp:useBean id="dealerHand" scope="session" class="pojo.Hand"/>
            <jsp:useBean id="playerHand" scope="session" class="pojo.Hand"/>
            <jsp:useBean id="blackJack" scope="session" class="games.BlackJack"/>

            ${deck.fillCardsInDeck(1,"blackJack")}
            ${dealerHand.dealer(deck, 2)}
            ${playerHand.dealer(deck, 2)}

        </c:if>

        <!--After the player pushes the "Add card" button, and while the player has less then 11
            cards, the player hand gets one more card.-->
        <c:if test="${addCard eq 'true' && playerHand.cardsInHand.size()<11}">
            ${playerHand.deal(deck, 1)}
        </c:if>

        <!--After the player pushes the "Showdown" button, and if the dealer hand's value is less
                then 17, the dealer hand gets one more card.-->
        <c:if test="${showCards eq 'true' && blackJack.evalueateHand(dealerHand)<17}">
            ${dealerHand.deal(deck, 1)}
        </c:if>

        <!--When the game starts (after the user pushes the "Start game" button), two variables are created,
                1-1 for each hand, to store the hands' values.-->
        <c:if test="${gameStatus eq 'started'}">
            <c:set var="playerHandForServlet" value="${blackJack.evalueateHand(playerHand)}" scope="session"/>
            <c:set var="dealerHandForServlet" value="${blackJack.evalueateHand(dealerHand)}" scope="session"/>
        </c:if>

        <div class="container">
            <div class="row row1">
                <div class="col-sm-4">
                    <form method="POST" action="BlackJack">
                        <!--Button disappears if the "Start game" button is pushed-->
                        <input type="submit" name="startGame" value="Start game" ${gameStatus eq 'started' ? 'Style="display: none"' : ''}/>
                        <!--Button appears if the "Start game" button is pushed-->
                        <input type="submit" name="endGame" value="End game" ${gameStatus ne 'started' ? 'Style="display: none"' : ''}/>
                    </form>
                </div>
                <div class="col-sm-4">
                    <img class="dealer" src="resources/img/dealer_icon.png" alt=""/>
                </div>
                <div class="col-sm-4">
                    <form method="POST" action="BlackJack">
                        <!--Returns the player to the index page.-->
                        <input type="submit" name="exitGame" value="Exit game"/>
                    </form>
                </div>
            </div>
            <div class="row row2">
                <div class="col-sm-12">
                    <!--Shows the cards of the dealer.
                            If the "Showdown" button was not pushed, only the first card of the dealer is shown.-->
                    <c:if test="${gameStatus eq 'started' && showCards ne 'true'}">
                        <img class="card" src="${dealerHand.cardsInHand.get(0).getImage()}"/>
                        <img class="card" src="${dealerHand.cardsInHand.get(1).getIMAGEBACKSOURCE()}"/>
                    </c:if>
                    <!--If the "Showdown" button was pushed, every card in the dealer's hand is shown.-->
                    <c:if test="${gameStatus eq 'started' && showCards eq 'true'}">
                        <c:forEach items="${dealerHand.cardsInHand}" var="card">
                            <img class="card" src="${card.getImage()}"/>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div class="row row3 align-items-center">
                <div class="col-sm-12">
                    <!--Shows the current result of the game.
                        If the game is already started, and the "Showdown" button was not pushed, it shows
                        only the value of the player's hand.-->
                    <c:if test="${gameStatus eq 'started' && showCards ne 'true'}">
                        <br>
                        ${playerHandForServlet}
                        vs
                        ?
                    </c:if>
                    <!--If the game is already started, and the "Showdown" button was pushed, it shows
                        the value of both hands, furthermore, it shows if the player won or not.-->
                    <c:if test="${gameStatus eq 'started' && showCards eq 'true'}">
                        <br>
                        <span style="color:${playerWin ?'green':'red'}">${playerHandForServlet}</span>
                        vs
                        <span style="color:${not playerWin ?'green':'red'}">${showCards eq 'true'? dealerHandForServlet:'?'}</span>
                        <br>
                        <span style="color:${playerWin ?'green':'red'}">${playerWin ?'Win':'Lost'}</span>
                    </c:if>                    
                </div>
            </div>
            <div class="row row4">
                <div class="col-sm-12">
                    <!--If the game has started, it shows the cards of the player's hand-->
                    <c:if test="${gameStatus eq 'started'}">
                        <c:forEach items="${playerHand.cardsInHand}" var="card">
                            <img class="card" src="${card.getImage()}"/>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div class="row row5">
                <div class="col-sm-12">
                    <form method="POST" action="BlackJack">
                        <!--Pushing this button adds an extra card to the player, if he has less then 11 cards-->
                        <input type="submit" name="addCard" value="Add card" ${(gameStatus ne 'started' ||
                                                                               showCards eq 'true' ||
                                                                               playerHandForServlet>=21) ? 'Style="display: none"' : ''}/>
                        <!--Pushing this button ends the action phase, both player and dealer shows their cards. The dealer still
                            has option to get one more card if his hand's value is less then 17-->
                        <input type="submit" name="show" value="Showdown" ${(gameStatus ne 'started' || showCards eq 'true') ? 'Style="display: none"' : ''}/>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>