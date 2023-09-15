package com.sxt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bullet extends GameObject{
//      尺寸
	int width=10;
	int height=10;
//	   速度
	int speed=7;
//	方向
	  Direction direction;
	  public Bullet(String img,int x,int y,GamePanel gamePanel,Direction direction) {
		  super(img,x,y,gamePanel);
		  this.direction=direction;
	  }
	  public void leftward() {
		  x-=speed;
	  }
	  public void rightward() {
		  x+=speed;
	  }
	  public void upward() {
		  y-=speed;
	  }
	  public void downward() {
		  y+=speed;
	  }
	  
	  public void go() {
		  this.moveToBorder();
		  switch(direction) {
		  case LEFT:
			  leftward();
			  break;
		  case RIGHT:
			  rightward();
			  break;
		  case UP:
			  upward();
			  break;
		  case DOWN:
			  downward();
			  break;
		  }
		  this.hitWall();
		  this.hitBase();
	  }
	  public void hitBot() {
		  ArrayList<Bot> bots=this.gamePanel.botList;
		  for(Bot bot:bots) {
			  if(this.getRec().intersects(bot.getRec())) {
				  this.gamePanel.blastList.add(new Blast("",bot.x-34,bot.y-14,this.gamePanel));
				  this.gamePanel.botList.remove(bot);
				  this.gamePanel.removeList.add(this);
				  break;
			  }
		  }
	  }
	  public void hitBase() {
		  ArrayList<Base> baseList=this.gamePanel.baseList;
		  for(Base base:baseList) {
			  if(this.getRec().intersects(base.getRec())) {
				  this.gamePanel.baseList.remove(base);
				  this.gamePanel.removeList.add(this);
				  
				  break;
			  }
		  }
	  }
//	  子弹与墙壁碰撞检测
	  public  void hitWall() {
//		  围墙列表
		  ArrayList <Wall> walls=this.gamePanel.wallList;
//		  遍历列表
		  for(Wall wall:walls) {
//			 与每一个围墙进行碰撞检测
			  if(this.getRec().intersects(wall.getRec())) {
//				  删去围墙和子弹
				  this.gamePanel.wallList.remove(wall);
				  this.gamePanel.removeList.add(this);
//				  停止循环
				  break;
				  
			  }
		  }
	  }
	  public void moveToBorder() {
		  if(x<0||x>this.gamePanel.getWidth()) {
			  this.gamePanel.removeList.add(this);
		  }
		  if(y<0||y>this.gamePanel.getHeight()) {
			  this.gamePanel.removeList.add(this);
		  }
	  }
	  @Override
	  public void paintSelf(Graphics g) {
		  g.drawImage(img,x, y, null);
//		  移动
		  this.go();
		  this.hitBot();
	  }
	  @Override
	  public Rectangle getRec() {
		  return new Rectangle(x,y,width,height);
	  }
}
