package powerUps;

import resources.Assets;

public class DamageBooster extends PowerUp{

	public DamageBooster(int multiplier) {
		super(1, multiplier, 10000, Assets.p_db);
	}
	
}
