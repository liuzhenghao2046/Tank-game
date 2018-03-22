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


class myStartPanel extends JPanel implements Runnable {
	int times = 0;

	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		if (times % 2 == 0) {
			g.setColor(Color.BLUE);
			Font f = new Font("宋体", Font.BOLD, 30);
			g.setFont(f);
			g.drawString("stage:1", 150, 150);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(300);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
			times++;
		}
	}
}
