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

public class Tank {
	int x = 0;
	int y = 0;
	int direct = 0;// 方向
	int type = 0;// 类型
	int speed = 30;// 速度
	int color;
	boolean isLive;

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getS() {
		return speed;
	}

	public void setS(int speed) {
		this.speed = speed;
	}

	public Tank(int x, int y, int direct, int type, int speed, boolean isLive) {
		this.x = x;
		this.y = y;
		this.direct = direct;
		this.type = type;
		this.speed = speed;
		this.isLive = isLive;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getD() {
		return direct;
	}

	public void setD(int direct) {
		this.direct = direct;
	}

	public int getT() {
		return type;
	}

	public void setT(int type) {
		this.type = type;
	}
}

class EnemyTask extends Tank implements Runnable {
	Vector<shot> ss = new Vector<shot>();
	Vector<EnemyTask> ets = new Vector<EnemyTask>();
	boolean isdirects = true;

	public EnemyTask(int x, int y, int direct, int type, int speed, boolean isLive) {
		super(x, y, direct, type, speed, isLive);
		// TODO Auto-generated constructor stub
	}

	public boolean isTouchOtherTank(){
		boolean b = false;
		switch(this.direct){
		case 0://此坦克向上
			for(int i = 0; i <ets.size(); i++){
				EnemyTask et = ets.get(i);
				if(et != this){
					if(et.direct == 0 || et.direct == 1){//上或下
						if((this.x >= et.x && this.x <= et.x +20 && this.y>=et.y && this.y <= et.y +30)||(this.x + 20>=et.x && this.x+ 20 <= et.x + 20//第二个点x在内
							&& this.y >=et.y && this.y <= et.y + 30)){//第二个点y在内
							b = true;
					}
				}
					if(et.direct == 2 || et.direct == 3){//左或右
						if((this.x>=et.x && this.x <= et.x + 30  && this.y>=et.y && this.y <= et.y + 20)||(this.x + 20>=et.x && this.x+ 20 <= et.x + 30 
						 && this.y >=et.y && this.y <= et.y + 20)){
							b = true;
						}
					}
			}
		}
			break;
		case 1://向下
			for(int i = 0; i<ets.size(); i++){
				EnemyTask et = ets.get(i);
				if(et != this){
				if(et.direct == 0 || et.direct == 1){//上或下
				if((this.x>=et.x && this.x <= et.x + 20  && this.y + 30>=et.y && this.y + 30 <= et.y + 30)||(this.x + 20>=et.x && this.x+ 20 <= et.x + 20 && this.y + 30>=et.y && this.y + 30 <= et.y + 30)){
				b = true;	
				}
				}
				if(et.direct == 2 || et.direct == 3){
					if((this.x>=et.x && this.x <= et.x + 30 && this.y + 30 >=et.y && this.y + 30 <= et.y + 20)||(this.x + 20 >=et.x && this.x+ 20 <= et.x + 30 && this.y + 30 >=et.y && this.y + 30 <= et.y + 20)){
						b = true;
					}
				}
			}
	}
			break;
		case 2:
			for(int i = 0; i < ets.size(); i++){
				 EnemyTask et = ets.get(i);
				 if(et != this){
					 if(et.direct == 0 || et.direct == 1){
						 if((this.x>=et.x && this.x <= et.x + 20 && this.y>=et.y && this.y <= et.y + 30)||(this.x >=et.x && this.x<= et.x + 20 && this.y + 20 >=et.y && this.y + 20 <= et.y + 30)){
							 b = true;
						 }
					 }
					 if(et.direct == 2 || et.direct == 3){//左或右
						 if((this.x>=et.x && this.x <= et.x + 30 && this.y>=et.y && this.y <= et.y + 20)||(this.x >=et.x && this.x<= et.x + 30  && this.y + 20>=et.y && this.y + 20 <= et.y + 20)){
							 b = true;
						 }
					 }
				 }
			}
			break;
		case 3:
		for(int i = 0; i < ets.size(); i ++){
			EnemyTask et = ets.get(i);
			if(et != this){
				if(et.direct == 0 || et.direct == 1){//上或下
					if((this.x + 30>=et.x && this.x + 30 <= et.x + 20  && this.y>=et.y && this.y <= et.y + 30) ||(this.x + 30 >=et.x && this.x+ 30 <= et.x + 20 && this.y + 20 >=et.y && this.y + 20 <= et.y + 30)){
						b =true;
					}
				}
				if(et.direct == 2 || et.direct == 3){//左或右
					if((this.x + 30>=et.x && this.x + 30 <= et.x + 30 && this.y>=et.y && this.y <= et.y + 20) || (this.x + 30>=et.x && this.x+ 30 <= et.x + 30  && this.y + 20>=et.y && this.y + 20 <= et.y + 20)){
						b = true;
					}
				}
			}
		}
		break;
		
		default:
		break;
		}
		return b;
	}

	@Override
	public void run() {
		int a = (int) (Math.random()* 10 +2);//连动步数
		int times = 0;
		while(true){
			try{
				Thread.sleep(300);
			}catch(InterruptedException e){
		// TODO Auto-generated method stub
				e.printStackTrace();
			}
			//if(x > 0 && x < 400 - 30 & y > 0 && y < 300 - 30){
			//if(!this.isTouchOhterTank()){
			switch(this.direct){
			case 0:
				//System.out.println(isTouchOtherTank());
				for(int i = 0; i<a; i++){
					if(y - this.speed > 0 && !this.isTouchOtherTank()){
						y -= this.speed;
					}
					try{
						Thread.sleep(50);
					}catch (InterruptedException e){
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 1:
				for(int i = 0; i<a; i++){
					if( y + speed < 300 - 30 && !this.isTouchOtherTank()){
						y += this.speed;
					}
					try{
						Thread.sleep(50);
					}catch (InterruptedException e){
					//TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 2:
				for(int i = 0; i<a;i++){
					if(x - speed >0 && ! this.isTouchOtherTank()){
						x -= this.speed;
					}
					try{
						Thread.sleep(50);
					}catch (InterruptedException e){
					//TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 3:
				for(int i = 0; i<a; i++){
					if(x + speed <400 -30 && !this.isTouchOtherTank()){
						x += this.speed;
					}
					try{
						Thread.sleep(50);
					}catch (InterruptedException e){
					//TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			}
			times ++;
			if(times % 2 == 0){
				if(isLive){
					if(ss.size() < 3){
						shot s = null;
						switch(direct){
						case 0://向上
							s = new shot(x +10, y, 0);
							ss.add(s);
							break;
						case 1://向下
							s = new shot(x +10, y +30, 1);
							ss.add(s);
							break;
						case 2://向左
							s = new shot(x, y + 10, 2);
							ss.add(s);
							break;
						case 3://向右
							s = new shot(x +30, y +10, 3);
							ss.add(s);
							break;
							
							default:
							break;
						}
						Thread t = new Thread(s);
						t.start();
					}
				}
			}
			//}
			//
			// public void changeDirect(){
			//
			//}
			if(isdirects){
				this.direct = (int)(Math.random()*4);
			}
			if(this.isLive == false){
				break;
			}
			if(ss.size() <3){
				
			}
		}
		
	}
	public void setEts(Vector<EnemyTask> ets){
		this.ets = ets;
	}
	//@Override
	// public void run() {
	//  while(true){
	//   try {
	//    Thread.sleep(50);
	//   } catch (InterruptedException e) {
	//    // TODO Auto-generated catch block
	//    e.printStackTrace();
	//   }
	//   
	//    y ++ ;
	//    System.out.println(y);
	//   }
	//  
	// }
	
}
