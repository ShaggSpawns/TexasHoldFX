package resource;

import game.Card;
import game.Rank;
import game.Suit;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardImageView extends Parent {
	private static final int WIDTH = 79;
	private static final int HEIGHT = 123;

	private boolean isShowing;

	private static Image cards = new Image("file:cards.png");
	private static Image cardBackTemplate = new Image("file:cardBack.png");

	private ImageView cardView;
	private ImageView cardBack;

	private String cardInfo;

	public CardImageView(Card card) {
		this(card.getRank(), card.getSuit(), 1, true);
	}

	public CardImageView(Card card, boolean isShowing) {
		this(card.getRank(), card.getSuit(), 1, isShowing);
	}

	public CardImageView(Card card, double scale) {
		this(card.getRank(), card.getSuit(), scale, true);
	}

	public CardImageView(Card card, double scale, boolean isShowing) {
		this(card.getRank(), card.getSuit(), scale, isShowing);
	}

	public CardImageView(Rank rank, Suit suit, double scale, boolean isShowing) {
		cardInfo = rank.name() + " " + suit.name();
		int x = WIDTH * (rank.getValue() - 1);
		int y = HEIGHT * suit.getValue();

		cardView = new ImageView(cards);
		Rectangle2D cropped = new Rectangle2D(x, y, WIDTH, HEIGHT);
		cardView.setViewport(cropped);
		cardView.setPreserveRatio(true);
		cardView.setSmooth(true);
		cardView.setFitWidth(WIDTH * scale);
		cardView.setFitHeight(HEIGHT * scale);

		cardBack = new ImageView(cardBackTemplate);
		cardBack.setPreserveRatio(true);
		cardBack.setSmooth(true);
		cardBack.setFitWidth(WIDTH * scale);
		cardBack.setFitHeight(HEIGHT * scale);

		this.isShowing = isShowing;

		if (isShowing)
			getChildren().add(cardView);
		else
			getChildren().add(cardBack);
	}

	public void show() {
		getChildren().remove(cardBack);
		getChildren().add(cardView);
		isShowing = true;
	}

	public void hide() {
		getChildren().remove(cardView);
		getChildren().add(cardBack);
		isShowing = false;
	}

	public void flip() {
		if (isShowing)
			hide();
		else
			show();
	}

	public boolean isShowing() {
		return isShowing;
	}

	public String toString() {
		return cardInfo;
	}
}
