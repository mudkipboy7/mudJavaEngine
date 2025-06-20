package mudkipboy7.game.render;

import java.util.Arrays;

/**
 * This is used for telling the renderer what part of the texture should be
 * loaded this frame
 */
public class ComplexTexture extends Texture {
	int framesPerWidth = 0;
	int framesPerHeight = 0;

	public ComplexTexture(String path, int framesPerWidth, int framesPerHeight) {
		super(path);
		this.framesPerWidth = framesPerWidth;
		this.framesPerHeight = framesPerHeight;
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
