package game;

import java.util.ArrayList;
import java.util.List;

public class Player {
	List<Card> cards = new ArrayList<>();
	private int money;
	private String name;
	private Status status = Status.WAITING;
    private boolean isButton;

	public Player(String name) {
		this(name, 1000);
	}

	public Player(String name, int initMoney) {
		setName(name);
		setMoney(initMoney);
	}

	private void setName(String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Name cannot be empty");
		this.name = name;
	}

	private void setMoney(int amount) {
		if (amount < 0)
			throw new IllegalArgumentException("Money cannot be negative");
		this.money = amount;
	}

	public void addMoney(int amount) {
		if (amount < 0)
			throw new IllegalArgumentException("Money cannot be negative");
		this.money += amount;
	}

	public int removeMoney(int amount) {
		if (amount > money) {
			amount = money;
			money = 0;
		} else
			money -= amount;
		return amount;
	}

	public int getMoney() {
		return money;
	}

	public boolean hasMoney() {
		return money > 0;
	}

	public void addCards(List<Card> cards) {
		if (cards.size() > 2)
			throw new IllegalArgumentException("Player hand overflow");
		if (!this.cards.isEmpty())
			removeCards();
		this.cards.addAll(cards);
	}

	public void removeCards() {
		cards.clear();
	}

	public List<Card> getCards() {
		return cards;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

    public void setIsButton(boolean isButton) {
        this.isButton = isButton;
    }

    public boolean isButton() {
        return isButton;
    }

	public String toString() {
		return name + " [$" + money + "]";
	}
}
