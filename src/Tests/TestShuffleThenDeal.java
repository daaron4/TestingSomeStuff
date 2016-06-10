package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import CardModel.Deck;
import CardModel.Player;
import CardModel.Suit;

public class TestShuffleThenDeal {
	@Test
	public void testShuffleThenDeal() {
		Deck aDeck = new Deck();
		aDeck.shuffle();		
		Player Human = new Player("Human");
		Player ComputerOne = new Player("Computer One");
		Player ComputerTwo = new Player("Computer Two");
		Player ComputerThree = new Player("Computer Three");
		assertEquals(52,aDeck.getSize());
		aDeck.deal(Human, ComputerOne, ComputerTwo, ComputerThree);
		assertEquals(13,Human.getHand().size());
		assertEquals(13,ComputerOne.getHand().size());
		assertEquals(13,ComputerTwo.getHand().size());
		assertEquals(13,ComputerThree.getHand().size());
		
		int spadesCounter = 0;
		int clubsCounter = 0;
		int heartsCounter = 0;
		int diamondsCounter = 0;
		
		int aceCounter = 0;
		int deuceCounter = 0;
		int threesCounter = 0;
		int foursCounter = 0;
		int fivesCounter = 0;
		int sixesCounter = 0;
		int sevensCounter = 0;
		int eightsCounter = 0;
		int ninesCounter = 0;
		int tensCounter = 0;
		int jacksCounter = 0;
		int queensCounter = 0;
		int kingsCounter = 0;
		
		for (int i = 0; i < 13; i++) {
			if (Human.getHand().get(i).getSuit()== Suit.Spades)
				spadesCounter++;
			if (Human.getHand().get(i).getSuit()== Suit.Hearts)
				heartsCounter++;
			if (Human.getHand().get(i).getSuit()== Suit.Diamonds)
				diamondsCounter++;
			if (Human.getHand().get(i).getSuit()== Suit.Clubs)
				clubsCounter++;
			
			if (Human.getHand().get(i).getRank().getValue()==14)
				aceCounter++;
			if (Human.getHand().get(i).getRank().getValue()==2)
				deuceCounter++;
			if (Human.getHand().get(i).getRank().getValue()==3)
				threesCounter++;
			if (Human.getHand().get(i).getRank().getValue()==4)
				foursCounter++;
			if (Human.getHand().get(i).getRank().getValue()==5)
				fivesCounter++;
			if (Human.getHand().get(i).getRank().getValue()==6)
				sixesCounter++;
			if (Human.getHand().get(i).getRank().getValue()==7)
				sevensCounter++;
			if (Human.getHand().get(i).getRank().getValue()==8)
				eightsCounter++;
			if (Human.getHand().get(i).getRank().getValue()==9)
				ninesCounter++;
			if (Human.getHand().get(i).getRank().getValue()==10)
				tensCounter++;
			if (Human.getHand().get(i).getRank().getValue()==11)
				jacksCounter++;
			if (Human.getHand().get(i).getRank().getValue()==12)
				queensCounter++;
			if (Human.getHand().get(i).getRank().getValue()==13)
				kingsCounter++;
			
		}
		for (int i = 0; i < 13; i++) {
			if (ComputerOne.getHand().get(i).getSuit()== Suit.Spades)
				spadesCounter++;
			if (ComputerOne.getHand().get(i).getSuit()== Suit.Hearts)
				heartsCounter++;
			if (ComputerOne.getHand().get(i).getSuit()== Suit.Diamonds)
				diamondsCounter++;
			if (ComputerOne.getHand().get(i).getSuit()== Suit.Clubs)
				clubsCounter++;
			
			if (ComputerOne.getHand().get(i).getRank().getValue()==14)
				aceCounter++;
			if (ComputerOne.getHand().get(i).getRank().getValue()==2)
				deuceCounter++;
			if (ComputerOne.getHand().get(i).getRank().getValue()==3)
				threesCounter++;
			if (ComputerOne.getHand().get(i).getRank().getValue()==4)
				foursCounter++;
			if (ComputerOne.getHand().get(i).getRank().getValue()==5)
				fivesCounter++;
			if (ComputerOne.getHand().get(i).getRank().getValue()==6)
				sixesCounter++;
			if (ComputerOne.getHand().get(i).getRank().getValue()==7)
				sevensCounter++;
			if (ComputerOne.getHand().get(i).getRank().getValue()==8)
				eightsCounter++;
			if (ComputerOne.getHand().get(i).getRank().getValue()==9)
				ninesCounter++;
			if (ComputerOne.getHand().get(i).getRank().getValue()==10)
				tensCounter++;
			if (ComputerOne.getHand().get(i).getRank().getValue()==11)
				jacksCounter++;
			if (ComputerOne.getHand().get(i).getRank().getValue()==12)
				queensCounter++;
			if (ComputerOne.getHand().get(i).getRank().getValue()==13)
				kingsCounter++;
			
		}
		for (int i = 0; i < 13; i++) {
			if (ComputerTwo.getHand().get(i).getSuit()== Suit.Spades)
				spadesCounter++;
			if (ComputerTwo.getHand().get(i).getSuit()== Suit.Hearts)
				heartsCounter++;
			if (ComputerTwo.getHand().get(i).getSuit()== Suit.Diamonds)
				diamondsCounter++;
			if (ComputerTwo.getHand().get(i).getSuit()== Suit.Clubs)
				clubsCounter++;
			
			if (ComputerTwo.getHand().get(i).getRank().getValue()==14)
				aceCounter++;
			if (ComputerTwo.getHand().get(i).getRank().getValue()==2)
				deuceCounter++;
			if (ComputerTwo.getHand().get(i).getRank().getValue()==3)
				threesCounter++;
			if (ComputerTwo.getHand().get(i).getRank().getValue()==4)
				foursCounter++;
			if (ComputerTwo.getHand().get(i).getRank().getValue()==5)
				fivesCounter++;
			if (ComputerTwo.getHand().get(i).getRank().getValue()==6)
				sixesCounter++;
			if (ComputerTwo.getHand().get(i).getRank().getValue()==7)
				sevensCounter++;
			if (ComputerTwo.getHand().get(i).getRank().getValue()==8)
				eightsCounter++;
			if (ComputerTwo.getHand().get(i).getRank().getValue()==9)
				ninesCounter++;
			if (ComputerTwo.getHand().get(i).getRank().getValue()==10)
				tensCounter++;
			if (ComputerTwo.getHand().get(i).getRank().getValue()==11)
				jacksCounter++;
			if (ComputerTwo.getHand().get(i).getRank().getValue()==12)
				queensCounter++;
			if (ComputerTwo.getHand().get(i).getRank().getValue()==13)
				kingsCounter++;
		}
		for (int i = 0; i < 13; i++) {
			if (ComputerThree.getHand().get(i).getSuit()== Suit.Spades)
				spadesCounter++;
			if (ComputerThree.getHand().get(i).getSuit()== Suit.Hearts)
				heartsCounter++;
			if (ComputerThree.getHand().get(i).getSuit()== Suit.Diamonds)
				diamondsCounter++;
			if (ComputerThree.getHand().get(i).getSuit()== Suit.Clubs)
				clubsCounter++;
			
			if (ComputerThree.getHand().get(i).getRank().getValue()==14)
				aceCounter++;
			if (ComputerThree.getHand().get(i).getRank().getValue()==2)
				deuceCounter++;
			if (ComputerThree.getHand().get(i).getRank().getValue()==3)
				threesCounter++;
			if (ComputerThree.getHand().get(i).getRank().getValue()==4)
				foursCounter++;
			if (ComputerThree.getHand().get(i).getRank().getValue()==5)
				fivesCounter++;
			if (ComputerThree.getHand().get(i).getRank().getValue()==6)
				sixesCounter++;
			if (ComputerThree.getHand().get(i).getRank().getValue()==7)
				sevensCounter++;
			if (ComputerThree.getHand().get(i).getRank().getValue()==8)
				eightsCounter++;
			if (ComputerThree.getHand().get(i).getRank().getValue()==9)
				ninesCounter++;
			if (ComputerThree.getHand().get(i).getRank().getValue()==10)
				tensCounter++;
			if (ComputerThree.getHand().get(i).getRank().getValue()==11)
				jacksCounter++;
			if (ComputerThree.getHand().get(i).getRank().getValue()==12)
				queensCounter++;
			if (ComputerThree.getHand().get(i).getRank().getValue()==13)
				kingsCounter++;
		}
		
		assertEquals(13,diamondsCounter);
		assertEquals(13,spadesCounter);
		assertEquals(13,clubsCounter);
		assertEquals(13,heartsCounter);
		
		assertEquals(4,aceCounter);
		assertEquals(4,deuceCounter);
		assertEquals(4,threesCounter);
		assertEquals(4,foursCounter);
		assertEquals(4,fivesCounter);
		assertEquals(4,sixesCounter);
		assertEquals(4,sevensCounter);
		assertEquals(4,eightsCounter);
		assertEquals(4,ninesCounter);
		assertEquals(4,tensCounter);
		assertEquals(4,jacksCounter);
		assertEquals(4,queensCounter);
		assertEquals(4,kingsCounter);
	}
	

}
