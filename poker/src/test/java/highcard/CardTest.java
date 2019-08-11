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
    @Test
    public void should_return_draw_when_give_two_cardList() {
        //given
        Poker poker = new Poker();
        List<Card> cards1 = Arrays.asList(new Card("2C"), new Card("3C"), new Card("QH"), new Card("7H"), new Card("8C"));
        List<Card> cards2 = Arrays.asList(new Card("2D"), new Card("3D"), new Card("QS"), new Card("7C"), new Card("8H"));
        //when
        String result = poker.compare(cards1, cards2);
        //then
        Assert.assertEquals("draw", result);
    }
    @Test
    public void should_return_winner1_when_have_pair() {
        //given
        Poker poker = new Poker();
        List<Card> cards1 = Arrays.asList(new Card("QC"), new Card("3C"), new Card("3H"), new Card("7H"), new Card("8C"));
        List<Card> cards2 = Arrays.asList(new Card("2D"), new Card("3D"), new Card("QS"), new Card("7C"), new Card("8H"));
        //when
        String result = poker.compare(cards1, cards2);
        //then
        Assert.assertEquals("winner1", result);
    }
    @Test
    public void should_return_winner2_when_both_have_pair(){
        //given
        Poker poker = new Poker();
        List<Card> cards1 = Arrays.asList(new Card("QC"), new Card("3C"), new Card("3H"), new Card("5H"), new Card("8C"));
        List<Card> cards2 = Arrays.asList(new Card("2D"), new Card("3D"), new Card("QS"), new Card("7C"), new Card("7H"));
        //when
        String result = poker.compare(cards1, cards2);
        //then
        Assert.assertEquals("winner2", result);
    }
    @Test
    public void should_return_winner2_when_one_have_two_pair_and_the_other_have_one(){
        //given
        Poker poker = new Poker();
        List<Card> cards1 = Arrays.asList(new Card("QC"), new Card("3C"), new Card("3H"), new Card("5H"), new Card("8C"));
        List<Card> cards2 = Arrays.asList(new Card("2D"), new Card("QD"), new Card("QS"), new Card("7C"), new Card("7H"));
        //when
        String result = poker.compare(cards1, cards2);
        //then
        Assert.assertEquals("winner2", result);
    }
    @Test
    public void should_return_winner1_when_both_have_two_pair() {
        //given
        Poker poker = new Poker();
        List<Card> cards1 = Arrays.asList(new Card("QC"), new Card("QH"), new Card("3H"), new Card("7H"), new Card("7C"));
        List<Card> cards2 = Arrays.asList(new Card("2D"), new Card("QD"), new Card("QS"), new Card("5S"), new Card("5D"));
        //when
        String result = poker.compare(cards1, cards2);
        //then
        Assert.assertEquals("winner1", result);
    }
    @Test
    public void should_return_winner1_when_have_two_same_pair(){
        //given
        Poker poker = new Poker();
        List<Card> cards1 = Arrays.asList(new Card("QC"), new Card("QH"), new Card("3H"), new Card("7H"), new Card("7C"));
        List<Card> cards2 = Arrays.asList(new Card("2D"), new Card("QD"), new Card("QS"), new Card("7S"), new Card("7D"));
        //when
        String result = poker.compare(cards1, cards2);
        //then
        Assert.assertEquals("winner1", result);
    }
    @Test
    public void should_return_winner1_when_one_have_three_pair_the_other_have_two_pairs(){
        //given
        Poker poker = new Poker();
        List<Card> cards1 = Arrays.asList(new Card("QC"), new Card("4H"), new Card("7H"), new Card("7S"), new Card("7C"));
        List<Card> cards2 = Arrays.asList(new Card("2D"), new Card("QD"), new Card("QS"), new Card("7S"), new Card("7D"));
        //when
        String result = poker.compare(cards1, cards2);
        //then
        Assert.assertEquals("winner1", result);
    }
    @Test
    public void should_return_winner2_when_both_have_three_pair(){
        //given
        Poker poker = new Poker();
        List<Card> cards1 = Arrays.asList(new Card("QC"), new Card("4H"), new Card("7H"), new Card("7S"), new Card("7C"));
        List<Card> cards2 = Arrays.asList(new Card("2D"), new Card("QD"), new Card("QS"), new Card("QH"), new Card("7D"));
        //when
        String result = poker.compare(cards1, cards2);
        //then
        Assert.assertEquals("winner2", result);
    }
    @Test
    public void should_return_winner1_when_one_have_three_pair_the_other_have_three_and_two_pair(){
        //given
        Poker poker = new Poker();
        List<Card> cards1 = Arrays.asList(new Card("3C"), new Card("3H"), new Card("7H"), new Card("7S"), new Card("7C"));
        List<Card> cards2 = Arrays.asList(new Card("2D"), new Card("QD"), new Card("QS"), new Card("QH"), new Card("7D"));
        //when
        String result = poker.compare(cards1, cards2);
        //then
        Assert.assertEquals("winner1", result);
    }
    @Test
    public void should_return_winner2_when_both_have_three_pair_and_two_pair(){
        //given
        Poker poker = new Poker();
        List<Card> cards1 = Arrays.asList(new Card("3C"), new Card("3H"), new Card("7H"), new Card("7S"), new Card("7C"));
        List<Card> cards2 = Arrays.asList(new Card("QH"), new Card("QD"), new Card("QS"), new Card("5H"), new Card("5D"));
        //when
        String result = poker.compare(cards1, cards2);
        //then
        Assert.assertEquals("winner2", result);
    }
    @Test
    public void should_return_winner2_when_one_have_four_the_other_have_three_pair_and_two_pair(){
        //given
        Poker poker = new Poker();
        List<Card> cards1 = Arrays.asList(new Card("3C"), new Card("3H"), new Card("7H"), new Card("7S"), new Card("7C"));
        List<Card> cards2 = Arrays.asList(new Card("QH"), new Card("QD"), new Card("QS"), new Card("QC"), new Card("5D"));
        //when
        String result = poker.compare(cards1, cards2);
        //then
        Assert.assertEquals("winner2", result);
    }
    @Test
    public void should_return_winner2_when_both_have_four_pair(){
        //given
        Poker poker = new Poker();
        List<Card> cards1 = Arrays.asList(new Card("3C"), new Card("7H"), new Card("7D"), new Card("7S"), new Card("7C"));
        List<Card> cards2 = Arrays.asList(new Card("QH"), new Card("QD"), new Card("QS"), new Card("QC"), new Card("5D"));
        //when
        String result = poker.compare(cards1, cards2);
        //then
        Assert.assertEquals("winner2", result);
    }

}
