package resources;

import java.awt.Graphics;

public class Background {
	
	private int x;
	private int y;
	private int x2;
	private int speed;
	
	public Background(int speed) {
		this.x = 0;
		this.y = 0;
		this.x2 = 1100;
		this.speed = speed;
	}
	
	public void tick() {
		x -= speed;
		x2 -= speed;
	}
	
	public void render(Graphics g) {
		System.out.println(x);
		if (x <= -1100) {
			this.x = 1093;
		}
		if (x2 <= -1100)
			this.x2 = 1093;
		g.drawImage(Assets.bg, x, y, null);
		g.drawImage(Assets.bg, x2, y, null);
	}
}
