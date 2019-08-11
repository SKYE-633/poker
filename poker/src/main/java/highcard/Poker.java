package main.java.highcard;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

public class Poker {
    private static Map<String, Integer> cardMap = null;

    public Poker(){
        this.cardMap = new HashMap<String, Integer>(){{
            put("T",10);
            put("J",11);
            put("Q",12);
            put("K",13);
            put("A",14);
        }};
    }

    private List<Integer> sortCard(List<Card> cards){
        return cards.stream().map(card -> mapValue(card.getNum())).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }
    public String compare(List<Card> cards1, List<Card> cards2) {
        List<Integer> cardsValue1 = sortCard(cards1);
        List<Integer> cardsValue2 = sortCard(cards2);
        for(int i = 0; i < cardsValue1.size(); i++){
            String result = compareCard(cardsValue1.get(i), cardsValue2.get(i));
            if(result.equals("draw")){
                continue;
            }
            return result;
        }
        return "draw";
    }
    public String compareCard(Integer value1, Integer value2){
        if(value1 > value2){
            return "winner1";
        }
        if(value1 < value2){
            return "winner2";
        }
        return "draw";
    }

    private int mapValue(String cardNum) {
        if(cardNum.compareTo("2")>=0 && cardNum.compareTo("9")<=0){
            return Integer.parseInt(cardNum);
        }
        return this.cardMap.get(cardNum);
    }
}
