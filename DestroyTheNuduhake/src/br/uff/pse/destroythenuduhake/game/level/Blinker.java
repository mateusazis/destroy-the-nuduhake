package br.uff.pse.destroythenuduhake.game.level;

public class Blinker {

	private float elapsed, duration, delta;
	private boolean active = false;
	
	public Blinker(float duration, int times){
		this.elapsed = 0;
		this.duration = duration;
		this.delta = duration / (2f * times);
	}
	
	public void update(float delta){
		if(active){
			elapsed += delta;
			if(elapsed >= duration)
				active = false;
		}
	}
	
	public boolean isBlinking(){
		return active;
	}
	
	public boolean isVisible(){
		int stepCount = (int)(elapsed / delta);
		return stepCount % 2 == 1 || !active; //visível ser for ímpar
	}
	
	public void start(){
		elapsed = 0;
		active = true;
	}
	
}
