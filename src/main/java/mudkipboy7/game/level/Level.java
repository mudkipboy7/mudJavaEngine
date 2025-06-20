package mudkipboy7.game.level;

import java.util.HashMap;
import java.util.Random;

import mudkipboy7.game.GameMain;
import mudkipboy7.game.level.entity.EntityManager;
import mudkipboy7.game.level.entity.PlayerObject;
import mudkipboy7.game.level.entity.VictimObject;
import mudkipboy7.game.level.tile.CollidableTile;
import mudkipboy7.game.level.tile.Tile;
import mudkipboy7.game.level.tile.TilePos;

public class Level {
	GameMain gameMain;
	private EntityManager entityManager;
	private PlayerObject player;
	// private TileManager tileManager;
	public static int height = 100;
	// First is pos, second is id in pos
	private HashMap<Integer, Integer> loadedTiles = new HashMap<>();

	public Level(GameMain gameMain) {
		this.gameMain = gameMain;
		this.entityManager = new EntityManager(this);

		this.player = new PlayerObject(this);
		for (int i = 0; i <= 200; i++) {
			addTile(new TilePos(i, 0), Tile.simpleCollidableTile);
		}
		addTile(new TilePos(0, 1), Tile.simpleCollidableTile);
		addTile(new TilePos(0, 2), Tile.simpleCollidableTile);
		addTile(new TilePos(0, 3), Tile.simpleCollidableTile);
		addTile(new TilePos(0, 4), Tile.simpleCollidableTile);
		addTile(new TilePos(0, 5), Tile.simpleCollidableTile);
		addTile(new TilePos(200, 1), Tile.simpleCollidableTile);
		addTile(new TilePos(200, 2), Tile.simpleCollidableTile);
		addTile(new TilePos(200, 3), Tile.simpleCollidableTile);
		addTile(new TilePos(200, 4), Tile.simpleCollidableTile);
		addTile(new TilePos(200, 5), Tile.simpleCollidableTile);
		// addTile(new TilePos(20, 1), Tile.blackTile);
		// addTile(new TilePos(20, 2), Tile.blackTile);
		// addTile(new TilePos(20, 3), Tile.blackTile);
		// addTile(new TilePos(20, 4), Tile.blackTile);
		// addTile(new TilePos(20, 5), Tile.blackTile);
		Random random = new Random();
		for (int i = 0; i < GameMain.numOfEnemies; i++) {
			new VictimObject(this, new LevelPos(random.nextFloat() * 200.0F, 3F, 0.05F));
		}
		// placeUnMembers(5, 6, 7, 8, 10, 20, 24, 13, 53, 67, 53, 71, 32, 4, 8, 20, 02);

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

	public GameMain getGameMain() {
		return gameMain;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public PlayerObject getPlayer() {
		return player;
	}

	public boolean addTile(TilePos tilePos, int id) {

		if (!(tilePos.x < 0 || tilePos.y < 0 || tilePos.y >= height)) {
			Integer posNum = (height * tilePos.getX()) + (tilePos.getY());
			loadedTiles.put(posNum, id);
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

}
