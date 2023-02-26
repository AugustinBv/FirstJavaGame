package ch.epfl.cs107.play.game.enigme.actor.TimedSwitchable;

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
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class TimedSwitchable extends AreaEntity implements Logic, Interactable{

	private boolean state = false;
	protected Sprite spriteOn;
	protected Sprite spriteOff;
	protected Sprite sprite;
	private int i = 0;

	/**
	 * TimedSwitchable constructor
	 * @param area (Area) : area where the object is set in
	 * @param orientation (Orientation) : orientation in which the object is set in
	 * @param position (DiscreteCoordinates) : position the object is set in
	 * @param state (Boolean) : state the object is set in
	 */
	public TimedSwitchable(Area area, Orientation orientation, DiscreteCoordinates position, boolean state) {
		super(area, orientation, position);
		sprite = new Sprite("GroundPlateOff", 1, 1.f, this);
	}


	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	/**
	 * setter for switched
	 * @param state
	 */
	public void setSwitched(boolean state) {
		this.state = state;
	}

	/**
	 * getter for switched
	 * @return
	 */
	public boolean getSwitched() {
		System.out.println(state);
		return state;
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

	public boolean isOn(){
		return state;
	}


	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		if(isOn()) {
			sprite = new Sprite("GroundLightOn" , 1, 1.f, this);
			i++;
			if (i > 3*(1/deltaTime)) {
				i=0;
				sprite = new Sprite("GroundPlateOff", 1, 1.f, this);
				setSwitched(false);
			}
		}
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}
}
