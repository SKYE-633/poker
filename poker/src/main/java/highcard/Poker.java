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
        return compareCardIntegerList(cardsValue1, cardsValue2, 5);
    }

    private String compareCardIntegerList(List<Integer> cardsValue1, List<Integer> cardsValue2, int cardSize) {
        String result = null;
        if (cardSize == 0) {
            return "draw";
        }

        Map<Integer, Integer> cardMap1 = changeToMap(cardsValue1);
        Map<Integer, Integer> cardMap2 = changeToMap(cardsValue2);

        if (cardMap1.size() == cardMap2.size() && cardMap1.size() == 4) {
            result = comparePair(cardMap1, cardMap2);
            if (result != null) return result;
            Integer pair = getPairKey(cardMap1);
            cardsValue1 = cardsValue1.stream().filter(card -> card != pair).collect(Collectors.toList());
            cardsValue2 = cardsValue2.stream().filter(card -> card != pair).collect(Collectors.toList());
            compareCardIntegerList(cardsValue1, cardsValue2, 3);
        }
        if (cardMap1.size() < cardMap2.size()) {
            return "winner1";
        }
        if (cardMap2.size() < cardMap1.size()) {
            return "winner2";
        }
        return "draw";
    }

    private String comparePair(Map<Integer, Integer> pairMap1, Map<Integer, Integer> pairMap2) {
        Integer pair1 = 0;
        Integer pair2 = 0;
        pair1 = getPairKey(pairMap1);
        pair2 = getPairKey(pairMap2);
        if(pair1 > pair2) return "winner1";
        if(pair1 < pair2) return  "winner2";
        return null;
    }

    private Integer getPairKey(Map<Integer, Integer> pairMap) {
        Integer pair1 = null;
        for (Integer key : pairMap.keySet()) {
            if (pairMap.get(key) == 2) {
                pair1 = key;
                break;
            }
        }
        return pair1;
    }
    public Map<Integer,Integer> changeToMap(List<Integer> cardList) {

        Map<Integer,Integer> pairMap = new HashMap<>();
        for(Integer integer : cardList){
            if(pairMap.containsKey(integer)){
                pairMap.put(integer,pairMap.get(integer)+1);
            }else {
                pairMap.put(integer, 1);
            }
        }
        return pairMap;
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
