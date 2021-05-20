package resources;           
 
import java.awt.Graphics;
import java.awt.Rectangle;
import java.lang.System;
import java.lang.Math;

import backend.Controller;
import backend.Game;

public class Player extends Character{
	
	private Game game;
	private boolean isJumping;
	private double dy;
	public boolean isShooting;
	private Controller c;
	private int shootDelayInMilliseconds;
	private long shootTime;
	int iDelay;
	private long iTime;
	private double hazardSpeed;
	private boolean gameOver;
	private Boss boss;
	
	
	public Player(Game game, float x, float y) {
		super(x, y);
		c = new Controller(this);
		this.game = game;
		dy = 20;
		shootTime = 0;
		shootDelayInMilliseconds = 60;
		hazardSpeed = 8;
		iDelay = 3000;
		iTime = 0;
		gameOver = false;	
	}

	@Override
	public void tick(){
		if (game.getKeyManager().jumping && y >= 75 && y <= 790) {
			dy = 20;
			isJumping = true;
		}
		if (isJumping) {
			y -=  dy / 2;
			dy -= 1;
		}
		if (game.getKeyManager().shoot && System.currentTimeMillis() - shootTime > shootDelayInMilliseconds) {
			shootTime = System.currentTimeMillis();
			c.addBullet(new Bullet(x, y, 20));
		}
		if (System.currentTimeMillis() - iTime > iDelay && !c.ibf) {
			iTime = System.currentTimeMillis();
			iDelay -= 1;
			c.addIcicle(new Icicle(1100, (int)(Math.random() * (701)), hazardSpeed + (c.getScore() / 5), 4));
			
		}
		for (int i = 0; i < c.getIcicleList().size(); i++) {
			if(this.getBounds().intersects(c.getIcicleList().get(i).getBounds())) {
				gameOver = true;
				y = Integer.MIN_VALUE;
			}
		}
		if (c.ibf) {
			for (int i = 0; i < c.getBoss().getShots().size(); i++) {
				if (this.getBounds().intersects(c.getBoss().getShots().get(i).getBounds())) {
					gameOver = true;
					y = Integer.MIN_VALUE;
				}
			}
		}
		c.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int) x, (int) y, null);
		c.render(g);
	}
	

	public float getX() {
		return super.x;
	}
	
	public float getY() {
		return super.y;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)super.x, (int)super.y, Assets.player.getWidth(), Assets.player.getHeight());
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public int getScore() {
		return c.getScore();
	}
	
}
	
