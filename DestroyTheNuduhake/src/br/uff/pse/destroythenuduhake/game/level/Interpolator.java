package br.uff.pse.destroythenuduhake.game.level;

public class Interpolator {
	private float elapsed = 0, duration;
	
	public Interpolator(float duration){
		reset(duration);
	}
	
	public void reset(float duration){
		this.duration = duration;
		this.elapsed = 0;
	}
	
	public void update(float delta){
		elapsed = Math.min(elapsed + delta, duration);
	}
	
	public float getValue(){
		return elapsed / duration;
	}
}
