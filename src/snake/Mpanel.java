package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Mpanel extends JPanel implements KeyListener, ActionListener {
	public int len = 3;
	public int[] snakex = new int[750];
	public int[] snakey = new int[750];
	public String fx = "R"; // 方向:R,L,U,D.
	public boolean isstart = false;
	boolean isfild=true;
	boolean Over=true;
	
	//食物坐标。
	int foodx,foody;
	Random rand = new Random();
	//蛇的相关图片声明。
		ImageIcon title = new ImageIcon("title.jpg");
		ImageIcon body = new ImageIcon("body.png");
		ImageIcon down = new ImageIcon("down.png");
		ImageIcon food = new ImageIcon("food.png");
		ImageIcon up = new ImageIcon("up.png");
		ImageIcon right = new ImageIcon("right.png");
		ImageIcon left = new ImageIcon("left.png");
	
	//控制蛇的快慢。（计时器）
	Timer timer = new Timer(150, this);
	

	public Mpanel() {
		initsnake();
		this.setFocusable(true);
		this.addKeyListener(this);
		timer.start();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		title.paintIcon(this, g, 25, 11);

		g.fillRect(25, 75, 850, 600);

		if (fx == "R") {
			right.paintIcon(this, g, snakex[0], snakey[0]);
		} else if (fx == "L") {
			left.paintIcon(this, g, snakex[0], snakey[0]);
		} else if (fx == "U") {
			up.paintIcon(this, g, snakex[0], snakey[0]);
		} else if (fx == "D") {
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}
		for (int i = 1; i < len; i++) {
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		
		food.paintIcon(this, g, foodx, foody);
		if(!Over&&!isfild) {
			g.setColor(Color.RED);
			g.setFont(new Font("arial", Font.BOLD, 50));
			g.drawString("your length finally is : "+len, 150, 300);
			
		}else {
			if (!isstart) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Press Space To Star", 250, 400);
			}
			
			if (!isfild) {
				g.setColor(Color.RED);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Fild:Press Space To reStar", 150, 300);
				g.drawString("Or Press Entry to over", 150, 350);
			}
		}


	}

	public void initsnake() {
		snakex[0] = 100;
		snakey[0] = 100;
		snakex[1] = 75;
		snakey[1] = 100;
		snakex[2] = 50;
		snakey[2] = 100;
		foodx=25+rand.nextInt(34)*25;
		foody=75+rand.nextInt(24)*25;
		fx="R";
		len=3;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keycode = e.getKeyCode();
		if(Over) {
			if (keycode == KeyEvent.VK_SPACE) {
				if(!isfild) {
					isfild=!isfild;
					initsnake();
				} else
					isstart = !isstart;
				repaint();
			}else if(keycode == KeyEvent.VK_LEFT) {
				fx = "L";
			}else if(keycode == KeyEvent.VK_DOWN) {
				fx = "D";
			}else if(keycode == KeyEvent.VK_RIGHT) {
				fx = "R";
			}else if(keycode == KeyEvent.VK_UP) {
				fx = "U";
			}else if(!isfild&&keycode == KeyEvent.VK_ENTER) {
				Over=!Over;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (isstart && isfild) {
			for (int i = len - 1; i > 0; i--) {
				snakex[i] = snakex[i - 1];
				snakey[i] = snakey[i - 1];
			}
			if(fx=="R")
				snakex[0] += 25;
			else if(fx=="L")
				snakex[0]-=25;
			else if(fx=="D")
				snakey[0]+=25;
			else if(fx=="U")
				snakey[0]-=25;
			if(snakex[0]>850)
				snakex[0]=25;
			else if(snakex[0]<25)
				snakex[0]=850;
			else if(snakey[0]<75)
				snakey[0]=650;
			else if(snakey[0]>650)
				snakey[0]=75;
			
			if(snakex[0]==foodx&&snakey[0]==foody) {
				len++;
				foodx=25+rand.nextInt(34)*25;
				foody=75+rand.nextInt(24)*25;
			}
			
			for(int i=1;i<len;i++) {
				if(snakex[0]==snakex[i]&&snakey[0]==snakey[i])
					isfild=!isfild;
			}
			repaint();
		}else if(!Over)
			repaint();
		timer.start();

	}
}
