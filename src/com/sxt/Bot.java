package com.sxt;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Bot extends Tank{
	int moveTime=0;
	
	public Bot(String img,int x,int y,GamePanel gamePanel,
			  String upImg,String leftImg,String rightImg,
			  String downImg) {
		     super(img,x,y,gamePanel,upImg,leftImg,rightImg,downImg);
	     }
	public Direction getRandomDirection() {
		Random random=new Random();
		int rnum=random.nextInt(4);
		switch(rnum) {
		case 0:
			return Direction.LEFT;
		case 1:
			return Direction.RIGHT;
		case 2:
			return Direction.UP;
		case 3:
			return Direction.DOWN;
			default:
				return null;
		}
	}
	public void go() {
		attack();
		if(moveTime>=20) {
			direction=getRandomDirection();
			moveTime=0;
		}else {
			moveTime++;
		}
		if(direction==Direction.LEFT&&!hitplayer(x-speed,y)) {
    		leftward();
    	}else if(direction==Direction.RIGHT&&!hitplayer(x+speed,y)) {
    		rightward();
    	}else if(direction==Direction.UP&&!hitplayer(x,y-speed)) {
    		upward();
    	}else if(direction==Direction.DOWN&&!hitplayer(x,y+speed)) {
    		downward();
    	}
	}
	public void attack() {
		Point p=getHeadPoint();
		Random random=new Random();
		int rnum=random.nextInt(400);
		if(rnum<4) {
			this.gamePanel.bulletList.add(new EnemyBullet("src/image/bulletYellow.gif",p.x,p.y,this.gamePanel,direction));
		}
	}
//	  与敌方坦克碰撞检测
	  public boolean hitplayer(int x,int y) {
//		  敌机列表
		  ArrayList<Tank> players=this.gamePanel.playerList;
//		  下一步矩形
		  Rectangle nexts=new Rectangle(x,y,width,height);
		  for(Tank player: players) {
			  if(nexts.intersects(player.getRec())) {
				  return true;
			  }
		  }
		  return false;
	  }
	@Override
	  public  void paintSelf(Graphics g) {
		g.drawImage(img,x, y, null);
		go();
	}
	  @Override
	  public  Rectangle getRec() {
		  return new Rectangle(x,y,width,height);
	  }
}
