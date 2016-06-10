package HeartsModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import CardModel.Card;
import CardModel.Deck;
import CardModel.Player;
import CardModel.Rank;
import CardModel.Suit;

public class HeartsModel {

	private Deck deck;
	private Player human;
	private Player comp1;
	private Player comp2;
	private Player comp3;
	private Player leadingPlayer;
	private boolean playing;
	private boolean heartsBroken;
	private ArrayList<Card> board;
	private int roundsPlayed;

	public HeartsModel(String player1Name, String player2Name, String player3Name, String player4Name) {
		human = new Player(player1Name);
		comp1 = new Player(player2Name);
		comp2 = new Player(player3Name);
		comp3 = new Player(player4Name);
		leadingPlayer = null;
		playing = true;
		heartsBroken = false;
		board = new ArrayList<Card>();
		deck = new Deck();
		roundsPlayed = 1;
	}

	public Card humanPlaysACard(int turns, String str) {
		if (str.equals("")) {
		System.out.println("Select a card: ");
		Scanner scan = new Scanner(System.in);
		String userSelection = scan.nextLine();
		Card userCard = null;
		while (userCard == null) {
			for (int i = 0; i < human.getHand().size(); i++) {
				if (human.getCardAsString(i).equals(userSelection)) {
					userCard = human.getHand().get(i);
					// Check validity:
					if (!canPlayCard(userCard, human.getHand(), leadingPlayer,
							human, turns)) {
						userCard = null;
					}
					break;

				}
			}
			if (userCard == null) {
				System.out.println("Try Again:");
				userSelection = scan.nextLine();
			}
		}
		// user has selected a card at this point:
		// display that selection:
		System.out.println("User Selected " + userCard.toString());

		// remove card from user hand:
		human.getHand().remove(userCard);
		return userCard;
		}
		else {
			Card userCard = null;
			for (int i = 0; i < human.getHand().size(); i++) {
				if (str.equals(human.getHand().get(i).toString())) {
					userCard = human.getHand().get(i);
					return userCard;
				}
			}
			return null;
		}
	}

	public Card computerPlaysACard(int computerNumber, int turns) {
		if (computerNumber == 1) {
			Card comp1Card = null;
			for (int i = 0; i < comp1.getHand().size(); i++) {
				comp1Card = comp1.getHand().get(i);
				if (canPlayCard(comp1Card, comp1.getHand(), leadingPlayer,
						comp1, turns)) {
					break;
				}
			}
			// Show comp1 selection:
			//System.out.println("Computer One Selected " + comp1Card.toString());

			// remove card from comp1 hand:
			comp1.getHand().remove(comp1Card);
			// return the card:
			return comp1Card;

		} else if (computerNumber == 2) {
			Card comp2Card = null;
			for (int i = 0; i < comp2.getHand().size(); i++) {
				comp2Card = comp2.getHand().get(i);
				if (canPlayCard(comp2Card, comp2.getHand(), leadingPlayer,
						comp2, turns)) {
					break;
				}
			}
			// Show comp2 selection:
			//System.out.println("Computer Two Selected " + comp2Card.toString());

			// remove card from comp2 hand:
			comp2.getHand().remove(comp2Card);
			// return the card:
			return comp2Card;

		} else if (computerNumber == 3) {
			Card comp3Card = null;
			for (int i = 0; i < comp3.getHand().size(); i++) {
				comp3Card = comp3.getHand().get(i);
				if (canPlayCard(comp3Card, comp3.getHand(), leadingPlayer,
						comp3, turns)) {
					break;
				}
			}
			// Show comp3 selection:
			//System.out.println("Computer Three Selected " + comp3Card.toString());

			// remove card from comp3 hand:
			comp3.getHand().remove(comp3Card);
			// return the card:
			return comp3Card;
		} else {
			return null;
		}
	}

