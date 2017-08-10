package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojo.*;

public class BlackJack extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //Generate session
        HttpSession session = request.getSession();

        //If player pushes "Exit game" button, session gets deleted,
        //and the player is redirected to the "index.jsp" page
        if (request.getParameter("exitGame") != null) {
            session.invalidate();
            response.sendRedirect("index.jsp");
        } else {
            if (request.getParameter("startGame") != null) {
                request.setAttribute("gameStatus", "started");
            } else if (request.getParameter("endGame") != null) {
                request.setAttribute("gameStatus", "ended");
                session.invalidate();
            } else if (request.getParameter("addCard") != null) {
                request.setAttribute("gameStatus", "started");
                request.setAttribute("addCard", "true");
                request.setAttribute("showCards", "false");
            } else 
              //If player pushes the "Showdown" button, the servlet pulls the hands' values,
              //and compares them. Eventually responds with a boolean valriable, telling 
              //if the player won or not.
                if (request.getParameter("show") != null) {
                request.setAttribute("gameStatus", "started");
                request.setAttribute("addCard", "false");
                request.setAttribute("showCards", "true");
                int playerHand = (Integer) session.getAttribute("playerHandForServlet");
                int dealerHand = (Integer) session.getAttribute("dealerHandForServlet");
                boolean playerWin = playerHand <= 21 && playerHand > dealerHand;
                request.setAttribute("playerWin", playerWin);
            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/blackjack.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public int evalueateHand(Hand hand) {
        int sumValue1 = 0;
        int sumValue2 = 0;
        int bestValue = 0;

        for (Card card : hand.getCardsInHand()) {
            sumValue1 += card.getValue1();
            System.out.println("val1: " + card.getValue1());
        }

        for (Card card : hand.getCardsInHand()) {
            sumValue2 += card.getValue2();
            System.out.println("val2: " + card.getValue2());
        }

        if (sumValue1 - 21 > sumValue2 - 21 && sumValue1 - 21 <= 0) {
            bestValue = sumValue1;
        } else {
            bestValue = sumValue2;
        }
        return bestValue;
    }

    public void addNewCard(Hand hand, Deck deck) {
        hand.deal(deck, 1);
    }
}
