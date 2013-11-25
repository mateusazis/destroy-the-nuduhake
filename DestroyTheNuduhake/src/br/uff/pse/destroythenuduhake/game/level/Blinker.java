package br.uff.pse.destroythenuduhake.game.level;

public class Blinker {

	private float delta;
	private boolean active = false;
	private Interpolator interpolator;
	
	public Blinker(float duration, int times){
		interpolator = new Interpolator(duration);
		this.delta = 1 / (2f * times);
	}
	
	public void update(float delta){
		if(active){
			interpolator.update(delta);
			if(interpolator.getValue() >= 1.0f)
				active = false;
		}
	}
	
	public boolean isBlinking(){
		return active;
	}
	
	public boolean isVisible(){
		int stepCount = (int)(interpolator.getValue() / delta);
		return stepCount % 2 == 1 || !active; //vis�vel ser for �mpar
	}
	
	public void start(){
		interpolator.reset();
		active = true;
	}
}
