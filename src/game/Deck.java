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
		if (size == 0)
			shuffle();
		return cards.get(--size);
	}

	private void shuffle() {
		Collections.shuffle(cards);
		size = cards.size();
	}
	public int getSize() {
		return size;
	}
}
