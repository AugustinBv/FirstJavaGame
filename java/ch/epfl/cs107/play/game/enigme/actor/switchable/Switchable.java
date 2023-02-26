package ch.epfl.cs107.play.game.enigme.actor.switchable;

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

public class Switchable extends AreaEntity implements Logic, Interactable{

	private boolean state = false;
	protected Sprite spriteOn;
	protected Sprite spriteOff;

	/**
	 * Switchable constructor
	 * @param area (Area) : area the switchable is set in
	 * @param orientation (Orientation) : orientation the switchable is set in
	 * @param position (DiscreteCoordinates) : position the switchable is set in
	 * @param state (Boolean) : state the switchable is set in
	 */
	public Switchable(Area area, Orientation orientation, DiscreteCoordinates position, boolean state) {
		super(area, orientation, position);
	}

	/**
	 * Setter for the state
	 * @param state (Boolean) : set the state
	 */
	public void setState(boolean state) {
		this.state=state;
	}

	/**
	 * Setter for the sprite
	 * @param spriteOn (Sprite) : sprite if the switchable is on
	 * @param spriteOff (Sprite) : sprite if the switchable is off
	 */
	public void setSprite(Sprite spriteOn, Sprite spriteOff) {
		this.spriteOff=spriteOff;
		this.spriteOn=spriteOn;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	/**
	 * Setter for the state
	 * @param state (Boolean) : state to set
	 */
	public void setSwitched(boolean state) {
		this.state = state;
	}

	/**
	 * getter for switched
	 * @return (Boolean) : return if switched
	 */
	public boolean getSwitched() {

		System.out.println(state);
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

	public boolean isOn(){
		return state;
	}


	@Override
	public void draw(Canvas canvas) {
		if(isOn()) {
			spriteOn.draw(canvas);
		} else {
			spriteOff.draw(canvas);
		}
	}

}