	public void determineWinner(int playerNumber) {
		Card winner = board.get(0);
		Suit lead = winner.getSuit();
		int leadValue = winner.getRank().getValue();
		for (int i = 1; i < board.size(); i++) {
			if (board.get(i).getSuit() == lead) {
				if (board.get(i).getRank().getValue() > leadValue) {
					winner = board.get(i);
					leadValue = winner.getRank().getValue();
				}
			}
		}

		if (playerNumber == 1) {
			System.out.println("The " + winner.toString() + " wins the hand");
			// We know that the human lead off, so we know who played
			// what in board
			if (winner == board.get(0)) {
				System.out.println(human.getName() + " wins the hand");
				// Cards in board go to player's other hand
				for (int i = 0; i < 4; i++)
					human.getOldCards().add(board.get(i));
				// Clear the board
				board.clear();
				// Assign the leading player
				leadingPlayer = human;
			}

			else if (winner == board.get(1)) {
				System.out.println(comp1.getName() + " wins the hand");
				// Cards in board go to comp1 other hand
				for (int i = 0; i < 4; i++)
					comp1.getOldCards().add(board.get(i));
				// Clear the board
				board.clear();
				// Assign the leading player
				leadingPlayer = comp1;
			} else if (winner == board.get(2)) {
				System.out.println(comp2.getName() + " wins the hand");
				// Cards in board go to comp2 other hand
				for (int i = 0; i < 4; i++)
					comp2.getOldCards().add(board.get(i));
				// Clear the board
				board.clear();
				// Assign the leading player
				leadingPlayer = comp2;

			} else if (winner == board.get(3)) {
				System.out.println(comp3.getName() + " wins the hand");
				// Cards in board go to comp3 other hand
				for (int i = 0; i < 4; i++)
					comp3.getOldCards().add(board.get(i));
				// Clear the board
				board.clear();
				// Assign the leading player
				leadingPlayer = comp3;

			}
		} else if (playerNumber == 2) {
			System.out.println("The " + winner.toString() + " wins the hand");

			// comp1 is leading card, board[0]
			if (winner == board.get(0)) {
				System.out.println(comp1.getName() + " wins the hand");
				// Cards in board go to player's other hand
				for (int i = 0; i < 4; i++)
					comp1.getOldCards().add(board.get(i));
				// Clear the board
				board.clear();
				// Assign the leading player
				leadingPlayer = comp1;
			} else {
				if (winner == board.get(1)) {
					System.out.println(comp2.getName() + " wins the hand");
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						comp2.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = comp2;
				} else if (winner == board.get(2)) {
					System.out.println(comp3.getName() + " wins the hand");
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						comp3.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = comp3;

				} else if (winner == board.get(3)) {
					System.out.println(human.getName() + " wins the hand");
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						human.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = human;

				}
			}
		} else if (playerNumber == 3) {
			System.out.println("The " + winner.toString() + " wins the hand");

			// comp2 is leading card, board[0]
			if (winner == board.get(0)) {
				System.out.println(comp2.getName() + " wins the hand");
				// Cards in board go to player's other hand
				for (int i = 0; i < 4; i++)
					comp2.getOldCards().add(board.get(i));
				// Clear the board
				board.clear();
				// Assign the leading player
				leadingPlayer = comp2;
			} else {
				if (winner == board.get(1)) {
					System.out.println(comp3.getName() + " wins the hand");
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						comp3.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = comp3;
				} else if (winner == board.get(2)) {
					System.out.println(human.getName() + " wins the hand");
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						human.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = human;

				} else if (winner == board.get(3)) {
					System.out.println(comp1.getName() + " wins the hand");
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						comp1.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = comp1;

				}
			}
		} else {
			System.out.println("The " + winner.toString() + " wins the hand");
			// Find out who played that card:
			// comp3 is leading card, board[0]
			if (winner == board.get(0)) {
				System.out.println(comp3.getName() + " wins the hand");
				// Cards in board go to player's other hand
				for (int i = 0; i < 4; i++)
					comp3.getOldCards().add(board.get(i));
				// Clear the board
				board.clear();
				// Assign the leading player
				leadingPlayer = comp3;
			} else {
				if (winner == board.get(1)) {
					System.out.println(human.getName() + " wins the hand");
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						human.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = human;
				} else if (winner == board.get(2)) {
					System.out.println(comp1.getName() + " wins the hand");
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						comp1.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = comp1;

				} else if (winner == board.get(3)) {
					System.out.println(comp2.getName() + " wins the hand");
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						comp2.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = comp2;

				}
			}

		}

	}
	public String determineWinnerStringVersion(int playerNumber) {
		Card winner = board.get(0);
		Suit lead = winner.getSuit();
		int leadValue = winner.getRank().getValue();
		for (int i = 1; i < board.size(); i++) {
			if (board.get(i).getSuit() == lead) {
				if (board.get(i).getRank().getValue() > leadValue) {
					winner = board.get(i);
					leadValue = winner.getRank().getValue();
				}
			}
		}

		if (playerNumber == 1) {
			// We know that the human lead off, so we know who played
			// what in board
			if (winner == board.get(0)) {
				// Cards in board go to player's other hand
				for (int i = 0; i < 4; i++)
					human.getOldCards().add(board.get(i));
				// Clear the board
				board.clear();
				// Assign the leading player
				leadingPlayer = human;
				return human.getName();
			}

			else if (winner == board.get(1)) {
				// Cards in board go to comp1 other hand
				for (int i = 0; i < 4; i++)
					comp1.getOldCards().add(board.get(i));
				// Clear the board
				board.clear();
				// Assign the leading player
				leadingPlayer = comp1;
				return comp1.getName();
				
			} else if (winner == board.get(2)) {
				// Cards in board go to comp2 other hand
				for (int i = 0; i < 4; i++)
					comp2.getOldCards().add(board.get(i));
				// Clear the board
				board.clear();
				// Assign the leading player
				leadingPlayer = comp2;
				return comp2.getName();

			} else if (winner == board.get(3)) {
				// Cards in board go to comp3 other hand
				for (int i = 0; i < 4; i++)
					comp3.getOldCards().add(board.get(i));
				// Clear the board
				board.clear();
				// Assign the leading player
				leadingPlayer = comp3;
				return comp3.getName();

			}
		} else if (playerNumber == 2) {
			// comp1 is leading card, board[0]
			if (winner == board.get(0)) {
				// Cards in board go to player's other hand
				for (int i = 0; i < 4; i++)
					comp1.getOldCards().add(board.get(i));
				// Clear the board
				board.clear();
				// Assign the leading player
				leadingPlayer = comp1;
				return comp1.getName();
				
			} else {
				if (winner == board.get(1)) {
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						comp2.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = comp2;
					return comp2.getName();
					
				} else if (winner == board.get(2)) {
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						comp3.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = comp3;
					return comp3.getName();

				} else if (winner == board.get(3)) {
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						human.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = human;
					return human.getName();
				}
			}
		} else if (playerNumber == 3) {
			// comp2 is leading card, board[0]
			if (winner == board.get(0)) {
				// Cards in board go to player's other hand
				for (int i = 0; i < 4; i++)
					comp2.getOldCards().add(board.get(i));
				// Clear the board
				board.clear();
				// Assign the leading player
				leadingPlayer = comp2;
				return comp2.getName();
			} else {
				if (winner == board.get(1)) {
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						comp3.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = comp3;
					return comp3.getName();
					
				} else if (winner == board.get(2)) {
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						human.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = human;
					return human.getName();

				} else if (winner == board.get(3)) {
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						comp1.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = comp1;
					return comp1.getName();

				}
			}
		} else {
			// Find out who played that card:
			// comp3 is leading card, board[0]
			if (winner == board.get(0)) {
				// Cards in board go to player's other hand
				for (int i = 0; i < 4; i++)
					comp3.getOldCards().add(board.get(i));
				// Clear the board
				board.clear();
				// Assign the leading player
				leadingPlayer = comp3;
				return comp3.getName();
			} else {
				if (winner == board.get(1)) {
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						human.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = human;
					return human.getName();
					
				} else if (winner == board.get(2)) {
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						comp1.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = comp1;
					return comp1.getName();

				} else if (winner == board.get(3)) {
					// Cards in board go to player's other hand
					for (int i = 0; i < 4; i++)
						comp2.getOldCards().add(board.get(i));
					// Clear the board
					board.clear();
					// Assign the leading player
					leadingPlayer = comp2;
					return comp2.getName();

				}
			}

		}
		return "";

	}

