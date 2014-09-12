package ky.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Random;

public class MyPlane2 {
	static Image myPlane2Img=GameStart.tool.getImage(GameStart.class.getResource("/images/player01.png"));//我军飞机2图片
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean isLive;
	private Random r =new Random();
	private GameStart gs=null;
	
	boolean U,D,L,R,Fire;//定义四个方向变量

	public MyPlane2(int x, int y, int width, int height, boolean isLive,
			GameStart gs) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isLive = isLive;
		this.gs = gs;
	}
	public void drawMyPlane(Graphics g){//让飞机在规定区域飞行，别出界
		g.drawImage(myPlane2Img, x, y, width,height,gs);
		
		if(U&&y>20)
			y-=5;
		if(D&&y<450)
			y+=5;
		if(L&&x>5)
			x-=5;
		if(R&&x<350)
			x+=5;
		if (Fire) {
			if (r.nextInt(15) % 6 == 0) {
				MyBullet bullet=new MyBullet(x+15, y, 20, 20, true, gs);
				gs.mb2List.add(bullet);
				GameStart.beammusic.playSound("music/Beam.mp3");
			}
			
		}

	}
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub//按键按下事件
		int code=e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_UP:
			U=true;
			break;
		case KeyEvent.VK_DOWN:
			D=true;
			break;
		case KeyEvent.VK_LEFT:
			L=true;
			break;
		case KeyEvent.VK_RIGHT:
			R=true;
			break;
		case KeyEvent.VK_O:
			Fire=true;
//			MyBullet bullet=new MyBullet(x+15, y, 20, 20, true, gs);
//			gs.mb2List.add(bullet);
//			GameStart.beammusic.playSound("music/Beam.mp3");
			break;
		default:
			break;
		}
	}
	public void keyReleased(KeyEvent e) {//按键松开事件
		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_UP:
			U=false;
			break;
		case KeyEvent.VK_DOWN:
			D=false;
			break;
		case KeyEvent.VK_LEFT:
			L=false;
			break;
		case KeyEvent.VK_RIGHT:
			R=false;
			break;
		case KeyEvent.VK_O:
			Fire=false;
			break;
		default:
			break;
		}
	}

}
