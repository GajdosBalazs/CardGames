package pojo;

import java.util.ArrayList;
import java.util.Comparator;
import pojo.Card;

public class Game {

    // Generates the to be used deck (it may contain more then 1 deck)
    public static ArrayList<Card> generateDeck(int nrOfDecks) {
        ArrayList<Card> deck = new ArrayList<Card>();

        // number of decks
        for (int i = 0; i < nrOfDecks; i++) {
            // number of colors
            for (int h = 0; h < 4; h++) {
                // number of cards
                for (int j = 0; j < 13; j++) {
                    String color;
                    int value2;
                    String number;

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

                    // Initialize value2
                    if (j == 12) {
                        value2 = 1;
                    } else {
                        value2 = j + 2;
                    }

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
                    deck.add(new Card(color, number, j + 2, value2, color + " " + number));
                }
            }
        }

        return deck;
    }

    public static ArrayList<Card> dealer(ArrayList<Card> deck) {
        ArrayList<Card> hand = new ArrayList<Card>();
        for (int i = 0; i < 5; i++) {
            int k = (int) (Math.random() * deck.size());
            while (deck.get(k).isOutOfDeck()) {
                k = (int) (Math.random() * deck.size());
            }
            deck.get(k).setOutOfDeck(true);
            hand.add(deck.get(k));
        }
        return hand;
    }

    public static void countCards(ArrayList<Card> hand) {
        for (int i = 0; i < 5; i++) {
            for (int h = 4; h >= 0; h--) {
                if (hand.get(i).getNumber().equals(hand.get(h).getNumber()) & hand.get(h).isChecked() == false) {
                    hand.get(i).setCount(hand.get(i).getCount() + 1);
                    hand.get(h).setChecked(true);
                }
            }
        }

    }

    public static String isRoyalFlush(ArrayList<Card> hand) {
        boolean handWithAce = false;
        boolean handWithKing = false;
        String result = "Royal flush";

        for (Card card : hand) {
            if (card.getValue1() == 14) {
                handWithAce = true;
            } else if (card.getValue1() == 13) {
                handWithKing = true;
            }
        }

        if (!isStraight(hand).equals("") & !isFlush(hand).equals("") & handWithKing & handWithAce) {
            return result;
        } else {
            return "";
        }
    }

