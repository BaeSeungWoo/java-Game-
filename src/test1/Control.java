package test1;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Control extends KeyAdapter implements Runnable {

	boolean KeyUp = false; // ���� �̵�.
	boolean KeyDown = false; // ������ �̵�.
	boolean KeyLeft = false; // ���� �̵�.
	boolean KeyRight = false; // ������ �̵�.
	Player player;
	Main unit;

	public Control(Player player, Main unit) {
		this.player = player;
		this.unit = unit;
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			KeyUp = true;
			break;
		case KeyEvent.VK_DOWN:
			KeyDown = true;
			break;
		case KeyEvent.VK_LEFT:
			KeyLeft = true;
			break;
		case KeyEvent.VK_RIGHT:
			KeyRight = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			KeyUp = false;
			break;
		case KeyEvent.VK_DOWN:
			KeyDown = false;
			break;
		case KeyEvent.VK_LEFT:
			KeyLeft = false;
			break;
		case KeyEvent.VK_RIGHT:
			KeyRight = false;
			break;
		}
	}

	public void keyProcess() {

		if (KeyUp == true) {
			if (this.player.posY >= 26)
				this.player.posY -= 10;
		}
		if (KeyDown == true) {
			if (this.player.posY < 584)
				this.player.posY += 10;
		}
		if (KeyLeft == true) {
			if (this.player.posX > 0)
				this.player.posX -= 10;
		}
		if (KeyRight == true) {
			if (this.player.posX < 740)
				this.player.posX += 10;
		}
	}

	// �÷��̾� �̹����� ���� ó��.
	public void PlayerProcess() {
		// �÷��̾�� �� ����Ⳣ���� �浹�� ó��.
		for (int j = 0; j < this.unit.Enemys.size(); j++) {
			if (unit.Crash(this.player.posX, this.player.posY, this.unit.Enemys.get(j).posX,
					this.unit.Enemys.get(j).posY, this.player.width, this.player.height, this.unit.Enemys.get(j).width,
					this.unit.Enemys.get(j).height)) {
				this.player.hp -= 1;
				this.unit.Enemys.get(j).hp -= 1;
				if (this.unit.Enemys.get(j).hp <= 0)
					this.unit.Enemys.remove(j);
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				keyProcess();
				PlayerProcess();
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}