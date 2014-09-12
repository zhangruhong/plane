package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

public class OldBossBullet extends Ebullet {
	private int random = 0;

	public OldBossBullet(int x, int y, int width, int height, boolean Live,
			GameStart gs) {
		super(x, y, width, height, Live, gs);
		super.setEBulletImg(GameStart.tool.getImage(GameStart.class
				.getResource("/images/en_bul01.png")));
		random = (new Random()).nextInt(6) - 3;
	}

	@Override
	public void drawEBullet(Graphics g) {
		g.drawImage(super.getEBulletImg(), super.getX() + 20, super.getY(),
				super.getWidth(), super.getHeight(), super.getGs());// »­µÐ¾ü×Óµ¯
		super.setY(super.getY() + 3);
		super.setX(super.getX() + random);

	}
}
