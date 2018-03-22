package newtank;
import  java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;



class Recoder {
	private static Vector<EnemyTask> ets = new Vector<EnemyTask>();

	public static Vector<EnemyTask> getEts() {
		return ets;
	}

	public static void setEtss(Vector<EnemyTask> ets) {
		Recoder.ets = ets;
	}

	private static int enSize;

	public static int getEnSize() {
		return enSize;
	}

	public static void setEnSize(int enSize) {
		Recoder.enSize = enSize;
	}

	private static Hero hero;

	public static Hero getHero() {
		return hero;
	}

	public static void setHero(Hero hero) {
		Recoder.hero = hero;
	}

	public static void KeepExit() {
		// System.out.println(hero.getX());
		int[] TanksX = new int[20];
		int[] TanksY = new int[20];
		int[] ShotsX = new int[100];
		int[] ShotsY = new int[100];
		int[] ETdirects = new int[20];
		int[] HeroShotsX = new int[10];
		int[] HeroShotsY = new int[10];
		int[] heros = new int[] { hero.getX(), hero.getY(), hero.direct };
		for(int i =0; i<ets.size();i++){
			EnemyTask et = ets.get(i);
			TanksX[i] = et.getX();
			TanksY[i] = et.getY();
			ETdirects[i] = et.direct;
		}
		for (int j = 1; j < hero.ss.size(); j++) {
			shot s = hero.ss.get(j);
			ShotsX[j] = s.x;
			ShotsY[j] = s.y;
		}

		for (int i = 0; i < hero.ss.size(); i++) {
			shot s = hero.ss.get(i);
			HeroShotsX[i] = s.x;
			HeroShotsY[i] = s.y;
		}
		BufferedWriter bw = null;
		BufferedWriter bw1 = null;
		try{
			bw = new BufferedWriter(new FileWriter("d:\\aa\\TanksX.txt"));
			for(int i = 0; i<enSize; i++){
				bw.write(TanksX[i] +"\r\n");
				bw.write(TanksY[i] + "\r\n");
			}
			bw1 = new BufferedWriter(new FileWriter("d:\\aa\\KeepRecoding.txt"));
			bw1.write((20- getEnNum()) + "\r\n");
		}catch (Exception e){
			//TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				bw1.close();
				bw.close();
			}catch (IOException e){
			//TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static int enNum = 5;
	public static int getEnNum(){
		return enNum;
	}
	
	public static int getMyLife(){
		return myLife;
	}
	public static void setMyLife(int myLife){
		Recoder.myLife = myLife;
	}
	private static int myLife = 3;
	private static BufferedReader br = null;
	public static void getRecoding(){
		try{
			br = new BufferedReader(new FileReader("d:\\aa\\KeepRecoding.txt"));
			String n;
			while((n = br.readLine()) != null){
				String a = n;
				enNum = 20 - Integer.parseInt(n);
			}
		}catch (Exception e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				br.close();
			}catch(IOException e){
			//TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static BufferedWriter bw = null;
	public static void KeepRecording(){
		try{
			bw = new BufferedWriter(new FileWriter("d:\\aa\\KeepRecoding.txt"));
			bw.write((20 - getEnNum()) + "\r\n" );
		}catch (IOException e){
			//TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				bw.close();		//先开后关
				//new FileWriter("d:\\aa\\KeepRecoding.txt").close();
			}catch(IOException e){
				//TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void setEnNum(int i) {
		// TODO Auto-generated method stub
		
	}

	public static void getRecording() {
		// TODO Auto-generated method stub
		
	}
}




