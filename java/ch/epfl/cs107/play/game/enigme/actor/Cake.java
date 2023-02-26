package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.hpcollectable.EndCollectable;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Cake extends EndCollectable{


	/**
	 * cake constructor
	 * @param area (Area) ; area the cake is set in
	 * @param orientation (Orientation) : Orientation the cake is set in
	 * @param position (DiscreteCoordinates) : position the cake is set in
	 */
	public Cake(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position, false);

		enterArea(area, position);
		sprite = new Sprite("cake.1", 1, 1.f, this);
	}


	public Cake(Area area, DiscreteCoordinates position) {
		this(area, Orientation.DOWN, position);
	}

	/**
	 * The cake enters the area
	 * @param area (Area) : area the cake is set in
	 * @param position (DiscreteCoordinates) : position the cake is set in the area
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