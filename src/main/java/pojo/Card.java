package pojo;

public class Card {

    //count variable is to count how many of that kind of card (checking only the number not the color) is in the hand.
    private int count;
    //the number of the card - String becouse it can be Jack/Queen/King or Ace
    private String number;
    //value1 is the variable that shows the original value of the card
    private int value1;
    //value2 equals value1 except in case of Ace. In case of Ace it's one lower than card 2.
    private int value2;
    //the color of the card
    private String color;
    //checked variable is used when counting each card's number in a hand. It prevents counting a card twice
    private boolean checked;
    //shows if the card is still in the deck or already in a hand
    private boolean outOfDeck = false;
    //the source of the image file of the card
    private String image;
    //
    final static String IMAGESOURCE = "resources/img/cards/";
    //
    final static String IMAGEEXTENSION = ".png";

    public Card(String color, String number, int value1, int value2, String image) {
        this.color = color;
        this.number = number;
        this.value1 = value1;
        this.value2 = value2;
        this.image = image;
    }

    public Card(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Card [number=" + number + ", value1=" + value1 + ", value2=" + value2 + ", color=" + color
                + ", checked=" + checked + ", outOfDeck=" + outOfDeck + ", count=" + count + "]";
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getValue1() {
        return value1;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isOutOfDeck() {
        return outOfDeck;
    }

    public void setOutOfDeck(boolean outOfDeck) {
        this.outOfDeck = outOfDeck;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
