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
	static Toolkit tool = Toolkit.getDefaultToolkit();// 拿到默认的工具包
	public final int width = 400;
	public final int height = 500;

	public Image MyBulletImg2 = tool.getImage(GameStart.class
			.getResource("/images/bb_02.png")); // 我军子弹图片2
	Image bloodImg = tool.getImage(GameStart.class
			.getResource("/images/blood01.png"));// 血图片

	public BackGround bg = new BackGround(0, 0, 400, 500, this);// 背景图
	public MyPlane mp = new MyPlane(160, 400, 50, 50, true, this);// 我军飞机对象1
	public MyPlane2 mp2 = new MyPlane2(350, 400, 50, 50, true, this);// 我军飞机对象2
	public ArrayList<EnemyPlane> enemyList = new ArrayList<EnemyPlane>();// 敌军集合1
	public ArrayList<EnemyBullet> enemyBulletList = new ArrayList<EnemyBullet>();// 敌军子弹集合2
	public ArrayList<MyBullet> mbList = new ArrayList<MyBullet>();// 玩家1子弹集合
	public ArrayList<MyBullet> mb2List = new ArrayList<MyBullet>();// 玩家2子弹集合
	public ArrayList<OldBossBullet> oldBossmbList = new ArrayList<OldBossBullet>();// oldBoss子弹集合
	public OldBossEnemyPlane obep = new OldBossEnemyPlane(150, 45, width - 250,
			height - 350, true, 1000, this);
	public Power p = new Power(200, 260, 40, 40, true, this);// 能量
	private GameSound bgmusic = new GameSound();
	private Bomb bomb = null;
	private int totalScore = 4990;
	private boolean stop = true;
	Image[] enemyPlaneImages = new Image[4];
	int count = 1;// 计数，用于控制POWER出现和爆炸事件

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
		this.setSize(width, height);// 窗口大小
		this.setTitle("第一组-好基友大战灰机");
		bgmusic.playBgSound("music/BGM_0001.mp3");
		initEnemyImg();
		this.addWindowListener(new WindowAdapter() {// 关闭按钮，直接退出
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
				super.windowClosing(arg0);
			}

		});
		// 添加移动按钮监听事件
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
		this.setResizable(false);// 不可改变大小
		this.setLocationRelativeTo(null);// 设置初始位置
		this.setVisible(true);// 设置可见

		new MyThread().start();// 背景线程启动
	}

	public void initEnemyImg() {
		for (int i = 0; i < enemyPlaneImages.length; i++) {
			enemyPlaneImages[i] = tool.getImage(GameStart.class
					.getResource("/images/enemy0" + (i + 1) + ".png"));// 敌军飞机1图片
		}
	}

	/*
	 * 添加绘图线程，绘制背景和我方飞机
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
				// 调用paint(Graphics g)
				if (time % 40 == 0) {
					EnemyPlane ep = new EnemyPlane(r.nextInt(350), 0, 50, 50,
							true, enemyPlaneImages[index], 10, GameStart.this);// 敌军飞机随记在上方出现
					enemyList.add(ep);
				}
				time++;
			}
		}
	}

	Image tempImg = null;

	@Override
	// 二级缓冲技术
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		if (tempImg == null) {
			tempImg = this.createImage(400, 500);// 第一步，创建缓冲区画纸
		}
		Graphics newG = tempImg.getGraphics();// 第二步，拿到新画笔
		g.drawImage(tempImg, 0, 0, 400, 500, this);// 成像
		paint(newG);
	}

	@Override
	public void paint(Graphics g) {
		bg.drawBG(g);// 画背景
		if (mp != null && mp.isAlive()) {
			mp.drawMyPlane(g);// 画自己飞机1
		}
		if (mp2 != null && mp2.isAlive()) {
			mp2.drawMyPlane(g);// 画自己飞机2
		}

		if (stop == true && !mp.isAlive() && !mp2.isAlive()
				&& mp.getBlood() < 1 && mp2.getBlood() < 1) {
			stop = false;
			mp = null;
			mp2 = null;
			int option = JOptionPane.showConfirmDialog(this, "再来一次？", "挑战成功",
					JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				// 重新开始
				gamestart.dispose();
				gamestart = null;
				gamestart = new GameStart();
				return;
			} else {
				System.exit(0);
				return;
			}
		}


		// 分数在这个范围出现老王1以及他的子弹等。。。
		if (totalScore > 5000 && totalScore < 10000) {
			// oldboss模式
			for (int i = 0; i < mbList.size(); i++) {
				MyBullet mb = mbList.get(i);
				mb.drawMyBullet(g);// 将数组集合里的子弹对象依次画出来
				mb.HitOldBossPlane(obep);
				if (mb.getY() < 0)
					mb.setLive(false);
				if (!mb.isLive()) {
					mbList.remove(mb);
				}
			}
			mp.EBoosHitMyPlane(obep);
			mp2.EBoosHitMyPlane(obep);
			// 自己的子弹2
			for (int i = 0; i < mb2List.size(); i++) {
				MyBullet mb = mb2List.get(i);
				mb.drawMyBullet(g);// 将数组集合里的子弹对象依次画出来
				mb.HitOldBossPlane(obep);
				if (mb.getY() < 0)
					mb.setLive(false);
				if (!mb.isLive()) {
					mb2List.remove(mb);
				}
			}

			obep.drawEPlane(g);
			// 老波斯子弹
			for (int i = 0; i < oldBossmbList.size(); i++) {
				OldBossBullet obb = oldBossmbList.get(i);
				mp.bulletHitMyplane(obb);
				obb.drawEBullet(g);
				// 将数组集合里的敌军子弹对象依次画出来
				if (obb.getY() > 600)
					obb.setLive(false);
				if (!obb.isLive()) {
					enemyBulletList.remove(obb);
				}
			}
		} else if (totalScore <= 5000) {
			// 普通模式
			// 自己的子弹
			for (int i = 0; i < mbList.size(); i++) {
				MyBullet mb = mbList.get(i);
				if ((count / 500) % 2 == 1) {// 判断子弹是否命中食物，命中修改子弹类型
					mb.eatPower(p);
				}
				mb.drawMyBullet(g);// 将数组集合里的子弹对象依次画出来
				mb.HitPlane(enemyList);
				if (mb.getY() < 0)
					mb.setLive(false);
				if (!mb.isLive()) {
					mbList.remove(mb);
				}
			}
			// 自己的子弹2
			for (int i = 0; i < mb2List.size(); i++) {
				MyBullet mb = mb2List.get(i);
				mb.drawMyBullet(g);// 将数组集合里的子弹对象依次画出来
				mb.HitPlane(enemyList);
				if (mb.getY() < 0)
					mb.setLive(false);
				if (!mb.isLive()) {
					mb2List.remove(mb);
				}
			}
			// 敌机
			for (int i = 0; i < enemyList.size(); i++) {
				EnemyPlane ep = enemyList.get(i);
				ep.drawEPlane(g);// 将数组集合里的子敌军对象依次画出来
				mp.EPlanehitMyplane(ep);;
				mp2.EPlanehitMyplane(ep);;
				if (ep.getY() > 650)
					ep.setAlive(false);
				if (!ep.isAlive()) {
					enemyList.remove(ep);
				}
			}
			// 敌人子弹1
			for (int i = 0; i < enemyBulletList.size(); i++) {
				EnemyBullet eb = enemyBulletList.get(i);
				mp.bulletHitMyplane(eb);
				mp2.bulletHitMyplane(eb);
				eb.drawEBullet(g);// 将数组集合里的敌军子弹对象依次画出来
				if (eb.getY() > 650)
					eb.setLive(false);
				if (!eb.isLive()) {
					enemyBulletList.remove(eb);
				}
			}
		}
		if (stop == true && totalScore == 10000) {
			// JOptionPane.showMessageDialog(this, "挑战成功！"
			// ,"KO",MessageType.INFO);
			stop = false;
			// JOptionPane.showMessageDialog(null, "挑战成功！");
			int option = JOptionPane.showConfirmDialog(null, "再来一次？", "挑战成功",
					JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				// 重新开始
				gamestart.dispose();
				gamestart = null;
				gamestart = new GameStart();

			} else {
				System.exit(0);
			}

		}
		// 爆炸
		if (bomb != null) {
			bomb.drawBomb(g);// 画爆炸
		}
		bomb = null;

		count++;// 相当于定时器

		// 血块 得分
		int pointx = 10, pointy = 30;
		g.setColor(Color.GREEN);
		g.drawString("韬哥:", pointx, pointy + 20);
		g.drawRect(pointx + 30, pointy + 10, 70, 10);
		g.fillRect(pointx + 30, pointy + 10, (int) (0.7 * mp.getBlood()), 10);

		g.setColor(Color.RED);
		g.drawString("祥哥:", pointx + 290, pointy + 20);
		g.drawRect(pointx + 320, pointy + 10, 70, 10);
		g.fillRect(pointx + 320, pointy + 10, (int) (0.7 * mp.getBlood()), 10);

		g.setColor(Color.WHITE);
		g.drawString("当前得分", pointx + 170, pointy + 10);
		g.drawString(String.valueOf(totalScore), pointx + 190, pointy + 30);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		gamestart = new GameStart();// 创建游戏
	}

}
