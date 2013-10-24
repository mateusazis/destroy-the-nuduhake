package br.uff.pse.destroythenuduhake.game.level.enemies;

import android.drm.DrmStore.Action;
import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.level.Enemy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class ShooterEnemy extends Enemy {

	private GraphicAsset shooterAsset, bulletAsset, smokeAsset;

	boolean podeAtirar;
	private float elapsed = 0;
	// private short numAtaques = 0;

	private static float SHOOT_INTERVAL = 3f;

	// private static float RECHARGE_INTERVAL = 3f;

	public ShooterEnemy(float x, float y, AssetBundle bundle) {
		super(x, y, bundle.<GraphicAsset> getAsset(AssetDatabase.SPRITE_SHELL),
				300);
		this.bulletAsset = bundle
				.<GraphicAsset> getAsset(AssetDatabase.SPRITE_SHELL);
		this.smokeAsset = bundle
				.<GraphicAsset> getAsset(AssetDatabase.SPRITE_SMOKE);
		this.podeAtirar = true;
	}

	@Override
	public void atack() {
		Vector2 shootPos = new Vector2(-bulletAsset.getWidth() - 30,
				getHeight() - bulletAsset.getHeight());
		shootPos = localToStageCoordinates(shootPos);

		Bullet bullet = new Bullet(shootPos.x, shootPos.y, bulletAsset,
				smokeAsset);
		bullet.setupPhysics(this.getBody().getWorld());
		getParent().addActor(bullet);

		getManager().addEnemies(bullet);
	}

	@Override
	public void updateIA(float delta) {
		if (isSleeping()) {
			System.out.println("sss");
			this.addAction(Actions.moveTo(100, 200, 0.7f));
		} else {
			elapsed += delta;
			if (elapsed >= SHOOT_INTERVAL) {
				atack();
				elapsed -= SHOOT_INTERVAL;
				// numAtaques++;
			}
		}
		super.updateIA(delta);

		// if(numAtaques == 3){
		// numAtaques = 0;
		// elapsed -= RECHARGE_INTERVAL;
		// }
	}
}