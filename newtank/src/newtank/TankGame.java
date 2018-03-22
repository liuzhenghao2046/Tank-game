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

public class TankGame extends JFrame implements ActionListener {

	MyPanel mp = null;
	myStartPanel msp = null;
	EnemyTask em = null;
	JMenuBar jmb = null;
	JMenu jm = null;
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	JMenuItem jmi3 = null;

	public TankGame() {
		jmb = new JMenuBar();
		jm = new JMenu("游戏(G)");
		jm.setMnemonic('G');
		jmi1 = new JMenuItem("开始游戏(S)");
		jmi1.setMnemonic('S');
		jmi1.addActionListener(this);
		jmi1.setActionCommand("newGame");
		jmi2 = new JMenuItem("继续游戏(O)");
		jmi2.setMnemonic('O');

		jmi3 = new JMenuItem("退出游戏(X)");
		jmi3.setMnemonic('X');
		jmi3.addActionListener(this);
		jmi3.setActionCommand("exit");

		// 启动mp线程
		// em = new EnemyTask();
		// mp = new MyPanel();
		msp = new myStartPanel();

		this.setJMenuBar(jmb);
		this.add(msp);
		// this.add(mp);
		jmb.add(jm);
		jm.add(jmi1);
		jm.add(jmi2);
		jm.add(jmi3);
		this.setSize(600, 500);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		// this.addKeyListener(mp);
		Thread t1 = new Thread(msp);
		t1.start();
		//   Thread  t = new Thread(mp);
		//   t.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("newGame")) {
			mp = new MyPanel();
			Thread t = new Thread(mp);
			t.start();
			this.remove(msp);
			this.remove(mp);
			this.add(mp);
			this.setVisible(true);
			this.addKeyListener(mp);
			//Recoder.getRecording();

		} else if (arg0.getActionCommand() == "exit") {
			// Recoder.KeepRecording();
			Recoder.KeepExit();
			// KeepExit();
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		TankGame tankGame = new TankGame();
	}
}