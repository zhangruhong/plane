package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class EPlane {
	private Image EPlaneImg = null;// 敌军飞机1图片
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean alive;
	private int liveValue;// 生命值
	private GameStart gs = null;

	public Image getEPlaneImg() {
		return EPlaneImg;
	}

	public void setEPlaneImg(Image ePlaneImg) {
		EPlaneImg = ePlaneImg;
	}

	public GameStart getGs() {
		return gs;
	}

	public void setGs(GameStart gs) {
		this.gs = gs;
	}

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

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getLiveValue() {
		return liveValue;
	}

	public void setLiveValue(int liveValue) {
		this.liveValue = liveValue;
	}

	public EPlane(int x, int y, int width, int height, boolean alive,
			int liveValue, GameStart gs) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.alive = alive;
		this.liveValue = liveValue;
		this.gs = gs;
	}

	public abstract void drawEPlane(Graphics g);// 画OldBoss飞机

	public Rectangle getRec() {
		return new Rectangle(x, y, width, height);// 获取老BOSS飞机矩形框
	}

}
