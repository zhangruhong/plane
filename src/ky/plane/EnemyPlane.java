package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class EnemyPlane extends EPlane {

	int time1 = 0;

	public EnemyPlane(int x, int y, int width, int height, boolean alive,
			Image enemyPlaneImages, int liveValue, GameStart gs) {
		super(x, y, width, height, alive, liveValue, gs);
		super.setEPlaneImg(enemyPlaneImages);
	}

	public Rectangle getRec() {
		return new Rectangle(super.getX(), super.getY(), super.getWidth(),
				super.getHeight());// 获取敌军飞机矩形框
	}

	@Override
	public void drawEPlane(Graphics g) {
		g.drawImage(super.getEPlaneImg(), super.getX(), super.getY(),
				super.getWidth(), super.getHeight(), super.getGs());
		if (time1 % 200 == 0) {
			EnemyBullet eb = new EnemyBullet(super.getX(), super.getY(), 10,
					10, true, super.getGs());
			super.getGs().enemyBulletList.add(eb);// 将敌军子弹加入数组列表
			time1 = 0;
		}
		time1++;
		super.setY(super.getY() + 3);

	}

}
