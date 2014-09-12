package ky.plane;

import java.awt.Graphics;
import java.awt.Image;

public class BackGround {
	static Image bgImg = GameStart.tool.getImage(GameStart.class
			.getResource("/images/bg.jpg"));// 以类路径方式拿到图片对象
	private int x;
	private int y;
	private int width;
	private int height;
	private GameStart gs = null;

	public BackGround(int x, int y, int width, int height, GameStart gs) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
	}

	int y1 = -500;

	public void drawBG(Graphics g) {
		g.drawImage(bgImg, x, y, 400, 500, gs);
		g.drawImage(bgImg, x, y1, 400, 500, gs);

		y += 1;
		y1 += 1;
		if (y == 500) {
			y = -500;
		}
		if (y1 == 500) {
			y1 = -500;
		}

	}
}
