package csaFinalProject;

import backend.Game;

public class Launcher {

	public static void main(String[] args) throws InterruptedException {
		Game game = new Game("APCSA Final Project", 1100, 800);
		game.start();
	}
	
}
