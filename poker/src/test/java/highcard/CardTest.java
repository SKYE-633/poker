package test.java.highcard;

import main.java.highcard.Poker;
import org.junit.Assert;
import org.junit.Test;

public class CardTest {
    @Test
    public void should_return_winner2_when_give_two_cards(){
        //given
        Poker poker = new Poker();
        //when
        String result = poker.compare("3C","4C");
        //then
        Assert.assertEquals("winner2",result);
    }

}
