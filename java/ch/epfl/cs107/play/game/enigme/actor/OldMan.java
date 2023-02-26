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
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;

public class OldMan extends Pnj {


	/**
	 * Constructor for OldMan
	 * @param area : the area the man is in
	 * @param orientation : the Orientation of the man
	 * @param position : the position of the man
	 */
	public OldMan(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		enterArea(area, position);
		sprite = new Sprite("old.man.1", 1, 1.3125f, this, new RegionOfInterest(16, 0*21, 16 , 21), Vector.ZERO);
	}


	public OldMan(Area area, DiscreteCoordinates position) {
		this(area, Orientation.LEFT, position);
	}


	/**
	 * puts the man in its area andd its position
	 * @param area : the area of the man
	 * @param position : the position to set the man in
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