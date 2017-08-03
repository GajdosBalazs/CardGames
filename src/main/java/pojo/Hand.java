package pojo;
import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cardsInHand = new ArrayList<>();

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(ArrayList<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }
    
    public void dealer(Deck deck,int handSize) {
        //handSize is the number of cards the hand can contain

        //New ArrayList to store the cards
        ArrayList<Card> hand = new ArrayList<>();
        
        //Prevent requesting more cards then the deck contains
        if(handSize>deck.getCardsInDeck().size()){
            handSize=deck.getCardsInDeck().size();
        }
        
        //Deal x random unique cards from the deck to the hand
        for (int i = 0; i < handSize; i++) {
            
            //Generate random numbers
            int k = (int) (Math.random() * deck.getCardsInDeck().size());
            
            //If the random card is already in the hand, generate new number.
            while (deck.getCardsInDeck().get(k).isOutOfDeck()) {
                k = (int) (Math.random() * deck.getCardsInDeck().size());
            }
            
            //Sign that the card is in the hand and out of the deck, preventing dealing it again
            deck.getCardsInDeck().get(k).setOutOfDeck(true);
            
            //Add the card into the hand
            this.cardsInHand.add(deck.getCardsInDeck().get(k));
        }
    }
}
