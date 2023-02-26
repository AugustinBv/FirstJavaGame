package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.Collectable.Collectable;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Potion extends Collectable {

	private final Sprite sprite; 


	/**
	 * Constructor for Potion
	 * @param area : the area the potion is in
	 * @param orientation : the Orientation of the potion
	 * @param position : the position of the potion
	 */
	public Potion(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		enterArea(area, position);
		sprite = new Sprite("potion.5",  0.7f, 0.7f, this);
	}

	public Potion(Area area, DiscreteCoordinates position) {
		this(area, Orientation.DOWN, position);
	}


	/**
	 * puts the potion in its area andd its position
	 * @param area : the area of the potion
	 * @param position : the position to set the potion in
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
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}


}


