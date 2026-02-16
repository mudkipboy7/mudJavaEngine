package com.mudkipboy7.mudJavaEngine.level.tile;

import com.mudkipboy7.mudJavaEngine.level.physics.Hitbox;
import com.mudkipboy7.mudJavaEngine.render.Texture;
import com.mudkipboy7.mudJavaEngine.render.Renderers;
import com.mudkipboy7.mudJavaEngine.render.renderers.TileRenderer;

public class Tile {
	public static Texture tileSet0 = new Texture("textures/tileset.png");
	public static Texture tileSet1 = new Texture("textures/tileset1.png");

	public static final Tile[] tiles = new Tile[512];
	public static final Tile emptyTile = new Tile(0, tileSet0, 0);
	public static final Tile simpleCollidableTile = new CollidableTile(1, tileSet0, 1);
	public static final Tile blackTile = new CollidableTile(2, tileSet0, 8);
	public static final Tile treeTile1 = new CollidableTile(3, tileSet1, 2);
	public static final Tile treeTile2 = new CollidableTile(4, tileSet1, 10);
	public static final Tile treeTile3 = new CollidableTile(5, tileSet1, 18);
	public static final Tile treeTile4 = new CollidableTile(6, tileSet1, 3);
	public static final Tile treeTile5 = new CollidableTile(7, tileSet1, 11);
	public static final Tile treeTile6 = new CollidableTile(8, tileSet1, 19);

	public Texture texture;
	private int id;
	int texNum;
	private final TileRenderer renderer;
	protected Hitbox hitbox = new Hitbox(1.0F, 1.0F);

	protected Tile(int id, Texture tileset, int texNum) {
		tiles[id] = this;
		this.id = id;
		this.texNum = texNum;
		this.renderer = Renderers.normalTileRenderer;
		this.texture = tileset;

	}

	public int getId() {
		return id;
	}

	public TileRenderer getRenderer() {
		return renderer;
	}

	public float[] getTextureCoords() {
		return texture.getCoordsOfFrame(this.texNum, false);

	}

	public Texture getTexture() {
		return texture;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public static Tile getTileOfId(int id) {
		// System.out.println(tiles[id]);
		return tiles[id];
	}
}
