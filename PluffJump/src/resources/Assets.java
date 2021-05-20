package resources;

import java.awt.image.BufferedImage;

import backend.ImageLoader;

public class Assets {
	
	public static BufferedImage player, bullet, hazard, boss, bossHazard;
	
	public static void init() {
		player = ImageLoader.loadImage("/resources/pluff.png");
		bullet = ImageLoader.loadImage("/resources/seed.png");
		hazard = ImageLoader.loadImage("/resources/iceShard.png");
		boss = ImageLoader.loadImage("/resources/ar.png");
		bossHazard = ImageLoader.loadImage("/resources/bossShard.png");
	}
	
}
