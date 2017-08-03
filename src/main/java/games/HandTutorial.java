package games;

import java.util.ArrayList;
import java.util.Comparator;
import pojo.*;

public class HandTutorial {
    
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

    public String runHandTutorial(String runUntil, int nrDecks) {
        int nrDeal = 1;
        boolean end;
        String result = "";

        do {
            String yourHand = "Your hand is: ";
            String cards = "";
            Deck deck = new Deck();
            deck.fillCardsInDeck(nrDecks);
            Hand hand = new Hand();
            hand.dealer(deck, 5);
            for (Card card : hand.getCardsInHand()) {
                yourHand += card.getColor() + " " + card.getNumber() + "|";
                cards += "<img class=\"card\" src=\"" + card.getImage() + "\"/>";
            }
            switch (runUntil) {
                case "e":
                    end = false;
                    break;
                case "1":
                    end = isOnePair(hand.getCardsInHand()).equals("");
                    break;
                case "2":
                    end = isTwoPair(hand.getCardsInHand()).equals("");
                    break;
                case "3":
                    end = isSet(hand.getCardsInHand()).equals("");
                    break;
                case "4":
                    end = isStraight(hand.getCardsInHand()).equals("");
                    break;
                case "5":
                    end = isFlush(hand.getCardsInHand()).equals("");
                    break;
                case "6":
                    end = isFullHouse(hand.getCardsInHand()).equals("");
                    break;
                case "7":
                    end = isStraightFlush(hand.getCardsInHand()).equals("");
                    break;
                case "8":
                    end = isPoker(hand.getCardsInHand()).equals("");
                    break;
                case "9":
                    end = isRoyalFlush(hand.getCardsInHand()).equals("");
                    break;
                default:
                    end = false;
            }
            result = yourHand + "<br>" + "Best combination: " + handCheck(hand.getCardsInHand()) + "<br>" + "Number of deals: " + nrDeal + "<br>" + cards + "<br>" + result;
            ++nrDeal;
        } while (end);
        return result;
    }
}
