package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public abstract class PlaneBase {
	public Image myPlaneImg = null;
	public int x;
	public int y;
	public int width;
	public int height;
	public boolean alive;
	public int blood;
	public Random random = new Random();

	public boolean U, D, L, R, Fire;// 定义四个方向变量
	public GameStart gs = null;

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public PlaneBase(int x, int y, int width, int height, boolean isLive,
			int blood, GameStart gs) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.alive = isLive;
		this.gs = gs;
		this.blood = blood;
	}

	public void drawMyPlane(Graphics g) {// 让飞机在规定区域飞行，别出界
		g.drawImage(getMyPlaneImg(), x, y, width, height, gs);

		if (U && y > 20)
			y -= 5;
		if (D && y < 450)
			y += 5;
		if (L && x > 5)
			x -= 5;
		if (R && x < 350)
			x += 5;
		if (Fire) {
			if (random.nextInt(15) % 7 == 0) {
				MyBullet bullet = new MyBullet(x + 15, y, 20, 20, true, gs);
				gs.mbList.add(bullet);
				gs.getBeammusic().playSound("music/Beam.mp3");
			}

		}

	}

	public Rectangle getRec() {
		return new Rectangle(x, y, width, height);// 获取敌军飞机矩形框
	}

	public abstract void keyPressed(KeyEvent e);

	public abstract void keyReleased(KeyEvent e);

	// 敌军子弹大战我军飞机（用泛型处理敌军普通子弹和boss子弹）
	public abstract <T> void bulletHitMyplane(Ebullet eb);

	public abstract void EPlanehitMyplane(EPlane ePlane);

	public abstract void EBoosHitMyPlane(OldBossEnemyPlane oldbossenemyplane);

	public Image getMyPlaneImg() {
		return myPlaneImg;
	}

	public void setMyPlaneImg(Image myPlaneImg) {
		this.myPlaneImg = myPlaneImg;
	}
}
