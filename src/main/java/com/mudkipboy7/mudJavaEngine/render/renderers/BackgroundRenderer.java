package com.mudkipboy7.mudJavaEngine.render.renderers;

import java.awt.Color;

import org.lwjgl.opengl.GL46;

import com.mudkipboy7.mudJavaEngine.render.Texture;

public class BackgroundRenderer extends AbstractObjectRenderer {
	Texture texture = null;
	float width = 1;
	float height = 1;
	// public static Texture texture = new Texture("textures/test_texture.png");
	protected Color color = Color.WHITE;

	public BackgroundRenderer(Texture texture, float width, float height) {
		super();
		this.texture = texture;
		this.width = width;
		this.height = height;

	}

	public void render(float xPos, float yPos, float zPos, int animationFrame) {
		
		float sizeMul = 1F;
		float vertices[] = {
				// Each line is the cords for a vertex
				-width, height, -1, // Vertex 1
				width, height, -1, // Vertex 2
				width, -height, -1, // Vertex 3
				-width, -height, -1 // Vertex 4
		};
		float[] textureCoords = texture.getCoordsOfFrame(animationFrame, false);

		GL46.glPushMatrix();

		texture.bind();
		GL46.glEnable(GL46.GL_TEXTURE_2D);
		GL46.glTranslatef(xPos, yPos, zPos);
		
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
}
