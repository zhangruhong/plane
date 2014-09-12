package ky.plane;

import java.awt.event.KeyEvent;

public class MyPlane extends PlaneBase {

	public MyPlane(int x, int y, int width, int height, boolean isLive,
			GameStart gs) {
		super(x, y, width, height, isLive, 100, gs);
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

	// �о��ӵ���ս�Ҿ��ɻ����÷��ʹ���о���ͨ�ӵ���boss�ӵ���
	@Override
	public <T> void bulletHitMyplane(Ebullet eb) {
		if (eb.isLive() && this.getRec().intersects(eb.getRec())) {
			if (blood < 1) {
				this.alive = false;
				gs.setBomb(new Bomb(x, y, 30, 30, true, gs));
			}
			eb.setLive(false);
			// ���ݲ�ͬ���ӵ��ۼ���ͬ�ķ���
			if (eb.getClass().isInstance(EnemyBullet.class)) {
				System.out
						.println("eb.getClass().isInstance(EnemyBullet.class����");
				this.blood -= 10;
			} else {
				this.blood -= 20;
				System.out
						.println("eb.getClass().isInstance(EnemyBullet.class������");
			}
		}
	}

	@Override
	public void EPlanehitMyplane(EPlane ePlane) {
		if (ePlane.isAlive() && this.getRec().intersects(ePlane.getRec())) {
			this.alive = false;
			ePlane.setAlive(false);
			gs.setBomb(new Bomb(x, y, 30, 30, true, gs));
			this.blood = 0;
		}
	}

	@Override
	public void EBoosHitMyPlane(OldBossEnemyPlane oldbossenemyplane) {
		OldBossEnemyPlane obep = oldbossenemyplane; // �õ��Ϸɻ�
		if (obep.isAlive() && this.getRec().intersects(obep.getRec())) {
			this.alive = false;
			obep.setAlive(false);
			gs.setBomb(new Bomb(x, y, 30, 30, true, gs));
			this.blood = 0;
		}
	}

}
