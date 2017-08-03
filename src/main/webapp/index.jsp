<%-- 
    Document   : index
    Created on : Aug 2, 2017, 4:39:48 PM
    Author     : balazs.gajdos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            html, body{
                height: 100%;
                width: 100%;
                background-image: url(resources/img/home_page.jpg);
                background-size: 100% 100%;
                background-attachment: fixed;
            }
            .container{
                height: inherit;
                display: flex;
                flex-direction: row;
                justify-content: space-around;
                align-items: center;
            }
            .elem{
                display: flex;
                flex-direction: row;
                justify-content: space-around;
                align-items: center;

                border-radius: 2em ;
                width: 300px;
                height: 300px;
                background-size: 100% 100%;
            }
            span{
                color:honeydew;
                font-size: 30px;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <a style="text-decoration: none" href="hand_tutorial.jsp">
                <div class="elem" style="background-image: url(resources/img/background.jpg)">
                    <span>Hand Tutorial</span>
                </div>
            </a>
            <a style="text-decoration: none" href="blackjack.jsp">
                <div class="elem" style="background-image: url(resources/img/poker_table.png)">
                    <span>Black Jack</span>
                </div>
            </a>
        </div>
    </body>
</html>
