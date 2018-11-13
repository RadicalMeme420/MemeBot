package me.radicalmeme420.discordbot.memebot.player;

import me.radicalmeme420.discordbot.memebot.io.MemebotIO;
import net.dv8tion.jda.core.entities.User;

public class Player {

	private User user;
	
	private int money;
	private int xp;
	private int level;
	
	public Player(User user) {
		this.user = user;
		String[] dat = MemebotIO.getPlayer(user.getId());
		money = Integer.parseInt(dat[0]);
		xp = Integer.parseInt(dat[1]);
		level = Integer.parseInt(dat[2]);
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
