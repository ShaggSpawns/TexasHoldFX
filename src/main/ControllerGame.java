package main;

import game.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import resource.CardImageView;
import resource.PlayerView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class ControllerGame implements Initializable {
	@FXML
	VBox playerListView;
	@FXML
	HBox boardCards;
	@FXML
	HBox playerCards;

	int index = 2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Random r = new Random(2);
		Player p1 = new Player("Bob");
		p1.setStatus(Status.BETTING);
		List<Card> p1Cards = new ArrayList<>();
		p1Cards.add(new Card(Rank.ACE, Suit.CLUB));
		p1Cards.add(new Card(Rank.TWO, Suit.CLUB));
		p1.addCards(p1Cards);
		PlayerView p1v = new PlayerView(p1);
		p1v.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			p1v.flipCards();
		});
		playerListView.getChildren().add(p1v);

		Player p2 = new Player("Joe", r.nextInt(5000));
		p2.setStatus(Status.WAITING);
		List<Card> p2Cards = new ArrayList<>();
		p2Cards.add(new Card(Rank.ACE, Suit.CLUB));
		p2Cards.add(new Card(Rank.ACE, Suit.CLUB));
		p2.addCards(p2Cards);
		playerListView.getChildren().add(new PlayerView(p2));

		Player p3 = new Player("Tim", r.nextInt(5000));
		p3.setStatus(Status.FOLDED);
		p3.addCards(p2Cards);
		playerListView.getChildren().add(new PlayerView(p3));

		Player p4 = new Player("Sarah", r.nextInt(5000));
		p4.setStatus(Status.WAITING);
		p4.addCards(p2Cards);
		playerListView.getChildren().add(new PlayerView(p4));

		Player p5 = new Player("Billy", r.nextInt(5000));
		p5.setStatus(Status.WAITING);
		p5.addCards(p2Cards);
		playerListView.getChildren().add(new PlayerView(p5));

		Player p6 = new Player("Jewels", r.nextInt(5000));
		p6.setStatus(Status.WAITING);
		p6.addCards(p2Cards);
		playerListView.getChildren().add(new PlayerView(p6));

		Player p7 = new Player("Abby", r.nextInt(5000));
		p7.setStatus(Status.WAITING);
		p7.addCards(p2Cards);
		playerListView.getChildren().add(new PlayerView(p7));

		// BOARD CARDS
		List<Card> boardCardArray = new ArrayList<>();
		boardCardArray.add(new Card(Rank.ACE, Suit.CLUB));
		boardCardArray.add(new Card(Rank.ACE, Suit.CLUB));
		boardCardArray.add(new Card(Rank.ACE, Suit.CLUB));
		boardCardArray.add(new Card(Rank.ACE, Suit.CLUB));
		boardCardArray.add(new Card(Rank.ACE, Suit.CLUB));

		for (Card c: boardCardArray) {
			CardImageView cv = c.getView(false);
			cv.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				cv.flip();
			});
			boardCards.getChildren().add(cv);
		}

		// PLAYER CARDS
		List<Card> playerCardArray = new ArrayList<>();
		playerCardArray.add(new Card(Rank.ACE, Suit.CLUB));
		playerCardArray.add(new Card(Rank.ACE, Suit.CLUB));

		Player self = new Player("Jackson");
		self.addCards(playerCardArray);

		for (Card c: playerCardArray) {
			CardImageView cv = c.getView(true);
			cv.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				cv.flip();
			});
			playerCards.getChildren().add(cv);
		}
	}

	@FXML
	private void handleButton() {
		if (index == 1) {
			((PlayerView)playerListView.getChildren().get(index++)).changeStatus(Status.BETTING);
			((PlayerView)playerListView.getChildren().get(7)).changeStatus(Status.WAITING);
		} else if (index == 7) {
			((PlayerView)playerListView.getChildren().get(index - 1)).changeStatus(Status.WAITING);
			((PlayerView)playerListView.getChildren().get(index)).changeStatus(Status.BETTING);
			index = 1;
		} else {
			((PlayerView)playerListView.getChildren().get(index - 1)).changeStatus(Status.WAITING);
			((PlayerView)playerListView.getChildren().get(index++)).changeStatus(Status.BETTING);
		}
	}
}
