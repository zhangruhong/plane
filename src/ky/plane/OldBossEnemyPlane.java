package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

public class OldBossEnemyPlane extends EPlane {

	Random random = new Random();
	private boolean flag = false;

	public OldBossEnemyPlane(int x, int y, int width, int height,
			boolean alive, int liveValue, GameStart gs) {
		super(x, y, width, height, alive, liveValue, gs);
		super.setEPlaneImg(GameStart.tool.getImage(GameStart.class
				.getResource("/images/boss_2.png")));
	}

	public Rectangle getRec() {
		return new Rectangle(super.getX(), super.getY(), super.getWidth(),
				super.getHeight());// 获取老BOSS飞机矩形框
	}

	@Override
	public void drawEPlane(Graphics g) {
		// 方向控制
		if (super.getX() == 0) {
			flag = true;
		}
		if (super.getX() >= 400 - super.getWidth()) {
			flag = false;
		}

		if (flag == true) {
			super.setX(super.getX() + 1);
		} else {
			super.setX(super.getX() - 1);
		}

		int pointx = super.getX() % (410 - super.getWidth());
		g.drawImage(super.getEPlaneImg(), pointx, super.getY(),
				super.getWidth(), super.getHeight(), super.getGs());
		if (random.nextInt(201) % 40 == 0) {
			OldBossBullet eb = new OldBossBullet(pointx + 50,
					super.getY() + 120, 10, 10, true, super.getGs());
			 super.getGs().oldBossmbList.add(eb);// 将敌军子弹加入数组列表
		}

	}

}
