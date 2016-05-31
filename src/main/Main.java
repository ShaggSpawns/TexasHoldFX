package main;

import game.Card;
import game.Rank;
import game.Suit;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resource.CardImageView;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = getContent();
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setTitle("Texas Hold'em");
		primaryStage.setScene(scene);
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(600);
		primaryStage.show();
	}

	private Parent getContent() {
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: green;");
		root.setCenter(getBoardInterface());
		root.setBottom(getPlayerInterface());
		root.setRight(getPlayerGroup(1));
		root.setTop(getPlayerGroup(2));
		root.setLeft(getPlayerGroup(3));
		return root;
	}

	private Parent getBoardInterface() {
		int potAmount = 10;
		String currentPlayer = "Bob";
		int numRemainingPlayers = 3;

		VBox boardInterface = new VBox(10);
		boardInterface.setAlignment(Pos.CENTER);

		Text potText = new Text("Pot: $" + potAmount);
		potText.setFont(Font.font("Arial", FontPosture.ITALIC, 32));
		boardInterface.getChildren().add(potText);

		HBox communityCards = new HBox(5);
		communityCards.setAlignment(Pos.CENTER);
		Card c1 = new Card(Rank.ACE, Suit.CLUB);

		Card c2 = new Card(Rank.ACE, Suit.CLUB);
		Card c3 = new Card(Rank.ACE, Suit.CLUB);
		Card c4 = new Card(Rank.ACE, Suit.CLUB);
		Card c5 = new Card(Rank.ACE, Suit.CLUB);
		communityCards.getChildren().addAll(c1.getView(true),c2.getView(true),c3.getView(false),c4.getView(false),c5.getView(false));
		boardInterface.getChildren().add(communityCards);

		Text playerInfo = new Text("Current Player: " + currentPlayer + " [" + numRemainingPlayers + "]");
		playerInfo.setFont(Font.font("Arial", 12));
		boardInterface.getChildren().add(playerInfo);

		return boardInterface;
	}

	private Parent getPlayerInterface() {
		int playerMoney = 100;

		BorderPane playerInterface = new BorderPane();
		playerInterface.setPadding(new Insets(10,10,10,10));

		StackPane moneyPane = new StackPane();
		moneyPane.setAlignment(Pos.BOTTOM_CENTER);
		Text moneyText = new Text("Bank: $" + playerMoney);
		moneyText.setStroke(Color.BLACK);
		moneyText.setFill(Color.GRAY);
		moneyText.setFont(Font.font("Arial", 30));
		moneyPane.getChildren().add(moneyText);
		playerInterface.setLeft(moneyPane);

		HBox hand = new HBox(10);
		hand.setAlignment(Pos.CENTER);
		Card c1 = new Card(Rank.ACE, Suit.CLUB);
		Card c2 = new Card(Rank.ACE, Suit.CLUB);
		CardImageView c1v = new CardImageView(c1, true);
		CardImageView c2v = new CardImageView(c2, true);
		c2v.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			c2v.flip();
		});
		hand.getChildren().addAll(c1v, c2v);
		moneyPane.getChildren().add(hand);
		playerInterface.setCenter(hand);

		StackPane playerOptionsPane = new StackPane();
		playerOptionsPane.setAlignment(Pos.BOTTOM_CENTER);
		Rectangle playerOptionsBackground = new Rectangle(240, 90);
		playerOptionsBackground.setStroke(Color.BLACK);
		playerOptionsBackground.setFill(Color.LIGHTBLUE);

		HBox playerOptions = new HBox(5);
		playerOptions.setAlignment(Pos.BOTTOM_CENTER);
		playerOptions.setPadding(new Insets(0,0,10,0));
		Button callBtn = new Button("Call");
		callBtn.setDisable(true);
		callBtn.setFocusTraversable(false);
		callBtn.setPrefSize(70,70);
		Button raiseBtn = new Button("Raise");
		raiseBtn.setDisable(true);
		raiseBtn.setFocusTraversable(false);
		raiseBtn.setPrefSize(70,70);
		Button foldBtn = new Button("Fold");
		foldBtn.setDisable(true);
		foldBtn.setFocusTraversable(false);
		foldBtn.setPrefSize(70,70);
		playerOptions.getChildren().addAll(callBtn, raiseBtn, foldBtn);

		playerOptionsPane.getChildren().addAll(playerOptionsBackground, playerOptions);
		playerInterface.setRight(playerOptionsPane);

		return playerInterface;
	}

	private Parent getPlayerGroup(int group) {
		switch (group) {
			case 1:
				HBox pCards1 = new HBox(1);
				pCards1.setRotate(-90);
				pCards1.setAlignment(Pos.CENTER);
				Card p1c1 = new Card(Rank.ACE, Suit.CLUB);
				Card p1c2 = new Card(Rank.ACE, Suit.CLUB);
				CardImageView p1c1v = new CardImageView(p1c1, .6, false);
				CardImageView p1c2v = new CardImageView(p1c2, .6, false);
				pCards1.getChildren().addAll(p1c1v, p1c2v);

				return pCards1;
			case 2:
				HBox pCards2 = new HBox(1);
				pCards2.setPadding(new Insets(5,5,5,5));
				pCards2.setRotate(180);
				pCards2.setAlignment(Pos.CENTER);
				Card p2c1 = new Card(Rank.ACE, Suit.CLUB);
				Card p2c2 = new Card(Rank.ACE, Suit.CLUB);
				CardImageView p2c1v = new CardImageView(p2c1, .6, false);
				CardImageView p2c2v = new CardImageView(p2c2, .6, false);
				pCards2.getChildren().addAll(p2c1v, p2c2v);
				return pCards2;
			case 3:
				HBox pCards3 = new HBox(1);
				pCards3.setAlignment(Pos.CENTER);
				Card p3c1 = new Card(Rank.ACE, Suit.CLUB);
				Card p3c2 = new Card(Rank.ACE, Suit.CLUB);
				CardImageView p3c1v = new CardImageView(p3c1, .6, false);
				CardImageView p3c2v = new CardImageView(p3c2, .6, false);
				pCards3.getChildren().addAll(p3c1v, p3c2v);
				pCards3.setRotate(90);
				return pCards3;
		}
		throw new IllegalArgumentException("Player Grouping");
	}
}
