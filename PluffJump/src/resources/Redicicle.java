package resources;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Redicicle {
	
	private int x;
	private int y;
	private double speed;
	private int hp;
	
	public Redicicle(int x, int y, double speed, int hp) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.hp = hp;
	}

	public void tick() {
		x -= speed;
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.bossHazard, (int) x, (int) y, null);
	}

	public void losehp() {
		hp--;
	}
	
	public int gethp() {
		return hp;
	}
	
	public int getX() {
		return x;
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x, y, Assets.bossHazard.getWidth(), Assets.hazard.getHeight());
	}
	
}