	public void displayGame() {
		System.out.println("Human hand:");
		for (int i = 0; i < human.getHand().size(); i++)
			System.out.print(human.getCardAsString(i) + " ");
		System.out.println();
		System.out.println("Computer One hand:");
		for (int i = 0; i < comp1.getHand().size(); i++)
			System.out.print(comp1.getCardAsString(i) + " ");
		System.out.println();
		System.out.println("Computer Two hand:");
		for (int i = 0; i < comp2.getHand().size(); i++)
			System.out.print(comp2.getCardAsString(i) + " ");
		System.out.println();
		System.out.println("Computer Three hand:");
		for (int i = 0; i < comp3.getHand().size(); i++)
			System.out.print(comp3.getCardAsString(i) + " ");
		System.out.println();
	}

	public void displayOldCards() {
		System.out.println();
		System.out.println("Old Cards for human:");
		for (int i = 0; i < human.getOldCards().size(); i++)
			System.out.print(human.getOldCardsCardAsString(i) + " ");
		System.out.println();
		System.out.println("Old Cards for comp1:");
		for (int i = 0; i < comp1.getOldCards().size(); i++)
			System.out.print(comp1.getOldCardsCardAsString(i) + " ");
		System.out.println();
		System.out.println("Old Cards for comp2:");
		for (int i = 0; i < comp2.getOldCards().size(); i++)
			System.out.print(comp2.getOldCardsCardAsString(i) + " ");
		System.out.println();
		System.out.println("Old Cards for comp3:");
		for (int i = 0; i < comp3.getOldCards().size(); i++)
			System.out.print(comp3.getOldCardsCardAsString(i) + " ");
		System.out.println();
	}

