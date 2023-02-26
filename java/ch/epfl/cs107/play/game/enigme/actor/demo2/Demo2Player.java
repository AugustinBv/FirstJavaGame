package ch.epfl.cs107.play.game.enigme.actor.demo2;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class Demo2Player extends MovableAreaEntity {

	private boolean goThroughDoor;

	/// Animation duration in frame number
	private final static int ANIMATION_DURATION = 8 ;

	private final Sprite sprite; 

	/**
	 * Demo2PLayer constructor
	 * @param area (Area) : Where the Demo2PLayer appears
	 * @param orientation (Orientation) : Orientation of the player at the beginning
	 * @param coordinates (DiscreteCoordinates) : Position of the Demo2Player in the Area when created
	 */
	public Demo2Player(Area area , Orientation orientation , DiscreteCoordinates coordinates) {
		super (area, orientation, coordinates);
		goThroughDoor = false;
		orientation = Orientation.DOWN;
		sprite = new Sprite("ghost.1", 1, 1.f, this);
	}

	/**
	 * Methode overloading
	 */
	public Demo2Player(Area area , DiscreteCoordinates coordinates) {
		this(area, Orientation.DOWN, coordinates);
	}


	/**
	 * Register an actor in an Area and then immobilize him
	 * @param area (Area) : Area where the Demo2Player will be placed
	 * @param position (DiscreteCoordinates) : position in this Area at the beginning
	 */
	public void enterArea(Area area , DiscreteCoordinates position) {

		setCurrentPosition(position.toVector());
		resetMotion();
		setOwnerArea(area);
		area.registerActor(this);
		goThroughDoor = false;
	}

	/**
	 * The Demo2Player leaves this Area
	 * @param area (Area) : Area the Demo2Player leaves
	 */
	public void leaveArea(Area area) {
		area.leaveAreaCells(this, getLeavingCells());
	}

	/**
	 * Indicates if the player is going through a door by verifying the nature of the cell the player is going in
	 */
	protected void isGoingThrough() {

		AreaBehavior.Cell[][] cells = getOwnerArea().getBehavior().getCells();
		for(int i = 0 ; i < getEnteringCells().size() ; i++) {
			DiscreteCoordinates c = getEnteringCells().get(i);
			AreaBehavior.Cell temp = cells[c.x][c.y];

			if (((Demo2Behavior.Demo2Cell)temp).getType() == Demo2Behavior.Demo2CellType.DOOR) {
				goThroughDoor = true;
			} else {
				goThroughDoor = false;
			}
		}
	}

	/**
	 * Getter for goThrough
	 * @return goThroughDoor (boolean) 
	 */
	public boolean getGoThroughDoor() {
		return goThroughDoor;
	}

	/**
	 * Setter for goThrough
	 * @param state (boolean) : state of goThroughDoor
	 */
	public void setIsGoingThrough(boolean state) {
		goThroughDoor = state;
	}


	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates()) ;
	}


	/**
	 * call move of the super with the frames for move
	 * @param framesForMove (int) : number of frames for the movement 
	 * @return true
	 */
	protected boolean move(int framesForMove) {
		super.move(framesForMove);
		isGoingThrough();
		return true;
	}


	public void update(float deltaTime) {

		super.update(deltaTime);

		Keyboard keyboard = getOwnerArea().getKeyboard();

		Button downArrow = keyboard.get(Keyboard.DOWN);
		Button upArrow = keyboard.get(Keyboard.UP); 
		Button leftArrow = keyboard.get(Keyboard.LEFT);
		Button rightArrow = keyboard.get(Keyboard.RIGHT);


		if(!getIsMoving()) {

			if(downArrow.isDown()) {
				if (getOrientation() == Orientation.DOWN) {
					move(ANIMATION_DURATION);
				} else {
					setOrientation(Orientation.DOWN);
				}
			}

			if(upArrow.isDown()) {
				if (getOrientation() == Orientation.UP) {
					move(ANIMATION_DURATION);
				} else {
					setOrientation(Orientation.UP);
				}
			}

			if(rightArrow.isDown()) {
				if (getOrientation() == Orientation.RIGHT) {
					move(ANIMATION_DURATION);
				} else {
					setOrientation(Orientation.RIGHT);
				}
			}

			if(leftArrow.isDown()) {
				if (getOrientation() == Orientation.LEFT) {
					move(ANIMATION_DURATION);
				} else {
					setOrientation(Orientation.LEFT);
				}
			}

		}

	}


	@Override
	public boolean takeCellSpace() {
		return true;
	}



	@Override
	public boolean isViewInteractable() {
		return true;
	}



	@Override
	public boolean isCellInteractable() {
		return true;
	}



	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// TODO Auto-generated method stub

	}

}