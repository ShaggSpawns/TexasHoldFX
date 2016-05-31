package game;

import resource.CardImageView;

public class Card {
	private final Rank rank;
	private final Suit suit;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public CardImageView getView(double scale, boolean isShowing) {
		return new CardImageView(this, scale, isShowing);
	}

	public CardImageView getView(boolean isShowing) {
		return new CardImageView(this, 1, isShowing);
	}

	@Override
	public String toString() {
		return "(" + rank.name() + ", " + suit.toString() + ")";
	}
}
