package ch.epfl.cs107.play.game.enigme.actor;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Door extends AreaEntity {

	private Area doorArea;
	private String nextArea;
	private DiscreteCoordinates arrivalPosition;
	private Orientation orientation;
	private DiscreteCoordinates mainPosition;
	private LinkedList<DiscreteCoordinates> doorCoordinates;
	private Sprite sprite;

	/**
	 * Door constructor
	 * @param doorArea (Area) : Area the door is set in
	 * @param nextArea (Area) : Area the door leads to
	 * @param arrivalPosition (Discrete Coordinates) : Position where the door leads to in the nextArea
	 * @param orientation (Orientation) : Orientation the door is set in
	 * @param mainPosition (DiscreteCoordinates) : Position the door is set in
	 * @param otherCoordinates (DiscreteCoordinates) : other positions the door could occupy
	 */
	public Door(Area doorArea, String nextArea, DiscreteCoordinates arrivalPosition, Orientation orientation, DiscreteCoordinates mainPosition,
			DiscreteCoordinates... otherCoordinates) {
		super(doorArea, orientation, mainPosition);
		doorCoordinates = new LinkedList<DiscreteCoordinates>();
		this.doorArea = doorArea;
		this.nextArea = nextArea;
		this.arrivalPosition = arrivalPosition;
		this.orientation = orientation;
		this.mainPosition = mainPosition;
		doorCoordinates.add(mainPosition);
		for(DiscreteCoordinates a : otherCoordinates) {
			doorCoordinates.add(a);
		}
		sprite = new Sprite("door.open.1", 1, 1.f, this);


		// TODO Vérifier l'histoire des différentes coordonnées qu'occupe la door (wtf dude ?)
	}


	// Getters & Setters for doors


	public String getNextArea() {
		return nextArea;
	}

	public DiscreteCoordinates getArrivalPosition() {
		return arrivalPosition;
	}

	public DiscreteCoordinates getMainPosition() {
		return mainPosition;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return doorCoordinates;
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

	/**
	 * Puts the door in the area 
	 * @param area (Area) : area the door is set in
	 * @param position (DiscreteCoordinates) : where the door is in this area
	 */
	public void enterArea(Area area , DiscreteCoordinates position) {

		setOwnerArea(area);
		setCurrentPosition(position.toVector());
		area.registerActor(this);
		area.enterAreaCells(this, getCurrentCells());

	}


	@Override
	public  void draw(Canvas canvas) {
		sprite.draw(canvas);
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this) ;
	}

}
