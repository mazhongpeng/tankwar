package com.sxt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Base extends GameObject{
	int lenght=50;
    public Base(String img,int x,int y,GamePanel gamePanel) {
            super(img,x,y,gamePanel);
    }
    @Override
    public  void paintSelf(Graphics g) {
    	g.drawImage(img,x, y, null);
    }
    @Override
    public Rectangle getRec() {
    	return new Rectangle(x,y,lenght,lenght);
    }
}
