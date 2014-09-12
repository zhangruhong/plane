package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Bomb {
	static Image bombImg = GameStart.tool.getImage(GameStart.class
			.getResource("/images/bomb_enemy_4.png"));// ±¬Õ¨Í¼Æ¬1
	static Image bombImg2 = GameStart.tool.getImage(GameStart.class
			.getResource("/images/bomb_enemy_5.png"));// ±¬Õ¨Í¼Æ¬2
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean isLive;

	private GameStart gs = null;

	public Bomb(int x, int y, int width, int height, boolean isLive,
			GameStart gs) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isLive = isLive;
		this.gs = gs;
	}

	public void drawBomb(Graphics g) {// »­±¬Õ¨

		g.drawImage(bombImg, x, y, 30, 30, gs);
		g.drawImage(bombImg2, x, y, 30, 30, gs);
	}

}