    public static String isStraightFlush(ArrayList<Card> hand) {
        String result = " high straight flush";

        Comparator<Card> cmp = new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                if (o1.getValue1() < o2.getValue1()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        ArrayList<Card> sortedHand = new ArrayList<Card>(hand);
        sortedHand.sort(cmp);

        if (!isStraight(hand).equals("") & !isFlush(hand).equals("") & !sortedHand.get(0).getNumber().equals("Ace")) {
            return result = sortedHand.get(0).getNumber() + result;
        } else {
            return "";
        }
    }

    public static String isPoker(ArrayList<Card> hand) {
        int nrPoker = 0;
        String result = "Four of a kind, ";

        countCards(hand);

        for (Card card : hand) {
            if (card.getCount() == 4) {
                nrPoker++;
                result += card.getNumber() + "s";
            }
        }

        if (nrPoker == 1) {
            return result;
        } else {
            return "";
        }
    }

    public static String isFullHouse(ArrayList<Card> hand) {
        int nrPairs = 0;
        int nrSet = 0;
        String pair = "";
        String set = "";
        String result = "Full house, ";

        countCards(hand);

        for (Card card : hand) {
            if (card.getCount() == 2) {
                nrPairs++;
                pair += card.getNumber() + "s";
            } else if (card.getCount() == 3) {
                nrSet++;
                set += card.getNumber() + "s";
            }
        }

        if (nrPairs == 1 & nrSet == 1) {
            return result += set + " over " + pair;
        } else {
            return "";
        }
    }

    public static String isFlush(ArrayList<Card> hand) {
        int i = 0;
        String result = " high flush";

        Comparator<Card> cmp = new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                if (o1.getValue1() < o2.getValue1()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        ArrayList<Card> sortedHand = new ArrayList<Card>(hand);
        sortedHand.sort(cmp);

        for (Card card : hand) {
            if (card.getColor().equals(hand.get(0).getColor())) {
                i++;
            }
        }
        if (i == 5) {
            return result = sortedHand.get(0).getNumber() + result;
        } else {
            return "";
        }

    }

    public static String isStraight(ArrayList<Card> hand) {
        String result = " high straight";

        // Declaration of Comparator (AceTop)
        Comparator<Card> cmp1 = new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                if (o1.getValue1() < o2.getValue1()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        // Declaration of Comparator (AceBottom)
        Comparator<Card> cmp2 = new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                if (o1.getValue2() < o2.getValue2()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        ArrayList<Card> value1 = new ArrayList<Card>(hand);
        ArrayList<Card> value2 = new ArrayList<Card>(hand);

        // Hand is sorted descending (both AceBottom & AceTop)
        value1.sort(cmp1);
        value2.sort(cmp2);

        int k = 0;
        int l = 0;

        for (int i = 0; i < 4; i++) {
            if (value1.get(i).getValue1() - value1.get(i + 1).getValue1() == 1) {
                k++;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (value2.get(i).getValue2() - value2.get(i + 1).getValue2() == 1) {
                l++;
            }
        }

        if (k == 4) {
            return result = value1.get(0).getNumber() + result;
        } else if (l == 4) {
            return result = value2.get(0).getNumber() + result;
        } else {
            return "";
        }
    }

    public static String isSet(ArrayList<Card> hand) {
        int nrPairs = 0;
        int nrSet = 0;
        String result = "Three of a kind, ";

        countCards(hand);

        for (Card card : hand) {
            if (card.getCount() == 2) {
                nrPairs++;
            } else if (card.getCount() == 3) {
                nrSet++;
                result += card.getNumber() + "s";
            }
        }

        if (nrPairs == 0 & nrSet == 1) {
            return result;
        } else {
            return "";
        }
    }

    public static String isTwoPair(ArrayList<Card> hand) {
        int nrPairs = 0;
        String result = "Two pairs, ";

        countCards(hand);

        for (Card card : hand) {
            if (card.getCount() == 2) {
                if (nrPairs == 0) {
                    result += card.getNumber();
                } else {
                    result += "s and " + card.getNumber() + "s";
                }
                nrPairs++;
            }
        }

        if (nrPairs == 2) {
            return result;
        } else {
            return "";
        }
    }

    public static String isOnePair(ArrayList<Card> hand) {
        int nrPairs = 0;
        int nrSet = 0;
        String result = "One pair, ";

        countCards(hand);

        for (Card card : hand) {
            if (card.getCount() == 2) {
                nrPairs++;
                result += card.getNumber();
            } else if (card.getCount() == 3) {
                nrSet++;
            }
        }

        if (nrPairs == 1 & nrSet == 0) {
            return result;
        } else {
            return "";
        }
    }

    public static String handCheck(ArrayList<Card> hand) {
        String result;

        if (!isRoyalFlush(hand).equals("")) {
            result = isRoyalFlush(hand);
        } else if (!isStraightFlush(hand).equals("")) {
            result = isStraightFlush(hand);
        } else if (!isPoker(hand).equals("")) {
            result = isPoker(hand);
        } else if (!isFullHouse(hand).equals("")) {
            result = isFullHouse(hand);
        } else if (!isFlush(hand).equals("")) {
            result = isFlush(hand);
        } else if (!isStraight(hand).equals("")) {
            result = isStraight(hand);
        } else if (!isSet(hand).equals("")) {
            result = isSet(hand);
        } else if (!isTwoPair(hand).equals("")) {
            result = isTwoPair(hand);
        } else if (!isOnePair(hand).equals("")) {
            result = isOnePair(hand);
        } else {
            Comparator<Card> cmp = new Comparator<Card>() {
                @Override
                public int compare(Card o1, Card o2) {
                    if (o1.getValue1() < o2.getValue1()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            };

            ArrayList<Card> sortedHand = new ArrayList<Card>(hand);
            sortedHand.sort(cmp);

            result = "High card, " + sortedHand.get(0).getNumber();
        }

        return result;
    }

//	public static void main(String[] attributes) {
    public String k() {
        int nrHand = 1;
        int nrDecks;
        String runUntil;
        int runUntilNr = 0;
        boolean end = true;

        System.out.println(
                "Hány paklival játsszuk a játékot? (Adjon meg egy tetszőleges, 0-tól eltérő számot és üssön egy enter-t!)");
//		nrDecks = Communicator.getIntData(false);

        nrDecks = 1;
        while (nrDecks == 0) {
            System.out.println("Érvénytelen szám! Próbálja újra!");
//			nrDecks = Communicator.getIntData(false);
        }

//		Communicator.drawLine("-_", 50);
//		System.out.println("Meddig fusson a program?");
//		System.out.println("Csak egyszer (e)");
//		System.out.println("Egy pár (1)");
//		System.out.println("Két pár (2)");
//		System.out.println("Szett (3)");
//		System.out.println("Sor (4)");
//		System.out.println("Flush (5)");
//		System.out.println("Fullhouse (6)");
//		System.out.println("Színsor (7)");
//		System.out.println("Póker (8)");
//		System.out.println("RoyalFlush (9)");
//		System.out.println("Tetszőleges szám (0)");
//		runUntil = Communicator.getStringData("e1234567890", 1, "Hibás parancs! Próbálja újra!",false);
        runUntil = "3";
//        if (runUntil.equals("0")) {
//            System.out.println("Kért kezek száma? (Adjon meg egy tetszőleges, 0-tól eltérő számot!");
//			runUntilNr = Communicator.getIntData(false);
//			while (runUntilNr == 0) {
//				System.out.println("Érvénytelen szám! Próbálja újra!");
//				runUntilNr = Communicator.getIntData(false);
//			}
//        }

//		Communicator.drawLine("-_", 50);
        String result = "";

        do {
            String yourHand = "Your hand is: ";
            String cards = "";
            ArrayList<Card> deck = generateDeck(nrDecks);
            ArrayList<Card> hand = dealer(deck);
            for (Card card : hand) {
                yourHand += card.getColor() + " " + card.getNumber() + "|";
                cards += "<img class=\"card\" src=\"resources/img/cards/" + card.getImage() + ".png\"/>";
            }

            System.out.println(yourHand);
            System.out.println("Best hand: " + handCheck(hand));

            System.out.println(nrHand);

//			Communicator.drawLine("-_", 50);
            switch (runUntil) {
                case "e":
                    end = false;
                    break;
                case "1":
                    end = isOnePair(hand).equals("");
                    break;
                case "2":
                    end = isTwoPair(hand).equals("");
                    break;
                case "3":
                    end = isSet(hand).equals("");
                    break;
                case "4":
                    end = isStraight(hand).equals("");
                    break;
                case "5":
                    end = isFlush(hand).equals("");
                    break;
                case "6":
                    end = isFullHouse(hand).equals("");
                    break;
                case "7":
                    end = isStraightFlush(hand).equals("");
                    break;
                case "8":
                    end = isPoker(hand).equals("");
                    break;
                case "9":
                    end = isRoyalFlush(hand).equals("");
                    break;
                case "0":
                    end = !(nrHand == runUntilNr);
                    break;
                default:
                    end = false;
            }
            
            result = yourHand + "<br>" + "Best hand: " + handCheck(hand) + "<br>" + "Number of hands: " + nrHand + "<br>" + cards + "<br>" + result;
            
            ++nrHand;
        } while (end);
        
        System.out.println(result);
        return result;
    }
}
