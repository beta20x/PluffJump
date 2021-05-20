package resources;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet{
	
	private float x, y;
	private int speed;
	
	public Bullet(float x, float y, int speed) {
		this.x = x + 100;
		this.y = y + 117;
		this.speed = speed;
	}
	
	public void tick() {
		x += speed;
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.bullet, (int) x, (int) y, null);
	}	
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setSpeed(int s) {
		speed = s;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, Assets.bullet.getWidth(), Assets.bullet.getHeight());
	}
}
