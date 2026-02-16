package com.mudkipboy7.mudJavaEngine.render.renderers;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.lwjgl.opengl.GL46;

import com.mudkipboy7.mudJavaEngine.Pair;
import com.mudkipboy7.mudJavaEngine.render.Texture;

public class CharRenderer extends AbstractObjectRenderer {
	Texture texture = null;
	// public static Texture texture = new Texture("textures/test_texture.png");
	protected Color color = Color.WHITE;

	public static final HashMap<Character, Pair<Integer, Float>> characters = new HashMap<Character, Pair<Integer, Float>>();;

	public CharRenderer(Texture texture, Color color) {
		super();
		this.texture = texture;
	}

	public void render(char character, float width, float height, float x, float y) {
		float vertices[] = {
				// Each line is the cords for a vertex
				-width, height, -0.1F, // Vertex 1
				width, height, -0.1F, // Vertex 2
				width, -height, -0.1F, // Vertex 3
				-width, -height, -0.1F // Vertex 4
		};

		/*
		 * float textureCoords[] = { // 0, 0, // 1, 0, // 1, 1, // 0, 1 };// if (mirror)
		 * { textureCoords[2] = -textureCoords[2]; textureCoords[4] = -textureCoords[4];
		 * }
		 */

		GL46.glPushMatrix();

		texture.bind();
		GL46.glEnable(GL46.GL_TEXTURE_2D);
		// GL46.glScalef(1 / sizeMul, 1 / sizeMul, 1 / sizeMul);
		GL46.glTranslatef(x, y, 0.0F);
		float[] textureCoords = texture.getCoordsOfFrame(getCharTexId(character), false);
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

	/**
	 * Renders a single line of text
	 * 
	 */
	private void renderSingleLine(float width, float height, float xPos, float yPos, String string) {
		int g = string.length();
		char[] chars = string.toCharArray();
		float soFar = 0;
		for (int i = 0; i < g; i++) {

			render(chars[i], width, height, xPos + soFar, yPos);
			soFar += width * getCharDistAdder(chars[i]);
		}
	}

	/**
	 * Renders multiple lines of text
	 */
	private void renderMultipleLines(float width, float height, float xPos, float yPos, List<String> lines) {
		float yPosMod = yPos;
		int g = lines.size();

		for (int i = 0; i < g; i++) {

			renderSingleLine(width, height, xPos, yPosMod, lines.get(i));
			yPosMod -= height * 2.2F;
		}
	}

	public void render(float width, float height, float xPos, float yPos, String string) {
		renderMultipleLines(width, height, xPos, yPos, string.lines().toList());
	}

	private static void addCharToList(char character, float distadderMod) {
		Pair<Integer, Float> pair = new Pair<Integer, Float>(((int) character), distadderMod);
		characters.put(character, pair);
	}

	private static int getCharTexId(char character) {
		if (characters.containsKey(character)) {
			return characters.get(character).getKey();
		}
		return character;
	}

	private static float getCharDistAdder(char character) {
		if (characters.containsKey(character)) {
			return characters.get(character).getValue();
		}
		return 1.5F;
	}

	public static void initChars() {

		// Only add characters here if you need to change how they render.
		// Space
		addCharToList(' ', 1.1F);

		// Upper-case

		addCharToList('i', 0.8F);
		addCharToList('l', 0.8F);
		addCharToList('o', 1.3F);
		addCharToList('t', 1.1F);
		addCharToList('v', 1.2F);
		addCharToList('x', 1.2F);
		// Numbers

		// ETC
		addCharToList('.', 1.0F);

		addCharToList(':', 1.0F);
		addCharToList('"', 2.0F);
		addCharToList('\'', 0.6F);

	}

}
