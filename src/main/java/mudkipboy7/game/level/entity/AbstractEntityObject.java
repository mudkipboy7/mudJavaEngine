package mudkipboy7.game.level.entity;

import mudkipboy7.game.level.AbstractPosTracker;
import mudkipboy7.game.level.Level;
import mudkipboy7.game.level.LevelPos;
import mudkipboy7.game.level.physics.Hitbox;
import mudkipboy7.game.level.tile.CollidableTile;
import mudkipboy7.game.level.tile.Tile;
import mudkipboy7.game.level.tile.TilePos;
import mudkipboy7.game.render.Renderers;
import mudkipboy7.game.render.renderers.EntityRenderer;

public abstract class AbstractEntityObject {
	protected EntityManager entityManager;
	protected Level level;
	public LevelPos levelPos;
	protected EntityRenderer renderer = Renderers.defaultEntityRenderer;
	protected float width = 1.0F;
	protected float height = 1.0F;
	public boolean facingLeft = false;
	public int animationFrame = 0;

	public AbstractEntityObject(Level level, LevelPos levelPos) {
		this.level = level;
		if (levelPos != null)
			this.levelPos = levelPos;
		this.entityManager = level.getEntityManager();
	}

	public boolean moveX(float ammount) {
		float newAmmount = ammount;
		// float yPosOfTop = getYPos() + (height / 2.0F);
		if (ammount == 0) {
			return false;
		}

		levelPos.moveX(newAmmount);
		return true;
	}

	public boolean moveY(float ammount) {
		float yPosOfBottom = getYPos() - (height / 2.0F);
		// float yPosOfTop = getYPos() + (height / 2.0F);
		if (ammount == 0)
			return false;
		if (ammount < 0) {
			TilePos posOfBottomTile = new TilePos(getXPos(), yPosOfBottom + ammount);
			TilePos posOfBottomTileRight = new TilePos(getXPos() + 1, yPosOfBottom + ammount);
			TilePos posOfBottomTileLeft = new TilePos(getXPos() - 1, yPosOfBottom + ammount);
			if (checkTileBottomCol(posOfBottomTile, yPosOfBottom) != 0
					|| checkTileBottomCol(posOfBottomTileRight, yPosOfBottom) != 0
					|| checkTileBottomCol(posOfBottomTileLeft, yPosOfBottom) != 0) {
				return false;
			}
		}
		levelPos.moveY(ammount);
		return true;
	}

	private float checkTileBottomCol(TilePos pos, float posbottom) {
		Tile temp = level.getTileTypeAt(pos);
		if (temp instanceof CollidableTile) {
			CollidableTile tile = (CollidableTile) temp;
			float x = tile.getHitbox().getPointInsideY(new LevelPos(0, posbottom), pos);
			if (x != 0) {
				return x;
			}
		}
		return 0;
	}

	public boolean moveZ(float ammount) {
		levelPos.moveZ(ammount);
		return true;
	}

	public abstract void tick();

	public float getXPos() {
		return this.levelPos.x;
	}

	public float getYPos() {
		return this.levelPos.y;
	}

	public float getZPos() {
		return this.levelPos.z;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Level getLevel() {
		return level;
	}

	public EntityRenderer getRenderer() {
		return renderer;
	}

	public int getAnimationFrame() {
		return animationFrame;
	}

	public boolean moveXOld(float ammount) {

		// float yPosOfTop = getYPos() + (height / 2.0F);
		if (ammount == 0)
			return false;
		if (ammount < 0) {
			float xPosOfLeftSide = getXPos() - (width / 2.0F);
			TilePos posOfTileToLeft = new TilePos(xPosOfLeftSide + ammount, getYPos());
			Tile temp = level.getTileTypeAt(posOfTileToLeft);
			if (temp instanceof CollidableTile) {
				CollidableTile tile = (CollidableTile) temp;
				/*
				 * The reason I'm doing it like this is because if I declared in the operation
				 * it won't work in java 1.8 which is what I want to export into
				 */
				float xPosOfRightOfTile = posOfTileToLeft.getX().floatValue() + (tile.getHitbox().getWidth() / 2.0F);
				// Checks to see if its inside of the tile, if it is moves it out
				if (xPosOfRightOfTile >= xPosOfLeftSide) {
					levelPos.moveX(xPosOfRightOfTile - xPosOfLeftSide);
					return false;
				} else if (xPosOfRightOfTile > xPosOfLeftSide) {
					return false;
				}
			}
		} else if (ammount > 0) {
			float xPosOfRightSide = getXPos() + (width / 2.0F);
			TilePos posOfTileToRight = new TilePos(xPosOfRightSide + ammount, getYPos());

			Tile temp = level.getTileTypeAt(posOfTileToRight);
			if (temp instanceof CollidableTile) {
				/*
				 * The reason I'm doing it like this is because if I declared in the operation
				 * it won't work in java 1.8 which is what I want to export into
				 */
				CollidableTile tile = (CollidableTile) temp;
				float xPosOfLeftOfTile = posOfTileToRight.getX().floatValue() - (tile.getHitbox().getWidth() / 2.0F);
				// Checks to see if its inside of the tile, if it is moves it out
				if (xPosOfLeftOfTile <= xPosOfRightSide) {
					levelPos.moveX(xPosOfLeftOfTile - xPosOfRightSide);
					return false;
				} else if (xPosOfLeftOfTile < xPosOfRightSide) {
					return false;
				}
			}
		}
		levelPos.moveX(ammount);
		return true;
	}

	public boolean moveYold(float ammount) {
		float yPosOfBottom = getYPos() - (height / 2.0F);
		// float yPosOfTop = getYPos() + (height / 2.0F);
		if (ammount == 0)
			return false;
		if (ammount < 0) {
			TilePos posOfBottomTile = new TilePos(getXPos(), yPosOfBottom + ammount);
			Tile temp = level.getTileTypeAt(posOfBottomTile);
			if (temp instanceof CollidableTile) {
				/*
				 * The reason I'm doing it like this is because if I declared in the operation
				 * it won't work in java 1.8 which is what I want to export into
				 */
				CollidableTile tile = (CollidableTile) temp;
				float yPosOfTopOfTile = posOfBottomTile.getY().floatValue() + (tile.getHitbox().getHeight() / 2.0F);
				// Checks to see if its inside of the tile, if it is moves it out
				if (yPosOfTopOfTile >= yPosOfBottom) {
					levelPos.moveY(yPosOfTopOfTile - yPosOfBottom);
					return false;
				} else if (yPosOfTopOfTile > yPosOfBottom) {
					return false;
				}
			}
		}

		levelPos.moveY(ammount);
		return true;
	}
}
