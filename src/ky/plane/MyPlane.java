package ky.plane;

import java.awt.event.KeyEvent;

public class MyPlane extends PlaneBase {

	public MyPlane(int x, int y, int width, int height, boolean isLive,
			GameStart gs) {
		super(x, y, width, height, isLive, 100,gs);
		super.setMyPlaneImg(GameStart.tool.getImage(GameStart.class
				.getResource("/images/player06.png")));// �Ҿ��ɻ�ͼƬ
	}

	public void keyPressed(KeyEvent e) {
		// ���������¼�
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

	public void keyReleased(KeyEvent e) {// �����ɿ��¼�
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
