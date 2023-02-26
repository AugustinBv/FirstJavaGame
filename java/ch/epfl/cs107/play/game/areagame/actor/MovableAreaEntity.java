package ch.epfl.cs107.play.game.areagame.actor;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {

	// TODO implements me #PROJECT #TUTO
	/// Indicate if the actor is currently moving
	private boolean isMoving = false;
	/// Indicate how many frames the current move is supposed to take
	private int framesForCurrentMove;
	/// The target cell (i.e. where the mainCell will be after the motion)
	private DiscreteCoordinates targetMainCellCoordinates;

	private Interactable interactable;
	
	/**
	 * Default MovableAreaEntity constructor
	 * 
	 * @param area        (Area): Owner area. Not null
	 * @param position    (Coordinate): Initial position of the entity. Not null
	 * @param orientation (Orientation): Initial orientation of the entity. Not null
	 */
	public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		// TODO implements me #PROJECT #TUTO
		resetMotion();
	}

	/**
	 * Initialize or reset the current motion information
	 */
	protected void resetMotion() {
		// TODO implements me #PROJECT #TUTO
		isMoving = false;
		framesForCurrentMove = 0;
		targetMainCellCoordinates = getCurrentMainCellCoordinates();
	}

	/**
	 * 
	 * @param frameForMove (int): number of frames used for simulating motion
	 * @return (boolean): returns true if motion can occur
	 */

	protected boolean move(int framesForMove) {
		// TODO implements me #PROJECT #TUTO\
		boolean canEnter = getOwnerArea().getBehavior().canEnter(this, getEnteringCells());
		boolean canLeave = getOwnerArea().getBehavior().canLeave(this, getLeavingCells());
		boolean moves = canEnter && canLeave;
		System.out.println("moves " + moves + " enter " + canEnter + " leave " + canLeave);

		if (!moves) {
			return false;
		} else {
			if (framesForMove < 1) {
				framesForCurrentMove = 1;
			} else {
				framesForCurrentMove = framesForMove;
			}
			Vector orientation = getOrientation().toVector();
			isMoving = true;
			targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
			return true;
		}

		/*
		 * if (!moves) { return false; } else { Vector orientation =
		 * getOrientation().toVector(); isMoving = true ;
		 * 
		 * if (framesForMove < 1) { framesForCurrentMove = 1; } else {
		 * framesForCurrentMove = framesForMove; } targetMainCellCoordinates =
		 * getCurrentMainCellCoordinates().jump(orientation) ; return true; }
		 */
	}

	public boolean getIsMoving() {
		return isMoving;
	}

	/// MovableAreaEntity implements Actor

	@Override
	public void update(float deltaTime) {
		// TODO implements me #PROJECT #TUTO
		if (isMoving) {
			// test si position.equascmcc => reset
			// JS
			if (getPosition().x == targetMainCellCoordinates.x && getPosition().y == targetMainCellCoordinates.y) {
				resetMotion();
			} else {
				Vector distance = getOrientation().toVector();
				distance = distance.mul(1.0f / framesForCurrentMove);

				setCurrentPosition(getPosition().add(distance));
				System.out.println("new position" + getPosition());
			}
		}
	}

	/// Implements Positionable

	@Override
	public Vector getVelocity() {
		// TODO implements me #PROJECT #TUTO
		// the velocity must be computed as the orientation vector
		// (getOrientation().toVector() mutiplied by
		// framesForCurrentMove
		return null;
	}

	protected final List<DiscreteCoordinates> getLeavingCells() {

		List<DiscreteCoordinates> leavingCells = new LinkedList<>();
		leavingCells.addAll(getCurrentCells());

		return leavingCells;
	}

	protected final List<DiscreteCoordinates> getEnteringCells() {

		List<DiscreteCoordinates> enteringCells = new LinkedList<>();

		for (int i = 0; i < getCurrentCells().size(); ++i) {

			DiscreteCoordinates coord = getCurrentCells().get(i).jump(getOrientation().toVector());

			if ((coord.getY() < getOwnerArea().getWidth()) && (coord.getX() < getOwnerArea().getHeight())) {
				enteringCells.add(getCurrentCells().get(i).jump(getOrientation().toVector()));
			}
		}
		return enteringCells;
	}

	@Override
	protected Orientation setOrientation(Orientation newOrientation) {
		if (!isMoving) {
			super.setOrientation(newOrientation);
		}
		return getOrientation();
	}

}
