package mudkipboy7.game.level.tile;

import mudkipboy7.game.level.physics.Hitbox;
import mudkipboy7.game.render.ComplexTexture;
import mudkipboy7.game.render.Renderers;
import mudkipboy7.game.render.Texture;
import mudkipboy7.game.render.renderers.TileRenderer;

public class Tile {
	public static ComplexTexture tileSet = new ComplexTexture("textures/tileset.png", 8, 8);
	public static final Tile[] tiles = new Tile[512];
	public static final Tile emptyTile = new Tile(0, 0);
	public static final Tile simpleCollidableTile = new CollidableTile(1, 1);
	public static final Tile blackTile = new CollidableTile(2, 8);

	private int id;
	int texNum;
	private final TileRenderer renderer;
	protected Hitbox hitbox = new Hitbox(1.0F, 1.0F);

	protected Tile(int id, int texNum) {
		tiles[id] = this;
		this.id = id;
		this.texNum = texNum;
		this.renderer = Renderers.normalTileRenderer;
	}

	public int getId() {
		return id;
	}

	public TileRenderer getRenderer() {
		return renderer;
	}

	public float[] getTextureCoords() {
		return tileSet.getCoordsOfFrame(this.texNum, false);

	}

	public ComplexTexture getTexture() {
		return Tile.tileSet;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public static Tile getTileOfId(int id) {
		// System.out.println(tiles[id]);
		return tiles[id];
	}
}
