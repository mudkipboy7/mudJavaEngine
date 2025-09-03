package com.mudkipboy7.mudJavaEngine.render;

import com.mudkipboy7.mudJavaEngine.level.LevelPos;
import com.mudkipboy7.mudJavaEngine.level.entity.AbstractEntityObject;
import com.mudkipboy7.mudJavaEngine.level.tile.Tile;
import com.mudkipboy7.mudJavaEngine.render.renderers.AbstractObjectRenderer;
import com.mudkipboy7.mudJavaEngine.render.renderers.CharRenderer;
import com.mudkipboy7.mudJavaEngine.render.renderers.EntityRenderer;
import com.mudkipboy7.mudJavaEngine.render.renderers.RectangleRenderer;
import com.mudkipboy7.mudJavaEngine.render.renderers.TileRenderer;

public abstract class AbstractRendererInstance<T extends AbstractObjectRenderer> {
	T renderer;
	LevelPos levelPos;

	public AbstractRendererInstance(T renderer, LevelPos levelPos) {
		this.renderer = renderer;
		this.levelPos = levelPos;
	}

	public abstract void render();

	public T getRenderer() {
		return renderer;
	}

	public static class EntityRendererInstance extends AbstractRendererInstance<EntityRenderer> {

		int textureToLoad = 0;
		boolean mirror;
		Camera camera;
		AbstractEntityObject entity;

		public EntityRendererInstance(AbstractEntityObject entity, Camera camera) {
			super(entity.getRenderer(), entity.levelPos);
			this.entity = entity;
			this.camera = camera;

		}

		@Override
		public void render() {
			this.renderer.render(levelPos, camera, entity.getAnimationFrame(), mirror);
		}

		public int getTextureToLoad() {
			return textureToLoad;
		}
	}

	public static class TileRendererInstance extends AbstractRendererInstance<TileRenderer> {
		Tile tile;

		Camera camera;

		public TileRendererInstance(int tileId, LevelPos levelPos, Camera camera) {
			super(Tile.getTileOfId(tileId).getRenderer(), levelPos);
			this.tile = Tile.getTileOfId(tileId);
			this.camera = camera;
		}

		@Override
		public void render() {
			this.renderer.render(tile, camera, levelPos, false);
		}
	}

	public static class GenericRectangleRendererInstance extends AbstractRendererInstance<RectangleRenderer> {

		public GenericRectangleRendererInstance(RectangleRenderer renderer, LevelPos levelPos) {
			super(renderer, levelPos);
		}

		@Override
		public void render() {
			this.renderer.render(levelPos);
		}
	}

}