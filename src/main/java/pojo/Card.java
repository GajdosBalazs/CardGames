package pojo;

public class Card {

    private int count;
    private String number;
    private int value1;
    private int value2;
    private String color;
    private boolean checked;
    private boolean outOfDeck = false;
    private String image;

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
