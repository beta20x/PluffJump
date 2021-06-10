package resources;

import java.awt.image.BufferedImage;

import backend.ImageLoader;

public class Assets {
	
	public static BufferedImage player, bullet, hazard, boss, bossHazard, p_sb, p_db, bg;
	
	public static void init() {
		player = ImageLoader.loadImage("/resources/pluff.png");
		bullet = ImageLoader.loadImage("/resources/seed.png");
		hazard = ImageLoader.loadImage("/resources/iceShard.png");
		boss = ImageLoader.loadImage("/resources/ar.png");
		bossHazard = ImageLoader.loadImage("/resources/bossShard.png");
		p_sb = ImageLoader.loadImage("/powerUps/tmY.png");
		p_db = ImageLoader.loadImage("/powerUps/tmO.png");
		bg = ImageLoader.loadImage("/resources/sky.png");
	}
	
}
