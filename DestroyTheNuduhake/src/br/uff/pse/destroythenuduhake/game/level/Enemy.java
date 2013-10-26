package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

public class Enemy extends ControlableEntity {

	private Player target;
	private float minXPlayerDistance = 300f;
	private IAManager manager;

	public Enemy(float x, float y, GraphicAsset asset) {
		this(x, y, asset, 300);
	}

	public Enemy(float x, float y, GraphicAsset asset, float minXPlayerDistance) {
		super(x, y, asset);
		this.minXPlayerDistance = minXPlayerDistance;
	}

	public float getMinXPlayerDistance() {
		return minXPlayerDistance;
	}

	public void awake(Player p) {
		this.target = p;
	}

	public void sleep() {
		this.target = null;
	}

	public void updateIA(float delta) {
	}

	public boolean isSleeping() {
		return target == null;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (!isSleeping()) {
			updateIA(delta);
		} else {
			patrol();
		}
	}

	public Player getTarget() {
		return target;
	}

	public void setManager(IAManager manager) {
		this.manager = manager;
	}

	public IAManager getManager() {
		return manager;
	}

	public void patrol() {}

	public boolean isLeft() {
		return this.getX() < getTarget().getX();
	}
}
