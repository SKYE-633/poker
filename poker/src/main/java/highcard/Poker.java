package main.java.highcard;

import java.util.*;

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

        String result = null;
        List<Integer> cardsValue1 = sortCard(cards1);
        List<Integer> cardsValue2 = sortCard(cards2);

        Set<Integer> set1 = new HashSet<>(cardsValue1);
        Set<Integer> set2 = new HashSet<>(cardsValue2);

        result = checkPair(set1, set2);
        if (result != null) return result;

        result = compareCardList(cardsValue1, cardsValue2);
        if (result != null) return result;
        return "draw";
    }

    private String compareCardList(List<Integer> cardsValue1, List<Integer> cardsValue2) {
        for(int i = 0; i < cardsValue1.size(); i++){
            String result = compareCard(cardsValue1.get(i), cardsValue2.get(i));
            if(result.equals("draw")){
                continue;
            }
            return result;
        }
        return null;
    }

    private String checkPair(Set<Integer> set1, Set<Integer> set2) {
        if(set1.size() < set2.size()){
            return "winner1";
        }
        if(set2.size() < set1.size()){
            return "winner2";
        }
        return null;
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
