package ch.epfl.cs107.play.game.enigme.actor.hpcollectable;

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

public class EndCollectable extends AreaEntity implements Logic, Interactable{

	private boolean state = false;
	protected Sprite sprite;

	/*
	 * EndCollectable constructor
	 */
	public EndCollectable(Area area, Orientation orientation, DiscreteCoordinates position, boolean state) {
		super(area, orientation, position);
	}

	/*
	 * Setter for the state
	 */
	public void setState(boolean state) {
		this.state=state;
	}

	/*
	 * Setter for the sprite
	 */
	public void setSprite(Sprite sprite) {
		this.sprite=sprite;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	/*
	 * Setter for Collected
	 */
	public void setCollected(boolean state) {
		this.state = state;
	}

	/*
	 * Getter for Collected
	 */
	public boolean getCollected() {

		System.out.println(state);
		return state;
	}



	@Override
	public boolean takeCellSpace() {
		return !isOn();
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
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);		
	}

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.cs107.play.signal.logic.Logic#isOn()
	 */
	public boolean isOn(){
		return state;
	}


	@Override
	public void draw(Canvas canvas) {
		if(!isOn()) {
			sprite.draw(canvas);
		} else {
			System.exit(0);
		}
	}

}
