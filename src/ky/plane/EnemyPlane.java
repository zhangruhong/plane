package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class EnemyPlane {
	private Image enemyPlaneImg = GameStart.tool.getImage(GameStart.class
			.getResource("/images/enemy01.png"));// 默认敌军飞机1图片
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean Live;
	private GameStart gs = null;

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

	public void setLive(boolean live) {
		Live = live;
	}

	public int getTime1() {
		return time1;
	}

	public void setTime1(int time1) {
		this.time1 = time1;
	}

	public Image getEnemyPlaneImg() {
		return enemyPlaneImg;
	}

	public void setEnemyPlaneImg(Image enemyPlaneImg) {
		this.enemyPlaneImg = enemyPlaneImg;
	}

	public EnemyPlane(int x, int y, int width, int height, boolean isLive,
			Image enemyPlaneImg, GameStart gs) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.Live = isLive;
		this.enemyPlaneImg = enemyPlaneImg;
		this.gs = gs;
	}

	int time1 = 0;

	public void drawEnemyPlane(Graphics g)// 画敌军飞机
	{
		g.drawImage(enemyPlaneImg, x, y, width, height, gs);
		if (time1 % 200 == 0) {
			EnemyBullet eb = new EnemyBullet(x, y, 10, 10, Live, gs);
			gs.enemyBulletList.add(eb);// 将敌军子弹加入数组列表
			time1 = 0;
		}
		time1++;
		y += 3;
	}

	public Rectangle getRec() {
		return new Rectangle(x, y, width, height);// 获取敌军飞机矩形框
	}

}
