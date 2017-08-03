package pojo;

import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cardsInDeck = new ArrayList<>();
    //The Deck object can contain more then 1 decks. Based on this variable, the deck contains x*52 cards.
    private int nrOfDecks;

    public ArrayList<Card> getCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(ArrayList<Card> cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }

    public int getNrOfDecks() {
        return nrOfDecks;
    }

    public void setNrOfDecks(int nrOfDecks) {
        this.nrOfDecks = nrOfDecks;
    }

    // Generates the to be used deck (it may contain more then 1 deck). Fills the cardsInDeck ArrayList with cards.
    public void fillCardsInDeck(int nrOfDecks) {

        //Generates cards based on the number of decks (given as parameter) * 4 color * 13 number (2 to Ace)
        // number of decks
        for (int i = 0; i < nrOfDecks; i++) {
            // number of colors
            for (int h = 0; h < 4; h++) {
                // number of numbers
                for (int j = 0; j < 13; j++) {
                    String color;
                    int value2;
                    String number;
                    String image;

                    // Initialize number
                    if (j < 9) {
                        number = j + 2 + "";
                    } else {
                        switch (j) {
                            case 9:
                                number = "Jack";
                                break;
                            case 10:
                                number = "Queen";
                                break;
                            case 11:
                                number = "King";
                                break;
                            case 12:
                                number = "Ace";
                                break;
                            default:
                                number = "Error";
                        }
                    }

                    // Initialize value2 -> it equals to the value1 except in case of Ace
                    value2 = j == 12 ? 1 : j + 2;

                    //Assign colors
                    switch (h) {
                        case 0:
                            color = "Hearts";
                            break;
                        case 1:
                            color = "Diamonds";
                            break;
                        case 2:
                            color = "Clubs";
                            break;
                        case 3:
                            color = "Spades";
                            break;
                        default:
                            color = "Error";
                    }
                    
                    //Assign the source of the to be created card's image's source to the variable
                    image = Card.IMAGESOURCE + color + " " + number + Card.IMAGEEXTENSION;

                    //Generate card and add it to the cardsInDeck (given in the parameter) ArrayList
                    this.cardsInDeck.add(new Card(color, number, j + 2, value2, image));
                }
            }
        }
    }
}
