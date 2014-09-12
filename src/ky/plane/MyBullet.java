package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class MyBullet {
	static Image myBulletImg = GameStart.tool.getImage(GameStart.class
			.getResource("/images/bb_02.png")); // 我军子弹图片1
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean live;
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
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public MyBullet(int x, int y, int width, int height, boolean isLive,
			GameStart gs) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.live = isLive;
		this.gs = gs;
	}

	public void drawMyBullet(Graphics g) {

		g.drawImage(myBulletImg, x, y, width, height, gs);// 画我军子弹
		y -= 10;

	}

	public Rectangle getRec() {
		return new Rectangle(x, y, width, height);// 获取子弹矩形框
	}

	public void HitPlane(ArrayList<EnemyPlane> enemyList) { // 判断子弹和敌军相撞
		for (int i = 0; i < enemyList.size(); i++) {
			EnemyPlane ep = enemyList.get(i); // 拿到每一架飞机
			if (this.live && ep.isAlive()
					&& this.getRec().intersects(ep.getRec())) {
				this.live = false;
				ep.setAlive(false);
				gs.setBomb(new Bomb(x, y, 30, 30, true, gs));
				gs.setTotalScore(gs.getTotalScore() + 10);
			}

		}
	}

	// /////////////////////变子弹///////////////////////
	public void eatPower(Power p) {// 判断子弹和食物相撞
		if (this.live && p.isLive && this.getRec().intersects(p.getRec())) {
			gs.type = 2;
			gs.count = 500;
			this.live = false;
			p.isLive = false;

		}

	}

	public void HitOldBossPlane(OldBossEnemyPlane oldBossEnemyPlane) { // 判断子弹和oldBoss相撞
		OldBossEnemyPlane obep = oldBossEnemyPlane; // 拿到oldBoss飞机
		// 没有血了 设置状态为死亡
		if (obep.getLiveValue() < 1) {
			obep.setAlive(false);
		}
		// 还有血 扣血
		if (this.live && obep.isAlive()
				&& this.getRec().intersects(obep.getRec())) {
			this.live = false;
			// gs.setBomb(new Bomb(x, y, 30, 30, true, gs));
			obep.setLiveValue(obep.getLiveValue() - 1);
			gs.setTotalScore(gs.getTotalScore() + 10);
		}
	}
}
