package backend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import resources.Player;

public class Scoreboard {

	private Player player;
	
	public Scoreboard(Player p) {
		player = p;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.PLAIN, 40));
		g.drawString("Score: " + player.getScore(), 900, 750);
	}
	
}
