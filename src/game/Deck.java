package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private final List<Card> cards = new ArrayList<>();
	private int size;

	public Deck() {
		for (Rank r: Rank.values()) {
			for (Suit s: Suit.values()) {
				cards.add(new Card(r, s));
			}
		}
		shuffle();
	}

	public Card drawCard() {
		if (size == 0) {
			System.out.println("Out of cards, shuffling");
			shuffle();
		}
		return cards.get(--size);
	}

	public List<Card> drawCards(int numCards) {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < numCards; i++)
			cards.add(drawCard());
		return cards;
	}

	private void shuffle() {
		Collections.shuffle(cards);
		size = cards.size();
	}
	public int getSize() {
		return size;
	}
}
