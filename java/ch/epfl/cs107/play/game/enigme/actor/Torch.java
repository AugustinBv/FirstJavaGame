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

public class Torch extends Switchable{

	/**
	 * Constructor for Torch
	 * @param area : the area the torch is in
	 * @param orientation : the Orientation of the torch
	 * @param position : the position of the torch
	 */
	public Torch(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position, false);
		enterArea(area, position);
		spriteOff = new Sprite("torch.ground.off", 1, 1.f, this);
		spriteOn = new Sprite("torch.ground.on.1", 1, 1.f, this);
	}

	public Torch(Area area, DiscreteCoordinates position) {
		this(area, Orientation.DOWN, position);
	}


	/**
	 * puts the torch in its area andd its position
	 * @param area : the area of the torch
	 * @param position : the position to set the Torch in
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