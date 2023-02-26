package ch.epfl.cs107.play.game.enigme.actor;

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
import ch.epfl.cs107.play.window.Canvas;

public class Pannel extends AreaEntity implements Interactable {

	private Sprite sprite;


	/**
	 * Constructor for Pannel
	 * @param area : the area the pannel is in
	 * @param orientation : the Orientation of the pannel
	 * @param position : the position of the pannel
	 */
	public Pannel(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		enterArea(area, position);
		sprite = new Sprite("sign", 1, 1.f, this);
	}

	public Pannel(Area area, DiscreteCoordinates position) {
		this(area, Orientation.DOWN, position);
	}


	/**
	 * puts the pannel in its area andd its position
	 * @param area : the area of the pannel
	 * @param position : the position to set the pannel in
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