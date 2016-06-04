package game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Deck deck = new Deck();
    private Board board;

    public Game(Player player, int numAI) {
        List<Player> players = new ArrayList<>();

        players.add(player);
        for (int i = 0; i < numAI; i++) {
            players.add(new PlayerAI(1000));
        }

        setPlayers(players);
        startGame();
    }

    public Game(List<Player> players) {
        setPlayers(players);
        startGame();
    }

    private void startGame() {
        dealCards();
        board = new Board(deck.drawCards(5));
    }

    private void dealCards() {
        for (Player p: players)
            p.addCards(deck.drawCards(2));
    }

    private void setPlayers(List<Player> players) {
        if (players.size() > 8 || players.size() < 2)
            throw new IllegalArgumentException("Player overflow");
        this.players = players;
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
