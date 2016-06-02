package main.controllers;

import game.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.runners.TexasHoldFX;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class ControllerMainMenu implements Initializable {
    @FXML
    Text spBtn, mpBtn, optionBtn, exitBtn;

    private final Color HIGHLIGHT_COLOR = Color.MIDNIGHTBLUE;
    private final Color HIGHLIGHT_STROKE = Color.MIDNIGHTBLUE;
    private final Font HIGHLIGHT_FONT = Font.font("", 28);

    private final Color DEFAULT_COLOR = Color.BLACK;
    private final Color DEFAULT_STROKE = Color.BLACK;
    private final Font DEFAULT_FONT = Font.font("", 24);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetBtn(spBtn);
        resetBtn(mpBtn);
        resetBtn(optionBtn);
        resetBtn(exitBtn);
    }

    @FXML
    private void handleEnter(MouseEvent event) {
        highlightBtn((Text) event.getSource());
    }

    @FXML
    private void handleExit(MouseEvent event) {
        resetBtn((Text) event.getSource());
    }

    @FXML
    private void handleClick(MouseEvent event) {
        if (event.getSource().equals(spBtn)) {
            try {
                launchGame(GameMode.SINGLEPLAYER);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource().equals(mpBtn)) {
            try {
                launchGame(GameMode.MULTIPLAYER);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource().equals(optionBtn)) {
            launchOption();
        } else if (event.getSource().equals(exitBtn)) {
            Platform.exit();
        }
    }

    private void highlightBtn(Text btn) {
        btn.setFill(HIGHLIGHT_COLOR);
        btn.setStroke(HIGHLIGHT_STROKE);
        btn.setFont(HIGHLIGHT_FONT);
    }

    private void resetBtn(Text btn) {
        btn.setFill(DEFAULT_COLOR);
        btn.setStroke(DEFAULT_STROKE);
        btn.setFont(DEFAULT_FONT);
    }

    private void launchGame(GameMode gameMode) throws IOException {
        switch (gameMode) {
            case SINGLEPLAYER:
                TexasHoldFX.game = new Game(new Player("Jackson"), 5);
                break;
            case MULTIPLAYER:
                TexasHoldFX.game = new Game(getPlayers(5));
                break;
        }
        Parent root = FXMLLoader.load(getClass().getResource("../resources/game_ui.fxml"));
        Scene scene = new Scene(root, TexasHoldFX.WIDTH, TexasHoldFX.HEIGHT);
        TexasHoldFX.getStage().setScene(scene);
    }

    private List<Player> getPlayers(int num) {
        List<Player> players = new ArrayList<>();
        Random r = new Random(2);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.ACE, Suit.CLUB));
        cards.add(new Card(Rank.TWO, Suit.CLUB));

        for (int i = 0; i < num; i++) {
            Player p = new Player("Bob");
            p.addCards(cards);
            players.add(p);
        }

        return players;
    }

    private void launchOption() {

    }
}

