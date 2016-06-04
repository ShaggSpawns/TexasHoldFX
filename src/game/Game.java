package game;

import javafx.fxml.FXMLLoader;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Deck deck = new Deck();
    private Board board;
    private static int currentButtonIndex = -1;

    public Game(Player localPlayer, int numAI) {
        this(localPlayer, null, numAI);
    }

    public Game(Player localPlayer, List<Player> remotePlayers) {
        this(localPlayer, remotePlayers, 0);
    }

    public Game(Player localPlayer, List<Player> remotePlayers, int numAI) {
        if (remotePlayers == null) {
            if (numAI > 7 || numAI < 1)
                throw new IllegalArgumentException("Invalid number of other players");
        } else if (remotePlayers.size() + numAI > 7 || remotePlayers.size() + numAI < 1) {
            throw new IllegalArgumentException("Invalid number of other players.");
        }
        players = new ArrayList<>();
        players.add(localPlayer);
        if (remotePlayers != null)
            players.addAll(remotePlayers);

        for (int i = 0; i < numAI; i++)
            players.add(new PlayerAI());
        startGame();
    }

    private void startGame() {
        setButtonDealer();
        dealCards();
        board = new Board(deck.drawCards(5));
        showCard(4);
    }

    private void setButtonDealer() {
        if (currentButtonIndex == -1)
            currentButtonIndex = (int)(Math.random() * (players.size() - 1));
        else if (currentButtonIndex == players.size())
            currentButtonIndex = 1;
        players.get(currentButtonIndex++).setIsButton(true);
    }

    private void dealCards() {
        for (Player p: players)
            p.addCards(deck.drawCards(2));
    }

    private void showCard(int cardIndex) {

    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public Board getBoard() {
        return board;
    }
}
