package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class MyBullet {
	static Image myBulletImg = GameStart.tool.getImage(GameStart.class
			.getResource("/images/bb_02.png")); // �Ҿ��ӵ�ͼƬ1
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

		g.drawImage(myBulletImg, x, y, width, height, gs);// ���Ҿ��ӵ�
		y -= 10;

	}

	public Rectangle getRec() {
		return new Rectangle(x, y, width, height);// ��ȡ�ӵ����ο�
	}

	public void HitPlane(ArrayList<EnemyPlane> enemyList) {// �ж��ӵ��͵о���ײ
		for (int i = 0; i < enemyList.size(); i++) {
			EnemyPlane ep = enemyList.get(i);// �õ�ÿһ�ܷɻ�
			if (this.live && ep.isLive()
					&& this.getRec().intersects(ep.getRec())) {
				this.live = false;
				ep.setLive(false);
				gs.setBomb(new Bomb(x, y, 30, 30, true, gs));
				gs.setTotalScore(gs.getTotalScore()+10);
			}

		}
	}
}