	public void passCards(int roundsPlayed) {

		// display the game:
		System.out.println("Human hand:");
		for (int i = 0; i < human.getHand().size(); i++)
			System.out.print(human.getCardAsString(i) + " ");
		System.out.println();
		System.out.println("Computer One hand:");
		for (int i = 0; i < comp1.getHand().size(); i++)
			System.out.print(comp1.getCardAsString(i) + " ");
		System.out.println();
		System.out.println("Computer Two hand:");
		for (int i = 0; i < comp2.getHand().size(); i++)
			System.out.print(comp2.getCardAsString(i) + " ");
		System.out.println();
		System.out.println("Computer Three hand:");
		for (int i = 0; i < comp3.getHand().size(); i++)
			System.out.print(comp3.getCardAsString(i) + " ");

		System.out.println();
		
		// Pass Cards Direction:
		if (roundsPlayed == 1 || roundsPlayed == 5 || roundsPlayed == 9
				|| roundsPlayed == 13) {
			System.out.println("Pass three cards left: ");
		}
		if (roundsPlayed == 2 || roundsPlayed == 6 || roundsPlayed == 10) {
			System.out.println("Pass three cards right: ");
		}
		if (roundsPlayed == 3 || roundsPlayed == 7 || roundsPlayed == 11) {
			System.out.println("Pass three cards across: ");
		}
		if (roundsPlayed == 4 || roundsPlayed == 8 || roundsPlayed == 12) {
			System.out.println("No Passing...: ");
		}
		ArrayList<Card> temp = new ArrayList<Card>();

		System.out.println("Select first card: ");
		Scanner scan = new Scanner(System.in);
		String userSelection = scan.nextLine();
		Card firstUserCard = null;
		while (firstUserCard == null) {
			for (int i = 0; i < human.getHand().size(); i++) {
				if (human.getCardAsString(i).equals(userSelection)) {
					firstUserCard = human.getHand().get(i);
					human.getHand().remove(i);
					break;
				}
			}
			if (firstUserCard == null) {
				System.out.println("Try Again:");
				userSelection = scan.nextLine();
			}
		}
		temp.add(firstUserCard);

		System.out.println("Select second card: ");
		userSelection = scan.nextLine();
		Card secondUserCard = null;
		while (secondUserCard == null) {
			for (int i = 0; i < human.getHand().size(); i++) {
				if (human.getCardAsString(i).equals(userSelection)) {
					secondUserCard = human.getHand().get(i);
					human.getHand().remove(i);
					break;
				}
			}
			if (secondUserCard == null) {
				System.out.println("Try Again:");
				userSelection = scan.nextLine();
			}
		}
		temp.add(secondUserCard);

		System.out.println("Select third card: ");
		userSelection = scan.nextLine();
		Card thirdUserCard = null;
		while (thirdUserCard == null) {
			for (int i = 0; i < human.getHand().size(); i++) {
				if (human.getCardAsString(i).equals(userSelection)) {
					thirdUserCard = human.getHand().get(i);
					human.getHand().remove(i);
					break;
				}
			}
			if (thirdUserCard == null) {
				System.out.println("Try Again:");
				userSelection = scan.nextLine();
			}
		}
		temp.add(thirdUserCard);

		// Display human hand before passing them:
		System.out.println("Cards before passing: ");
		for (int i = 0; i < human.getHand().size(); i++)
			System.out.print(human.getCardAsString(i) + " ");

		// This method just gives the computer to the left three cards, and
		// gives
		// the user the first three cards of the computer to the left:

		// Add cards from temp to end of comp1
		for (int i = 0; i < temp.size(); i++)
			comp1.getHand().add(temp.get(i));

		// Clear temp:
		temp.clear();
		// Add first three Cards of comp1's hand to temp, and then remove them:
		temp.add(comp1.getHand().get(0));
		comp1.getHand().remove(0);
		temp.add(comp1.getHand().get(0));
		comp1.getHand().remove(0);
		temp.add(comp1.getHand().get(0));
		comp1.getHand().remove(0);
		System.out.println();

		// display cards about to be received:
		System.out.println("You will get these cards: ");
		for (int i = 0; i < temp.size(); i++)
			System.out.println(temp.get(i).toString());

		// Add cards from temp to end of human hand:
		for (int i = 0; i < temp.size(); i++)
			human.getHand().add(temp.get(i));

		// sort the hands that changed:
		sort(human.getHand());
		sort(comp1.getHand());
		System.out.println();

	}

