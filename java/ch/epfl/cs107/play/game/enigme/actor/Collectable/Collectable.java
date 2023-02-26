package ch.epfl.cs107.play.game.enigme.actor.Collectable;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Collectable extends AreaEntity {
	

	/**
	 * Collectable constructor
	 * @param area (Area) : Area in which the collectable is put
	 * @param orientation (Orientation) : Orientation of the collectable at the beginning
	 * @param position (DiscreteCoordinates) : Position of the collectable in the Area
	 */
	public Collectable(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
	}
	
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return this.getCurrentCells();
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


	@Override
	public void draw(Canvas canvas) {
	}


}
