package com.mudkipboy7.mudJavaEngine.render.renderers;

import java.awt.Color;
import java.util.Arrays;

import org.lwjgl.opengl.GL46;

import com.mudkipboy7.mudJavaEngine.level.LevelPos;
import com.mudkipboy7.mudJavaEngine.level.tile.Tile;
import com.mudkipboy7.mudJavaEngine.render.Camera;
import com.mudkipboy7.mudJavaEngine.render.ComplexTexture;

public class TileRenderer extends AbstractObjectRenderer {
	static float width = 1.0F;
	static float height = 1.0F;
	protected Color color = Color.WHITE;

	public TileRenderer() {
		super();
	}

	public void render(Tile tileType, float x, float y, float z, boolean mirror) {
		// if (tileType != null) {

		ComplexTexture texture = tileType.getTexture();
		float sizeMul = -z;
		float vertices[] = {
				// Each line is the cords for a vertex
				-width, height, 0, // Vertex 1
				width, height, 0, // Vertex 2
				width, -height, 0, // Vertex 3
				-width, -height, 0 // Vertex 4
		};
		float modX = 0.0F;
		float modY = 1.0F;
		// System.out.println(z);
		float textureCoords[] = tileType.getTextureCoords();
		GL46.glPushMatrix();
		texture.bind();
		GL46.glEnable(GL46.GL_TEXTURE_2D);
		// GL46.glScalef(sizeMul, sizeMul, 0);
		GL46.glTranslatef(x, y, z);

		GL46.glBegin(GL46.GL_QUADS);
		GL46.glColor4f(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), this.color.getAlpha());

		GL46.glTexCoord2f(textureCoords[0], textureCoords[1]);
		GL46.glVertex3f(vertices[0], vertices[1], vertices[2]);

		GL46.glTexCoord2f(textureCoords[2], textureCoords[3]);
		GL46.glVertex3f(vertices[3], vertices[4], vertices[5]);

		GL46.glTexCoord2f(textureCoords[4], textureCoords[5]);
		GL46.glVertex3f(vertices[6], vertices[7], vertices[8]);

		GL46.glTexCoord2f(textureCoords[6], textureCoords[7]);
		GL46.glVertex3f(vertices[9], vertices[10], vertices[11]);
		GL46.glEnd();
		GL46.glDisable(GL46.GL_TEXTURE_2D);
		GL46.glPopMatrix();
	}

	public void render(Tile tileType, Camera camera, LevelPos levelPos, boolean mirror) {
		if (levelPos != null) {
			this.render(tileType, levelPos.getX() * 2.0F, levelPos.getY() * 2.0F, camera.getZ() - levelPos.getZ(),
					mirror);
		}
	}
}