	public void reset() {
		// Clear all OldCards:
		human.getOldCards().clear();
		comp1.getOldCards().clear();
		comp2.getOldCards().clear();
		comp3.getOldCards().clear();
		// reset heartsBorken:
		heartsBroken = false;
		// increment roundPlayed so that passing works:
		roundsPlayed++;
	}

	public void calculatePoints() {
		// Check for moon shoot:
		int player1Points = 0;
		int player2Points = 0;
		int player3Points = 0;
		int player4Points = 0;

		for (int i = 0; i < human.getOldCards().size(); i++) {
			if (human.getOldCards().get(i).getSuit() == Suit.Hearts)
				player1Points++;
			if (human.getOldCards().get(i).getSuit() == Suit.Spades
					&& human.getOldCards().get(i).getRank() == Rank.Queen)
				player1Points = player1Points + 13;
		}
		for (int i = 0; i < comp1.getOldCards().size(); i++) {
			if (comp1.getOldCards().get(i).getSuit() == Suit.Hearts)
				player2Points++;
			if (comp1.getOldCards().get(i).getSuit() == Suit.Spades
					&& comp1.getOldCards().get(i).getRank() == Rank.Queen)
				player2Points = player2Points + 13;
		}
		for (int i = 0; i < comp2.getOldCards().size(); i++) {
			if (comp2.getOldCards().get(i).getSuit() == Suit.Hearts)
				player3Points++;
			if (comp2.getOldCards().get(i).getSuit() == Suit.Spades
					&& comp2.getOldCards().get(i).getRank() == Rank.Queen)
				player3Points = player3Points + 13;
		}
		for (int i = 0; i < comp3.getOldCards().size(); i++) {
			if (comp3.getOldCards().get(i).getSuit() == Suit.Hearts)
				player4Points++;
			if (comp3.getOldCards().get(i).getSuit() == Suit.Spades
					&& comp3.getOldCards().get(i).getRank() == Rank.Queen)
				player4Points = player4Points + 13;
		}

		if (player1Points == 26) {
			// Add 26 points to all computers:
			comp1.setPoints(comp1.getPoints() + 26);
			comp2.setPoints(comp2.getPoints() + 26);
			comp3.setPoints(comp3.getPoints() + 26);
		} else if (player2Points == 26) {
			// Add 26 points to human and comp2 and comp3:
			human.setPoints(human.getPoints() + 26);
			comp2.setPoints(comp2.getPoints() + 26);
			comp3.setPoints(comp3.getPoints() + 26);
		} else if (player3Points == 26) {
			// Add 26 points to human and comp1 and comp3
			human.setPoints(human.getPoints() + 26);
			comp1.setPoints(comp1.getPoints() + 26);
			comp3.setPoints(comp3.getPoints() + 26);
		} else if (player4Points == 26) {
			// Add 26 points to human and comp1 and comp2
			human.setPoints(human.getPoints() + 26);
			comp1.setPoints(comp1.getPoints() + 26);
			comp2.setPoints(comp2.getPoints() + 26);
		} else {
			// Calculate points, no moon shoot:
			for (int i = 0; i < human.getOldCards().size(); i++) {
				if (human.getOldCards().get(i).getSuit() == Suit.Hearts)
					human.setPoints(human.getPoints() + 1);
				if (human.getOldCards().get(i).getSuit() == Suit.Spades
						&& human.getOldCards().get(i).getRank() == Rank.Queen)
					human.setPoints(human.getPoints() + 13);
			}
			for (int i = 0; i < comp1.getOldCards().size(); i++) {
				if (comp1.getOldCards().get(i).getSuit() == Suit.Hearts)
					comp1.setPoints(comp1.getPoints() + 1);
				if (comp1.getOldCards().get(i).getSuit() == Suit.Spades
						&& comp1.getOldCards().get(i).getRank() == Rank.Queen)
					comp1.setPoints(comp1.getPoints() + 13);
			}
			for (int i = 0; i < comp2.getOldCards().size(); i++) {
				if (comp2.getOldCards().get(i).getSuit() == Suit.Hearts)
					comp2.setPoints(comp2.getPoints() + 1);
				if (comp2.getOldCards().get(i).getSuit() == Suit.Spades
						&& comp2.getOldCards().get(i).getRank() == Rank.Queen)
					comp2.setPoints(comp2.getPoints() + 13);
			}
			for (int i = 0; i < comp3.getOldCards().size(); i++) {
				if (comp3.getOldCards().get(i).getSuit() == Suit.Hearts)
					comp3.setPoints(comp3.getPoints() + 1);
				if (comp3.getOldCards().get(i).getSuit() == Suit.Spades
						&& comp3.getOldCards().get(i).getRank() == Rank.Queen)
					comp3.setPoints(comp3.getPoints() + 13);
			}
		}
	}

