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

public class Lever extends Switchable{


	/**
	 * Constructor for Lever
	 * @param area : the area the lever is in
	 * @param orientation : the Orientation of the lever
	 * @param position : the position of the lever
	 */
	public Lever(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position, false);
		enterArea(area, position);
		spriteOff = new Sprite("lever.big.left", 1, 1.f, this);
		spriteOn = new Sprite("lever.big.right", 1, 1.f, this);
	}

	public Lever(Area area, DiscreteCoordinates position) {
		this(area, Orientation.DOWN, position);
	}



	/**
	 * puts the lever in its area andd its position
	 * @param area : the area of the lever
	 * @param position : the position to set the lever in
	 */
	private void enterArea(Area area , DiscreteCoordinates position) {

		setOwnerArea(area);
		setCurrentPosition(position.toVector());
		area.registerActor(this);
		area.enterAreaCells(this, getCurrentCells());
	}




	//	public boolean getSwitched() {
	//		return state;
	//	}
	//	
	//	protected void setSwitched(boolean state) {
	//		this.state = state;
	//	}
	//	
	//	
	//	public void update(float deltaTime) {
	//		if(isOn()) {
	//			sprite = new Sprite("Lever.ground.on.1" , 1, 1.f, this);
	//			//set signal on ON
	//		} else {
	//			sprite = new Sprite("Lever.ground.off" , 1, 1.f, this);
	//			//set signal on OFF
	//		}
	//	}

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

	//	@Override
	//	public void draw(Canvas canvas) {
	//		sprite.draw(canvas);
	//	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}

}