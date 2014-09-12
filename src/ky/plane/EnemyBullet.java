package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class EnemyBullet extends Ebullet {
	public EnemyBullet(int x, int y, int width, int height, boolean Live,
			GameStart gs) {
		super(x, y, width, height, Live, gs);
		super.setEBulletImg(GameStart.tool.getImage(GameStart.class
				.getResource("/images/en_bul01.png")));
	}

	@Override
	public void drawEBullet(Graphics g) {
		g.drawImage(super.getEBulletImg(), super.getX() + 20, super.getY(),
				super.getWidth(), super.getHeight(), super.getGs());// »­µÐ¾ü×Óµ¯
		super.setY(super.getY() + 3);

	}

}
