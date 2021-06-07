package powerUps;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PowerUp {
	
	private int effect;
	private int multiplier;
	private int timeActive;
	private BufferedImage image;
	private int x, y;
	
	public PowerUp(int effect, int multiplier, int timeActive, BufferedImage image) {
		this.effect = effect;
		this.multiplier = multiplier;
		this.timeActive = timeActive;
		this.image = image;
		this.x = 1100;
		this.y = (int)(Math.random() * 701);
	}
	
	public int getEffect() {
		return effect;
	}
	
	public int getMultiplier() {
		return multiplier;
	}
	
	public int getTimeActive() {
		return timeActive;
	}
	
	public void tick() {
		x -= 9;
	}
	
	public void render(Graphics g) {
		g.drawImage(image, x, y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, image.getWidth(), image.getHeight());
	}
	
	public int getX() {
		return x;
	}
}
