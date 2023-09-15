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
//	        ����˫����ͼƬ
	        Image offScreemImage=null;
//	        ����ָ��ͼƬ
//	        ����
	        int s=0;
	        Image select=Toolkit.getDefaultToolkit().getImage("src/image/selecttank.gif");
//	        ָ��ͼƬ�ĳ�ʼλ��
	        int y=150;
//	        �����Ϸģʽ 0��Ϸδ��ʼ 1������Ϸ 2˫����Ϸ 5��Ϸʤ��
	      public int state=0;
//	      ��ʱ����
	      public  int a=1;
	      public  int i=1;
//	      �ػ����
	      int count=0;
//	      �����ɵ�������
	      int enemyCount=0;
//	      ��ϷԪ���б�
	      ArrayList<Bullet> bulletList=new ArrayList<Bullet>();
	      ArrayList<Bot> botList=new ArrayList<Bot>();
	      ArrayList<Bullet> removeList=new ArrayList<Bullet>();
	      ArrayList<Tank> playerList=new ArrayList<Tank>();
	      ArrayList<Wall> wallList=new ArrayList<Wall>();
	      ArrayList<Base> baseList=new ArrayList<Base>();
	      ArrayList<Blast> blastList=new ArrayList<Blast>();
//	      ��Ϸ�Ƿ�ʼ
	      private boolean start=false;
//	      ���1
	      PlayerOne playerOne=new PlayerOne("src/image/p1tankU.gif",125,510,this,"src/image/p1tankU.gif",
	    		                  "src/image/p1tankL.gif" ,"src/image/p1tankR.gif",
	    		                  "src/image/p1tankD.gif");
//	      ���2
	      PlayerTwo playerTwo=new PlayerTwo("src/image/p2tankU.gif",625,510,this,"src/image/p2tankU.gif",
                  "src/image/p2tankL.gif" ,"src/image/p2tankR.gif",
                  "src/image/p2tankD.gif");
//	      Base
	      Base base=new Base("src/image/star.gif",375,570,this);
//	      ���ڵ���������
          public void launch() {
//    ����
    	      setTitle ("̹�˴�ս");
//    	��С
    	      setSize(800,610);
//    	����
    	      setLocationRelativeTo(null);
//  �ر��¼�  	
    	      this.setDefaultCloseOperation(3);
//    	���ܵ�����С
    	        setResizable(false);
//    	ʹ���ڿɼ�
    	        setVisible(true);
//    	��Ӽ��̼�����
            	this.addKeyListener(new GamePanel.KeyMonitor());
//            	���Χǽ
            	for(int i=0;i<14;i++) {
            		wallList.add(new Wall("src/image/walls.gif",i*60,170,this));
            	}
            	wallList.add(new Wall("src/image/walls.gif",305,560,this));
            	wallList.add(new Wall("src/image/walls.gif",305,500,this));
            	wallList.add(new Wall("src/image/walls.gif",365,500,this));
            	wallList.add(new Wall("src/image/walls.gif",425,500,this));
            	wallList.add(new Wall("src/image/walls.gif",425,560,this));
//            	��ӻ���
            	baseList.add(base);
//    	�ػ�
            	while(true) {
//            		��Ϸʤ���ж�
            		if(botList.size()==0&&enemyCount==10) {
            		state=5;	
            		}
//     
//            		���ʧ���ж�
            		if((playerList.size()==0&&(state==1||state==2))||baseList.size()==0){
            			if(baseList.size()==0) {
            				this.base=new Base("src/image/star.gif",375,570,this);
            				baseList.add(base);
            			}
            			state=4; 
            			}
//            		�����Ϸ̹��
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
//    	����һ�����ڴ�Сһ����ͼƬ
        	  if(offScreemImage==null) {
        		  offScreemImage=this.createImage(800, 610);
        	  }
//    	��ȡ��ͼƬ�Ļ���
        	  Graphics gImage=offScreemImage.getGraphics();
//    	������ɫ
        	  gImage.setColor(Color.gray);
//    	�滭ʵ�ľ���
        	  gImage.fillRect(0,0,800,610);
        	  gImage.setColor(Color.blue);
//    	�ı����ִ�С
        	  gImage.setFont(new Font("����",Font.BOLD,50));
        	  if(state==0) {
//    	�������
        		  gImage.drawString("ѡ����Ϸģʽ",220,100);
        		  gImage.drawString("������Ϸ",220,200);
        		  gImage.drawString("˫����Ϸ",220,300);
        		  gImage.drawString("��r���¿�ʼ��Ϸ", 220,400);
        		  gImage.drawString("��1��2ѡ����Ϸģʽ", 220,500);
//    	����ָ��
        		  gImage.drawImage(select,160, y, null);
        	  }else if(state==1||state==2){
        	       gImage.setFont(new Font("����",Font.BOLD,30));
        	       gImage.setColor(Color.red);
        	       gImage.drawString("ʣ����ˣ�"+botList.size(),8, 55);
//    			������ϷԪ��
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
//    			�ػ�һ��
    			count++;
        	  }
        	  else if(state==5) {
        		  gImage.drawString("��Ϸʤ��", 220,200);
        		  gImage.drawString("��r���¿�ʼ��Ϸ", 220,300);
        	  }else if(state==4) {
        		  a=4;
        		  gImage.drawString("��Ϸʧ��", 220,200);
        		  gImage.drawString("��r���¿�ʼ��Ϸ", 220,300);
        	  } else if(state==3) {
        		  gImage.drawString("��Ϸ��ͣ", 220,200);
        		  gImage.drawString("��r���¿�ʼ��Ϸ", 220,300);
        	  }
//    	������������ƺõ�ͼ���������Ƶ������Ļ�����
        	  g.drawImage(offScreemImage,0,0,null);
          }
////   ���̼�����
//		���¿�ʼ
          class KeyMonitor extends KeyAdapter{
////    	���¼���
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
//        	  �ɿ�����
        	  @Override
        	  public void keyReleased(KeyEvent e) {
        		  playerOne.keyReleased(e);
        		  playerTwo.keyReleased(e);
        	  }
          }
         public void lv() {
//        	 ��ǽ����
        	 wallList.clear(); 
        	 for(int i=0;i<14;i++) {
         		wallList.add(new Wall("src/image/walls.gif",i*60,170,this));
         	}
         	wallList.add(new Wall("src/image/walls.gif",305,560,this));
         	wallList.add(new Wall("src/image/walls.gif",305,500,this));
         	wallList.add(new Wall("src/image/walls.gif",365,500,this));
         	wallList.add(new Wall("src/image/walls.gif",425,500,this));
         	wallList.add(new Wall("src/image/walls.gif",425,560,this));
//         	��������Ϊ0
         	enemyCount=0;
         	botList.clear();
         	Random random=new Random();
			int rnum=random.nextInt(800);
         	botList.add(new Bot("src/image/enemy1U.gif",rnum,110,this,"src/image/enemy1U.gif",
    	    		  "src/image/enemy1L.gif","src/image/enemy1R.gif",
  	    		  "src/image/enemy1D.gif"));
//         	����
         	 this.base=new Base("src/image/star.gif",375,570,this);
//   	     ���  
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
