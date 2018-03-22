package newtank;
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

public class MyPanel extends JPanel implements KeyListener,Runnable{
	int[] TanksX = new int[20];
	int[] TanksY = new int[20];
	int[] ShotsX = new int[100];
	int[] ShotsY = new int[100];
	//  int [][]Tanks = new int[20][2];
	//  int [][]Shots = new int[20][2];
	int[] ETdirects = new int[20];
	int[] HeroShotsX = new int[10];
	int[] HeroShotsY = new int[10];
	
	static Hero hero = null;
	EnemyTask et = null;
	shot s = null;
	boolean isPaintShot = true;
	
	int a = 0, b = 0, c = 0;
	int[] directs = new int[10];
	
	static //敌人坦克
	Vector<EnemyTask> ets = new Vector<EnemyTask>();
	int enSize = 3;//敌人坦克初始数量
	int enSizes = 5;//敌人坦克总数量
	int hintEnemyTanks = 0;//击中敌人坦克数量
	public void showInfo(Graphics g){	//画提示信息
	//画出提示坦克
	this.drawTank(80,330,g,0,1);
	this.drawTank(160,330,g,0,0);
	g.setColor(Color.BLACK);
	g.drawString(Recoder.getEnNum()+"",105,350);
	g.drawString(Recoder.getMyLife()+"", 185, 350);
	
	//画出成绩
	g.setColor(Color.BLACK);
	Font f = new Font("宋体", Font.BOLD, 15);
	g.setFont(f);
	g.drawString("你的总成绩：", 400, 20);
	this.drawTank(410,30,g,0,1);
	g.drawString((20 - Recoder.getEnNum())+ "", 435, 50);
	
}
public void paint(Graphics g){
	super.paint(g);
	g.fillRect(0, 0, 400, 300);
	this.showInfo(g);//画出提示信息
	
	//自己坦克
	if(hero.isLive == true){
		this.drawTank(hero.getX(), hero.getY(), g, hero.direct, hero.type);
	}else{
		if(Recoder.getMyLife()>0){
			hero = new Hero(30,270,0,0,10, true);
			hero.isLive = true;
		}
	}
	//画出敌人坦克
	for(int i = 0; i<ets.size(); i++){
		EnemyTask et = ets.get(i);
		if(!et.isLive){
			ets.remove(i);
			if(Recoder.getEnNum()>0){
				this.drawTank(50, 0, g, 1, 1);
				ets.add(et);
				//et.isLive = true;
				Thread t = new Thread(et);
				t.start();
			}
			//if (Recoder.getEnNum()>0{
			//et.isLive = true;
			//}
		}
		if(et.isLive){
			this.drawTank(et.getX(), et.getY(), g, et.direct, et.type);
			System.out.println("et.ss.size()"+et.ss.size());
			for(int j = 0; j<et.ss.size();j++){//敌人坦克子弹
				s = et.ss.get(j);
				if(s.isLive){
					if(isPaintShot){
						g.fill3DRect(s.x, s.y, 5, 10, false);
					}
				}else{
					et.ss.remove(j);
				}
			}
		}
		//if(!et.isLive){
		//ets.remove(i);
		//}
	}
	//画出子弹
	for(int i =0; i<hero.ss.size();i++){
		if(hero.ss.get(i)!=null && hero.ss.get(i).isLive == true){
		g.fill3DRect(hero.ss.get(i).x, hero.ss.get(i).y, 5, 10, false);	
		}
		if(hero.ss.get(i).isLive == false){
			hero.ss.remove(hero.ss.get(i));
		}
	}
}

