package backend;

import java.awt.Graphics;

import resources.Player;

public class GameState extends State{
	
	private Player player;
	private Scoreboard scoreboard;
	
	public GameState(Game game) {
		player = new Player(game, 20, 200);
		scoreboard = new Scoreboard(player);
	}
	
	@Override
	public void tick() {
		player.tick();
	}
	
	@Override
	public void render(Graphics g) {
		player.render(g);
		scoreboard.render(g);
	}
	
}
