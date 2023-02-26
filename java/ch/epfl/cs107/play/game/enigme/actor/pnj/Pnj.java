package ch.epfl.cs107.play.game.enigme.actor.pnj;

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

public class Pnj extends AreaEntity implements Logic, Interactable{

	private boolean state = false;
	protected Sprite sprite;

	/**
	 * Pnj constructor
	 * @param area (Area) : area the pnj is set in
	 * @param orientation (Orientation) : orientation the pnj is set in
	 * @param position (DiscreteCoordinates) : position de pnj is set in
	 */
	public Pnj(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
	}

	/**
	 * setter for state
	 * @param state (boolean)
	 */
	public void setState(boolean state) {
		this.state=state;
	}

	/**
	 * setter for sprite
	 * @param sprite (Sprite) : sprite the pnj is set in
	 */
	public void setSprite(Sprite sprite) {
		this.sprite=sprite;
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
		sprite.draw(canvas);
	}
}
