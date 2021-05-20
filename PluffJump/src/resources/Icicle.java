package resources;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Icicle{
	
	protected float x;
	protected int y;
	double speed;
	private int hp;
	
	public Icicle(int x, int y, double speed, int hp) {
		this.hp = hp;
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public void tick() {
		x -= (int) speed;
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.hazard, (int) x, (int) y, null);
	}

	public float getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, y, Assets.hazard.getWidth(), Assets.hazard.getHeight());
	}
	
	public void setSpeed(double s) {
		speed = s;
	}
	
	public int gethp() {
		return hp;
	}
	
	public void losehp() {
		hp--;
	}
}

