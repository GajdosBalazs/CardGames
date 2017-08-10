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
public class BlackJack {
    
    //Evaluates the black jack hand. Gives different value to Ace based on the current situation
    public int evalueateHand(Hand hand){
        int sumValue1=0;
        int sumValue2=0;
        int bestValue=0;
        
        for(Card card : hand.getCardsInHand()){
            sumValue1 += card.getValue1();
            System.out.println("val1: "+card.getValue1());
        }
        
        for(Card card : hand.getCardsInHand()){
            sumValue2 += card.getValue2();
            System.out.println("val2: "+card.getValue2());
        }
        
        if(sumValue1-21 > sumValue2-21 && sumValue1-21<=0){
            bestValue = sumValue1;
        }else{
            bestValue = sumValue2;
        }
        return bestValue;
    }
}