	public void updatePlaying() {
		if (human.getPoints() >= 100 || comp1.getPoints() >= 100
				|| comp2.getPoints() >= 100 || comp3.getPoints() >= 100) {
			displayWinner();
			playing = false;
		}

	}
	
	public void updatePlayingGUI() {
		if (human.getPoints() >= 100 || comp1.getPoints() >= 100
				|| comp2.getPoints() >= 100 || comp3.getPoints() >= 100) {
			playing = false;
		}

	}

	public void displayWinner() {
		int oneMin = Math.min(human.getPoints(), comp1.getPoints());
		int twoMin = Math.min(comp2.getPoints(), comp3.getPoints());
		int overallMin = Math.min(oneMin, twoMin);
		if (overallMin == human.getPoints())
			System.out.println("YOU WIN THE GAME!");
		else if (overallMin == comp1.getPoints())
			System.out.println("Computer one wins the game...");
		else if (overallMin == comp2.getPoints())
			System.out.println("Computer two wins the game...");
		else if (overallMin == comp3.getPoints())
			System.out.println("Computer three wins the game...");

	}
	public String displayWinnerStringVersion() {
		int oneMin = Math.min(human.getPoints(), comp1.getPoints());
		int twoMin = Math.min(comp2.getPoints(), comp3.getPoints());
		int overallMin = Math.min(oneMin, twoMin);
		if (overallMin == human.getPoints())
			return human.getName() + " WINS THE GAME!!!!! PENIS!!";
		else if (overallMin == comp1.getPoints())
			return comp1.getName() + " WINS THE GAME!!!!! WOOPS!!";
		else if (overallMin == comp2.getPoints())
			return comp2.getName() + " WINS THE GAME!!!!! TERD CITY!!";
		else if (overallMin == comp3.getPoints())
			return comp3.getName() + " WINS THE GAME!!!!! YOU SUCK!!";
		else
			return "";
	}

