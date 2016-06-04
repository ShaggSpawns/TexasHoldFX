package main.controllers;

import game.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.runners.TexasHoldFX;
import resource.CardImageView;
import resource.PlayerView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerGame implements Initializable {
	@FXML
	VBox playerListView;
	@FXML
	HBox boardCardView;
	@FXML
	HBox playerCards;

	@FXML
	Button callBtn;
	@FXML
	Button raiseBtn;
	@FXML
	Button foldBtn;

	private List<Player> playerList = TexasHoldFX.game.getPlayers();
	private List<Card> boardCards = TexasHoldFX.game.getBoard().getCards();

    public void setPlayerList(List<Player> players) {
        if (players.size() > 8 || players.size() < 2)
            throw new IllegalArgumentException("Invalid number of players");
        this.playerList = players;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 1; i < playerList.size(); i++)
            playerListView.getChildren().add(new PlayerView(playerList.get(i)));

		playerListView.getChildren().forEach(pv -> {
			pv.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				if (pv instanceof PlayerView)
					((PlayerView) pv).flipCards();
			});
		});

		for (Card c: boardCards) {
			CardImageView cv = c.getView(true);
			cv.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> cv.flip());
			boardCardView.getChildren().add(cv);
		}

		for (Card c: TexasHoldFX.game.getPlayer(0).getCards())
			playerCards.getChildren().add(c.getView(true));
	}

	@FXML
	private void handleButton(ActionEvent event) {
		if (event.getSource().equals(callBtn)) {

		} else if (event.getSource().equals(raiseBtn)) {

		} else if (event.getSource().equals(foldBtn)) {

		}
	}

	@FXML
	private void handleFlip(MouseEvent event) {
		if (event.getSource().equals(playerCards)) {
			playerCards.getChildren().forEach(n -> {
				if (n instanceof CardImageView)
					((CardImageView) n).flip();
			});
		}
	}
}
