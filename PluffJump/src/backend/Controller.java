package backend;

import java.util.ArrayList;

import powerUps.PowerUp;
import resources.Background;
import resources.Boss;
import resources.Bullet;
import resources.Icicle;
import resources.Player;

import java.awt.Graphics;

public class Controller {
	
	private ArrayList<Icicle> ic;
	private ArrayList<Bullet> b;
	private Bullet tempBullet;
	private Icicle tempIcicle;
	private int score;
	private boolean scoreUp;
	private Boss boss;
	public boolean ibf;
	private Player player;
	private int prevBossScore = 0;
	private int scoreBooster;
	private ArrayList<PowerUp> powerups;
	private PowerUp tempPU;
	private Background background;
	
	public Controller (Player player){
		this.player = player;
		background = new Background(7);
		b = new ArrayList<Bullet>();
		ic = new ArrayList<Icicle>();
		powerups = new ArrayList<PowerUp>();
		score = 0;
		scoreUp = false;
		ibf = false;
		scoreBooster = 0;
	}

	public void tick() {
		background.tick();
		if (player.getEff() == 0) {
			scoreBooster = 1;
		} else {
			scoreBooster = 0;
		}
		for (int i = 0; i < b.size(); i++) {
			tempBullet = b.get(i);
			
			if (tempBullet.getX() > 1100) {
				removeBullet(tempBullet);
			}
			
			tempBullet.tick();
		}
		for (int i = 0; i < ic.size(); i++) {
			tempIcicle = ic.get(i);
			
			if (tempIcicle.getX() <= 0) {
				removeIcicle(tempIcicle);
			}
			
			for (int j = 0; j < b.size(); j++) {
				tempBullet = b.get(j);
				
				if (tempIcicle.getBounds().intersects(tempBullet.getBounds())) {
					b.remove(tempBullet);
					tempIcicle.losehp(player.getDealtDamage());
				}
				if (tempIcicle.gethp() <= 0) {
					removeIcicle(tempIcicle);
					scoreUp = true;
					
				}
				
			}
			
			tempIcicle.tick();
		}
		
		for (int i = 0; i < powerups.size(); i++) {
			tempPU = powerups.get(i);
			
			if (tempPU.getBounds().intersects(player.getBounds()) || tempPU.getX() < -10) {
				powerups.remove(tempPU);
			}
			
			tempPU.tick();
		}
		
		if (scoreUp) {                      
			score += 1 + scoreBooster; 
			scoreUp = false;  
		}
		if (!ibf && (score % 8 == 0 || score % 9 == 0) && score > 0 && score - prevBossScore > 5) {
			summonBoss();
			ibf = true;
		}
		if (ibf && boss.getHp() > 0) {
			boss.tick();
			for (int i = 0; i < b.size(); i++) {
				if (boss.getBounds().intersects(b.get(i).getBounds())) {
					b.remove(i);
					boss.losehp(player.getDealtDamage());
				}
			}
		}
		if (ibf && boss.getHp() <= 0) {
			ibf = false;
			score += 4 + scoreBooster;
			prevBossScore = score;
		}
		if (ibf) {
			for (int i = 0; i < boss.getShots().size(); i++) {
				for (int j = 0; j < b.size(); j++) {
					if (boss.getShots().get(i).getBounds().intersects(b.get(j).getBounds())){
						boss.getShots().get(i).losehp();
						b.remove(j);
					}
				}			
			}
		}
	}
	
	public void render(Graphics g) {
		background.render(g);
		for (int i = 0; i < b.size(); i++) {
			tempBullet = b.get(i);
			
			tempBullet.render(g);
		}
		for (int i = 0; i < ic.size(); i++) {
			tempIcicle = ic.get(i);
			
			
			tempIcicle.render(g);
		}
		if (ibf && boss.getHp() > 0) {
			boss.render(g);
		}
		for (int i = 0; i < powerups.size(); i++) {
			tempPU = powerups.get(i);
			
			tempPU.render(g);
		}
	}
	
	public void addBullet(Bullet bullet) {
		b.add(bullet);
	}
	
	public boolean removeBullet(Bullet bullet) {
		return b.remove(bullet);
	}
	
	public void addIcicle(Icicle icicle) {
		ic.add(icicle);
	}
	
	public boolean removeIcicle(Icicle icicle) {
		return ic.remove(icicle);
	}
	
	public ArrayList<Icicle> getIcicleList(){
		return ic;
	}
	
	public ArrayList<PowerUp> getPowerUps(){
		return powerups;
	}
	
	public void addPowerUp(PowerUp p) {
		powerups.add(p);
	}
	
	public void removePowerUp(int i) {
		powerups.remove(i);
	}
	
	public int getScore() {
		return score;
	}
	private void summonBoss() {
		boss = new Boss(score * 10, player);
	}
	
	public Boss getBoss() {
		return boss;
	}
}
