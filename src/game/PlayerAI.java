package game;

public class PlayerAI extends Player {
    private static int id = 0;

    public PlayerAI() {
        super("AI_" + id++, (int)(Math.random() * 10000));
    }
    public PlayerAI(int initMoney) {
        super("AI_" + id++, initMoney);
    }

    public PlayerAI(boolean real) {
        super("Bob", (int)(Math.random() * 10000));
    }
}
