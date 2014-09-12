package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

public class OldBossEnemyPlane {
	public static Image enemyPlaneImg = GameStart.tool.getImage(GameStart.class
			.getResource("/images/boss_2.png"));// 敌军飞机1图片
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean alive;
	private int liveValue;// 生命值
	private GameStart gs = null;

	
	public static Image getEnemyPlaneImg() {
		return enemyPlaneImg;
	}

	public static void setEnemyPlaneImg(Image enemyPlaneImg) {
		OldBossEnemyPlane.enemyPlaneImg = enemyPlaneImg;
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

	public OldBossEnemyPlane(int x, int y, int width, int height,
			boolean alive, int liveValue, GameStart gs) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.alive = alive;
		this.liveValue = liveValue;
		this.gs = gs;
	}

	Random random = new Random();

	public void drawEnemyPlane(Graphics g)// 画OldBoss飞机
	{
		g.drawImage(enemyPlaneImg, x, y, width, height, gs);
		if (random.nextInt(201) % 200 == 0) {
			EnemyBullet eb = new EnemyBullet(x, y, 10, 10, alive, gs);
			gs.enemyBulletList.add(eb);// 将敌军子弹加入数组列表
		}
	}

	public Rectangle getRec() {
		return new Rectangle(x, y, width, height);// 获取老BOSS飞机矩形框
	}

}
