package com.mudkipboy7.mudJavaEngine.render.renderers;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.opengl.GL46;

import com.mudkipboy7.mudJavaEngine.Pair;
import com.mudkipboy7.mudJavaEngine.render.ComplexTexture;

public class CharRenderer extends AbstractObjectRenderer {
	ComplexTexture texture = null;
	// public static Texture texture = new Texture("textures/test_texture.png");
	protected Color color = Color.WHITE;

	public static final HashMap<Character, Pair<Integer, Float>> characters = new HashMap<Character, Pair<Integer, Float>>();;

	public CharRenderer(ComplexTexture texture, Color color) {
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
	 * @param string
	 * @param width
	 * @param height
	 * @param xPos
	 * @param yPos
	 */
	private void render(float width, float height, float xPos, float yPos, String string) {
		int g = string.length();
		char[] chars = string.toCharArray();
		float soFar = 0;
		for (int i = 0; i < g; i++) {

			render(chars[i], width, height, xPos + soFar, yPos);
			soFar += width * getCharDistAdder(chars[i]);
		}
	}

	public void render(float width, float height, float xPos, float yPos, String... lines) {
		List<String> list = Arrays.asList(lines);
		float yPosMod = yPos;
		int g = list.size();
		for (int i = 0; i < g; i++) {
			render(width, height, xPos, yPosMod, lines[i]);
			yPosMod -= height * 2.2F;
		}

	}

	private static void addCharToList(char character, int texId, float distadderMod) {
		Pair<Integer, Float> pair = new Pair<Integer, Float>(((int) character), distadderMod);
		characters.put(character, pair);
	}

	private static int getCharTexId(char character) {
		if (characters.containsKey(character)) {
			return characters.get(character).getKey();
		}
		return 0;
	}

	private static float getCharDistAdder(char character) {
		if (characters.containsKey(character)) {
			return characters.get(character).getValue();
		}
		return 1.5F;
	}

	public static void initChars() {
		// Space
		addCharToList(' ', 0, 1.1F);

		// Upper-case
		addCharToList('A', 1, 1.5F);
		addCharToList('B', 2, 1.5F);
		addCharToList('C', 3, 1.5F);
		addCharToList('D', 4, 1.5F);
		addCharToList('E', 5, 1.5F);
		addCharToList('F', 6, 1.5F);
		addCharToList('G', 7, 1.5F);
		addCharToList('H', 8, 1.5F);
		addCharToList('I', 9, 1.5F);
		addCharToList('J', 10, 1.5F);
		addCharToList('K', 11, 1.5F);
		addCharToList('L', 12, 1.5F);
		addCharToList('M', 13, 1.5F);
		addCharToList('N', 14, 1.5F);
		addCharToList('O', 15, 1.5F);
		addCharToList('P', 16, 1.5F);
		addCharToList('Q', 17, 1.5F);
		addCharToList('R', 18, 1.5F);
		addCharToList('S', 19, 1.5F);
		addCharToList('T', 20, 1.5F);
		addCharToList('U', 21, 1.5F);
		addCharToList('V', 22, 1.5F);
		addCharToList('W', 23, 1.5F);
		addCharToList('X', 24, 1.5F);
		addCharToList('Y', 25, 1.5F);
		addCharToList('Z', 26, 1.5F);
		// Lower-case
		addCharToList('a', 27, 1.5F);
		addCharToList('b', 28, 1.5F);
		addCharToList('c', 29, 1.5F);
		addCharToList('d', 30, 1.5F);
		addCharToList('e', 31, 1.5F);
		addCharToList('f', 32, 1.5F);
		addCharToList('g', 33, 1.5F);
		addCharToList('h', 34, 1.5F);
		addCharToList('i', 35, 0.8F);
		addCharToList('j', 36, 1.5F);
		addCharToList('k', 37, 1.5F);
		addCharToList('l', 38, 0.8F);
		addCharToList('m', 39, 1.5F);
		addCharToList('n', 40, 1.5F);
		addCharToList('o', 41, 1.3F);
		addCharToList('p', 42, 1.5F);
		addCharToList('q', 43, 1.5F);
		addCharToList('r', 44, 1.5F);
		addCharToList('s', 45, 1.5F);
		addCharToList('t', 46, 1.1F);
		addCharToList('u', 47, 1.5F);
		addCharToList('v', 48, 1.2F);
		addCharToList('w', 49, 1.5F);
		addCharToList('x', 50, 1.2F);
		addCharToList('y', 51, 1.5F);
		addCharToList('z', 52, 1.5F);
		// Numbers
		addCharToList('0', 53, 1.5F);
		addCharToList('1', 54, 1.5F);
		addCharToList('2', 55, 1.5F);
		addCharToList('3', 56, 1.5F);
		addCharToList('4', 57, 1.5F);
		addCharToList('5', 58, 1.5F);
		addCharToList('6', 59, 1.5F);
		addCharToList('7', 60, 1.5F);
		addCharToList('8', 61, 1.5F);
		addCharToList('9', 62, 1.5F);
		// ETC
		addCharToList('.', 63, 1.0F);
		addCharToList('!', 64, 1.5F);
		addCharToList('?', 65, 1.5F);
		addCharToList(',', 66, 1.5F);

		addCharToList('+', 67, 1.5F);
		addCharToList('-', 68, 1.5F);
		addCharToList('*', 69, 1.5F);
		addCharToList('÷', 70, 1.5F);
		addCharToList('/', 71, 1.5F);
		addCharToList('\\', 72, 1.5F);
		addCharToList(':', 73, 1.0F);
		addCharToList('"', 74, 2.0F);
		addCharToList('\'', 75, 0.6F);

	}

}
