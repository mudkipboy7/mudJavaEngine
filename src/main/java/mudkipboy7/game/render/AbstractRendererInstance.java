package mudkipboy7.game.render;

import mudkipboy7.game.level.LevelPos;
import mudkipboy7.game.level.tile.Tile;
import mudkipboy7.game.render.renderers.AbstractObjectRenderer;
import mudkipboy7.game.render.renderers.CharRenderer;
import mudkipboy7.game.render.renderers.EntityRenderer;
import mudkipboy7.game.render.renderers.RectangleRenderer;
import mudkipboy7.game.render.renderers.TileRenderer;

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

		public EntityRendererInstance(EntityRenderer renderer, LevelPos levelPos, Camera camera, int textureToLoad,
				boolean mirror) {
			super(renderer, levelPos);
			this.mirror = mirror;
			this.textureToLoad = textureToLoad;
			this.camera = camera;
		}

		@Override
		public void render() {
			this.renderer.render(levelPos, camera, textureToLoad, mirror);
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

	public static class TextRendererInstance extends AbstractRendererInstance<CharRenderer> {
		String[] text;
		float x = 0;
		float y = 0;
		float width = 0;
		float height = 0;

		public TextRendererInstance(CharRenderer renderer, float width, float height, float xPos, float yPos,
				String... text) {
			super(renderer, new LevelPos(0, 0, 1F));
			this.text = text;
			this.x = xPos;
			this.y = yPos;
			this.width = width;
			this.height = height;
		}

		@Override
		public void render() {
			this.renderer.render(width, height, x, y, text);
		}
	}
}