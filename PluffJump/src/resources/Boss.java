package resources;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.lang.Math;

public class Boss extends Character{
	
	private int hp;
	private double dy = 0;
	private ArrayList<Redicicle> ri;
	private Redicicle temp;
	private int icicleDelay = 550;
	private long shootTime = 0;
	
	public Boss(int hp, Player player) {
		super(900, -10);
		this.hp = hp;
		ri = new ArrayList<Redicicle>();
	}
	
	public void tick() {
		super.y += Math.sin(Math.toRadians(dy));
		dy += 0.4;
		
		
		if (System.currentTimeMillis() - shootTime > icicleDelay) {
			shootTime = System.currentTimeMillis();
			ri.add(new Redicicle(900, (int) super.y + 200, 6, 3));
		}
		
		for (int i = 0; i < ri.size(); i++) {
			temp = ri.get(i);
			
			if (temp.getX() <= 0 || temp.gethp() <= 0) {
				ri.remove(temp);
			}
			temp.tick();
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.boss, (int) super.x, (int) super.y, null);
		for (int i = 0; i < ri.size(); i++) {
			temp = ri.get(i);
					
			if (temp.getX() <= 0 || temp.gethp() <= 0) {
				ri.remove(temp);
			}
			temp.render(g);		
				
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)super.x, (int)super.y, Assets.boss.getWidth(), Assets.boss.getHeight());
	}
	
	public void losehp(int amt) {
		hp -= amt;
	}
	
	public ArrayList<Redicicle> getShots(){
		return ri;
	}
	
	public int getHp() {
		return hp;
	}
}
