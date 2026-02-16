package com.mudkipboy7.mudJavaEngine.level;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.mudkipboy7.mudJavaEngine.Main;
import com.mudkipboy7.mudJavaEngine.level.entity.EntityManager;
import com.mudkipboy7.mudJavaEngine.level.entity.PlayerObject;
import com.mudkipboy7.mudJavaEngine.level.entity.VictimObject;
import com.mudkipboy7.mudJavaEngine.level.tile.CollidableTile;
import com.mudkipboy7.mudJavaEngine.level.tile.Tile;
import com.mudkipboy7.mudJavaEngine.level.tile.TilePos;

public class Level {
	Main gameMain;
	private EntityManager entityManager;
	private PlayerObject player;
	// private TileManager tileManager;
	public static int height = 10;

	// First is pos, second is id in pos
	private HashMap<Integer, Integer> loadedTiles = new HashMap<>();

	public Level(Main gameMain, String areaPath) {
		this.gameMain = gameMain;
		this.entityManager = new EntityManager(this);
		this.player = new PlayerObject(this, new LevelPos(10, 0, -0.1F));

		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(areaPath);
		byte[] loadedFile = null;
		try {
			loadedFile = inputStream.readAllBytes();
		} catch (IOException e) {
		}
		if (loadedFile != null) {
			height = loadedFile[0];
			for (int i = 1; i < loadedFile.length; i++) {
				addTile(i - 1, loadedFile[i]);
			}
		}
		System.out.println(getByteValue());
	}

	public void placeUnMembers(float... poses) {
		for (int i = 0; i < poses.length; i++) {
			System.out.println();
			new VictimObject(this, new LevelPos(poses[i], 3, 0.05F));
		}
	}

	public void tickEntities() {
		this.entityManager.physicsTickEntities();
	}

	public Main getGameMain() {
		return gameMain;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public PlayerObject getPlayer() {
		return player;
	}

	public void addTile(int pos, int id) {
		loadedTiles.put(pos, id);
	}

	public boolean addTile(TilePos tilePos, int id) {

		if (!(tilePos.x < 0 || tilePos.y < 0 || tilePos.y >= height)) {
			Integer posNum = (height * tilePos.getX()) + (tilePos.getY());
			addTile(posNum, id);
			// dwsSystem.out.println("X:" + tilePos.x + " Y:" + tilePos.y);
			return true;
		}
		return false;
	}

	public boolean addTile(TilePos tilePos, Tile tile) {
		return addTile(tilePos, tile.getId());
	}

	public int getTileIdAt(TilePos tilePos) {
		int posNum = (height * tilePos.x) + tilePos.y;
		if (loadedTiles.get(posNum) == null) {
			return 0;
		}
		return loadedTiles.get(posNum);
	}

	public Tile getTileTypeAt(TilePos tilePos) {
		return Tile.tiles[getTileIdAt(tilePos)];
	}

	public boolean isTileAtPosCollidable(TilePos tilePos) {
		if (getTileTypeAt(tilePos) instanceof CollidableTile) {
			return true;
		}
		return false;
	}

	public HashMap<Integer, Integer> getLoadedTiles() {
		return loadedTiles;
	}

	public static TilePos getTilePosFromTileKey(Integer key) {
		int x = key / height;
		int y = key - (x * height);
		// int z = 9;
		return new TilePos(x, y);
	}

	public String getByteValue() {
		String x = String.valueOf((char) (byte) height);
		getLoadedTiles().forEach((pos, value) -> {
			String y = String.valueOf((char) (byte) (int) value);
			x.concat(y);
		});
		return x;

	}
}