	public void displayPoints() {
		System.out.println("Human Points: " + human.getPoints());
		System.out.println("Computer One Points: " + comp1.getPoints());
		System.out.println("Computer Two Points: " + comp2.getPoints());
		System.out.println("Computer Three Points: " + comp3.getPoints());
		System.out.println();
	}
	
	public Player playerWithTheTwoOfClubs() {
		Player playerWithTwoOfClubs = null;
		for (int i = 0; i < 13; i++) {
			if (human.getHand().get(i).getRank().getValue() == 2
					&& human.getHand().get(i).getSuit() == Suit.Clubs)
				playerWithTwoOfClubs = human;
			if (comp1.getHand().get(i).getRank().getValue() == 2
					&& comp1.getHand().get(i).getSuit() == Suit.Clubs)
				playerWithTwoOfClubs = comp1;
			if (comp2.getHand().get(i).getRank().getValue() == 2
					&& comp2.getHand().get(i).getSuit() == Suit.Clubs)
				playerWithTwoOfClubs = comp2;
			if (comp3.getHand().get(i).getRank().getValue() == 2
					&& comp3.getHand().get(i).getSuit() == Suit.Clubs)
				playerWithTwoOfClubs = comp3;
		}
		return playerWithTwoOfClubs;
	}

	public boolean canPlayCard(Card userCard, ArrayList<Card> handHoldingCard,
			Player lead, Player whosPlaying, int turn) {
		if (turn == 1) {
			if (lead == whosPlaying) {
				if (!(userCard.getSuit() == Suit.Clubs && userCard.getRank()
						.getValue() == 2)) {
					return false;
				}
			}
			// If you aren't the leading player, you have to follow suit, unless
			// you don't have clubs, then you can play anything except the queen
			// of spades or hearts, unless its all you have:
			else {
				int numberOfClubs = 0;
				for (int i = 0; i < handHoldingCard.size(); i++) {
					if (handHoldingCard.get(i).getSuit() == Suit.Clubs) {
						numberOfClubs++;
					}
				}
				// Have to play a club here:
				if (numberOfClubs > 0) {
					if (userCard.getSuit() != Suit.Clubs)
						return false;
				}
				// Don't have any clubs, can't play a trick unless there is no
				// other choice:
				else {
					int notHearts = 0;
					for (int i = 0; i < handHoldingCard.size(); i++) {
						if (handHoldingCard.get(i).getSuit() != Suit.Hearts
								|| (handHoldingCard.get(i).getSuit() != Suit.Spades && handHoldingCard
										.get(i).getRank() != Rank.Queen)) {
							notHearts++;
						}
					}
					// Have stuff that isn't Hearts, must play a non-heart here:
					if (notHearts > 0) {
						if (userCard.getSuit() == Suit.Hearts || (userCard.getSuit() == Suit.Spades && userCard.getRank() == Rank.Queen)) {
							return false;
						}
					}
					// All cards are hearts
					else {
						return true;
					}

				}

			}

		}
		// If all you have is hearts or all hearts and the queen of spades, you
		// can play right away no matter what:
		int numHearts = 0;
		for (int i = 0; i < handHoldingCard.size(); i++) {
			if (handHoldingCard.get(i).getSuit() == Suit.Hearts
					|| (handHoldingCard.get(i).getSuit() == Suit.Spades && handHoldingCard
							.get(i).getRank() == Rank.Queen))
				numHearts++;
		}
		if (numHearts == handHoldingCard.size()) {
			// All you have is hearts, return true and break hearts:
			heartsBroken = true;
			return true;
		}

		if (board.isEmpty()) {
			// Nobody has played any cards yet, make sure they don't play a
			// heart, or the queen, if hearts have not been broken:
			if (heartsBroken == false) {
				if (userCard.getSuit() == Suit.Hearts
						|| (userCard.getSuit() == Suit.Spades && userCard
								.getRank() == Rank.Queen)) {
					// System.out.println("Cant play that, Hearts have not been broken yet, ");
					return false;
				}
			}
			// Hearts have been broken, the user can play any card
			leadingPlayer = whosPlaying;
			return true;
		} else {
			// board is not empty so there is at least one card in board:
			Suit leadingSuit = board.get(0).getSuit();
			// Check to see if the current player can follow suit:
			int times = 0;
			for (int i = 0; i < handHoldingCard.size(); i++) {
				if (handHoldingCard.get(i).getSuit() == leadingSuit)
					times++;
			}
			// I have to follow suit here
			if (times > 0) {
				// Didnt follow suit, cant do this because they have the leading
				// suit
				if (userCard.getSuit() != leadingSuit) {
					// System.out.println("Cant play that, you must follow Suit, ");
					return false;
				}
			}
			// Dont have to follow suit, can play whatever card you want
			// Check to see if this causes hearts to be broken:
			if (userCard.getSuit() == Suit.Hearts
					|| (userCard.getSuit() == Suit.Spades && userCard.getRank() == Rank.Queen)) {
				heartsBroken = true;
			}
			return true;
		}

	}

