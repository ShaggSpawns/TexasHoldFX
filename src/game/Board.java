package game;

import java.util.List;

public class Board {
    private List<Card> cards;

    public Board(List<Card> cards) {
        setCards(cards);
    }

    private void setCards(List<Card> cards) {
        if (cards.size() != 5)
            throw new IllegalArgumentException("Invalid number of board cards");
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }
}
