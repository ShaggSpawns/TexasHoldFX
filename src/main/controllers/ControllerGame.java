package main.controllers;

import game.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.runners.TexasHoldFX;
import resource.CardImageView;
import resource.PlayerView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerGame implements Initializable {
	@FXML
	VBox playerListView;
	@FXML
	HBox boardCards;
	@FXML
	HBox playerCards;

	int index = 2;

	private List<Player> playerList = TexasHoldFX.game.getPlayers();

	public static GameMode mode = GameMode.SINGLEPLAYER;

    public void setPlayerList(List<Player> players) {
        if (players.size() > 8 || players.size() < 2)
            throw new IllegalArgumentException("Invalid number of players");
        this.playerList = players;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		switch (mode) {
			case SINGLEPLAYER:

		}
        for (Player p: playerList) {
            playerListView.getChildren().add(new PlayerView(p));
        }

        playerListView.getChildren().get(1).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> ((PlayerView)playerListView.getChildren().get(1)).flipCards());

		// BOARD CARDS
		List<Card> boardCardArray = new ArrayList<>();
		boardCardArray.add(new Card(Rank.ACE, Suit.CLUB));
		boardCardArray.add(new Card(Rank.ACE, Suit.CLUB));
		boardCardArray.add(new Card(Rank.ACE, Suit.CLUB));
		boardCardArray.add(new Card(Rank.ACE, Suit.CLUB));
		boardCardArray.add(new Card(Rank.ACE, Suit.CLUB));

		for (Card c: boardCardArray) {
			CardImageView cv = c.getView(false);
			cv.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> cv.flip());
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
			cv.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> cv.flip());
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
