package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Random;

public class MyPlane {
	static Image myPlaneImg = GameStart.tool.getImage(GameStart.class
			.getResource("/images/player06.png"));// 我军飞机图片
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean alive;
	private Random random = new Random();

	private GameStart gs = null;

	boolean U, D, L, R, Fire;// 定义四个方向变量

	public MyPlane(int x, int y, int width, int height, boolean isLive,
			GameStart gs) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.alive = isLive;
		this.gs = gs;
	}

	public void drawMyPlane(Graphics g) {// 让飞机在规定区域飞行，别出界
		g.drawImage(myPlaneImg, x, y, width, height, gs);

		if (U && y > 20)
			y -= 5;
		if (D && y < 450)
			y += 5;
		if (L && x > 5)
			x -= 5;
		if (R && x < 350)
			x += 5;
		if (Fire) {
			if (random.nextInt(15) % 6 == 0) {
				MyBullet bullet = new MyBullet(x + 15, y, 20, 20, true, gs);
				gs.mbList.add(bullet);
				GameStart.beammusic.playSound("music/Beam.mp3");
			}

		}

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub//按键按下事件
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_W:
			U = true;
			break;
		case KeyEvent.VK_S:
			D = true;
			break;
		case KeyEvent.VK_A:
			L = true;
			break;
		case KeyEvent.VK_D:
			R = true;
			break;
		case KeyEvent.VK_J:
			Fire = true;

			break;
		default:
			break;
		}
	}  
 
	public void keyReleased(KeyEvent e) {// 按键松开事件
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_W:
			U = false;
			break;
		case KeyEvent.VK_S:
			D = false;
			break;
		case KeyEvent.VK_A:
			L = false;
			break;
		case KeyEvent.VK_D:
			R = false;
			break;
		case KeyEvent.VK_J:
			Fire = false;
			break;
		default:
			break;
		}
	}

}
