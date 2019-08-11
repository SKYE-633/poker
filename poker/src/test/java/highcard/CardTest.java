package test.java.highcard;

import main.java.highcard.Card;
import main.java.highcard.Poker;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CardTest {
    @Test
    public void should_return_winner2_when_give_two_cards(){
        //given
        Poker poker = new Poker();
        //when
        String result = poker.compare(Arrays.asList(new Card("3C")),Arrays.asList(new Card("4C")));
        //then
        Assert.assertEquals("winner2",result);
    }
    @Test
    public void should_return_winner2_when_give_two_cardList(){
        //given
        Poker poker = new Poker();
        List<Card> cards1 = Arrays.asList(new Card("2C"),new Card("3C"),new Card("5H"),new Card("7H"),new Card("8C"));
        List<Card> cards2 = Arrays.asList(new Card("2D"),new Card("4D"),new Card("9S"),new Card("JC"),new Card("QC"));
        //when
        String result = poker.compare(cards1,cards2);
        //then
        Assert.assertEquals("winner2",result);
    }

}
