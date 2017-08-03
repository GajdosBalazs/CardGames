/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games;

import pojo.*;

/**
 *
 * @author balazs.gajdos
 */
public class test {

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.fillCardsInDeck(1);

        Hand hand = new Hand();
        hand.dealer(deck, 5);
        
        for(Card card:deck.getCardsInDeck()){
            System.out.println(card.toString());
        }
        
        for(Card card:hand.getCardsInHand()){
            System.out.println(card.toString());
        }
    }
}
