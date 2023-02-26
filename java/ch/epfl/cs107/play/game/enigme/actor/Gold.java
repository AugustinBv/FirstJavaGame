package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.Collectable.Collectable;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Gold extends Collectable {

	private final Sprite sprite; 
	private boolean state;



	/**
	 * Constructor for Gold
	 * @param area : the area the gold is in
	 * @param orientation : the Orientation of the gold
	 * @param position : the position of the gold
	 */
	public Gold(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		enterArea(area, position);
		sprite = new Sprite("ingot.1", 1, 1.f, this);
		state = false;
	}

	public Gold(Area area, DiscreteCoordinates position) {
		this(area, Orientation.DOWN, position);
	}



	/**
	 * puts the gold in its area andd its position
	 * @param area : the area of the gold
	 * @param position : the position to set the gold in
	 */
	private void enterArea(Area area , DiscreteCoordinates position) {

		setOwnerArea(area);
		setCurrentPosition(position.toVector());
		area.registerActor(this);
		area.enterAreaCells(this, getCurrentCells());

	}


	/**
	 * setter for the state of the gold(collected or not)
	 * @param state : true if they were colected
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


