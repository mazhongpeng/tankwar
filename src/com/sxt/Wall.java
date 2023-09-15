package com.sxt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Wall extends GameObject{
//	     ³ß´ç
	int length=50;
    public Wall(String img,int x,int y,GamePanel gamePanel) {
           super(img,x,y,gamePanel);     
    }
    @Override
    public  void paintSelf(Graphics g) {
    	g.drawImage(img,x,y, gamePanel);
    }
    @Override
    public Rectangle getRec() {
    	return new Rectangle(x,y,length,length);
    }
}
