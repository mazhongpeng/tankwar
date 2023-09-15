package com.sxt;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
public class GamePanel extends JFrame{
//	        定义双缓冲图片
	        Image offScreemImage=null;
//	        定义指针图片
//	        变量
	        int s=0;
	        Image select=Toolkit.getDefaultToolkit().getImage("src/image/selecttank.gif");
//	        指针图片的初始位置
	        int y=150;
//	        添加游戏模式 0游戏未开始 1单人游戏 2双人游戏 5游戏胜利
	      public int state=0;
//	      临时变量
	      public  int a=1;
	      public  int i=1;
//	      重绘次数
	      int count=0;
//	      以生成敌人数量
	      int enemyCount=0;
//	      游戏元素列表
	      ArrayList<Bullet> bulletList=new ArrayList<Bullet>();
	      ArrayList<Bot> botList=new ArrayList<Bot>();
	      ArrayList<Bullet> removeList=new ArrayList<Bullet>();
	      ArrayList<Tank> playerList=new ArrayList<Tank>();
	      ArrayList<Wall> wallList=new ArrayList<Wall>();
	      ArrayList<Base> baseList=new ArrayList<Base>();
	      ArrayList<Blast> blastList=new ArrayList<Blast>();
//	      游戏是否开始
	      private boolean start=false;
//	      玩家1
	      PlayerOne playerOne=new PlayerOne("src/image/p1tankU.gif",125,510,this,"src/image/p1tankU.gif",
	    		                  "src/image/p1tankL.gif" ,"src/image/p1tankR.gif",
	    		                  "src/image/p1tankD.gif");
//	      玩家2
	      PlayerTwo playerTwo=new PlayerTwo("src/image/p2tankU.gif",625,510,this,"src/image/p2tankU.gif",
                  "src/image/p2tankL.gif" ,"src/image/p2tankR.gif",
                  "src/image/p2tankD.gif");
//	      Base
	      Base base=new Base("src/image/star.gif",375,570,this);
//	      窗口的启动方法
          public void launch() {
//    标题
    	      setTitle ("坦克大战");
//    	大小
    	      setSize(800,610);
//    	居中
    	      setLocationRelativeTo(null);
//  关闭事件  	
    	      this.setDefaultCloseOperation(3);
//    	不能调整大小
    	        setResizable(false);
//    	使窗口可见
    	        setVisible(true);
//    	添加键盘监视器
            	this.addKeyListener(new GamePanel.KeyMonitor());
//            	添加围墙
            	for(int i=0;i<14;i++) {
            		wallList.add(new Wall("src/image/walls.gif",i*60,170,this));
            	}
            	wallList.add(new Wall("src/image/walls.gif",305,560,this));
            	wallList.add(new Wall("src/image/walls.gif",305,500,this));
            	wallList.add(new Wall("src/image/walls.gif",365,500,this));
            	wallList.add(new Wall("src/image/walls.gif",425,500,this));
            	wallList.add(new Wall("src/image/walls.gif",425,560,this));
//            	添加基地
            	baseList.add(base);
//    	重绘
            	while(true) {
//            		游戏胜利判定
            		if(botList.size()==0&&enemyCount==10) {
            		state=5;	
            		}
//     
//            		添加失败判定
            		if((playerList.size()==0&&(state==1||state==2))||baseList.size()==0){
            			if(baseList.size()==0) {
            				this.base=new Base("src/image/star.gif",375,570,this);
            				baseList.add(base);
            			}
            			state=4; 
            			}
//            		添加游戏坦克
            		if(count%100==1&&enemyCount<10&&(state==1||state==2)) {
            			Random random=new Random();
            			int rnum=random.nextInt(800);
//            			int rnum = (int)(Math.random()*800);
            		
            		botList.add(new Bot("src/image/enemy1U.gif",rnum,110,this,"src/image/enemy1U.gif",
          	    		  "src/image/enemy1L.gif","src/image/enemy1R.gif",
        	    		  "src/image/enemy1D.gif"
        	    		  ));
            		   enemyCount++;
            		}
            		repaint();
            		try {
            			Thread.sleep(25);
            		}catch(Exception e) {
            			e.printStackTrace();
            		        }
            	        }
            	
                     }    
          private Blast Blast(String string, int i, int j, Object object) {
	// TODO Auto-generated method stub
	        return null;
           }
		@Override
          public void paint(Graphics g) {
//        	  System.out.println(bulletList.size());
//    	创建一个窗口大小一样的图片
        	  if(offScreemImage==null) {
        		  offScreemImage=this.createImage(800, 610);
        	  }
//    	获取该图片的画笔
        	  Graphics gImage=offScreemImage.getGraphics();
//    	画笔颜色
        	  gImage.setColor(Color.gray);
//    	绘画实心矩形
        	  gImage.fillRect(0,0,800,610);
        	  gImage.setColor(Color.blue);
//    	改变文字大小
        	  gImage.setFont(new Font("宋体",Font.BOLD,50));
        	  if(state==0) {
//    	添加文字
        		  gImage.drawString("选择游戏模式",220,100);
        		  gImage.drawString("单人游戏",220,200);
        		  gImage.drawString("双人游戏",220,300);
        		  gImage.drawString("按r重新开始游戏", 220,400);
        		  gImage.drawString("按1或2选择游戏模式", 220,500);
//    	绘制指针
        		  gImage.drawImage(select,160, y, null);
        	  }else if(state==1||state==2){
        	       gImage.setFont(new Font("宋体",Font.BOLD,30));
        	       gImage.setColor(Color.red);
        	       gImage.drawString("剩余敌人："+botList.size(),8, 55);
//    			绘制游戏元素
    			for(Tank player:playerList) {
    				player.paintSelf(gImage);
    			}
    			for(Bullet bullet:bulletList) {
    				bullet.paintSelf(gImage);
    			}
    			bulletList.removeAll(removeList);
    			for(Bot bot:botList) {
    				bot.paintSelf(gImage);
    			}
    			for(Wall wall:wallList) {
    				wall.paintSelf(gImage);
    			}
    			for(Base base:baseList) {
    				base.paintSelf(gImage);
    			}
    			for(Blast blast:blastList) {
    				blast.paintSelf(gImage);
    			}
//    			重绘一次
    			count++;
        	  }
        	  else if(state==5) {
        		  gImage.drawString("游戏胜利", 220,200);
        		  gImage.drawString("按r重新开始游戏", 220,300);
        	  }else if(state==4) {
        		  a=4;
        		  gImage.drawString("游戏失败", 220,200);
        		  gImage.drawString("按r重新开始游戏", 220,300);
        	  } else if(state==3) {
        		  gImage.drawString("游戏暂停", 220,200);
        		  gImage.drawString("按r重新开始游戏", 220,300);
        	  }
//    	将缓存区域绘制好的图形整个绘制到容器的画布中
        	  g.drawImage(offScreemImage,0,0,null);
          }
////   键盘监视器
//		重新开始
          class KeyMonitor extends KeyAdapter{
////    	按下键盘
        	  @Override
        	  public void keyPressed(KeyEvent e) {
        		  int key=e.getKeyCode();
        		  switch(key) {
        		  case KeyEvent.VK_1:
        			  a=1;
        			  y=150;
        			  break;
        		  case KeyEvent.VK_2:
        			  a=2;
        			  y=250;
        			  break;
        		  case KeyEvent.VK_R:
        			  state=0;
        			  lv();
        			  break;
        		  case KeyEvent.VK_ENTER:
        			  state=a;
        			  playerList.add(playerOne);
//        			  pleyertwo
        			  if(state==2) {
        				  playerList.add(playerTwo);
        				  playerTwo.alive=true;
        			  }
        			  playerOne.alive=true;
        			  break;
        		  case KeyEvent.VK_P:
        			  if(state!=3) {
        				  a=state;
        				  state=3;
        			  }else {
        				  state=a;
        				  if(a==0) {
        					  a=1;
        				  }
        			  }
        			  default:
        				  playerOne.keyPressed(e);
        				  playerTwo.keyPressed(e);
        		  }
        	  }
//        	  松开键盘
        	  @Override
        	  public void keyReleased(KeyEvent e) {
        		  playerOne.keyReleased(e);
        		  playerTwo.keyReleased(e);
        	  }
          }
         public void lv() {
//        	 将墙补齐
        	 wallList.clear(); 
        	 for(int i=0;i<14;i++) {
         		wallList.add(new Wall("src/image/walls.gif",i*60,170,this));
         	}
         	wallList.add(new Wall("src/image/walls.gif",305,560,this));
         	wallList.add(new Wall("src/image/walls.gif",305,500,this));
         	wallList.add(new Wall("src/image/walls.gif",365,500,this));
         	wallList.add(new Wall("src/image/walls.gif",425,500,this));
         	wallList.add(new Wall("src/image/walls.gif",425,560,this));
//         	敌人数量为0
         	enemyCount=0;
         	botList.clear();
         	Random random=new Random();
			int rnum=random.nextInt(800);
         	botList.add(new Bot("src/image/enemy1U.gif",rnum,110,this,"src/image/enemy1U.gif",
    	    		  "src/image/enemy1L.gif","src/image/enemy1R.gif",
  	    		  "src/image/enemy1D.gif"));
//         	基地
         	 this.base=new Base("src/image/star.gif",375,570,this);
//   	     玩家  
         	playerList.clear();
            this. playerOne=new PlayerOne("src/image/p1tankU.gif",125,510,this,"src/image/p1tankU.gif",
	                  "src/image/p1tankL.gif" ,"src/image/p1tankR.gif",
	                  "src/image/p1tankD.gif");
            this. playerTwo=new PlayerTwo("src/image/p1tankU.gif",125,510,this,"src/image/p1tankU.gif",
               "src/image/p1tankL.gif" ,"src/image/p1tankR.gif",
               "src/image/p1tankD.gif");
          }
          public static void main(String[] args) {
        	  GamePanel gp=new GamePanel();
        	  gp.launch();
          }
	}
