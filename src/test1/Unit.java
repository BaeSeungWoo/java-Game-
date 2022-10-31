package test1;

import java.awt.*;

import javax.swing.ImageIcon;

public class Unit {
	// 유닛 초기 설정값.
	int hp;
	int posX;
	int posY;
	int width;
	int height;
	Image img;
	Image resizeImg;
	
}

class Player extends Unit {
        // 플레이어 설정.
	public Player() {
		this.hp = 15;
		this.posX = 400;
		this.posY = 800;	
		this.width = 70;
		this.height = 70;
		this.img = new ImageIcon(Player.class.getResource("flight.png")).getImage();
		this.resizeImg = img.getScaledInstance(width, height, height);
	}
}

class Enemy extends Unit {
	public void move() {
	}
}
// 폭풍 설정.
class Storm extends Enemy{
	public Storm(int posX, int posY) {
		this.hp = 15;
		this.posX = posX;
		this.posY = posY;
		this.width = 70;
		this.height = 70;
		this.img = new ImageIcon(Storm.class.getResource("storm.png")).getImage();
		this.resizeImg = img.getScaledInstance(width, height, height);
	}
	
	//폭풍
	@Override
	public void move() {
		int randomNum = (int)(Math.random()*2);
		this.posY += randomNum;
		if(this.posY == 840) {
			this.posY = 30;
		}
	}
}
// 번개 설정.
class Thunder extends Enemy{
	public Thunder(int posX, int posY) {
		this.hp = 15;
		this.posX = posX;
		this.posY = posY;
		this.width = 70;
		this.height = 70;
		this.img = new ImageIcon(Thunder.class.getResource("thunder.png")).getImage();
		this.resizeImg = img.getScaledInstance(width, height, height);
	}
	
	//번개
	@Override
	public void move() {
		int randomNum = (int)(Math.random()*2);
		this.posY += randomNum;
		if(this.posY == 840) {
			this.posY = 30;
		}
	}
}
// 제트기 설정.
class Jet extends Enemy{
	public Jet(int posX, int posY) {
		this.hp = 15;
		this.posX = posX;
		this.posY = posY;
		this.width = 70;
		this.height = 70;
		this.img = new ImageIcon(Jet.class.getResource("—Pngtree—vector fighter jet icon_3985636.png")).getImage();
		this.resizeImg = img.getScaledInstance(width, height, height);
	}
	
	// 제트기
	@Override
	public void move() {
		int randomNum = (int)Math.random()+1;
		this.posY += randomNum;
		if(this.posY == 840) {
			this.posY = 30;
		}
	}
}






