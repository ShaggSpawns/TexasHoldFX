package game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;

    public Game(Player player, int numAI) {
        List<Player> players = new ArrayList<>();

        players.add(player);
        for (int i = 0; i < numAI; i++) {
            players.add(new PlayerAI(1000));
        }

        setPlayers(players);
    }

    public Game(List<Player> players) {
        setPlayers(players);
    }

    private void setPlayers(List<Player> players) {
        if (players.size() > 8 || players.size() < 2)
            throw new IllegalArgumentException("Player overflow");
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
