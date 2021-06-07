package powerUps;

import resources.Assets;

public class ScoreBooster extends PowerUp{
	
	public ScoreBooster(int multiplier) {
		super(0, multiplier, 8000, Assets.p_sb);
	}
	
}
