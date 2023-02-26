package ch.epfl.cs107.play.game.enigme.actor;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.switchable.Switchable;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalDoor extends Switchable implements Logic{

	private Area signalDoorArea;
	private String nextArea;
	private DiscreteCoordinates arrivalPosition;
	private Orientation orientation;
	private DiscreteCoordinates mainPosition;
	private LinkedList<DiscreteCoordinates> signalDoorCoordinates;
	private Sprite sprite;
	private Logic signal;
	private Key key;




	/**
	 * Constructor for SignalDoor
	 * @param area : the area the door is in
	 * @param orientation : the Orientation of the door
	 * @param position : the position of the door
	 */
	public SignalDoor(Area signalDoorArea, String nextArea, DiscreteCoordinates arrivalPosition, Orientation orientation, DiscreteCoordinates mainPosition,
			Logic signal, Key key, DiscreteCoordinates... otherCoordinates) {
		super(signalDoorArea, orientation, mainPosition, signal.isOn());
		signalDoorCoordinates = new LinkedList<DiscreteCoordinates>();
		this.signalDoorArea = signalDoorArea;
		this.nextArea = nextArea;
		this.arrivalPosition = arrivalPosition;
		this.orientation = orientation;
		this.mainPosition = mainPosition;
		signalDoorCoordinates.add(mainPosition);
		for(DiscreteCoordinates a : otherCoordinates) {
			signalDoorCoordinates.add(a);
		}

		this.key=key;
		this.signal=signal;

		sprite = new Sprite("door.close.1", 1, 1.f, this);

	}




	/**
	 * get the signal given by the key associated with the door
	 * @return the signal from the key
	 */
	protected Logic getSignal() {
		return key.getSignal();
	}



	public void update(float deltaTime) {
		super.update(deltaTime);

		if (getSignal().isOn()) {
			this.getSignalDoorArea().unregisterActor(this);
		}
	}


	@Override
	public  void draw(Canvas canvas) {
		sprite.draw(canvas);
	}




	/**
	 * setter for the signaldoor area
	 * @param a : the area to set to the signal door
	 */
	protected void setSignalDoorArea(Area a) {
		signalDoorArea = a;
	}

	/**
	 * getter for the area of the signal door
	 * @return the signal door area
	 */
	public Area getSignalDoorArea() {
		return signalDoorArea;
	}


	/**
	 * puts the signal door in its area andd its position
	 * @param area : the area of the door
	 * @param position : the position to set the door in
	 */
	public void enterArea(Area area , DiscreteCoordinates position) {
		setOwnerArea(area);
		setCurrentPosition(position.toVector());
		area.registerActor(this);
		area.enterAreaCells(this, getCurrentCells());
	}





	/**
	 * getter for the mainposition of the signal door
	 * @return the mainposition of the door 
	 */
	public DiscreteCoordinates getMainPosition() {
		return mainPosition;
	}


	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return signalDoorCoordinates;
	}

	@Override
	public boolean takeCellSpace() {
		if (getSignal().isOn()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		if (getSignal().isOn()) {
			return true;
		}
		return false;
	}


	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this) ;
	}

}