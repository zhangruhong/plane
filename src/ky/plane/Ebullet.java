package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class Ebullet {
	private Image EBulletImg = null;// 敌军子弹图片默认
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

	public Image getEBulletImg() {
		return EBulletImg;
	}

	public void setEBulletImg(Image eBulletImg) {
		EBulletImg = eBulletImg;
	}

	public GameStart getGs() {
		return gs;
	}

	public void setGs(GameStart gs) {
		this.gs = gs;
	}

	public Ebullet(int x, int y, int width, int height, boolean Live,
			GameStart gs) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.Live = Live;
		this.gs = gs;

	}

	public abstract void drawEBullet(Graphics g);

	public Rectangle getRec() {
		return new Rectangle(x, y, width, height);// 获取敌军飞机矩形框
	}

}
