package backend;

import java.awt.Graphics;

public abstract class State {
	
	private static State currentState = null;
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
}
