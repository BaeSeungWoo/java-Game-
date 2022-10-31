package test1;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main extends JFrame implements Runnable {

	boolean Crash(int x1, int y1, int x2, int y2, int w1, int h1, int w2, int h2) {
		// x,y : ��ġ�� , w,h : �̹����� ���̿� ����.
		boolean result = false;
		if (Math.abs((x1 + w1 / 2) - (x2 + w2 / 2)) < (w2 / 2 + w1 / 2)
				&& Math.abs((y1 + h1 / 2) - (y2 + h2 / 2)) < (h2 / 2 + h1 / 2))
			result = true;
		else
			result = false;
		return result;
	}

	Image background = new ImageIcon(Main.class.getResource("background.png")).getImage();
	int frameWidth = 800;
	int frameHeight = 900;
	Player player;
	Unit unit;
	Thread th;
	Image buffImg;
	Graphics buffG;
	boolean checkExit;
	static String timerBuffer;
	static int gameTime;
	ArrayList<Enemy> Enemys = new ArrayList<Enemy>();

	public Main(Player player) {
		this.player = player;
		this.checkExit = false;
		Control key = new Control(player, this);
		th = new Thread(key);
		th.start();
		homeframe();

		Enemy storm1 = new Storm(100, 30);
		Enemy thunder1 = new Thunder(300, 30);
		Enemy storm2 = new Storm(500, 30);
		Enemy thunder2 = new Thunder(700, 30);
		Enemy Jet1 = new Jet(200, 30);
		Enemy Jet2 = new Jet(400, 30);
		Enemy Jet3 = new Jet(600, 30);

		Enemys.add(storm1);
		Enemys.add(storm2);
		Enemys.add(thunder1);
		Enemys.add(thunder2);
		Enemys.add(Jet1);
		Enemys.add(Jet2);
		Enemys.add(Jet3);
		

		addKeyListener(key);
		gameTimer(1);

	}

	/* �����ڿ� �ᵵ �Ǵ� �͵��Դϴ�. �׳� ���� �Լ� ���� �����ϴ�. */
	public void homeframe() {
		setTitle("����� ����");// Ÿ��Ʋ
		setSize(frameWidth, frameHeight);// �������� ũ��
		setResizable(false);// â�� ũ�⸦ �������� ���ϰ�
		setLocationRelativeTo(null);// â�� ��� ������
		setLayout(null);// ���̾ƿ��� ������� ���������ϰ� ����.
		setVisible(true);// â�� ���̰�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame�� ���������� ����ǰ�
	}

	public void paint(Graphics g) {
		repaint();
		buffImg = createImage(getWidth(), getHeight());
		buffG = buffImg.getGraphics();
		update(g);
		if (this.player.hp <= 0)
			return;
	}

	public void update(Graphics g) {
		drawBackGround(g);
		drawPlayer(g);
		drawEnemy(g);
		drawStatus(g);
		g.drawImage(buffImg, 0, 0, this);
	}

	int count = 0;

	// ��� �׸���.
	public void drawBackGround(Graphics g) {
		buffG.clearRect(0, 0, frameWidth, frameHeight);
		buffG.drawImage(background, count, 0, this);
	}

	public void drawPlayer(Graphics g) {
		buffG.drawImage(this.player.resizeImg, this.player.posX, this.player.posY, this);
		// �÷��̾��� �ǰ�0�� �Ǹ� ��� ����.
		if (this.player.hp <= 0) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// �� ����Ʈ �ʱ�ȭ.
			Enemys = new ArrayList<Enemy>();
			gameTimer(0);
			System.out.println("������ ����Ǿ����ϴ�.");
			System.out.format("��ƾ �ð� : [%s]%n", Main.timerBuffer);

			// Frame ���ֱ�.
			this.checkExit = true;
			this.dispose();
		}

	}

	public void drawStatus(Graphics g) {
		buffG.drawString("Player HP : " + this.player.hp, 700, 50);
	}

	public void drawEnemy(Graphics g) {
		for (int i = 0; i < Enemys.size(); i++) {
			buffG.drawImage(Enemys.get(i).resizeImg, Enemys.get(i).posX, Enemys.get(i).posY, this);
			Enemys.get(i).move();
		}
	}

	public static void gameTimer(int onOff) {
		if (onOff == 1) {
			gameTime = (int) System.currentTimeMillis() / 1000;
		}
		if (onOff == 0)
			secToHHMMSS((int) System.currentTimeMillis() / 1000 - gameTime);
	}

	public static void secToHHMMSS(int secs) {
		int hour, min, sec;

		sec = secs % 60;
		min = secs / 60 % 60;
		hour = secs / 3600;

		timerBuffer = String.format("%2d:%2d:%2d", hour, min, sec);
	}

	@Override
	public void run() {
		while (true) {
			if (this.checkExit == true)
				break;
			else {
				System.out.print("");
				continue;
			}
		}
	}
}