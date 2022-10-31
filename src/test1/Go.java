package test1;

import javax.swing.JFrame;
// 실행 파일
public class Go extends JFrame {
	public static void main(String[] args) {
		Player player = new Player();
		Main th = new Main(player);
		Thread gameth = new Thread(th);
		gameth.start();
	}
}
