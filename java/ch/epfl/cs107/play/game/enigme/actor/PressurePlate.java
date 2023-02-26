package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.TimedSwitchable.TimedSwitchable;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class PressurePlate extends TimedSwitchable {


	/**
	 * Constructor for PressurePlate
	 * @param area : the area the plate is in
	 * @param orientation : the Orientation of the plate
	 * @param position : the position of the plate
	 */
	public PressurePlate(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position, false);
		enterArea(area, position);
		spriteOff = new Sprite("GroundPlateOff", 1, 1.f, this);
		spriteOn = new Sprite("GroundLightOn", 1, 1.f, this);
	}

	public PressurePlate(Area area, DiscreteCoordinates position) {
		this(area, Orientation.DOWN, position);
	}



	/**
	 * puts the plate in its area andd its position
	 * @param area : the area of the plate
	 * @param position : the position to set the plate in
	 */
	public void enterArea(Area area , DiscreteCoordinates position) {

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
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}


	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}

}