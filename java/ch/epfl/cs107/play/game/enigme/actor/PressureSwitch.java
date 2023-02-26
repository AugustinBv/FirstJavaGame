package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.switchable.Switchable;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class PressureSwitch extends Switchable {



	/**
	 * Constructor for PressureSwitch
	 * @param area : the area the switch is in
	 * @param orientation : the Orientation of the switch
	 * @param position : the position of the switch
	 */
	public PressureSwitch(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position, false);
		enterArea(area, position);
		spriteOff = new Sprite("GroundLightOff", 1, 1.f, this);
		spriteOn = new Sprite("GroundLightOn", 1, 1.f, this);
	}

	public PressureSwitch(Area area, DiscreteCoordinates position) {
		this(area, Orientation.DOWN, position);
	}


	/**
	 * puts the switch in its area andd its position
	 * @param area : the area of the switch
	 * @param position : the position to set the switch in
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