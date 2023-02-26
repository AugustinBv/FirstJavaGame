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
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends Collectable implements Logic{

	private final Sprite sprite; 
	private boolean state;
	private Logic signal;


	/**
	 * Constructor for Key
	 * @param area : the area the key is in
	 * @param orientation : the Orientation of the key
	 * @param position : the position of the key
	 */
	public Key(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);

		enterArea(area, position);
		sprite = new Sprite("key.1", 1, 1.f, this);
		state = false;
		signal = Logic.FALSE;
	}

	public Key(Area area, DiscreteCoordinates position) {
		this(area, Orientation.DOWN, position);
	}


	/**
	 * puts the key in its area andd its position
	 * @param area : the area of the key
	 * @param position : the position to set the key in
	 */
	private void enterArea(Area area , DiscreteCoordinates position) {

		setOwnerArea(area);
		setCurrentPosition(position.toVector());
		area.registerActor(this);
		area.enterAreaCells(this, getCurrentCells());
	}

	/**
	 * setter for the state of the key(collected or not)
	 * @param state : true if they were colected
	 */
	protected void setCollected(boolean state) {
		this.state = state;
	}


	/**
	 * getter for the state of the shoes(collected or not)
	 * @return signal : gets the signal of the key 
	 */
	public Logic getSignal() {
		return signal;
	}

	/**
	 * setter for the state of the shoes(collected or not)
	 * @param state : true if they were colected
	 */
	protected void setSignal(Logic signal) {
		this.signal = signal;
	}

	public void update(float deltaTime) {
		if(state) {
			this.getOwnerArea().unregisterActor(this);
			setSignal(Logic.TRUE);
		}
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
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