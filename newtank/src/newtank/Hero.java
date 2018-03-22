package newtank;
import  java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Hero extends Tank{

	public Hero(int x, int y, int direct, int type, int speed, boolean isLive) {
		super(x, y, direct, type, speed, isLive);
		// TODO Auto-generated constructor stub
	}
	Vector<shot> ss = new Vector<shot>();
	shot s = null;
	//fire
	public void shotEnemy(){
		//System.out.println("shotEnemy");
		if(Recoder.getMyLife() > 0){//生命没有了，无法发射子弹
			switch(this.direct){
			case 0://向上
				s = new shot(x +10, y, 0);
				ss.add(s);
				break;
			case 3://右
				s = new shot(x +30, y +10, 3);
				ss.add(s);
				break;
			case 1://向下
				s =new shot(x+10,y+30,1);
				ss.add(s);
				break;
			case 2://
				s = new shot(x, y+10,2);
				ss.add(s);
				break;
				
				default:
				break;
				
			}
			Thread t = new Thread(s);
			t.start();
		}
	}
	//向上，下，左，右
	public void MoveUp(){
		if( y - this.speed > 0){
			y -= speed;
		}
	}
	public void MoveDown(){
		if( y+ this.speed <300 -30){
			y += speed;
		}
	}
	public void MoveLeft(){
		if(x - this.speed > 0){
			x -= speed;
		}
	}
	public void MoveRight(){
		if(x + this.speed <400 -30){
			x += speed;
		}
	}
}
class shot implements Runnable{
	int x, y;
	int direct;
	int speed = 20;
	boolean isShot = true;
	boolean isLive = true;
	public shot(int x, int y, int direct){
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
	
	@Override
	public void run(){
		while (true){
			try{
				Thread.sleep(50);
			}catch (InterruptedException e){
				//TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(isShot){
				switch(direct){
				case 0:
					y -= speed;
					break;
				case 1:
					y += speed;
					break;
				case 2:
					x -= speed;
					break;
				case 3:
					x += speed;
					break;
					
					default:
					break;
				}
			}
			//System.out.println(x+""+y);
			if(x <0 || x > 400 || y<0 || y>300){
			//System.out.println("this.islive = false");
				this.isLive = false;
				break;
			}
		}
	}
	
}
