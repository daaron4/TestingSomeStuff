package CardModel;

import java.util.ArrayList;

public class Player {
	private String name;
	private int points;
	private ArrayList<Card> hand;
	private ArrayList<Card> oldCards;

	public Player(String playerName) {
		this.setName(playerName);
		setPoints(0);
		setOldCards(new ArrayList<Card>());
		setHand(new ArrayList<Card>());
	}

	public void setOldCards(ArrayList<Card> oldCards) {		
		this.oldCards = oldCards;
	}

	public String getCardAsString(int index) {
		return hand.get(index).toString();
	}
	
	public String getOldCardsCardAsString(int index) {
		return oldCards.get(index).toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public ArrayList<Card> getOldCards() {
		return oldCards;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	
	
}
