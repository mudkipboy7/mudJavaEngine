package com.mudkipboy7.mudJavaEngine.render;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL46;

import com.mudkipboy7.mudJavaEngine.Main;

public class Texture {
	public static final Texture defaultTexture = new Texture("textures/test_texture.png");
	private int id;
	private int width = 0;
	private int height = 0;

	private BufferedImage image = null;

	private Texture(String path, int startX, int startY, int width, int height) {
		image = getBufferedImageFromPath(path);
		this.width = width;
		this.height = height;
		processBufferedImage(startX, startY);
	}


	public Texture(String path) {
		image = getBufferedImageFromPath(path);
		this.width = image.getWidth();
		this.height = image.getHeight();
		//System.out.println("ffwf");
		processBufferedImage(0, 0);

	}


	private void processBufferedImage(int startX, int StartY) {
		int[] pixels_raw = new int[width * height * 4];
		pixels_raw = image.getRGB(0, 0, width, height, null, 0, width);
		ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int pixel = pixels_raw[i * height + j];
				pixels.put((byte) ((pixel >> 16) & 0xFF)); // Red
				pixels.put((byte) ((pixel >> 8) & 0xFF));// Blue
				pixels.put((byte) (pixel & 0xFF));// Green
				pixels.put((byte) ((pixel >> 24) & 0xFF));// Alpha
			}
		}

		pixels.flip();
		id = GL46.glGenTextures();
		GL46.glBindTexture(GL46.GL_TEXTURE_2D, id);
		GL46.glTexParameterf(GL46.GL_TEXTURE_2D, GL46.GL_TEXTURE_MIN_FILTER, GL46.GL_NEAREST);
		GL46.glTexParameterf(GL46.GL_TEXTURE_2D, GL46.GL_TEXTURE_MAG_FILTER, GL46.GL_NEAREST);
		GL46.glTexImage2D(GL46.GL_TEXTURE_2D, 0, GL46.GL_RGBA, width, height, 0, GL46.GL_RGBA, GL46.GL_UNSIGNED_BYTE,
				pixels);

	}

	public void bind() {
		GL46.glBindTexture(GL46.GL_TEXTURE_2D, id);
	}

	private BufferedImage getBufferedImageFromPath(String path) {
		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(path);
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getId() {
		return id;
	}
}