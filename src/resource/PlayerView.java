package resource;

import game.Card;
import game.Player;
import game.Status;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Stack;

public class PlayerView extends StackPane {
	private Player player;
	private HBox cardViews;
	private Rectangle statusBackground;
    private Rectangle backgroundEdge;
	private Text statusText;

	public PlayerView(Player player) {
		this.player = player;
		setAlignment(Pos.CENTER);
		backgroundEdge = new Rectangle(191, 71);
		backgroundEdge.setArcWidth(10);
		backgroundEdge.setArcHeight(10);
        changeButtonIndicator(player.isButton());
		getChildren().add(backgroundEdge);

		Rectangle background = new Rectangle(185,66);
		background.setStroke(Color.BLACK);
		background.setArcWidth(10);
		background.setArcHeight(10);
		background.setFill(Color.LIGHTBLUE);
		getChildren().add(background);

		VBox content = new VBox(6);
		content.setAlignment(Pos.BASELINE_CENTER);

		Text playerText = new Text(player.toString());
		playerText.setFont(Font.font("", FontPosture.ITALIC, 18));
		content.getChildren().add(playerText);

		HBox playerInfoBot = new HBox(6);
		playerInfoBot.setAlignment(Pos.CENTER);

		cardViews = new HBox(3);
		for (Card c: player.getCards())
			cardViews.getChildren().add(new CardImageView(c, .52, true, .6));

		StackPane statusPane = new StackPane();
		statusBackground = new Rectangle(65, 20);
		statusBackground.setStroke(Color.BLACK);
		statusBackground.setArcWidth(5);
		statusBackground.setArcHeight(5);
		statusText = new Text(player.getStatus().name());
		statusText.setFont(Font.font("", FontWeight.BOLD, 11));
		updateStatus(player.getStatus());
		statusPane.getChildren().addAll(statusBackground, statusText);

		playerInfoBot.getChildren().addAll(cardViews, statusPane);
		content.getChildren().add(playerInfoBot);

		getChildren().add(content);
	}

	public void flipCards() {
		cardViews.getChildren().forEach(n -> {
			if (n instanceof CardImageView)
				((CardImageView) n).flip();
		});
	}

	public void changeStatus(Status status) {
		player.setStatus(status);
		updateStatus(status);
	}

	private void updateStatus(Status status) {
		switch (status) {
			case BETTING:
				statusBackground.setFill(Color.LIGHTGREEN);
				break;
			case WAITING:
				statusBackground.setFill(Color.RED);
				break;
			case FOLDED:
				statusBackground.setFill(Color.GRAY);
				break;
		}
		statusText.setText(status.name());
	}

    public void changeButtonIndicator(boolean isButton) {
        if (isButton)
            backgroundEdge.setFill(Color.GREENYELLOW);
        else
            backgroundEdge.setFill(Color.CADETBLUE);
    }

	public Player getPlayer() {
		return player;
	}
}