	//  public void hintTank2(shot s , Hero et){
	//   switch (et.direct) {
	//   case 0:
	//   case 1:
	//    if(s.x < et.x + 20 && s.x > et.x 
	//     && s.y < et.y + 30 && s.y > et.y){
	//     s.isLive = false;
	//     et.isLive = false;
	//    }
	//    //break;
	//   case 2:
	//   case 3:
	//    if(s.x < et.x + 30 && s.x > et.x 
	//     && s.y < et.y + 20 && s.y > et.y){
	//     s.isLive = false;
	//     et.isLive = false;
	//    }
	//    //break;
	//
	////   default:
	////    break;
	//   } 
	//   
	//  }
	
public boolean hintTank(shot s, Tank et){
	boolean b = false;
	switch(et.direct){
	case 0:
	case 1:
		if(s.x <et.x +20 && s.x > et.x && s.y <et.y +30 && s.y > et.y){
			s.isLive = false;
			et.isLive = false;
			b = true;
		}
		break;
	case 2:
	case 3:
		if(s.x < et.x + 30 && s.x > et.x && s.y < et.y + 20 && s.y > et.y){
			s.isLive = false;
			et.isLive = false;
			b = true;
		}
		break;
		//default:
		//break;
	}
	return b;
}
	private void drawTank(int x, int y, Graphics g, int direct, int type) {
		// TODO Auto-generated method stub
		switch(type){//坦克类型
		case 0:
			g.setColor(Color.RED);
			break;
		case 1:
			g.setColor(Color.BLUE);
			break;
		}
		switch(direct){
		case 0://向上
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x +15, y, 5, 30, false);
			g.fill3DRect(x+5, y+5, 10, 20, false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y, x+10, y+15);
			break;
		case 1://向下
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x +15, y, 5, 30, false);
			g.fill3DRect(x+5, y+5, 10, 20, false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y+30);
			break;
		case 2://向左
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x, y+10, x+15, y+10);
			break;
		case 3://向右
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x+30, y+10);
			break;
			
			default:
			break;
		}
	}
	public MyPanel(){
		hero = new Hero(30,270,0,0,10,true);
		//tanks = new tank(100,100,2,1);
		//初始化敌方坦克
		Recoder.setHero(hero);
		for(int i = 0; i < enSize; i++){
			//创建敌人坦克对象
			Recoder.getRecording();
			EnemyTask et = new EnemyTask((i +1)*50,0,1,1,5,true);
			et.setEts(ets);
			Recoder.setEtss(ets);//传ets到Recoder
			Thread t = new Thread(et);
			t.start();
			
			shot s = new shot(et.x +10, et.y +30, et.direct);
			et.ss.add(s);
			Thread t2 = new Thread(s);
			t2.start();
			
			ets.add(et);//加入到集合中
			//et.setColor(1);
		}
		Recoder.setEnSize(enSize);
	}

	@Override
	public void run() {
		// 每隔100ms
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated method stub
				e.printStackTrace();
			}
			this.hintEnemyTank();
			this.hintMyTank();
			//     for(int i = 0; i < et.ss.size(); i ++){
			//      shot myshot = et.ss.get(i);
			//      if(et.isLive == true){
			//       
			//        if(hero.isLive = true){
			//         this.hintTank2(myshot, hero);
			//        }
			//       
			//      }
			//     }
			this.repaint();
		}
	}

	public void hintMyTank() {
		for (int i = 0; i < ets.size(); i++) {
			EnemyTask et = ets.get(i);
			for (int j = 0; j < et.ss.size(); j++) {
				shot s = et.ss.get(j);
				if (s != null) {
					if (hero.isLive) {
						this.hintTank(s, hero);
						if (this.hintTank(s, hero)) {// 判断是否击中，返回true则hero数量减一
							Recoder.setMyLife(Recoder.getMyLife() - 1);
						}
					}
				}
			}
		}
	}
	private void hintEnemyTank(){
		for(int i = 0; i< hero.ss.size(); i++){
			shot myshot = hero.ss.get(i);
			if(myshot.isLive == true){
				for(int j = 0 ; j <ets.size(); j++){
					EnemyTask et = ets.get(j);
					if(et.isLive = true){
						this.hintTank(myshot, et);
						if(this.hintTank(myshot,et)){
							hintEnemyTanks ++;
							Recoder.setEnNum(Recoder.getEnNum()-1);
						}
					}
				}
			}
		}
	}

	private void KeepExit() {
		int[] heros = new int[] { hero.getX(), hero.getY(), hero.direct, };
		for (int i = 0; i < ets.size(); i++) {
			EnemyTask et = ets.get(i);
			TanksX[i] = et.getX();
			TanksY[i] = et.getY();
			ETdirects[i] = et.direct;

			for (int j = 1; j < et.ss.size(); j++) {
				shot s = et.ss.get(j);
				ShotsX[j] = s.x;
				ShotsY[j] = s.y;

			}
		}
		for (int i = 0; i < hero.ss.size(); i++) {
			shot s = hero.ss.get(i);
			HeroShotsX[i] = s.x;
			HeroShotsY[i] = s.y;
		}
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(""));
			for (int i = 0; i < enSize; i++) {
				bw.write(TanksX[i] + "\r\n");
				bw.write(TanksY[i] + "\r\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void GetKeeper() {// 继续游戏时还原数据
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(""));
			for (int i = 0; i < enSize * 2; i++) {
				if (i % 2 != 0) {
					TanksY[i] = Integer.parseInt(br.readLine());
					System.out.println("TanksY" + TanksY[i]);
				} else {
					TanksX[i] = Integer.parseInt(br.readLine());
					System.out.println("TanksX" + TanksX[i]);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// class tank extends Tank{
	//
	//  public tank(int x, int y, int direct,int type) {
	//   super(x, y, direct,type);
	//   // TODO Auto-generated constructor stub
	//  }
	//  
	// }
	

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_S) {
			// hero.setX(hero.getX() ++ 1);
			// int i= hero.getY();
			// i += hero.speed; 
			// hero.setY(i);
			this.hero.MoveDown();
			hero.setD(1);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_W) {
			// hero.setX(hero.getX() ++ 1);
			// int i= hero.getY();
			// i -= hero.speed; 
			// hero.setY(i);
			hero.MoveUp();
			hero.setD(0);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_D) {
			// hero.setX(hero.getX() ++ 1);
			//  int i= hero.getX();
			//  i += hero.speed; 
			//  hero.setX(i);
			hero.MoveRight();
			hero.setD(3);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_A) {
			// hero.setX(hero.getX() ++ 1);
			// int i= hero.getX();
			// i -= hero.speed; 
			// hero.setX(i);
			hero.MoveLeft();
			hero.setD(2);
		}
		// 判断是否按下J
		if (arg0.getKeyCode() == KeyEvent.VK_J) {
			if (hero.ss.size() <= 4) {
				this.hero.shotEnemy();
				// System.out.println("j");
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {// 空格暂停
			if (hero.speed != 0) {
				a = hero.speed;
				hero.speed = 0;
				for (int i = 0; i < ets.size(); i++) {
					// System.out.println("aaaaaaaaaaaaaa");
					EnemyTask et = ets.get(i);
					// System.out.println("speed" + et.speed);
					b = et.speed;
					et.speed = 0;
					directs[i] = et.direct;
					// System.out.println(et.direct);
					// System.out.println(directs[i]);
					// et.direct = 0;
					et.isdirects = false;
					for (int j = 0; j < et.ss.size(); j++) {
						shot s = et.ss.get(j);
						c = s.speed;
						s.speed = 0;
						System.out.println("aa");
					}
				}
			} // System.out.println("b1 = " +b);
			isPaintShot = false;
		} else {
			hero.speed = a;
			// System.out.println("a2 =" + a);
			for (int i = 0; i < ets.size(); i++) {
				// System.out.println("b");
				EnemyTask et = ets.get(i);
				et.isdirects = true;
				et.speed = b;
				et.direct = directs[i];
				for (int j = 0; j < et.ss.size(); j++) {
					shot s = et.ss.get(j);
					s.speed = c;
					isPaintShot = true;
				}
			}s.isShot = true;
		}
	if(arg0.getKeyCode() == KeyEvent.VK_K){
		this.KeepExit();
		System.exit(0);
	}
	if(arg0.getKeyCode() == KeyEvent.VK_L){
		this.GetKeeper();
	}
	this.repaint();
}
	@Override
	public void keyReleased(KeyEvent arg0){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0){
		// TODO Auto-generated method stub
		
	}
}

