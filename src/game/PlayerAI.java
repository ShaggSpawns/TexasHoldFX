package game;

public class PlayerAI extends Player {
    private static int id = 0;
    public PlayerAI(int initMoney) {
        super("AI_" + id++, initMoney);
    }
}
