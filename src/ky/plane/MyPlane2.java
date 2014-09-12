package ky.plane;

import java.awt.event.KeyEvent;

public class MyPlane2 extends PlaneBase {
	public MyPlane2(int x, int y, int width, int height, boolean isLive,
			GameStart gs) {
		super(x, y, width, height, isLive, 100, gs);
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

	// 敌军子弹大战我军飞机（用泛型处理敌军普通子弹和boss子弹）
	@Override
	public <T> void bulletHitMyplane(Ebullet eb) {
		if (eb.isLive() && this.getRec().intersects(eb.getRec())) {
			if (blood < 1) {
				this.alive = false;
				gs.setBomb(new Bomb(x, y, 30, 30, true, gs));
			}
			eb.setLive(false);
			// 根据不同的子弹扣减不同的分数
			if (eb.getClass().isInstance(EnemyBullet.class)) {
				System.out
						.println("eb.getClass().isInstance(EnemyBullet.class成立");
				this.blood -= 10;
			} else {
				this.blood -= 20;
				System.out
						.println("eb.getClass().isInstance(EnemyBullet.class不成立");
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
		OldBossEnemyPlane obep = oldbossenemyplane; // 拿到老飞机
		if (obep.isAlive() && this.getRec().intersects(obep.getRec())) {
			this.alive = false;
			obep.setAlive(false);
			gs.setBomb(new Bomb(x, y, 30, 30, true, gs));
			this.blood = 0;
		}
	}

}
