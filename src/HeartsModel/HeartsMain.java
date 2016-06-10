package HeartsModel;

import java.util.Collections;

import CardModel.Card;
// runs Text Version of the game:
public class HeartsMain {

	public static void main(String[] args) {
		HeartsModel game = new HeartsModel("human", "comp1", "comp2", "comp3");

		while (game.getPlaying()) {
			game.getDeck().shuffle();
			game.getDeck().deal(game.getPlayer1(), game.getPlayer2(),
					game.getPlayer3(), game.getPlayer4());
			// Organize human hand:
			game.sort(game.getPlayer1().getHand());
			game.sort(game.getPlayer2().getHand());
			game.sort(game.getPlayer3().getHand());
			game.sort(game.getPlayer4().getHand());
			Collections.sort(game.getPlayer1().getHand());
			Collections.sort(game.getPlayer2().getHand());
			Collections.sort(game.getPlayer3().getHand());
			Collections.sort(game.getPlayer4().getHand());
			
			// Pass Cards:
			//game.passCards(game.getRoundsPlayed());
			// Organize all hands:
			game.sort(game.getPlayer1().getHand());
			game.sort(game.getPlayer2().getHand());
			game.sort(game.getPlayer3().getHand());
			game.sort(game.getPlayer4().getHand());
			Collections.sort(game.getPlayer1().getHand());
			Collections.sort(game.getPlayer2().getHand());
			Collections.sort(game.getPlayer3().getHand());
			Collections.sort(game.getPlayer4().getHand());
			
			// Play a Hand:
			for (int turns = 1; turns <= 13; turns++) {

				// display the game:
				game.displayGame();

				// play the game:
				System.out.println();
				// see who goes first:
				if (turns == 1)
					game.setLeadingPlayer(game.playerWithTheTwoOfClubs());

				if (game.getLeadingPlayer() == game.getPlayer1()) {
					
					// human plays a card:
					Card userCard;
					userCard = game.humanPlaysACard(turns, "");
					// add card to board:
					game.getBoard().add(0, userCard);

					// comp1 plays a card:
					Card comp1Card;
					comp1Card = game.computerPlaysACard(1, turns);
					// add card to board:
					game.getBoard().add(1, comp1Card);
					
					// comp2 plays a card:
					Card comp2Card;
					comp2Card = game.computerPlaysACard(2, turns);
					// add card to board:
					game.getBoard().add(2, comp2Card);

					// comp3 plays a card:
					Card comp3Card;
					comp3Card = game.computerPlaysACard(3, turns);
					game.getBoard().add(3, comp3Card);


					// display board:
					for (int i = 0; i < game.getBoard().size(); i++)
						System.out.println("Board is "
								+ game.getBoard().get(i).toString());

					// see who won the trick:
					game.determineWinner(1);

					// display cards in old hands:
					game.displayOldCards();

					// /////////////////////////////////////////
					// Now dealing with lead is comp1:

				} else if (game.getLeadingPlayer() == game.getPlayer2()) {
					System.out.println("comp1 goes");
					// comp1 plays a card:
					Card comp1Card;
					comp1Card = game.computerPlaysACard(1, turns);
					// add card to board:
					game.getBoard().add(0, comp1Card);

					// comp2 plays a card:
					Card comp2Card;
					comp2Card = game.computerPlaysACard(2, turns);
					// add card to board:
					game.getBoard().add(1, comp2Card);

					// comp3 plays a card:
					Card comp3Card;
					comp3Card = game.computerPlaysACard(3, turns);
					// add card to board:
					game.getBoard().add(2, comp3Card);

					// human plays a card:
					Card userCard;
					userCard = game.humanPlaysACard(turns, "");
					// add card to board:
					game.getBoard().add(3, userCard);

					// display board:
					for (int i = 0; i < game.getBoard().size(); i++)
						System.out.println("Board is "
								+ game.getBoard().get(i).toString());

					// see who won the trick:
					game.determineWinner(2);
					System.out.println();

					// display cards in old hands:
					game.displayOldCards();

				}

				// /////////////////////////////////////////
				// Now dealing with lead is comp2:

				else if (game.getLeadingPlayer() == game.getPlayer3()) {
					System.out.println("comp2 goes");
					// comp2 plays a card:
					Card comp2Card;
					comp2Card = game.computerPlaysACard(2, turns);
					// add card to board:
					game.getBoard().add(0, comp2Card);
					
					// comp3 plays a card:
					Card comp3Card;
					comp3Card = game.computerPlaysACard(3, turns);
					// add card to board:
					game.getBoard().add(1, comp3Card);

					// Human goes:
					Card userCard;
					userCard = game.humanPlaysACard(turns, "");
					// add card to board:
					game.getBoard().add(2, userCard);

					// comp1 plays a card:
					Card comp1Card;
					comp1Card = game.computerPlaysACard(1, turns);
					// add card to board:
					game.getBoard().add(3, comp1Card);

					// display board:
					for (int i = 0; i < game.getBoard().size(); i++)
						System.out.println("Board is "
								+ game.getBoard().get(i).toString());

					// see who won the trick:
					game.determineWinner(3);
					System.out.println();

					// display cards in old hands:
					game.displayOldCards();

				}
				// /////////////////////////////////////////
				// Now dealing with lead is comp3:

				else if (game.getLeadingPlayer() == game.getPlayer4()) {
					System.out.println("comp3 goes");
					// comp3 plays a card:
					Card comp3Card;
					comp3Card = game.computerPlaysACard(3, turns);
					// add card to board:
					game.getBoard().add(0, comp3Card);

					// Human goes:
					Card userCard;
					userCard = game.humanPlaysACard(turns, "");
					// add card to board:
					game.getBoard().add(1, userCard);

					// comp1 plays a card:
					Card comp1Card;
					comp1Card = game.computerPlaysACard(1, turns);
					// add card to board:
					game.getBoard().add(2, comp1Card);

					// comp2 plays a card:
					Card comp2Card;
					comp2Card = game.computerPlaysACard(2, turns);
					// add card to board:
					game.getBoard().add(3, comp2Card);

					// display board:
					for (int i = 0; i < game.getBoard().size(); i++)
						System.out.println("Board is "
								+ game.getBoard().get(i).toString());

					// see who won the trick:
					game.determineWinner(4);
					System.out.println();

					// display cards in old hands:
					game.displayOldCards();

				}

			}
			
			// Display and calculate points:
			game.calculatePoints();
			game.displayPoints();
			// Update playing to see if the game has ended:
			game.updatePlaying();
			// Get ready for the next hand:
			game.reset();

		}
	}
}
