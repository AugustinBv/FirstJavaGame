package ch.epfl.cs107.play.game.enigme.actor.Harmful;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Harmful extends AreaEntity implements Logic, Interactable{
	private boolean state = false;
	protected Sprite sprite;

	/**
	 * Harmful constructor 
	 * @param area (Area) : Area where the Harmful objects is placed
	 * @param orientation (Orientation) : orientation of the object at the beginning
	 * @param position (DiscreteCoordinates) : where the harmful object is placed in the area a the beginning
	 * @param state (Boolean) : a useless boolean
	 */
	public Harmful(Area area, Orientation orientation, DiscreteCoordinates position, boolean state) {
		super(area, orientation, position);
	}

	/**
	 * Set this object's sprite
	 * @param sprite (Sprite) : sprite the object is going to use
	 */
	public void setSprite(Sprite sprite) {
		this.sprite=sprite;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	/**
	 * Sets if this object is harming another one
	 * @param state (boolean) : sets the state of the boolean
	 */
	public void setHarmed(boolean state) {
		this.state = state;
	}

	/**
	 * Gets if this object harms another one
	 * @return state (boolean) : returns the state of the boolean
	 */
	public boolean getHarmed() {
		return state;
	}


	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}

	/**
	 * Another getter
	 */
	public boolean isOn(){
		return state;
	}
	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}

}
