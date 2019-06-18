package com.zhbit.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class YZMUtil {
	
	private static String YZM;
	private static int len;
	private static final int WIDTH=80;
	private static final int HEIGHT=35;
	
	static {
		YZM="";
		for(int i=0;i<=9;i++){
			YZM+=i;
		}
		for(int i='A';i<='Z';i++){
			YZM+=(char)i;
		}
		for(int i='a';i<='z';i++){
			YZM+=(char)i;
		}
		len=YZM.length();
	}
	
	private static int ran(int maxn){
		return (int) (Math.random()*maxn);
	}
	
	private static void drawYZM(Graphics2D g,String yzm,int idx,int x){
		if(idx==4)
			return;
		x+=12+ran(3);
		int y=23+ran(5);
		g.setFont(new Font("宋体", ran(3), 20+ran(10)));
		g.rotate(Math.PI*(-5+ran(10))/180,x,y);
		g.drawString(""+yzm.charAt(idx), x, y);
		drawYZM(g,yzm,idx+1,x);
	}
	
	public static BufferedImage getYZMImg(String yzm){
		BufferedImage img=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g=img.createGraphics();
		g.setBackground(Color.pink);
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(new Color(0,0x9a,0x61));
		drawYZM(g,yzm,0,0);
		for(int i=-3;i<ran(3);i++){
			g.drawLine(ran(WIDTH), ran(HEIGHT), ran(WIDTH), ran(HEIGHT));
		}
		return img;
	}
	
	public static String ranYZM(){
		String yzm="";
		for(int i=0;i<4;i++){
			yzm+=YZM.charAt(ran(len));
		}
		return yzm;
	}
	
	public static boolean equals(String correctYZM,String submitYZM){
		return correctYZM.equals(submitYZM.toLowerCase());
	}
	
}
