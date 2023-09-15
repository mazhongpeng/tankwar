package com.sxt;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
public class PlayerTwo extends Tank{
	private boolean up=false;
	private boolean left=false;
	private boolean right=false;
	private boolean down=false;
	public PlayerTwo(String img,int x,int y,GamePanel gamePanel,
			  String upImg,String leftImg,String rightImg,
			  String downImg) {
		super(img,x,y,gamePanel,upImg,leftImg,rightImg,downImg);
	    }
	    public void keyPressed(KeyEvent e) {
	    	int key=e.getKeyCode();
	    	switch(key){
	    	case KeyEvent.VK_LEFT:
	    		 left=true; 
	    		 break;
	    	case KeyEvent.VK_DOWN:
	    		 down=true; 
	    		 break; 
	    	case KeyEvent.VK_RIGHT:
	    		right=true; 
	    		 break;
	    	case KeyEvent.VK_UP:
	    		 up=true; 
	    		 break;
	    	case KeyEvent.VK_K:
	    		attack();
	    		 default:
	    			 break;
	    	}
	    }
	    public void keyReleased(KeyEvent e) {
	    	int key=e.getKeyCode();
	    	switch(key){
	    	case KeyEvent.VK_LEFT:
	    		 left=false; 
	    		 break;
	    	case KeyEvent.VK_DOWN:
	    		 down=false; 
	    		 break; 
	    	case KeyEvent.VK_RIGHT:
	    		right=false; 
	    		 break;
	    	case KeyEvent.VK_UP:
	    		 up=false; 
	    		 break;
	    		 default:
	    			 break;
	    	}
	    }
	    public void move() {
	    	if(left&&!hitbot(x-speed,y)) {
	    		leftward();
	    	}else if(right&&!hitbot(x+speed,y)) {
	    		rightward();
	    	}else if(up&&!hitbot(x,y-speed)) {
	    		upward();
	    	}else if(down&&!hitbot(x,y+speed)) {
	    		downward();
	    	}
	    }
//		  与敌方坦克碰撞检测
		  public boolean hitbot(int x,int y) {
//			  敌机列表
			  ArrayList<Bot> bots=this.gamePanel.botList;
//			  下一步矩形
			  Rectangle nexts=new Rectangle(x,y,width,height);
			  for(Bot bot:bots) {
				  if(nexts.intersects(bot.getRec())) {
					  return true;
				  }
			  }
			  return false;
		  }
		 @Override
		  public void paintSelf(Graphics g) {
			 g.drawImage(img,x,y,null);
			 move();
		 }
		 public Rectangle getRec() {
			 return new Rectangle(x,y,width,height);
		 }
}