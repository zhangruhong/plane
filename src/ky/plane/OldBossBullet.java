package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

public class OldBossBullet {
	private Image enemyBulletImg = GameStart.tool.getImage(GameStart.class
			.getResource("/images/en_bul01.png"));// µÐ¾ü×Óµ¯Í¼Æ¬Ä¬ÈÏ
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean Live;
	private GameStart gs = null;
	int random = 0;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isLive() {
		return Live;
	}

	public void setLive(boolean isLive) {
		this.Live = isLive;
	}

	public OldBossBullet(int x, int y, int width, int height, boolean Live,
			GameStart gs) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.Live = Live;
		this.gs = gs;

		Random r = new Random();
		random = r.nextInt(6) - 3;

	}

	public void drawOldBossBullet(Graphics g) {
		g.drawImage(enemyBulletImg, x + 20, y, width, height, gs);// »­µÐ¾ü×Óµ¯
		y += 3;
		x += random;
	}

}
