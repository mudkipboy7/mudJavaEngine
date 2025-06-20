package mudkipboy7.game.render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.lwjgl.opengl.GL46;

import mudkipboy7.game.GameMain;
import mudkipboy7.game.level.Level;
import mudkipboy7.game.level.LevelPos;
import mudkipboy7.game.level.entity.EntityManager;
import mudkipboy7.game.level.tile.TilePos;
import mudkipboy7.game.render.AbstractRendererInstance.EntityRendererInstance;
import mudkipboy7.game.render.AbstractRendererInstance.TileRendererInstance;

public class RendererManager {
	public GameMain gameMain;
	// The solid color background

	public ArrayList<AbstractRendererInstance<?>> stuffBeingRendered = new ArrayList<AbstractRendererInstance<?>>();

	public RendererManager() {
	}

	public RendererManager(GameMain gameMain) {
		this.gameMain = gameMain;

	}

	@SuppressWarnings("rawtypes")
	public void renderStuff() {
		GL46.glClear(GL46.GL_COLOR_BUFFER_BIT | GL46.GL_DEPTH_BUFFER_BIT);
		GL46.glDepthFunc(GL46.GL_LEQUAL);
		GL46.glEnable(GL46.GL_DEPTH_TEST);
		GL46.glBlendFunc(GL46.GL_SRC_ALPHA, GL46.GL_ONE_MINUS_SRC_ALPHA);
		GL46.glEnable(GL46.GL_BLEND);
		Renderers.solidColorBackgroundRenderer.render(0, 0, 1);
		if (gameMain.getLevel() != null) {
			Renderers.backgroundRenderer.render(0, 0, 1.0F, 0);
		}

		/*
		 * Everything in here is part of the game world, UI and stuff should be rendered
		 * before or after.
		 */

		GL46.glMatrixMode(GL46.GL_MODELVIEW);
		GL46.glLoadIdentity();
		GL46.glPushMatrix();

		if (gameMain.getLevel() != null) {
			GL46.glScalef(-getCamera().getZ(), -getCamera().getZ(), 0);
			GL46.glTranslatef(-getCamera().getX() * 2.0F, -getCamera().getY() * 2.0F, 0);
			this.gameMain.getLevel().getLoadedTiles().forEach((key, id) -> {
				// System.out.println(key);
				this.gameMain.getLevel();
				TilePos tilePos = Level.getTilePosFromTileKey(key);
				LevelPos screenPos = tilePos.getAsLevelPos();
				stuffBeingRendered.add(new TileRendererInstance(id, screenPos, getCamera()));
			});

			getEntityManager().getLoadedEntities().forEach(entity -> {
				stuffBeingRendered.add(new EntityRendererInstance(entity.getRenderer(), entity.levelPos, getCamera(),
						entity.getAnimationFrame(), entity.facingLeft));
			});
		}
		Collections.sort(this.stuffBeingRendered, new Comparator<AbstractRendererInstance>() {
			public int compare(AbstractRendererInstance i1, AbstractRendererInstance i2) {
				return Float.compare(-i1.levelPos.z, -i2.levelPos.z);
			}
		});
		stuffBeingRendered.forEach(thing -> thing.render());
		GL46.glPopMatrix();
		/*
		 * The stuff rendered here is UI
		 */

		if (this.getLevel() != null) {
			Renderers.charRenderer.render(0.04F, 0.04F, -0.9F, 0.9F,
					"Demons Killed:" + this.getLevel().getGameMain().enemiesKilled + "/" + GameMain.numOfEnemies);
		}
		stuffBeingRendered.clear();
		// Renderers.charRenderer.render('E', 0.7F, 0.9F);
	}

	public Level getLevel() {
		return gameMain.getLevel();
	}

	public EntityManager getEntityManager() {
		return getLevel().getEntityManager();
	}

	public Camera getCamera() {
		return gameMain.getCamera();
	}

}
