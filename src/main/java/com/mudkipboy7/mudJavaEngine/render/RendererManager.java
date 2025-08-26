package com.mudkipboy7.mudJavaEngine.render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL46;

import com.mudkipboy7.mudJavaEngine.Main;
import com.mudkipboy7.mudJavaEngine.XPerSecondKeeperTracker;
import com.mudkipboy7.mudJavaEngine.level.Level;
import com.mudkipboy7.mudJavaEngine.level.LevelPos;
import com.mudkipboy7.mudJavaEngine.level.entity.AbstractEntityObject;
import com.mudkipboy7.mudJavaEngine.level.entity.EntityManager;
import com.mudkipboy7.mudJavaEngine.level.tile.TilePos;
import com.mudkipboy7.mudJavaEngine.render.AbstractRendererInstance.EntityRendererInstance;
import com.mudkipboy7.mudJavaEngine.render.AbstractRendererInstance.TileRendererInstance;

public class RendererManager {
	public Main gameMain;

	// The solid color background

	private ArrayList<AbstractRendererInstance<?>> stuffBeingRendered = new ArrayList<AbstractRendererInstance<?>>();

	public RendererManager() {
	}

	public RendererManager(Main gameMain) {
		this.gameMain = gameMain;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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

			((ArrayList<AbstractEntityObject>) getEntityManager().getLoadedEntities().clone()).forEach(entity -> {
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
					"Demons Killed:" + this.getLevel().getGameMain().enemiesKilled + "/" + Main.numOfEnemies);
			//Renderers.charRenderer.render(0.04F, 0.04F, -0.9F, -0.9F,
			//		"Entities:" + this.getLevel().getEntityManager().getLoadedEntities().size());
			Renderers.charRenderer.render(0.04F, 0.04F, .3F, 0.9F, "FPS:" + getFPS());
			//Renderers.charRenderer.render(0.04F, 0.04F, .3F, -0.9F,
			//		"TPS:" + this.getLevel().getGameMain().TPStracker.getXps());
			Renderers.charRenderer.render(0.04F, 0.04F, -0.9F, -0.9F, "Coords:" + this.getCamera().getX() + ","
					+ this.getCamera().getY() + "," + this.getCamera().getZ());
		}
		stuffBeingRendered.clear();
		this.fpsTracker.tick();

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

	/*
	 * Used for FPS calculations
	 */
	public XPerSecondKeeperTracker fpsTracker = new XPerSecondKeeperTracker();

	public double getFPS() {
		return fpsTracker.xps;
	}
}
