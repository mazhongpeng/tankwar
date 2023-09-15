package com.sxt;

import java.awt.*;
import java.util.ArrayList;

public abstract class Tank extends GameObject{
//      尺寸
	  public int width=40;
	  public int height =50;
//	  速度
	  public int speed=3;
//	  方向
	  public Direction direction=Direction.UP;
//	  生命
	  public boolean alive=false;
//	  四个方向
	  private String upImg;
	  private String leftImg;
	  private String rightImg;
	  private String downImg;
//	  攻击冷却状态
	  private boolean attackCoolDown=true;
//	  攻击冷却时间毫秒间隔1000ms
	  private int attackCoolDounTime=1000;
	  public Tank(String img,int x,int y,GamePanel gamePanel,
			  String upImg,String leftImg,String rightImg,
			  String downImg) {
		     super(img,x,y,gamePanel);
		     this.upImg=upImg;
		     this.leftImg=leftImg;
		     this.rightImg=rightImg;
		     this.downImg=downImg;
	  }
	  public void leftward() {
		  direction=Direction.LEFT;
		  setImg(leftImg);
		  if(!hitWall(x-speed,y)&&!moveToBorder(x-speed,y)) {
		  this.x-=speed;
		  }
	  }
	  public void upward() {
		  direction=Direction.UP;
		  setImg(upImg);
		  if(!hitWall(x,y-speed)&&!moveToBorder(x,y-speed)) {
		  this.y-=speed;
		  }
	  }
	  public void rightward() {
		  direction=Direction.RIGHT;
		  setImg(rightImg);
		  if(!hitWall(x+speed,y)&&!moveToBorder(x+speed,y)) {
		  this.x+=speed;
		  }
	  }
	  public void downward() {
		  direction=Direction.DOWN;
		  setImg(downImg);
		  if(!hitWall(x,y+speed)&&!moveToBorder(x,y+speed)) {
		  this.y+=speed;
		  }
	  }
	  public void attack() {
		  if(attackCoolDown&&alive) {
		 Point p=this.getHeadPoint();
		 Bullet bullet=new Bullet("src/image/bulletGreen.gif",p.x,p.y,this.gamePanel,direction);
		 this.gamePanel.bulletList.add(bullet);
//		 线程开始
		 new AttackCD().start();
		  }
	  }
//	       新线程
	  class AttackCD extends Thread{
		  public void run() {
//			  将攻击功能设置为冷却状态
			  attackCoolDown=false;
//			  休眠1秒
			  try {
				  Thread.sleep(attackCoolDounTime);
			  }catch(Exception e) {
				  e.printStackTrace();
			  }
//			  将攻击功能解除冷却状态
			  attackCoolDown=true;
//			  线程终止
			  this.stop();
		  }
	  }
	  public Point getHeadPoint() {
		  switch(direction) {
		  case LEFT:
			  return new Point(x,y+height/2);
		  case RIGHT:
			  return new Point(x+width,y+height/2);
		  case UP:
			  return new Point(x+width/2,y);
		  case DOWN:
			  return new Point(x+width/2,y+height);
			  default:
				  return null;
		  }
	  }
//	  与围墙碰撞检测
	  public boolean hitWall(int x,int y) {
//		  围墙列表
		  ArrayList<Wall> walls=this.gamePanel.wallList;
//		  下一步矩形
		  Rectangle next=new Rectangle(x,y,width,height);
//		  遍历列表
		  for(Wall wall:walls) {
//			  与每一个围撞进行碰撞检测
			  if(next.intersects(wall.getRec())) {
//			 发生碰撞，返回true
			  return true;
			  }
		  }
		  return false;
	  }
	  public boolean moveToBorder(int x,int y) {
		  if(x<0) {
			  return true;
		  }else if(x+width>this.gamePanel.getWidth()) {
			  return true;
		  }else if(y<0) {
			  return true;
		  }else if(y+height>this.gamePanel.getHeight()) {
			  return true;
		  }
		  return false;
	  }
	  public void setImg(String img) {
		  this.img=Toolkit.getDefaultToolkit().getImage(img);
	  }
	  @Override
	  public abstract void paintSelf(Graphics g);
	  @Override
	  public abstract Rectangle getRec();
}
