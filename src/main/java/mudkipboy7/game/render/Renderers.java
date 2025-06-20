package mudkipboy7.game.render;

import java.awt.Color;

import mudkipboy7.game.render.renderers.BackgroundRenderer;
import mudkipboy7.game.render.renderers.CharRenderer;
import mudkipboy7.game.render.renderers.EntityRenderer;
import mudkipboy7.game.render.renderers.RectangleRenderer;
import mudkipboy7.game.render.renderers.TileRenderer;

public class Renderers {
	/*
	 * Entity
	 */
	public static final EntityRenderer defaultEntityRenderer = new EntityRenderer(
			new ComplexTexture("textures/test_texture.png", 1, 1), new Color(0, 0, 0), 1.0F, 1.0F);
	public static final EntityRenderer playerRenderer = new EntityRenderer(
			new ComplexTexture("textures/entity/player.png", 1, 1), new Color(1, 0, 0), 1.00F, 2.00F);
	public static final EntityRenderer bulletRenderer = new EntityRenderer(
			new ComplexTexture("textures/entity/projectile.png", 1, 1), new Color(0.7F, 0.7F, 0.7F), 0.1F, 0.1F);
	public static final EntityRenderer victimRenderer = new EntityRenderer(
			new ComplexTexture("textures/entity/victim.png", 1, 1), new Color(1, 1, 0), 1.0F, 2.0F);
	public static final EntityRenderer deadVictimRenderer = new EntityRenderer(
			new ComplexTexture("textures/entity/dead_victim.png", 1, 1), new Color(1, 1, 0), 2.0F, 1.0F);
	/*
	 * Tile
	 */
	public static Texture texture = new Texture("textures/tileset.png");
	public static final TileRenderer normalTileRenderer = new TileRenderer();

	/*
	 * Misc
	 */
	public static final RectangleRenderer solidColorBackgroundRenderer = new RectangleRenderer(new Color(0, 0.1F, 1),
			1F, 1F);

	public static final BackgroundRenderer backgroundRenderer = new BackgroundRenderer(
			new ComplexTexture("textures/tileset.png", 8, 8), 1F, 1F);
	public static final CharRenderer charRenderer = new CharRenderer(new ComplexTexture("textures/alphabet.png", 16, 16),
			new Color(0, 0.05F, 1));

}
