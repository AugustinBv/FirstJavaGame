package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.Collectable.Collectable;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Apple extends Collectable{
	private final Sprite sprite; 
	// State indicates if the apple is collected (true = yes)
	private boolean state;

	/**
	 * apple constructor
	 * @param area (Area) : area thea apple is set in
	 * @param orientation (Orientation) : orientation the apple is set in
	 * @param position (DiscreteCoordinates) : position the apple is set in
	 */
	public Apple(Area area, Orientation orientation, DiscreteCoordinates position) {

		super(area, orientation, position);
		enterArea(area, position);
		sprite = new Sprite("apple.1", 1, 1.f, this);
		state = false;
	}

	/**
	 * apple overloading
	 * @param area 
	 * @param position
	 */
	public Apple(Area area, DiscreteCoordinates position) {
		this(area, Orientation.DOWN, position);
	}

	/**
	 * Places the Apple in the area 
	 * @param area (Area) : Area the apple enters in
	 * @param position (DiscreteCoordinates) : position the apple is set in
	 */
	private void enterArea(Area area , DiscreteCoordinates position) {

		setOwnerArea(area);
		setCurrentPosition(position.toVector());
		area.registerActor(this);
		area.enterAreaCells(this, getCurrentCells());

	}

	/**
	 * getter
	 * @return
	 */
	public boolean getCollected() {
		return state;
	}

	/**
	 * setter
	 * @param state
	 */
	protected void setCollected(boolean state) {
		this.state = state;
	}


	public void update(float deltaTime) {
		if(state) {
			this.getOwnerArea().unregisterActor(this);
		}
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return !state;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}


}
