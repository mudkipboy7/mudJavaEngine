package com.mudkipboy7.mudJavaEngine.render;

import java.awt.Color;

import com.mudkipboy7.mudJavaEngine.render.renderers.BackgroundRenderer;
import com.mudkipboy7.mudJavaEngine.render.renderers.CharRenderer;
import com.mudkipboy7.mudJavaEngine.render.renderers.EntityRenderer;
import com.mudkipboy7.mudJavaEngine.render.renderers.RectangleRenderer;
import com.mudkipboy7.mudJavaEngine.render.renderers.TileRenderer;

public class Renderers {
	/*
	 * Entity
	 */
	public static final EntityRenderer defaultEntityRenderer = new EntityRenderer(
			new Texture("textures/test_texture.png"), new Color(0, 0, 0), 1.0F, 1.0F);
	public static final EntityRenderer playerRenderer = new EntityRenderer(
			new Texture("textures/entity/leaf.png"), new Color(1, 0, 0), 1.0F, 1.375F);
	public static final EntityRenderer bulletRenderer = new EntityRenderer(
			new Texture("textures/entity/projectile.png"), new Color(0.7F, 0.7F, 0.7F), 0.1F, 0.1F);
	public static final EntityRenderer victimRenderer = new EntityRenderer(
			new Texture("textures/entity/victim.png"), new Color(1, 1, 0), 1.0F, 2.0F);
	public static final EntityRenderer deadVictimRenderer = new EntityRenderer(
			new Texture("textures/entity/dead_victim.png"), new Color(1, 1, 0), 2.0F, 1.0F);
	/*
	 * Tile
	 */
	public static final TileRenderer normalTileRenderer = new TileRenderer();

	/*
	 * Misc
	 */
	public static final RectangleRenderer solidColorBackgroundRenderer = new RectangleRenderer(new Color(0, 0.1F, 1),
			1F, 1F);

	public static final BackgroundRenderer backgroundRenderer = new BackgroundRenderer(
			new Texture("textures/tileset.png"), 1F, 1F);
	public static final CharRenderer charRenderer = new CharRenderer(new Texture("textures/mudfont0.png"),
			new Color(0, 0.05F, 1));

}
