package main.java.highcard;

public class Card {
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    private String num;

    public Card(String card){
        this.num = card.substring(0,1);
    }

}
