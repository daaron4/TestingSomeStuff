package CardModel;

public enum Suit {
	Hearts(1), Spades(2), Clubs(4), Diamonds(3);
	
	private int strength;
	
	private Suit(int strength) {
		this.strength = strength;
	}
	public int getStrength() {
		return strength;
	}
}