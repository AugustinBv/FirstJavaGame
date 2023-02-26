package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.pnj.Pnj;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;


public class Death extends Pnj {

	/**
	 * Death constructor
	 * @param area (Area) : area the death is set in 
	 * @param orientation (Orientation) : Orientation the death is set in 
	 * @param position (DiscreteCoordinates) : position the death is set in 
	 */
	public Death(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		enterArea(area, position);
		sprite = new Sprite("death", 2, 2.7f, this, null , new Vector(-0.40f,0));
	}


	public Death(Area area, DiscreteCoordinates position) {
		this(area, Orientation.LEFT, position);
	}

	/**
	 * Places death in the area
	 * @param area
	 * @param position
	 */
	private void enterArea(Area area , DiscreteCoordinates position) {

		setOwnerArea(area);
		setCurrentPosition(position.toVector());
		area.registerActor(this);
		area.enterAreaCells(this, getCurrentCells());
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


}