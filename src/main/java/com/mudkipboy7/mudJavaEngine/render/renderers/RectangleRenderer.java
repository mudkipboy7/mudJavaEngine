package com.mudkipboy7.mudJavaEngine.render.renderers;

import java.awt.Color;

import org.lwjgl.opengl.GL46;

import com.mudkipboy7.mudJavaEngine.level.LevelPos;

public class RectangleRenderer extends AbstractObjectRenderer {
	float width = 0;
	float height = 0;
	// public static Texture texture = new Texture("textures/test_texture.png");
	protected Color color;

	public RectangleRenderer(Color color, float width, float height) {
		super(/* Math.max(width, height) */);
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public void render(float x, float y, float z) {
		float vertices[] = {
				// Each line is the cords for a vertex
				-width, height, 0, // Vertex 1
				width, height, 0, // Vertex 2
				width, -height, 0, // Vertex 3
				-width, -height, 0 // Vertex 4
		};
		GL46.glPushMatrix();
		GL46.glTranslatef(x, y, z);
		GL46.glBegin(GL46.GL_QUADS);
		GL46.glColor4f(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), this.color.getAlpha());
		GL46.glVertex3f(vertices[0], vertices[1], vertices[2]);
		GL46.glVertex3f(vertices[3], vertices[4], vertices[5]);
		GL46.glVertex3f(vertices[6], vertices[7], vertices[8]);
		GL46.glVertex3f(vertices[9], vertices[10], vertices[11]);
		GL46.glEnd();
		GL46.glPopMatrix();
	}

	public void render(LevelPos screenPos) {
		if (screenPos != null) {
			this.render(screenPos.getX() * 2, screenPos.getY() * 2, screenPos.getZ());
		}
	}
}
