package com.mudkipboy7.mudJavaEngine.render;

import java.util.Arrays;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.mudkipboy7.mudJavaEngine.FileGetter;

/**
 * Originally a specialized class for animating textures. But now the normal
 * class because now it pretty much acts the exact same but better
 */
public class Texture extends AbstractTexture {
	int framesPerWidth = 0;
	int framesPerHeight = 0;

	public Texture(String path) {
		super(path);
		CommentedConfig x = FileGetter.getTOML(path + ".toml");
		if (x != null) {
			framesPerWidth = x.getInt("fpw");
			framesPerHeight = x.getInt("fph");

			return;
		}
		this.framesPerWidth = 1;
		this.framesPerHeight = 1;

	}

	public float[] getCoordsOfFrame(int frameNum, boolean mirror) {
		float modY = ((int) frameNum / framesPerWidth);
		float modX = frameNum - (framesPerWidth * modY);

		float textureCoords[] = { //
				modX / framesPerWidth, modY / framesPerHeight, //
				(modX + 1.0F) / framesPerWidth, modY / framesPerHeight, //
				(modX + 1.0F) / framesPerWidth, (modY + 1.0F) / framesPerHeight, //
				modX / framesPerWidth, (modY + 1.0F) / framesPerHeight };//
		if (mirror) {
			float a = textureCoords[0];
			float b = textureCoords[2];
			float c = textureCoords[4];
			float d = textureCoords[6];
			textureCoords[0] = b;
			textureCoords[2] = d;
			textureCoords[4] = a;
			textureCoords[6] = c;
		}
		return textureCoords;
	}
}
