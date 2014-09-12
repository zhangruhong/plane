package ky.plane;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class GameStart extends Frame {

	private static final long serialVersionUID = 1L;
	private static GameStart gamestart = null;
	public static int type = 1;
	public GameSound beammusic = new GameSound();
	static Toolkit tool = Toolkit.getDefaultToolkit();// �õ�Ĭ�ϵĹ��߰�
	public final int width = 400;
	public final int height = 500;

	public Image MyBulletImg2 = tool.getImage(GameStart.class
			.getResource("/images/bb_02.png")); // �Ҿ��ӵ�ͼƬ2
	Image bloodImg = tool.getImage(GameStart.class
			.getResource("/images/blood01.png"));// ѪͼƬ

	public BackGround bg = new BackGround(0, 0, 400, 500, this);// ����ͼ
	public MyPlane mp = new MyPlane(160, 400, 50, 50, true, this);// �Ҿ��ɻ�����1
	public MyPlane2 mp2 = new MyPlane2(350, 400, 50, 50, true, this);// �Ҿ��ɻ�����2
	public ArrayList<EnemyPlane> enemyList = new ArrayList<EnemyPlane>();// �о�����1
	public ArrayList<EnemyBullet> enemyBulletList = new ArrayList<EnemyBullet>();// �о��ӵ�����2
	public ArrayList<MyBullet> mbList = new ArrayList<MyBullet>();// ���1�ӵ�����
	public ArrayList<MyBullet> mb2List = new ArrayList<MyBullet>();// ���2�ӵ�����
	public ArrayList<OldBossBullet> oldBossmbList = new ArrayList<OldBossBullet>();// oldBoss�ӵ�����
	public OldBossEnemyPlane obep = new OldBossEnemyPlane(150, 45, width - 250,
			height - 350, true, 1000, this);
	public Power p = new Power(200, 260, 40, 40, true, this);// ����
	private GameSound bgmusic = new GameSound();
	private Bomb bomb = null;
	private int totalScore = 4990;
	private boolean stop = true;
	Image[] enemyPlaneImages = new Image[4];
	int count = 1;// ���������ڿ���POWER���ֺͱ�ը�¼�

	public Bomb getBomb() {
		return bomb;
	}

	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public OldBossEnemyPlane getObep() {
		return obep;
	}

	public void setObep(OldBossEnemyPlane obep) {
		this.obep = obep;
	}

	public GameSound getBeammusic() {
		return beammusic;
	}

	public void setBeammusic(GameSound beammusic) {
		this.beammusic = beammusic;
	}

	public GameStart() {
		this.setSize(width, height);// ���ڴ�С
		this.setTitle("��һ��-�û��Ѵ�ս�һ�");
		bgmusic.playBgSound("music/BGM_0001.mp3");
		initEnemyImg();
		this.addWindowListener(new WindowAdapter() {// �رհ�ť��ֱ���˳�
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
				super.windowClosing(arg0);
			}

		});
		// ����ƶ���ť�����¼�
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				mp.keyPressed(e);
				mp2.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				mp.keyReleased(e);
				mp2.keyReleased(e);
			}

		});
		this.setResizable(false);// ���ɸı��С
		this.setLocationRelativeTo(null);// ���ó�ʼλ��
		this.setVisible(true);// ���ÿɼ�

		new MyThread().start();// �����߳�����
	}

	public void initEnemyImg() {
		for (int i = 0; i < enemyPlaneImages.length; i++) {
			enemyPlaneImages[i] = tool.getImage(GameStart.class
					.getResource("/images/enemy0" + (i + 1) + ".png"));// �о��ɻ�1ͼƬ
		}
	}

	/*
	 * ��ӻ�ͼ�̣߳����Ʊ������ҷ��ɻ�
	 */

	Random r = new Random();
	int time = 0;

	class MyThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (stop) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
				int index = r.nextInt(enemyPlaneImages.length);
				// ����paint(Graphics g)
				if (time % 40 == 0) {
					EnemyPlane ep = new EnemyPlane(r.nextInt(350), 0, 50, 50,
							true, enemyPlaneImages[index], 10, GameStart.this);// �о��ɻ�������Ϸ�����
					enemyList.add(ep);
				}
				time++;
			}
		}
	}

	Image tempImg = null;

	@Override
	// �������弼��
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		if (tempImg == null) {
			tempImg = this.createImage(400, 500);// ��һ����������������ֽ
		}
		Graphics newG = tempImg.getGraphics();// �ڶ������õ��»���
		g.drawImage(tempImg, 0, 0, 400, 500, this);// ����
		paint(newG);
	}

	@Override
	public void paint(Graphics g) {
		bg.drawBG(g);// ������
		if (mp != null && mp.isAlive()) {
			mp.drawMyPlane(g);// ���Լ��ɻ�1
		}
		if (mp2 != null && mp2.isAlive()) {
			mp2.drawMyPlane(g);// ���Լ��ɻ�2
		}

		if (stop == true && !mp.isAlive() && !mp2.isAlive()
				&& mp.getBlood() < 1 && mp2.getBlood() < 1) {
			stop = false;
			mp = null;
			mp2 = null;
			int option = JOptionPane.showConfirmDialog(this, "����һ�Σ�", "��ս�ɹ�",
					JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				// ���¿�ʼ
				gamestart.dispose();
				gamestart = null;
				gamestart = new GameStart();
				return;
			} else {
				System.exit(0);
				return;
			}
		}


		// �����������Χ��������1�Լ������ӵ��ȡ�����
		if (totalScore > 5000 && totalScore < 10000) {
			// oldbossģʽ
			for (int i = 0; i < mbList.size(); i++) {
				MyBullet mb = mbList.get(i);
				mb.drawMyBullet(g);// �����鼯������ӵ��������λ�����
				mb.HitOldBossPlane(obep);
				if (mb.getY() < 0)
					mb.setLive(false);
				if (!mb.isLive()) {
					mbList.remove(mb);
				}
			}
			mp.EBoosHitMyPlane(obep);
			mp2.EBoosHitMyPlane(obep);
			// �Լ����ӵ�2
			for (int i = 0; i < mb2List.size(); i++) {
				MyBullet mb = mb2List.get(i);
				mb.drawMyBullet(g);// �����鼯������ӵ��������λ�����
				mb.HitOldBossPlane(obep);
				if (mb.getY() < 0)
					mb.setLive(false);
				if (!mb.isLive()) {
					mb2List.remove(mb);
				}
			}

			obep.drawEPlane(g);
			// �ϲ�˹�ӵ�
			for (int i = 0; i < oldBossmbList.size(); i++) {
				OldBossBullet obb = oldBossmbList.get(i);
				mp.bulletHitMyplane(obb);
				obb.drawEBullet(g);
				// �����鼯����ĵо��ӵ��������λ�����
				if (obb.getY() > 600)
					obb.setLive(false);
				if (!obb.isLive()) {
					enemyBulletList.remove(obb);
				}
			}
		} else if (totalScore <= 5000) {
			// ��ͨģʽ
			// �Լ����ӵ�
			for (int i = 0; i < mbList.size(); i++) {
				MyBullet mb = mbList.get(i);
				if ((count / 500) % 2 == 1) {// �ж��ӵ��Ƿ�����ʳ������޸��ӵ�����
					mb.eatPower(p);
				}
				mb.drawMyBullet(g);// �����鼯������ӵ��������λ�����
				mb.HitPlane(enemyList);
				if (mb.getY() < 0)
					mb.setLive(false);
				if (!mb.isLive()) {
					mbList.remove(mb);
				}
			}
			// �Լ����ӵ�2
			for (int i = 0; i < mb2List.size(); i++) {
				MyBullet mb = mb2List.get(i);
				mb.drawMyBullet(g);// �����鼯������ӵ��������λ�����
				mb.HitPlane(enemyList);
				if (mb.getY() < 0)
					mb.setLive(false);
				if (!mb.isLive()) {
					mb2List.remove(mb);
				}
			}
			// �л�
			for (int i = 0; i < enemyList.size(); i++) {
				EnemyPlane ep = enemyList.get(i);
				ep.drawEPlane(g);// �����鼯������ӵо��������λ�����
				mp.EPlanehitMyplane(ep);;
				mp2.EPlanehitMyplane(ep);;
				if (ep.getY() > 650)
					ep.setAlive(false);
				if (!ep.isAlive()) {
					enemyList.remove(ep);
				}
			}
			// �����ӵ�1
			for (int i = 0; i < enemyBulletList.size(); i++) {
				EnemyBullet eb = enemyBulletList.get(i);
				mp.bulletHitMyplane(eb);
				mp2.bulletHitMyplane(eb);
				eb.drawEBullet(g);// �����鼯����ĵо��ӵ��������λ�����
				if (eb.getY() > 650)
					eb.setLive(false);
				if (!eb.isLive()) {
					enemyBulletList.remove(eb);
				}
			}
		}
		if (stop == true && totalScore == 10000) {
			// JOptionPane.showMessageDialog(this, "��ս�ɹ���"
			// ,"KO",MessageType.INFO);
			stop = false;
			// JOptionPane.showMessageDialog(null, "��ս�ɹ���");
			int option = JOptionPane.showConfirmDialog(null, "����һ�Σ�", "��ս�ɹ�",
					JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				// ���¿�ʼ
				gamestart.dispose();
				gamestart = null;
				gamestart = new GameStart();

			} else {
				System.exit(0);
			}

		}
		// ��ը
		if (bomb != null) {
			bomb.drawBomb(g);// ����ը
		}
		bomb = null;

		count++;// �൱�ڶ�ʱ��

		// Ѫ�� �÷�
		int pointx = 10, pointy = 30;
		g.setColor(Color.GREEN);
		g.drawString("躸�:", pointx, pointy + 20);
		g.drawRect(pointx + 30, pointy + 10, 70, 10);
		g.fillRect(pointx + 30, pointy + 10, (int) (0.7 * mp.getBlood()), 10);

		g.setColor(Color.RED);
		g.drawString("���:", pointx + 290, pointy + 20);
		g.drawRect(pointx + 320, pointy + 10, 70, 10);
		g.fillRect(pointx + 320, pointy + 10, (int) (0.7 * mp.getBlood()), 10);

		g.setColor(Color.WHITE);
		g.drawString("��ǰ�÷�", pointx + 170, pointy + 10);
		g.drawString(String.valueOf(totalScore), pointx + 190, pointy + 30);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		gamestart = new GameStart();// ������Ϸ
	}

}
