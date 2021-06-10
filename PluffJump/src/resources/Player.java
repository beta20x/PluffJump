package resources;           
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.lang.System;
import java.lang.Math;

import backend.Controller;
import backend.Game;
import powerUps.DamageBooster;
import powerUps.PowerUp;
import powerUps.ScoreBooster;


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
	private int dealtDamage;
	private int puDelay;
	private long puTime;
	private PowerUp currentPower;
	private long timeWithPowerUp;
	private int eff;
	
	
	public Player(Game game, float x, float y, int sdim, int dd) {
		super(x, y);
		eff = -1;
		c = new Controller(this);
		this.game = game;
		dy = 20;
		shootTime = 0;
		shootDelayInMilliseconds = sdim;
		hazardSpeed = 8;
		iDelay = 3000;
		iTime = 0;
		puDelay = 13500;
		puTime = 0;
		gameOver = false;	
		dealtDamage = dd;
		currentPower = new PowerUp(-1, 0, 0, null);
		timeWithPowerUp = System.currentTimeMillis();
	}

	public int getShootDelayInMilliseconds() {
		return shootDelayInMilliseconds;
	}

	public void setShootDelayInMilliseconds(int shootDelayInMilliseconds) {
		this.shootDelayInMilliseconds = shootDelayInMilliseconds;
	}

	public int getiDelay() {
		return iDelay;
	}

	public void setiDelay(int iDelay) {
		this.iDelay = iDelay;
	}

	public double getHazardSpeed() {
		return hazardSpeed;
	}

	public void setHazardSpeed(double hazardSpeed) {
		this.hazardSpeed = hazardSpeed;
	}

	public int getDealtDamage() {
		return dealtDamage;
	}

	public void setDealtDamage(int dealtDamage) {
		this.dealtDamage = dealtDamage;
	}

	@Override
	public void tick(){
		System.out.println(eff);
		eff = currentPower.getEffect();
		if (eff == 1) {
			dealtDamage = 2;
		} else {
			dealtDamage = 1;
		}
		
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
		if (System.currentTimeMillis() - puTime > puDelay) {
			int rando = (int)(Math.random() * 2);
			if (rando == 0) {
				c.addPowerUp(new ScoreBooster(2));
			} else if (rando == 1) {
				c.addPowerUp(new DamageBooster(2));
			} 
			puTime = System.currentTimeMillis();
		}
		for (int i = 0; i < c.getPowerUps().size(); i++) {
			 if (c.getPowerUps().get(i).getBounds().intersects(this.getBounds())){
				 timeWithPowerUp = System.currentTimeMillis();
				 currentPower = c.getPowerUps().get(i);
				 c.removePowerUp(i);
			 }
		}
		if (eff != -1 && System.currentTimeMillis() - timeWithPowerUp > currentPower.getTimeActive()) {
			timeWithPowerUp = System.currentTimeMillis();
			eff = -1;
			currentPower = new PowerUp(-1, 0, 0, null);
		}
		c.tick();
	}

	@Override
	public void render(Graphics g) {
		c.render(g);	
		if (eff == -1) {
			g.drawImage(Assets.player, (int) x, (int) y, null);
		} else if (eff == 0) {
			g.drawImage(Assets.player, (int) x, (int) y, new Color(252, 255, 65), null);
		} else if (eff == 1) {
			g.drawImage(Assets.player, (int) x, (int) y, new Color(255, 100, 65), null);
		}
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
	
	public int getEff() {
		return eff;
	}
	
}
	
