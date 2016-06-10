package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import CardModel.Card;
import CardModel.Rank;
import CardModel.CardBack;
import CardModel.Suit;
import HeartsModel.HeartsModel;

public class GraphicsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private HeartsModel game;
	private int turns;
	private JTextArea userName;
	private JTextArea comp1Name;
	private JTextArea comp2Name;
	private JTextArea comp3Name;
	private ArrayList<CardBack> fakeHand1;
	private ArrayList<CardBack> fakeHand2;
	private ArrayList<CardBack> fakeHand3;
	private Card userCard;
	private Random rand;
	private int randNum;
	private boolean timeToShuffleAndDeal;
	private Card comp1Card;
	private Card comp2Card;
	private Card comp3Card;
	private JTextArea scoreBoard;
	private boolean passing;
	private JButton passButton;
	private ArrayList<Card> temp;
	private int roundsPlayed;
	private int timesClicked;

	public GraphicsPanel(String userName, String comp1Name, String comp2Name,
			String comp3Name) {
		game = new HeartsModel(userName, comp1Name, comp2Name, comp3Name);
		turns = 1;
		rand = new Random();
		randNum = -1;
		timeToShuffleAndDeal = true;
		passing = true;
		temp = new ArrayList<Card>();
		roundsPlayed = 0;
		timesClicked = 0;
		scoreBoard = new JTextArea(userName + '\t' + comp1Name + '\t'
				+ comp2Name + '\t' + comp3Name);
		makeFakeHands();
		placeNames();
		start();
	}

	public void makeFakeHands() {
		fakeHand1 = new ArrayList<CardBack>();
		fakeHand2 = new ArrayList<CardBack>();
		fakeHand3 = new ArrayList<CardBack>();
		for (int i = 0; i < 13; i++) {
			fakeHand1.add(new CardBack());
			fakeHand2.add(new CardBack());
			fakeHand3.add(new CardBack());
		}

	}

	public void placeNames() {
		userName = new JTextArea(game.getPlayer1().getName());
		userName.setEditable(false);
		userName.setBounds(725, 600, 100, 25);
		comp2Name = new JTextArea(game.getPlayer3().getName());
		comp2Name.setEditable(false);
		comp2Name.setBounds(175, 15, 100, 25);
		comp1Name = new JTextArea(game.getPlayer2().getName());
		comp1Name.setEditable(false);
		comp1Name.setBounds(150, 500, 100, 25);
		comp3Name = new JTextArea(game.getPlayer4().getName());
		comp3Name.setEditable(false);
		comp3Name.setBounds(805, 300, 100, 25);
		add(userName);
		add(comp1Name);
		add(comp2Name);
		add(comp3Name);
	}

	public void start() {
		if (turns == 14) {
			// Calculate points:
			game.calculatePoints();
			// Display old cards:
			displayOldCards();
			// Update scoreBoard:
			scoreBoard.append("" + '\n' + game.getPlayer1().getPoints() + '\t'
					+ game.getPlayer2().getPoints() + '\t'
					+ game.getPlayer3().getPoints() + '\t'
					+ game.getPlayer4().getPoints());
			// Display points:
			JOptionPane.showMessageDialog(null, scoreBoard, "Score",
					JOptionPane.PLAIN_MESSAGE);
			// Clear the board:
			removeOldCards();
			// Update playing to see if the game has ended:
			game.updatePlayingGUI();
			// Game is over, display winner, ask to play again:
			if (game.getPlaying() == false) {
				int reply = JOptionPane.showConfirmDialog(null,
						game.displayWinnerStringVersion() + '\n'
								+ "Play Again?", "Game Over",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					roundsPlayed = 0;
					scoreBoard.setText(null);
					scoreBoard = new JTextArea(game.getPlayer1().getName()
							+ '\t' + game.getPlayer2().getName() + '\t'
							+ game.getPlayer3().getName() + '\t'
							+ game.getPlayer4().getName());
					game.setPlaying(true);
					game.getPlayer1().setPoints(0);
					game.getPlayer2().setPoints(0);
					game.getPlayer3().setPoints(0);
					game.getPlayer4().setPoints(0);

				} else {
					JOptionPane.showMessageDialog(null, "Peace");
					System.exit(0);
				}
			}
			// Get ready for the next hand:
			game.reset();
			turns = 1;
			timeToShuffleAndDeal = true;
			makeFakeHands();
			passing = true;
		}
		if (timeToShuffleAndDeal == true) {
			roundsPlayed++;
			game.getDeck().shuffle();
			game.getDeck().deal(game.getPlayer1(), game.getPlayer2(),
					game.getPlayer3(), game.getPlayer4());
			// Organize hands:
			game.sort(game.getPlayer1().getHand());
			game.sort(game.getPlayer2().getHand());
			game.sort(game.getPlayer3().getHand());
			game.sort(game.getPlayer4().getHand());
			Collections.sort(game.getPlayer1().getHand());
			Collections.sort(game.getPlayer2().getHand());
			Collections.sort(game.getPlayer3().getHand());
			Collections.sort(game.getPlayer4().getHand());

			// Pass Cards:
			// game.passCards(game.getRoundsPlayed());

			// display the game:
			placeImages();
			validate();
			updateUI();
			timeToShuffleAndDeal = false;
		}

		// Pass Cards:
		passCards();

	}

	public void play() {

		// Play a Hand:
		if (game.getLeadingPlayer() == game.getPlayer2()) {
			// comp1 plays a card:
			comp1Card = game.computerPlaysACard(1, turns);
			// remove a card:
			randNum = rand.nextInt(fakeHand1.size());
			remove(fakeHand1.get(randNum).getLabel());
			fakeHand1.remove(randNum);
			// display the card:
			comp1Card.getCardImageLabel().setBounds(350, 300, 100, 160);
			add(comp1Card.getCardImageLabel());
			validate();
			updateUI();
			// add card to board:
			game.getBoard().add(0, comp1Card);

			// comp2 plays a card:
			comp2Card = game.computerPlaysACard(2, turns);
			// remove a card:
			randNum = rand.nextInt(fakeHand2.size());
			remove(fakeHand2.get(randNum).getLabel());
			fakeHand2.remove(randNum);
			// display the card:
			comp2Card.getCardImageLabel().setBounds(425, 225, 100, 160);
			add(comp2Card.getCardImageLabel());
			validate();
			updateUI();
			// add card to board:
			game.getBoard().add(1, comp2Card);

			// comp3 plays a card:
			comp3Card = game.computerPlaysACard(3, turns);
			// remove a card:
			randNum = rand.nextInt(fakeHand3.size());
			remove(fakeHand3.get(randNum).getLabel());
			fakeHand3.remove(randNum);
			// display the card:
			comp3Card.getCardImageLabel().setBounds(500, 300, 100, 160);
			add(comp3Card.getCardImageLabel());
			validate();
			updateUI();
			// add card to board:
			game.getBoard().add(2, comp3Card);

		} else if (game.getLeadingPlayer() == game.getPlayer3()) {
			// comp2 plays a card:
			comp2Card = game.computerPlaysACard(2, turns);
			// remove a card:
			randNum = rand.nextInt(fakeHand2.size());
			remove(fakeHand2.get(randNum).getLabel());
			fakeHand2.remove(randNum);
			// display the card:
			comp2Card.getCardImageLabel().setBounds(425, 225, 100, 160);
			add(comp2Card.getCardImageLabel());
			validate();
			updateUI();
			// add card to board:
			game.getBoard().add(0, comp2Card);

			// comp3 plays a card:
			comp3Card = game.computerPlaysACard(3, turns);
			// remove a card:
			randNum = rand.nextInt(fakeHand3.size());
			remove(fakeHand3.get(randNum).getLabel());
			fakeHand3.remove(randNum);
			// display the card:
			comp3Card.getCardImageLabel().setBounds(500, 300, 100, 160);
			add(comp3Card.getCardImageLabel());
			validate();
			updateUI();
			// add card to board:
			game.getBoard().add(1, comp3Card);

		} else if (game.getLeadingPlayer() == game.getPlayer4()) {
			// comp3 plays a card:
			comp3Card = game.computerPlaysACard(3, turns);
			// remove a card:
			randNum = rand.nextInt(fakeHand3.size());
			remove(fakeHand3.get(randNum).getLabel());
			fakeHand3.remove(randNum);
			// display the card:
			comp3Card.getCardImageLabel().setBounds(500, 300, 100, 160);
			add(comp3Card.getCardImageLabel());
			validate();
			updateUI();
			// add card to board:
			game.getBoard().add(0, comp3Card);

		}
	}

	public void placeImages() {
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			game.getPlayer1().getHand().get(i).getCardImageLabel()
					.setBounds(175 + i * 50, 650, 100, 160);
			add(game.getPlayer1().getHand().get(i).getCardImageLabel());
			if (game.getPlayer1().getHand().get(i).getCardImageLabel().getMouseListeners() != null) {
				game.getPlayer1().getHand().get(i).getCardImageLabel()
				.addMouseListener(new MouseClickListener());
			}
			else {
				
			}
			
		}
		for (int i = 0; i < fakeHand1.size(); i++) {
			fakeHand1.get(i).setPlace(i);
			fakeHand1.get(i).getLabel().setBounds(25, 50 + i * 50, 100, 160);
			add(fakeHand1.get(i).getLabel());

		}
		for (int i = 0; i < fakeHand2.size(); i++) {
			fakeHand2.get(i).setPlace(i);
			fakeHand2.get(i).getLabel().setBounds(175 + i * 50, 50, 100, 160);
			add(fakeHand2.get(i).getLabel());

		}
		for (int i = 0; i < fakeHand3.size(); i++) {
			fakeHand3.get(i).setPlace(i);
			fakeHand3.get(i).getLabel().setBounds(925, 50 + i * 50, 100, 160);
			add(fakeHand3.get(i).getLabel());

		}

	}

	public void displayOldCards() {
		int index = 0;
		for (int i = 0; i < game.getPlayer1().getOldCards().size(); i++) {
			if (game.getPlayer1().getOldCards().get(i).getSuit() == Suit.Hearts
					|| (game.getPlayer1().getOldCards().get(i).getSuit() == Suit.Spades && game
							.getPlayer1().getOldCards().get(i).getRank() == Rank.Queen)) {
				game.getPlayer1().getOldCards().get(i).getCardImageLabel()
						.setBounds(175 + index * 50, 650, 100, 160);
				index++;
				add(game.getPlayer1().getOldCards().get(i).getCardImageLabel());
				validate();
				updateUI();
			}
		}
		index = 0;
		for (int i = 0; i < game.getPlayer2().getOldCards().size(); i++) {
			if (game.getPlayer2().getOldCards().get(i).getSuit() == Suit.Hearts
					|| (game.getPlayer2().getOldCards().get(i).getSuit() == Suit.Spades && game
							.getPlayer2().getOldCards().get(i).getRank() == Rank.Queen)) {
				game.getPlayer2().getOldCards().get(i).getCardImageLabel()
						.setBounds(25, 50 + index * 50, 100, 160);
				index++;
				add(game.getPlayer2().getOldCards().get(i).getCardImageLabel());
				validate();
				updateUI();
			}

		}
		index = 0;
		for (int i = 0; i < game.getPlayer3().getOldCards().size(); i++) {
			if (game.getPlayer3().getOldCards().get(i).getSuit() == Suit.Hearts
					|| (game.getPlayer3().getOldCards().get(i).getSuit() == Suit.Spades && game
							.getPlayer3().getOldCards().get(i).getRank() == Rank.Queen)) {
				game.getPlayer3().getOldCards().get(i).getCardImageLabel()
						.setBounds(175 + index * 50, 50, 100, 160);
				index++;
				add(game.getPlayer3().getOldCards().get(i).getCardImageLabel());
				validate();
				updateUI();
			}

		}
		index = 0;
		for (int i = 0; i < game.getPlayer4().getOldCards().size(); i++) {
			if (game.getPlayer4().getOldCards().get(i).getSuit() == Suit.Hearts
					|| (game.getPlayer4().getOldCards().get(i).getSuit() == Suit.Spades && game
							.getPlayer4().getOldCards().get(i).getRank() == Rank.Queen)) {
				game.getPlayer4().getOldCards().get(i).getCardImageLabel()
						.setBounds(925, 50 + index * 50, 100, 160);
				index++;
				add(game.getPlayer4().getOldCards().get(i).getCardImageLabel());
				validate();
				updateUI();
			}

		}
	}

	public void removeOldCards() {
		for (int i = 0; i < game.getPlayer1().getOldCards().size(); i++) {
			remove(game.getPlayer1().getOldCards().get(i).getCardImageLabel());
		}
		for (int i = 0; i < game.getPlayer2().getOldCards().size(); i++) {
			remove(game.getPlayer2().getOldCards().get(i).getCardImageLabel());
		}
		for (int i = 0; i < game.getPlayer3().getOldCards().size(); i++) {
			remove(game.getPlayer3().getOldCards().get(i).getCardImageLabel());
		}
		for (int i = 0; i < game.getPlayer4().getOldCards().size(); i++) {
			remove(game.getPlayer4().getOldCards().get(i).getCardImageLabel());
		}
	}

	public void passCards() {
		// Rounds played not turns!
		// Pass Cards Direction:
		if (roundsPlayed == 1 || roundsPlayed == 5 || roundsPlayed == 9
				|| roundsPlayed == 13 || roundsPlayed == 17) {
			passButton = new JButton("Pass three cards left: ");
			passButton.setBounds(450, 550, 200, 25);
			passButton.addActionListener(new ButtonClickListener());
			add(passButton);
			validate();
			updateUI();
		}
		if (roundsPlayed == 2 || roundsPlayed == 6 || roundsPlayed == 10
				|| roundsPlayed == 14 || roundsPlayed == 18) {
			passButton = new JButton("Pass three cards right: ");
			passButton.setBounds(450, 550, 200, 25);
			passButton.addActionListener(new ButtonClickListener());
			add(passButton);
			validate();
			updateUI();
		}
		if (roundsPlayed == 3 || roundsPlayed == 7 || roundsPlayed == 11
				|| roundsPlayed == 15 || roundsPlayed == 19) {
			passButton = new JButton("Pass three cards across: ");
			passButton.setBounds(450, 550, 200, 25);
			passButton.addActionListener(new ButtonClickListener());
			add(passButton);
			validate();
			updateUI();
		}
		if (roundsPlayed == 4 || roundsPlayed == 8 || roundsPlayed == 12
				|| roundsPlayed == 16 || roundsPlayed == 20) {
			JOptionPane.showMessageDialog(null, "No Passing");
			passing = false;
			// Set leading player to hand with two of clubs:
			game.setLeadingPlayer(game.playerWithTheTwoOfClubs());
			play();

		}

	}
	public void passLeft() {
		// Add cards from temp to end of comp1
		for (int i = 0; i < temp.size(); i++)
			game.getPlayer2().getHand().add(temp.get(i));

		// Remove cards from hand
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			if (game.getPlayer1().getHand().get(i) == temp.get(0)) {
				remove(game.getPlayer1().getHand().get(i)
						.getCardImageLabel());
				validate();
				updateUI();
				game.getPlayer1().getHand().remove(i);
			}

		}

		temp.remove(0);
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			if (game.getPlayer1().getHand().get(i) == temp.get(0)) {
				remove(game.getPlayer1().getHand().get(i)
						.getCardImageLabel());
				validate();
				updateUI();
				game.getPlayer1().getHand().remove(i);
			}

		}
		temp.remove(0);
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			if (game.getPlayer1().getHand().get(i) == temp.get(0)) {
				remove(game.getPlayer1().getHand().get(i)
						.getCardImageLabel());
				validate();
				updateUI();
				game.getPlayer1().getHand().remove(i);
			}

		}
		temp.remove(0);

		// Clear temp:
		temp.clear();
		// Add first three Cards of comp1's hand to temp, and then
		// remove them:
		temp.add(game.getPlayer2().getHand().get(0));
		game.getPlayer2().getHand().remove(0);
		temp.add(game.getPlayer2().getHand().get(0));
		game.getPlayer2().getHand().remove(0);
		temp.add(game.getPlayer2().getHand().get(0));
		game.getPlayer2().getHand().remove(0);

		// Add cards from temp to end of human hand:
		for (int i = 0; i < temp.size(); i++)
			game.getPlayer1().getHand().add(temp.get(i));

		// sort the hands that changed:
		game.sort(game.getPlayer1().getHand());
		game.sort(game.getPlayer2().getHand());
		Collections.sort(game.getPlayer1().getHand());
		Collections.sort(game.getPlayer2().getHand());

		// Tell user the cards they got:
		JOptionPane.showMessageDialog(null,
				"You got the " + temp.get(0).toString() + ", "
						+ temp.get(1).toString() + ", and the "
						+ temp.get(2).toString(),
				"Cards Received: ", JOptionPane.PLAIN_MESSAGE);

		// Clear temp again:
		temp.clear();
		// Fix images:
		// Remove old Hand:
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			remove(game.getPlayer1().getHand().get(i)
					.getCardImageLabel());
			validate();
			updateUI();
		}
		// Place new Hand:
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			game.getPlayer1().getHand().get(i).getCardImageLabel()
					.setBounds(175 + i * 50, 650, 100, 160);
			add(game.getPlayer1().getHand().get(i)
					.getCardImageLabel());
			game.getPlayer1().getHand().get(i).getCardImageLabel()
					.addMouseListener(new MouseClickListener());
			validate();
			updateUI();
		}

		passing = false;
		// remove the button
		remove(passButton);
		// Set leading player, turns will be 1:
		game.setLeadingPlayer(game.playerWithTheTwoOfClubs());
		play();
	}
	
	public void passAcross() {
		// Add cards from temp to end of comp2
		for (int i = 0; i < temp.size(); i++)
			game.getPlayer3().getHand().add(temp.get(i));

		// Remove cards from hand
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			if (game.getPlayer1().getHand().get(i) == temp.get(0)) {
				remove(game.getPlayer1().getHand().get(i)
						.getCardImageLabel());
				validate();
				updateUI();
				game.getPlayer1().getHand().remove(i);
			}

		}

		temp.remove(0);
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			if (game.getPlayer1().getHand().get(i) == temp.get(0)) {
				remove(game.getPlayer1().getHand().get(i)
						.getCardImageLabel());
				validate();
				updateUI();
				game.getPlayer1().getHand().remove(i);
			}

		}
		temp.remove(0);
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			if (game.getPlayer1().getHand().get(i) == temp.get(0)) {
				remove(game.getPlayer1().getHand().get(i)
						.getCardImageLabel());
				validate();
				updateUI();
				game.getPlayer1().getHand().remove(i);
			}

		}
		temp.remove(0);

		// Clear temp:
		temp.clear();
		// Add first three Cards of comp2's hand to temp, and then
		// remove them:
		temp.add(game.getPlayer3().getHand().get(0));
		game.getPlayer3().getHand().remove(0);
		temp.add(game.getPlayer3().getHand().get(0));
		game.getPlayer3().getHand().remove(0);
		temp.add(game.getPlayer3().getHand().get(0));
		game.getPlayer3().getHand().remove(0);

		// Add cards from temp to end of human hand:
		for (int i = 0; i < temp.size(); i++)
			game.getPlayer1().getHand().add(temp.get(i));

		// sort the hands that changed:
		game.sort(game.getPlayer1().getHand());
		game.sort(game.getPlayer3().getHand());
		Collections.sort(game.getPlayer1().getHand());
		Collections.sort(game.getPlayer3().getHand());

		// Tell user the cards they got:
		JOptionPane.showMessageDialog(null,
				"You got the " + temp.get(0).toString() + ", "
						+ temp.get(1).toString() + ", and the "
						+ temp.get(2).toString(),
				"Cards Received: ", JOptionPane.PLAIN_MESSAGE);

		// Clear temp again:
		temp.clear();
		// Fix images:
		// Remove old Hand:
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			remove(game.getPlayer1().getHand().get(i)
					.getCardImageLabel());
			validate();
			updateUI();
		}
		// Place new Hand:
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			game.getPlayer1().getHand().get(i).getCardImageLabel()
					.setBounds(175 + i * 50, 650, 100, 160);
			add(game.getPlayer1().getHand().get(i)
					.getCardImageLabel());
			game.getPlayer1().getHand().get(i).getCardImageLabel()
					.addMouseListener(new MouseClickListener());
			validate();
			updateUI();
		}

		passing = false;
		// remove the button
		remove(passButton);
		// Set leading player, turns will be 1:
		game.setLeadingPlayer(game.playerWithTheTwoOfClubs());
		play();
	}
	
	public void passRight() {
		// Add cards from temp to end of comp3
		for (int i = 0; i < temp.size(); i++)
			game.getPlayer4().getHand().add(temp.get(i));

		// Remove cards from hand
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			if (game.getPlayer1().getHand().get(i) == temp.get(0)) {
				remove(game.getPlayer1().getHand().get(i)
						.getCardImageLabel());
				validate();
				updateUI();
				game.getPlayer1().getHand().remove(i);
			}

		}

		temp.remove(0);
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			if (game.getPlayer1().getHand().get(i) == temp.get(0)) {
				remove(game.getPlayer1().getHand().get(i)
						.getCardImageLabel());
				validate();
				updateUI();
				game.getPlayer1().getHand().remove(i);
			}

		}
		temp.remove(0);
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			if (game.getPlayer1().getHand().get(i) == temp.get(0)) {
				remove(game.getPlayer1().getHand().get(i)
						.getCardImageLabel());
				validate();
				updateUI();
				game.getPlayer1().getHand().remove(i);
			}

		}
		temp.remove(0);

		// Clear temp:
		temp.clear();
		// Add first three Cards of comp3's hand to temp, and then
		// remove them:
		temp.add(game.getPlayer4().getHand().get(0));
		game.getPlayer4().getHand().remove(0);
		temp.add(game.getPlayer4().getHand().get(0));
		game.getPlayer4().getHand().remove(0);
		temp.add(game.getPlayer4().getHand().get(0));
		game.getPlayer4().getHand().remove(0);

		// Add cards from temp to end of human hand:
		for (int i = 0; i < temp.size(); i++)
			game.getPlayer1().getHand().add(temp.get(i));

		// sort the hands that changed:
		game.sort(game.getPlayer1().getHand());
		game.sort(game.getPlayer4().getHand());
		Collections.sort(game.getPlayer1().getHand());
		Collections.sort(game.getPlayer4().getHand());

		// Tell user the cards they got:
		JOptionPane.showMessageDialog(null,
				"You got the " + temp.get(0).toString() + ", "
						+ temp.get(1).toString() + ", and the "
						+ temp.get(2).toString(),
				"Cards Received: ", JOptionPane.PLAIN_MESSAGE);

		// Clear temp again:
		temp.clear();
		// Fix images:
		// Remove old Hand:
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			remove(game.getPlayer1().getHand().get(i)
					.getCardImageLabel());
			validate();
			updateUI();
		}
		// Place new Hand:
		for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
			game.getPlayer1().getHand().get(i).getCardImageLabel()
					.setBounds(175 + i * 50, 650, 100, 160);
			add(game.getPlayer1().getHand().get(i)
					.getCardImageLabel());
			game.getPlayer1().getHand().get(i).getCardImageLabel()
					.addMouseListener(new MouseClickListener());
			validate();
			updateUI();
		}

		passing = false;
		// remove the button
		remove(passButton);
		// Set leading player, turns will be 1:
		game.setLeadingPlayer(game.playerWithTheTwoOfClubs());
		play();
	}
	
	public class ButtonClickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == passButton) {
				if (passing == false) {
					// remove the button
					remove(passButton);
					validate();
					updateUI();
					play();
				} else if (temp.size() != 3) {
					JOptionPane.showMessageDialog(null,
							"Select Three Cards First ");
				}
				// Do actual passing
				else {
					if (roundsPlayed == 1 || roundsPlayed == 5 || roundsPlayed == 9
							|| roundsPlayed == 13 || roundsPlayed == 17) {
						passLeft();
					}
					if (roundsPlayed == 2 || roundsPlayed == 6 || roundsPlayed == 10
							|| roundsPlayed == 14 || roundsPlayed == 18) {
						passRight();

					}
					if (roundsPlayed == 3 || roundsPlayed == 7 || roundsPlayed == 11
							|| roundsPlayed == 15 || roundsPlayed == 19) {
						passAcross();

					}
			

				}
			}
		}

	}
	
	public class MouseClickListener implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
			timesClicked++;
			System.out.println("Clicked " + timesClicked + " times");
			for (int i = 0; i < game.getPlayer1().getHand().size(); i++) {
				System.out.println(i);
				if (arg0.getSource() == game.getPlayer1().getHand().get(i)
						.getCardImageLabel()) {
					if (passing) {
						if (temp.size() != 3) {
							if (game.getPlayer1().getHand().get(i).isSelected() == false) {
								temp.add(game.getPlayer1().getHand().get(i));
								game.getPlayer1().getHand().get(i)
										.getCardImageLabel()
										.setBounds(175 + i * 50, 625, 100, 160);
								game.getPlayer1().getHand().get(i)
										.setSelected(true);
							} else {
								temp.remove(game.getPlayer1().getHand().get(i));
								game.getPlayer1().getHand().get(i)
										.getCardImageLabel()
										.setBounds(175 + i * 50, 650, 100, 160);
								game.getPlayer1().getHand().get(i)
										.setSelected(false);
							}
						} else {

							if (game.getPlayer1().getHand().get(i).isSelected() == true) {
								temp.remove(game.getPlayer1().getHand().get(i));
								game.getPlayer1().getHand().get(i)
										.getCardImageLabel()
										.setBounds(175 + i * 50, 650, 100, 160);
								game.getPlayer1().getHand().get(i)
										.setSelected(false);
							}

							else {
								JOptionPane
										.showMessageDialog(null,
												"You can't select more than three cards");
							}

						}

					} 
					
					else {
						if (!game.canPlayCard(game.getPlayer1().getHand()
								.get(i), game.getPlayer1().getHand(),
								game.getLeadingPlayer(), game.getPlayer1(),
								turns)) {
							JOptionPane.showMessageDialog(null,
									"Can't play that");
							System.out.println("Here");
						} else {
							userCard = game.getPlayer1().getHand().get(i);
							if (game.getLeadingPlayer() == game.getPlayer1()) {
								game.getBoard().add(0, userCard);
								game.getPlayer1().getHand().get(i)
										.getCardImageLabel()
										.setBounds(425, 375, 100, 160);

								// comp1 plays a card:
								comp1Card = game.computerPlaysACard(1, turns);
								// add card to board:
								game.getBoard().add(1, comp1Card);
								// remove a card:
								randNum = rand.nextInt(fakeHand1.size());
								remove(fakeHand1.get(randNum).getLabel());
								fakeHand1.remove(randNum);
								// display the card:
								comp1Card.getCardImageLabel().setBounds(350,
										300, 100, 160);
								comp1Card.getCardImageLabel().setVisible(true);
								add(comp1Card.getCardImageLabel());
								validate();
								updateUI();

								// comp2 plays a card:
								comp2Card = game.computerPlaysACard(2, turns);
								// add card to board:
								game.getBoard().add(2, comp2Card);
								// remove a card:
								randNum = rand.nextInt(fakeHand2.size());
								remove(fakeHand2.get(randNum).getLabel());
								fakeHand2.remove(randNum);
								// display the card:
								comp2Card.getCardImageLabel().setBounds(425,
										225, 100, 160);
								comp2Card.getCardImageLabel().setVisible(true);
								add(comp2Card.getCardImageLabel());
								validate();
								updateUI();

								// comp3 plays a card:
								comp3Card = game.computerPlaysACard(3, turns);
								// add card to board:
								game.getBoard().add(3, comp3Card);
								// remove a card:
								randNum = rand.nextInt(fakeHand3.size());
								remove(fakeHand3.get(randNum).getLabel());
								fakeHand3.remove(randNum);
								// display the card:
								comp3Card.getCardImageLabel().setBounds(500,
										300, 100, 160);
								comp3Card.getCardImageLabel().setVisible(true);
								add(comp3Card.getCardImageLabel());
								validate();
								updateUI();
								String display = game
										.determineWinnerStringVersion(1);
								if (display.equals(game.getPlayer1().getName()))
									userName.setBackground(Color.BLUE);
								if (display.equals(game.getPlayer2().getName()))
									comp1Name.setBackground(Color.BLUE);
								if (display.equals(game.getPlayer3().getName()))
									comp2Name.setBackground(Color.BLUE);
								if (display.equals(game.getPlayer4().getName()))
									comp3Name.setBackground(Color.BLUE);

								JOptionPane.showMessageDialog(null, display
										+ " wins the trick");

								if (display.equals(game.getPlayer1().getName()))
									userName.setBackground(Color.WHITE);
								if (display.equals(game.getPlayer2().getName()))
									comp1Name.setBackground(Color.WHITE);
								if (display.equals(game.getPlayer3().getName()))
									comp2Name.setBackground(Color.WHITE);
								if (display.equals(game.getPlayer4().getName()))
									comp3Name.setBackground(Color.WHITE);

								// remove all cards:
								remove(game.getPlayer1().getHand().get(i)
										.getCardImageLabel());
								remove(comp1Card.getCardImageLabel());
								remove(comp2Card.getCardImageLabel());
								remove(comp3Card.getCardImageLabel());
								validate();
								updateUI();

								// Remove card from user's hand
								game.getPlayer1().getHand().remove(userCard);

								// play next hand:
								turns++;
								if (turns == 14)
									start();
								else
									play();

							}

							else if (game.getLeadingPlayer() == game
									.getPlayer2()) {
								game.getBoard().add(3, userCard);
								game.getPlayer1().getHand().get(i)
										.getCardImageLabel()
										.setBounds(425, 375, 100, 160);

								String display = game
										.determineWinnerStringVersion(2);
								if (display.equals(game.getPlayer1().getName()))
									userName.setBackground(Color.BLUE);
								if (display.equals(game.getPlayer2().getName()))
									comp1Name.setBackground(Color.BLUE);
								if (display.equals(game.getPlayer3().getName()))
									comp2Name.setBackground(Color.BLUE);
								if (display.equals(game.getPlayer4().getName()))
									comp3Name.setBackground(Color.BLUE);

								JOptionPane.showMessageDialog(null, display
										+ " wins the trick");

								if (display.equals(game.getPlayer1().getName()))
									userName.setBackground(Color.WHITE);
								if (display.equals(game.getPlayer2().getName()))
									comp1Name.setBackground(Color.WHITE);
								if (display.equals(game.getPlayer3().getName()))
									comp2Name.setBackground(Color.WHITE);
								if (display.equals(game.getPlayer4().getName()))
									comp3Name.setBackground(Color.WHITE);

								// remove all cards:
								remove(game.getPlayer1().getHand().get(i)
										.getCardImageLabel());
								remove(comp1Card.getCardImageLabel());
								remove(comp2Card.getCardImageLabel());
								remove(comp3Card.getCardImageLabel());
								validate();
								updateUI();

								// Remove card from user's hand
								game.getPlayer1().getHand().remove(userCard);

								// play next hand:
								turns++;
								if (turns == 14)
									start();
								else
									play();
							} else if (game.getLeadingPlayer() == game
									.getPlayer3()) {
								game.getBoard().add(2, userCard);
								game.getPlayer1().getHand().get(i)
										.getCardImageLabel()
										.setBounds(425, 375, 100, 160);

								// comp1 plays a card:
								comp1Card = game.computerPlaysACard(1, turns);
								// add card to board:
								game.getBoard().add(3, comp1Card);
								// remove a card:
								randNum = rand.nextInt(fakeHand1.size());
								remove(fakeHand1.get(randNum).getLabel());
								fakeHand1.remove(randNum);
								// display the card:
								comp1Card.getCardImageLabel().setBounds(350,
										300, 100, 160);
								comp1Card.getCardImageLabel().setVisible(true);
								add(comp1Card.getCardImageLabel());
								validate();
								updateUI();

								String display = game
										.determineWinnerStringVersion(3);
								if (display.equals(game.getPlayer1().getName()))
									userName.setBackground(Color.BLUE);
								if (display.equals(game.getPlayer2().getName()))
									comp1Name.setBackground(Color.BLUE);
								if (display.equals(game.getPlayer3().getName()))
									comp2Name.setBackground(Color.BLUE);
								if (display.equals(game.getPlayer4().getName()))
									comp3Name.setBackground(Color.BLUE);

								JOptionPane.showMessageDialog(null, display
										+ " wins the trick");

								if (display.equals(game.getPlayer1().getName()))
									userName.setBackground(Color.WHITE);
								if (display.equals(game.getPlayer2().getName()))
									comp1Name.setBackground(Color.WHITE);
								if (display.equals(game.getPlayer3().getName()))
									comp2Name.setBackground(Color.WHITE);
								if (display.equals(game.getPlayer4().getName()))
									comp3Name.setBackground(Color.WHITE);

								// remove all cards:
								remove(game.getPlayer1().getHand().get(i)
										.getCardImageLabel());
								remove(comp1Card.getCardImageLabel());
								remove(comp2Card.getCardImageLabel());
								remove(comp3Card.getCardImageLabel());
								validate();
								updateUI();

								// Remove card from user's hand
								game.getPlayer1().getHand().remove(userCard);

								// play next hand:
								turns++;
								if (turns == 14)
									start();
								else
									play();
							} else if (game.getLeadingPlayer() == game
									.getPlayer4()) {
								game.getBoard().add(1, userCard);
								game.getPlayer1().getHand().get(i)
										.getCardImageLabel()
										.setBounds(425, 375, 100, 160);

								// comp1 plays a card:
								comp1Card = game.computerPlaysACard(1, turns);
								// add card to board:
								game.getBoard().add(2, comp1Card);
								// remove a card:
								randNum = rand.nextInt(fakeHand1.size());
								remove(fakeHand1.get(randNum).getLabel());
								fakeHand1.remove(randNum);
								// display the card:
								comp1Card.getCardImageLabel().setBounds(350,
										300, 100, 160);
								comp1Card.getCardImageLabel().setVisible(true);
								add(comp1Card.getCardImageLabel());
								validate();
								updateUI();

								// comp2 plays a card:
								comp2Card = game.computerPlaysACard(2, turns);
								// add card to board:
								game.getBoard().add(3, comp2Card);
								// remove a card:
								randNum = rand.nextInt(fakeHand2.size());
								remove(fakeHand2.get(randNum).getLabel());
								fakeHand2.remove(randNum);
								// display the card:
								comp2Card.getCardImageLabel().setBounds(425,
										225, 100, 160);
								comp2Card.getCardImageLabel().setVisible(true);
								add(comp2Card.getCardImageLabel());
								validate();
								updateUI();
								String display = game
										.determineWinnerStringVersion(4);
								if (display.equals(game.getPlayer1().getName()))
									userName.setBackground(Color.BLUE);
								if (display.equals(game.getPlayer2().getName()))
									comp1Name.setBackground(Color.BLUE);
								if (display.equals(game.getPlayer3().getName()))
									comp2Name.setBackground(Color.BLUE);
								if (display.equals(game.getPlayer4().getName()))
									comp3Name.setBackground(Color.BLUE);

								JOptionPane.showMessageDialog(null, display
										+ " wins the trick");

								if (display.equals(game.getPlayer1().getName()))
									userName.setBackground(Color.WHITE);
								if (display.equals(game.getPlayer2().getName()))
									comp1Name.setBackground(Color.WHITE);
								if (display.equals(game.getPlayer3().getName()))
									comp2Name.setBackground(Color.WHITE);
								if (display.equals(game.getPlayer4().getName()))
									comp3Name.setBackground(Color.WHITE);

								// remove all cards:
								remove(game.getPlayer1().getHand().get(i)
										.getCardImageLabel());
								remove(comp1Card.getCardImageLabel());
								remove(comp2Card.getCardImageLabel());
								remove(comp3Card.getCardImageLabel());
								validate();
								updateUI();

								// Remove card from user's hand
								game.getPlayer1().getHand().remove(userCard);

								// play next hand:
								turns++;
								if (turns == 14)
									start();
								else
									play();
							}

						}
					}

				}
			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}
