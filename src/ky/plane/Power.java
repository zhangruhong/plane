package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Power {
	Image powerImg = GameStart.tool.getImage(GameStart.class
			.getResource("/images/bb_01.png"));// 能量图片
	int x;
	int y;
	int width;
	int height;
	boolean isLive;
	GameStart gs = null;

	public Power(int x, int y, int width, int height, boolean isLive,
			GameStart gs) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isLive = isLive;
		this.gs = gs;
	}

	boolean temp = false;

	public void drawPower(Graphics g) {
		if (isLive) {
			if (temp) {
				g.drawImage(powerImg, x, y, width, height, gs);// 画power
				y++;
				x++;
				if (y >= 400) {
					temp = false;
				}
			}
			if (!temp) {
				g.drawImage(powerImg, x, y, width, height, gs);// 画power
				x--;
				y--;
				if (y <= 100) {
					temp = true;
				}
			}
		}
	}

	public Rectangle getRec() {
		return new Rectangle(x, y, width, height);// 获取子弹矩形框
	}

}
