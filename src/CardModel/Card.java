package CardModel;

import javax.swing.JLabel;

public class Card implements Comparable<Card> {

	private Rank rank;
	private Suit suit;
	private JLabel cardImageLabel;
	private boolean selected;
	
	public Card(Rank rank, Suit suit, JLabel cardImageLabel) {
		this.rank = rank;
		this.suit = suit;
		this.cardImageLabel = cardImageLabel;
		selected = false;
		
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public JLabel getCardImageLabel() {
		return cardImageLabel;
	}

	public void setCardImageLabel(JLabel cardImageLabel) {
		this.cardImageLabel = cardImageLabel;
	}
	
	public String toString() {
		return rank + " of " + suit;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	@Override
	public int compareTo(Card arg0) {
		if (suit.getStrength() - arg0.getSuit().getStrength() == 0) {
			return rank.getValue() - arg0.getRank().getValue();
		}
		else {
			return 0;
		}
		

	}
}
