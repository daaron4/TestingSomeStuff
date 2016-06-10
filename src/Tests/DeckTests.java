package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import CardModel.Deck;
import CardModel.Player;
import CardModel.Suit;

public class DeckTests {

	@Test
	public void testCreateDeck() {
		Deck aDeck = new Deck();
		assertEquals(52,aDeck.getSize());
		for (int  i = 0; i < 13; i++) {
			if (i==0) {
				assertTrue(aDeck.get(i).getRank().getValue() == 14);
			}
			else {
				assertTrue(aDeck.get(i).getRank().getValue() == i+1);
			}
			assertTrue(aDeck.get(i).getSuit() == Suit.Spades);
		}
		for (int  i = 13; i < 26; i++) {
			if (i==13) {
				assertTrue(aDeck.get(i).getRank().getValue() == 14);
			}
			else {
				assertTrue(aDeck.get(i).getRank().getValue() == i-12);
			}
			assertTrue(aDeck.get(i).getSuit() == Suit.Diamonds);
		}
		for (int  i = 26; i < 39; i++) {
			if (i==26) {
				assertTrue(aDeck.get(i).getRank().getValue() == 14);
			}
			else {
				assertTrue(aDeck.get(i).getRank().getValue() == i-25);
			}
			assertTrue(aDeck.get(i).getSuit() == Suit.Hearts);
		}
		for (int  i = 39; i < 52; i++) {
			if (i==39) {
				assertTrue(aDeck.get(i).getRank().getValue() == 14);
			}
			else {
				assertTrue(aDeck.get(i).getRank().getValue() == i-38);
			}
			assertTrue(aDeck.get(i).getSuit() == Suit.Clubs);
		}
		//get this to work someday...
		//Card AS = new Card(Rank.Ace, Suit.Spades);
		//assertTrue(aDeck.contains(new Card(Rank.Ace, Suit.Spades)));
		
	}
	@Test
	public void testToString() {
		Deck aDeck = new Deck();
		assertEquals(52,aDeck.getSize());
		System.out.println(aDeck.toString());
	}
	
	@Test
	public void testShuffle() {
		Deck aDeck = new Deck();
		assertEquals(52,aDeck.getSize());
		aDeck.shuffle();
		assertEquals(52,aDeck.getSize());
		System.out.println();
		System.out.println(aDeck.toString());
	}
	@Test
	public void testDeal() {
		Deck aDeck = new Deck();
		Player Human = new Player("Human");
		Player ComputerOne = new Player("Computer One");
		Player ComputerTwo = new Player("Computer Two");
		Player ComputerThree = new Player("Computer Three");
		aDeck.deal(Human, ComputerOne, ComputerTwo, ComputerThree);
		assertEquals(13,Human.getHand().size());
		assertEquals(13,ComputerOne.getHand().size());
		assertEquals(13,ComputerTwo.getHand().size());
		assertEquals(13,ComputerThree.getHand().size());
		
		for (int  i = 0; i < 13; i++) {
			if (i==0) {
				assertTrue(Human.getHand().get(i).getRank().getValue() == 14);
			}
			else {
				assertTrue(Human.getHand().get(i).getRank().getValue() == i+1);
			}
			assertTrue(Human.getHand().get(i).getSuit() == Suit.Spades);
		}
		for (int  i = 0; i < 13; i++) {
			if (i==0) {
				assertTrue(ComputerOne.getHand().get(i).getRank().getValue() == 14);
			}
			else {
				assertTrue(ComputerOne.getHand().get(i).getRank().getValue() == i+1);
			}
			assertTrue(ComputerOne.getHand().get(i).getSuit() == Suit.Diamonds);
		}
		for (int  i = 0; i < 13; i++) {
			if (i==0) {
				assertTrue(ComputerTwo.getHand().get(i).getRank().getValue() == 14);
			}
			else {
				assertTrue(ComputerTwo.getHand().get(i).getRank().getValue() == i+1);
			}
			assertTrue(ComputerTwo.getHand().get(i).getSuit() == Suit.Hearts);
		}
		for (int  i = 0; i < 13; i++) {
			if (i==0) {
				assertTrue(ComputerThree.getHand().get(i).getRank().getValue() == 14);
			}
			else {
				assertTrue(ComputerThree.getHand().get(i).getRank().getValue() == i+1);
			}
			assertTrue(ComputerThree.getHand().get(i).getSuit() == Suit.Clubs);
		}
		
	}

}