	public boolean getPlaying() {
		return playing;
	}

	public void setPlaying(boolean newValue) {
		playing = newValue;
	}

	public boolean getHeartsBroken() {
		return heartsBroken;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck newDeck) {
		deck = newDeck;
	}

	public ArrayList<Card> getBoard() {
		return board;
	}

	public Player getPlayer1() {
		return human;
	}

	public Player getPlayer2() {
		return comp1;
	}

	public Player getPlayer3() {
		return comp2;
	}

	public Player getPlayer4() {
		return comp3;
	}

	public Player getLeadingPlayer() {
		return leadingPlayer;
	}

	public void setLeadingPlayer(Player newPlayer) {
		leadingPlayer = newPlayer;
	}

	public int getRoundsPlayed() {
		return roundsPlayed;
	}

	public void setRoundsPlayed(int newValue) {
		roundsPlayed = newValue;
	}

	public void sort(ArrayList<Card> handToBeSorted) {
		// Organize by suit first:
		int spot = 0;
		for (int i = 0; i < handToBeSorted.size(); i++) {
			if (handToBeSorted.get(i).getSuit() == Suit.Hearts) {
				// Put this card at front of hand1:
				Card temp = new Card(handToBeSorted.get(i).getRank(),
						handToBeSorted.get(i).getSuit(),handToBeSorted.get(i).getCardImageLabel());

				handToBeSorted.remove(i);
				handToBeSorted.add(0, temp);
				spot++;
			}
		}
		for (int i = 0; i < handToBeSorted.size(); i++) {
			if (handToBeSorted.get(i).getSuit() == Suit.Spades) {
				// Put this card at front of hand1:
				Card temp = new Card(handToBeSorted.get(i).getRank(),
						handToBeSorted.get(i).getSuit(),handToBeSorted.get(i).getCardImageLabel());

				handToBeSorted.remove(i);
				handToBeSorted.add(spot, temp);
				spot++;
			}
		}
		for (int i = 0; i < handToBeSorted.size(); i++) {
			if (handToBeSorted.get(i).getSuit() == Suit.Diamonds) {
				// Put this card at front of hand1:
				Card temp = new Card(handToBeSorted.get(i).getRank(),
						handToBeSorted.get(i).getSuit(),handToBeSorted.get(i).getCardImageLabel());

				handToBeSorted.remove(i);
				handToBeSorted.add(spot, temp);
				spot++;
			}
		}
		// Now organize by number:

	}

}
