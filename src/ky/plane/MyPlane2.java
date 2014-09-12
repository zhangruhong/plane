package ky.plane;

import java.awt.event.KeyEvent;

public class MyPlane2 extends PlaneBase {
	public MyPlane2(int x, int y, int width, int height, boolean isLive,
			GameStart gs) {
		super(x, y, width, height, isLive,100, gs);
		super.setMyPlaneImg(GameStart.tool.getImage(GameStart.class
				.getResource("/images/player01.png")));
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub//按键按下事件
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_UP:
			U = true;
			break;
		case KeyEvent.VK_DOWN:
			D = true;
			break;
		case KeyEvent.VK_LEFT:
			L = true;
			break;
		case KeyEvent.VK_RIGHT:
			R = true;
			break;
		case KeyEvent.VK_O:
			Fire = true;
			// MyBullet bullet=new MyBullet(x+15, y, 20, 20, true, gs);
			// gs.mb2List.add(bullet);
			// GameStart.beammusic.playSound("music/Beam.mp3");
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent e) {// 按键松开事件
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_UP:
			U = false;
			break;
		case KeyEvent.VK_DOWN:
			D = false;
			break;
		case KeyEvent.VK_LEFT:
			L = false;
			break;
		case KeyEvent.VK_RIGHT:
			R = false;
			break;
		case KeyEvent.VK_O:
			Fire = false;
			break;
		default:
			break;
		}
	}

}
