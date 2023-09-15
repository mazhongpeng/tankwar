package com.sxt;

import java.awt.*;
import java.util.ArrayList;

public abstract class Tank extends GameObject{
//      �ߴ�
	  public int width=40;
	  public int height =50;
//	  �ٶ�
	  public int speed=3;
//	  ����
	  public Direction direction=Direction.UP;
//	  ����
	  public boolean alive=false;
//	  �ĸ�����
	  private String upImg;
	  private String leftImg;
	  private String rightImg;
	  private String downImg;
//	  ������ȴ״̬
	  private boolean attackCoolDown=true;
//	  ������ȴʱ�������1000ms
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
//		 �߳̿�ʼ
		 new AttackCD().start();
		  }
	  }
//	       ���߳�
	  class AttackCD extends Thread{
		  public void run() {
//			  ��������������Ϊ��ȴ״̬
			  attackCoolDown=false;
//			  ����1��
			  try {
				  Thread.sleep(attackCoolDounTime);
			  }catch(Exception e) {
				  e.printStackTrace();
			  }
//			  ���������ܽ����ȴ״̬
			  attackCoolDown=true;
//			  �߳���ֹ
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
//	  ��Χǽ��ײ���
	  public boolean hitWall(int x,int y) {
//		  Χǽ�б�
		  ArrayList<Wall> walls=this.gamePanel.wallList;
//		  ��һ������
		  Rectangle next=new Rectangle(x,y,width,height);
//		  �����б�
		  for(Wall wall:walls) {
//			  ��ÿһ��Χײ������ײ���
			  if(next.intersects(wall.getRec())) {
//			 ������ײ������true
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
