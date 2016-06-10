package CardModel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Deck {

	private static BufferedImage aceSpades, deuceSpades, threeSpades,
			fourSpades, fiveSpades, sixSpades, sevenSpades, eightSpades,
			nineSpades, tenSpades, jackSpades, queenSpades, kingSpades,
			aceDiamonds, deuceDiamonds, threeDiamonds, fourDiamonds,
			fiveDiamonds, sixDiamonds, sevenDiamonds, eightDiamonds,
			nineDiamonds, tenDiamonds, jackDiamonds, queenDiamonds,
			kingDiamonds, aceHearts, deuceHearts, threeHearts, fourHearts,
			fiveHearts, sixHearts, sevenHearts, eightHearts, nineHearts,
			tenHearts, jackHearts, queenHearts, kingHearts, aceClubs,
			deuceClubs, threeClubs, fourClubs, fiveClubs, sixClubs, sevenClubs,
			eightClubs, nineClubs, tenClubs, jackClubs, queenClubs, kingClubs;

	private JLabel aceSpadesLabel, deuceSpadesLabel, threeSpadesLabel,
			fourSpadesLabel, fiveSpadesLabel, sixSpadesLabel, sevenSpadesLabel,
			eightSpadesLabel, nineSpadesLabel, tenSpadesLabel, jackSpadesLabel,
			queenSpadesLabel, kingSpadesLabel, aceDiamondsLabel,
			deuceDiamondsLabel, threeDiamondsLabel, fourDiamondsLabel,
			fiveDiamondsLabel, sixDiamondsLabel, sevenDiamondsLabel,
			eightDiamondsLabel, nineDiamondsLabel, tenDiamondsLabel,
			jackDiamondsLabel, queenDiamondsLabel, kingDiamondsLabel,
			aceHeartsLabel, deuceHeartsLabel, threeHeartsLabel,
			fourHeartsLabel, fiveHeartsLabel, sixHeartsLabel, sevenHeartsLabel,
			eightHeartsLabel, nineHeartsLabel, tenHeartsLabel, jackHeartsLabel,
			queenHeartsLabel, kingHeartsLabel, aceClubsLabel, deuceClubsLabel,
			threeClubsLabel, fourClubsLabel, fiveClubsLabel, sixClubsLabel,
			sevenClubsLabel, eightClubsLabel, nineClubsLabel, tenClubsLabel,
			jackClubsLabel, queenClubsLabel, kingClubsLabel;

	private static final String imageDir = System.getProperty("user.dir")
			+ File.separator + "cardImages" + File.separator;
	
	private ArrayList<Card> deck;

	public Deck() {
		deck = new ArrayList<Card>();
		createDeck();
	}

	public void createDeck() {
		// load images:
		loadImages();
		// assign images to labels:
		assignImages();

		// Add all cards:
		deck.add(new Card(Rank.Ace, Suit.Spades, aceSpadesLabel));
		deck.add(new Card(Rank.Deuce, Suit.Spades, deuceSpadesLabel));
		deck.add(new Card(Rank.Three, Suit.Spades, threeSpadesLabel));
		deck.add(new Card(Rank.Four, Suit.Spades, fourSpadesLabel));
		deck.add(new Card(Rank.Five, Suit.Spades, fiveSpadesLabel));
		deck.add(new Card(Rank.Six, Suit.Spades, sixSpadesLabel));
		deck.add(new Card(Rank.Seven, Suit.Spades, sevenSpadesLabel));
		deck.add(new Card(Rank.Eight, Suit.Spades, eightSpadesLabel));
		deck.add(new Card(Rank.Nine, Suit.Spades, nineSpadesLabel));
		deck.add(new Card(Rank.Ten, Suit.Spades, tenSpadesLabel));
		deck.add(new Card(Rank.Jack, Suit.Spades, jackSpadesLabel));
		deck.add(new Card(Rank.Queen, Suit.Spades, queenSpadesLabel));
		deck.add(new Card(Rank.King, Suit.Spades, kingSpadesLabel));

		deck.add(new Card(Rank.Ace, Suit.Diamonds, aceDiamondsLabel));
		deck.add(new Card(Rank.Deuce, Suit.Diamonds, deuceDiamondsLabel));
		deck.add(new Card(Rank.Three, Suit.Diamonds, threeDiamondsLabel));
		deck.add(new Card(Rank.Four, Suit.Diamonds, fourDiamondsLabel));
		deck.add(new Card(Rank.Five, Suit.Diamonds, fiveDiamondsLabel));
		deck.add(new Card(Rank.Six, Suit.Diamonds, sixDiamondsLabel));
		deck.add(new Card(Rank.Seven, Suit.Diamonds, sevenDiamondsLabel));
		deck.add(new Card(Rank.Eight, Suit.Diamonds, eightDiamondsLabel));
		deck.add(new Card(Rank.Nine, Suit.Diamonds, nineDiamondsLabel));
		deck.add(new Card(Rank.Ten, Suit.Diamonds, tenDiamondsLabel));
		deck.add(new Card(Rank.Jack, Suit.Diamonds, jackDiamondsLabel));
		deck.add(new Card(Rank.Queen, Suit.Diamonds, queenDiamondsLabel));
		deck.add(new Card(Rank.King, Suit.Diamonds, kingDiamondsLabel));

		deck.add(new Card(Rank.Ace, Suit.Hearts, aceHeartsLabel));
		deck.add(new Card(Rank.Deuce, Suit.Hearts, deuceHeartsLabel));
		deck.add(new Card(Rank.Three, Suit.Hearts, threeHeartsLabel));
		deck.add(new Card(Rank.Four, Suit.Hearts, fourHeartsLabel));
		deck.add(new Card(Rank.Five, Suit.Hearts, fiveHeartsLabel));
		deck.add(new Card(Rank.Six, Suit.Hearts, sixHeartsLabel));
		deck.add(new Card(Rank.Seven, Suit.Hearts, sevenHeartsLabel));
		deck.add(new Card(Rank.Eight, Suit.Hearts, eightHeartsLabel));
		deck.add(new Card(Rank.Nine, Suit.Hearts, nineHeartsLabel));
		deck.add(new Card(Rank.Ten, Suit.Hearts, tenHeartsLabel));
		deck.add(new Card(Rank.Jack, Suit.Hearts, jackHeartsLabel));
		deck.add(new Card(Rank.Queen, Suit.Hearts, queenHeartsLabel));
		deck.add(new Card(Rank.King, Suit.Hearts, kingHeartsLabel));

		deck.add(new Card(Rank.Ace, Suit.Clubs, aceClubsLabel));
		deck.add(new Card(Rank.Deuce, Suit.Clubs, deuceClubsLabel));
		deck.add(new Card(Rank.Three, Suit.Clubs, threeClubsLabel));
		deck.add(new Card(Rank.Four, Suit.Clubs, fourClubsLabel));
		deck.add(new Card(Rank.Five, Suit.Clubs, fiveClubsLabel));
		deck.add(new Card(Rank.Six, Suit.Clubs, sixClubsLabel));
		deck.add(new Card(Rank.Seven, Suit.Clubs, sevenClubsLabel));
		deck.add(new Card(Rank.Eight, Suit.Clubs, eightClubsLabel));
		deck.add(new Card(Rank.Nine, Suit.Clubs, nineClubsLabel));
		deck.add(new Card(Rank.Ten, Suit.Clubs, tenClubsLabel));
		deck.add(new Card(Rank.Jack, Suit.Clubs, jackClubsLabel));
		deck.add(new Card(Rank.Queen, Suit.Clubs, queenClubsLabel));
		deck.add(new Card(Rank.King, Suit.Clubs, kingClubsLabel));
	}

	public void shuffle() {
		// Shuffles the deck:
		int times = 0;
		while (times < 200) {
			Random rand = new Random();
			int randNum = rand.nextInt(52);
			int randNum2 = rand.nextInt(52);
			Card temp = new Card(deck.get(randNum).getRank(), deck.get(randNum)
					.getSuit(), deck.get(randNum).getCardImageLabel());
			Card temp2 = new Card(deck.get(randNum2).getRank(), deck.get(
					randNum2).getSuit(), deck.get(randNum2).getCardImageLabel());
			// if they are equal:
			while (temp.getRank() == temp2.getRank()
					&& temp.getSuit() == temp2.getSuit()) {
				randNum = rand.nextInt(52);
				randNum2 = rand.nextInt(52);
				temp = new Card(deck.get(randNum).getRank(), deck.get(randNum)
						.getSuit(), deck.get(randNum).getCardImageLabel());
				temp2 = new Card(deck.get(randNum2).getRank(), deck.get(
						randNum2).getSuit(), deck.get(randNum2).getCardImageLabel());
			}
			// they are not equal:
			deck.remove(randNum);
			deck.add(randNum, temp2);
			deck.remove(randNum2);
			deck.add(randNum2, temp);
			times++;
		}

	}

	public int getSize() {
		return deck.size();
	}

	public Card get(int index) {
		return deck.get(index);
	}

	public boolean contains(Card card) {
		return deck.contains(card);
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < 52; i++) {
			str += deck.get(i).getRank().toString() + " of "
					+ deck.get(i).getSuit().toString() + " ";
			if (i == 13 || i == 26 || i == 39 || i == 51)
				str += "\n";
		}
		return str;
	}

	public void deal(Player player1, Player player2, Player player3, Player player4) {
		for (int i = 0; i < 13; i++) {
			player1.getHand().add(deck.get(i));
		}
		for (int i = 13; i < 26; i++) {
			player2.getHand().add(deck.get(i));
		}
		for (int i = 26; i < 39; i++) {
			player3.getHand().add(deck.get(i));
		}
		for (int i = 39; i < 52; i++) {
			player4.getHand().add(deck.get(i));
		}
	}

	public void remove(int index) {
		deck.remove(index);
	}

	public void add(int index, Card card) {
		deck.add(index, card);
	}

	public static void loadImages() {
		try {
			aceSpades = ImageIO.read(new File(imageDir + "aceSpades.png"));
			deuceSpades = ImageIO.read(new File(imageDir + "deuceSpades.png"));
			threeSpades = ImageIO.read(new File(imageDir + "threeSpades.png"));
			fourSpades = ImageIO.read(new File(imageDir + "fourSpades.png"));
			fiveSpades = ImageIO.read(new File(imageDir + "fiveSpades.png"));
			sixSpades = ImageIO.read(new File(imageDir + "sixSpades.png"));
			sevenSpades = ImageIO.read(new File(imageDir + "sevenSpades.png"));
			eightSpades = ImageIO.read(new File(imageDir + "eightSpades.png"));
			nineSpades = ImageIO.read(new File(imageDir + "nineSpades.png"));
			tenSpades = ImageIO.read(new File(imageDir + "tenSpades.png"));
			jackSpades = ImageIO.read(new File(imageDir + "jackSpades.png"));
			queenSpades = ImageIO.read(new File(imageDir + "queenSpades.png"));
			kingSpades = ImageIO.read(new File(imageDir + "kingSpades.png"));

			aceDiamonds = ImageIO.read(new File(imageDir + "aceDiamonds.png"));
			deuceDiamonds = ImageIO.read(new File(imageDir
					+ "deuceDiamonds.png"));
			threeDiamonds = ImageIO.read(new File(imageDir
					+ "threeDiamonds.png"));
			fourDiamonds = ImageIO
					.read(new File(imageDir + "fourDiamonds.png"));
			fiveDiamonds = ImageIO
					.read(new File(imageDir + "fiveDiamonds.png"));
			sixDiamonds = ImageIO.read(new File(imageDir + "sixDiamonds.png"));
			sevenDiamonds = ImageIO.read(new File(imageDir
					+ "sevenDiamonds.png"));
			eightDiamonds = ImageIO.read(new File(imageDir
					+ "eightDiamonds.png"));
			nineDiamonds = ImageIO
					.read(new File(imageDir + "nineDiamonds.png"));
			tenDiamonds = ImageIO.read(new File(imageDir + "tenDiamonds.png"));
			jackDiamonds = ImageIO
					.read(new File(imageDir + "jackDiamonds.png"));
			queenDiamonds = ImageIO.read(new File(imageDir
					+ "queenDiamonds.png"));
			kingDiamonds = ImageIO
					.read(new File(imageDir + "kingDiamonds.png"));

			aceHearts = ImageIO.read(new File(imageDir + "aceHearts.png"));
			deuceHearts = ImageIO.read(new File(imageDir + "deuceHearts.png"));
			threeHearts = ImageIO.read(new File(imageDir + "threeHearts.png"));
			fourHearts = ImageIO.read(new File(imageDir + "fourHearts.png"));
			fiveHearts = ImageIO.read(new File(imageDir + "fiveHearts.png"));
			sixHearts = ImageIO.read(new File(imageDir + "sixHearts.png"));
			sevenHearts = ImageIO.read(new File(imageDir + "sevenHearts.png"));
			eightHearts = ImageIO.read(new File(imageDir + "eightHearts.png"));
			nineHearts = ImageIO.read(new File(imageDir + "nineHearts.png"));
			tenHearts = ImageIO.read(new File(imageDir + "tenHearts.png"));
			jackHearts = ImageIO.read(new File(imageDir + "jackHearts.png"));
			queenHearts = ImageIO.read(new File(imageDir + "queenHearts.png"));
			kingHearts = ImageIO.read(new File(imageDir + "kingHearts.png"));

			aceClubs = ImageIO.read(new File(imageDir + "aceClubs.png"));
			deuceClubs = ImageIO.read(new File(imageDir + "deuceClubs.png"));
			threeClubs = ImageIO.read(new File(imageDir + "threeClubs.png"));
			fourClubs = ImageIO.read(new File(imageDir + "fourClubs.png"));
			fiveClubs = ImageIO.read(new File(imageDir + "fiveClubs.png"));
			sixClubs = ImageIO.read(new File(imageDir + "sixClubs.png"));
			sevenClubs = ImageIO.read(new File(imageDir + "sevenClubs.png"));
			eightClubs = ImageIO.read(new File(imageDir + "eightClubs.png"));
			nineClubs = ImageIO.read(new File(imageDir + "nineClubs.png"));
			tenClubs = ImageIO.read(new File(imageDir + "tenClubs.png"));
			jackClubs = ImageIO.read(new File(imageDir + "jackClubs.png"));
			queenClubs = ImageIO.read(new File(imageDir + "queenClubs.png"));
			kingClubs = ImageIO.read(new File(imageDir + "kingClubs.png"));
			
		} catch (IOException e) {
			System.out.println("Could not find an Image!");
		}

	}

	public void assignImages() {
		aceSpadesLabel = (new JLabel(new ImageIcon(aceSpades)));
		deuceSpadesLabel = (new JLabel(new ImageIcon(deuceSpades)));
		threeSpadesLabel = (new JLabel(new ImageIcon(threeSpades)));
		fourSpadesLabel = (new JLabel(new ImageIcon(fourSpades)));
		fiveSpadesLabel = (new JLabel(new ImageIcon(fiveSpades)));
		sixSpadesLabel = (new JLabel(new ImageIcon(sixSpades)));
		sevenSpadesLabel = (new JLabel(new ImageIcon(sevenSpades)));
		eightSpadesLabel = (new JLabel(new ImageIcon(eightSpades)));
		nineSpadesLabel = (new JLabel(new ImageIcon(nineSpades)));
		tenSpadesLabel = (new JLabel(new ImageIcon(tenSpades)));
		jackSpadesLabel = (new JLabel(new ImageIcon(jackSpades)));
		queenSpadesLabel = (new JLabel(new ImageIcon(queenSpades)));
		kingSpadesLabel = (new JLabel(new ImageIcon(kingSpades)));

		aceDiamondsLabel = (new JLabel(new ImageIcon(aceDiamonds)));
		deuceDiamondsLabel = (new JLabel(new ImageIcon(deuceDiamonds)));
		threeDiamondsLabel = (new JLabel(new ImageIcon(threeDiamonds)));
		fourDiamondsLabel = (new JLabel(new ImageIcon(fourDiamonds)));
		fiveDiamondsLabel = (new JLabel(new ImageIcon(fiveDiamonds)));
		sixDiamondsLabel = (new JLabel(new ImageIcon(sixDiamonds)));
		sevenDiamondsLabel = (new JLabel(new ImageIcon(sevenDiamonds)));
		eightDiamondsLabel = (new JLabel(new ImageIcon(eightDiamonds)));
		nineDiamondsLabel = (new JLabel(new ImageIcon(nineDiamonds)));
		tenDiamondsLabel = (new JLabel(new ImageIcon(tenDiamonds)));
		jackDiamondsLabel = (new JLabel(new ImageIcon(jackDiamonds)));
		queenDiamondsLabel = (new JLabel(new ImageIcon(queenDiamonds)));
		kingDiamondsLabel = (new JLabel(new ImageIcon(kingDiamonds)));

		aceHeartsLabel = (new JLabel(new ImageIcon(aceHearts)));
		deuceHeartsLabel = (new JLabel(new ImageIcon(deuceHearts)));
		threeHeartsLabel = (new JLabel(new ImageIcon(threeHearts)));
		fourHeartsLabel = (new JLabel(new ImageIcon(fourHearts)));
		fiveHeartsLabel = (new JLabel(new ImageIcon(fiveHearts)));
		sixHeartsLabel = (new JLabel(new ImageIcon(sixHearts)));
		sevenHeartsLabel = (new JLabel(new ImageIcon(sevenHearts)));
		eightHeartsLabel = (new JLabel(new ImageIcon(eightHearts)));
		nineHeartsLabel = (new JLabel(new ImageIcon(nineHearts)));
		tenHeartsLabel = (new JLabel(new ImageIcon(tenHearts)));
		jackHeartsLabel = (new JLabel(new ImageIcon(jackHearts)));
		queenHeartsLabel = (new JLabel(new ImageIcon(queenHearts)));
		kingHeartsLabel = (new JLabel(new ImageIcon(kingHearts)));

		aceClubsLabel = (new JLabel(new ImageIcon(aceClubs)));
		deuceClubsLabel = (new JLabel(new ImageIcon(deuceClubs)));
		threeClubsLabel = (new JLabel(new ImageIcon(threeClubs)));
		fourClubsLabel = (new JLabel(new ImageIcon(fourClubs)));
		fiveClubsLabel = (new JLabel(new ImageIcon(fiveClubs)));
		sixClubsLabel = (new JLabel(new ImageIcon(sixClubs)));
		sevenClubsLabel = (new JLabel(new ImageIcon(sevenClubs)));
		eightClubsLabel = (new JLabel(new ImageIcon(eightClubs)));
		nineClubsLabel = (new JLabel(new ImageIcon(nineClubs)));
		tenClubsLabel = (new JLabel(new ImageIcon(tenClubs)));
		jackClubsLabel = (new JLabel(new ImageIcon(jackClubs)));
		queenClubsLabel = (new JLabel(new ImageIcon(queenClubs)));
		kingClubsLabel = (new JLabel(new ImageIcon(kingClubs)));
		
	}
}
