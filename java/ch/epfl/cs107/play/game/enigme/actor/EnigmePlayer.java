package ch.epfl.cs107.play.game.enigme.actor;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.Demo2;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class EnigmePlayer extends MovableAreaEntity implements Interactor {

	private boolean goThroughDoor;
	private Window window;
	/// Animation duration in frame number
	private final static int ANIMATION_DURATION = 8;
	private int speed;
	private final Sprite sprite;


	private Door lastPassedDoor;
	private final EnigmePlayerHandler handler;
	private boolean shoe;
	private int hp = 100;
	private int gold = 0;
	private TextGraphics textHP;
	private TextGraphics textGold;
	private Animation animation;
	private int movement;
	private int maxMovement;
	private boolean running;
	private boolean deathInteract;
	private boolean oldInteract;
	private boolean deathInteract2;
	private boolean pannelInteract;
	private boolean gameOver;


	/**
	 * Constructor for EnigmePlayer
	 * @param area : the area the player is in
	 * @param orientation : the Orientation of the player
	 * @param position : the position of the player
	 */
	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		enterArea(area, coordinates);
		goThroughDoor = false;
		sprite = new Sprite("ghost.1", 1, 1.f, this);
		handler = new EnigmePlayerHandler();
		shoe = false;
		animation = new Animation("max.new.1", 0.8f, 0.925f, this);
		movement = 0;
		running = false;
		deathInteract = false;
		oldInteract = false;
		deathInteract2 = false;
		pannelInteract = false;
	}

	public EnigmePlayer(Area area, DiscreteCoordinates coordinates) {
		this(area, Orientation.DOWN, coordinates);
	}


	/**
	 * puts the player in its area andd its position
	 * @param area : the area of the player
	 * @param position : the position to set the player in
	 */
	public void enterArea(Area area, DiscreteCoordinates position) {

		setOwnerArea(area);
		setCurrentPosition(position.toVector());
		resetMotion();
		area.registerActor(this);
		goThroughDoor = false;

	}


	/**
	 * removes the player from its area
	 * @param area : the area of the player
	 */
	public void leaveArea(Area area) {
		area.leaveAreaCells(this, getLeavingCells());
	}




	/**
	 * @return if the player is going through a door
	 */
	public boolean isGoingThrough() {
		return goThroughDoor;
	}



	/**
	 * sets variables when a player passes a door
	 * @param door
	 */
	private void setIsPassingDoor(Door door) {
		goThroughDoor = true;
		lastPassedDoor = door;
	}

	/**
	 * getter for the last past door
	 * @return
	 */
	public Door passedDoor() {
		return lastPassedDoor;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}



	/**
	 * checks if the plalyer is leaving pressure on a switch
	 * @param pressure
	 * @return true if the player  is leaving pressure
	 */
	public boolean setIsLeavingPressure(PressureSwitch pressure) {
		if(this.getCurrentMainCellCoordinates().equals(pressure.getCurrentMainCellCoordinates()) && this.getIsMoving()) {
			return true;
		}
		return false;
	}

	/**
	 * checks if the plalyer is leaving pressure on a plate
	 * @param pressure
	 * @return true if the player  is leaving pressure
	 */
	public boolean setIsLeavingPressure(PressurePlate plate) {
		if(this.getCurrentMainCellCoordinates().equals(plate.getCurrentMainCellCoordinates()) && this.getIsMoving()) {
			return true;
		}
		return false;
	}

	public void update(float deltaTime) {

		super.update(deltaTime);

		textHP = new TextGraphics("HP : " + hp + " /100", 0.3f, Color.GREEN);
		textGold = new TextGraphics("GOLD : " + gold, 0.3f, Color.YELLOW);
		textGold.setParent(this);
		textHP.setParent(this);
		textHP.setAnchor(new Vector(-0.4f, 1f));
		textGold.setAnchor(new Vector(-7f, -3f));

		Keyboard keyboard = getOwnerArea().getKeyboard();
		Button downArrow = keyboard.get(Keyboard.DOWN);
		Button upArrow = keyboard.get(Keyboard.UP);
		Button leftArrow = keyboard.get(Keyboard.LEFT);
		Button rightArrow = keyboard.get(Keyboard.RIGHT);
		Button sKey = keyboard.get(Keyboard.S);

		if(hp<=0) {
			gameOver = true;
		}

		if(!deathInteract && !deathInteract2 && !oldInteract && !pannelInteract) {
			if(sKey.isDown() && shoe) {
				speed = ANIMATION_DURATION/2;
				maxMovement = 8;
				running = true;
			} else { 
				speed = ANIMATION_DURATION;
				maxMovement = 16;
				running = false;
			}

			if (downArrow.isLastPressed() && getOrientation() == Orientation.DOWN) {
				move(speed);
			} else if (downArrow.isLastPressed() && getOrientation() != Orientation.DOWN) {
				setOrientation(Orientation.DOWN);
			}

			if (upArrow.isLastPressed() && getOrientation() == Orientation.UP) {
				move(speed);
			} else if (upArrow.isLastPressed() && getOrientation() != Orientation.UP) {
				setOrientation(Orientation.UP);
			}

			if (leftArrow.isLastPressed() && getOrientation() == Orientation.LEFT) {
				move(speed);
			} else if (leftArrow.isLastPressed() && getOrientation() != Orientation.LEFT) {
				setOrientation(Orientation.LEFT);
			}

			if (rightArrow.isLastPressed() && getOrientation() == Orientation.RIGHT) {
				move(speed);
			} else if (rightArrow.isLastPressed() && getOrientation() != Orientation.RIGHT) {
				setOrientation(Orientation.RIGHT);
			}
		}
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
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		if(movement<maxMovement-1) {
			movement++;
		} else {
			movement = 0;
		}
		if(!getIsMoving()) {
			if(getOrientation() == Orientation.DOWN) {
				animation.getSprite(0,0).draw(canvas);
			}
			if(getOrientation() == Orientation.LEFT) {
				animation.getSprite(1, 0).draw(canvas);
			}
			if(getOrientation() == Orientation.UP) {
				animation.getSprite(2, 0).draw(canvas);
			}
			if(getOrientation() == Orientation.RIGHT) {
				animation.getSprite(3, 0).draw(canvas);
			}
		}else {
			if(getOrientation() == Orientation.DOWN && !running) {
				if(movement == 0 || movement == 1 || movement == 2 || movement == 3) {
					animation.getSprite(0,0).draw(canvas);
				} else if(movement == 4 || movement == 5 || movement == 6 || movement == 7) {
					animation.getSprite(0, 1).draw(canvas);
				} else if(movement == 8 || movement == 9 || movement == 10 || movement == 11) {
					animation.getSprite(0, 2).draw(canvas);
				} else if(movement == 12|| movement == 13 || movement == 14 || movement == 15) {
					animation.getSprite(0,3).draw(canvas);
				}

			}
			if(getOrientation() == Orientation.LEFT && !running) {
				if(movement == 0 || movement == 1 || movement == 2 || movement == 3) {
					animation.getSprite(1,0).draw(canvas);
				} else if(movement == 4 || movement == 5 || movement == 6 || movement == 7) {
					animation.getSprite(1, 1).draw(canvas);
				} else if(movement == 8 || movement == 9 || movement == 10 || movement == 11) {
					animation.getSprite(1, 2).draw(canvas);
				} else if(movement == 12 || movement == 13 || movement == 14 || movement == 15) {
					animation.getSprite(1,3).draw(canvas);
				}

			}
			if(getOrientation() == Orientation.UP && !running) {
				if(movement == 0 || movement == 1 || movement == 2 || movement == 3) {
					animation.getSprite(2,0).draw(canvas);
				} else if(movement == 4 || movement == 5 || movement == 6 || movement == 7) {
					animation.getSprite(2, 1).draw(canvas);
				} else if(movement == 8 || movement == 9 || movement == 10 || movement == 11) {
					animation.getSprite(2, 2).draw(canvas);
				} else if(movement == 12 || movement == 13 || movement == 14 || movement == 15) {
					animation.getSprite(2,3).draw(canvas);
				}

			}
			if(getOrientation() == Orientation.RIGHT && !running) {
				if(movement == 0 || movement == 1 || movement == 2 || movement == 3) {
					animation.getSprite(3,0).draw(canvas);
				} else if(movement == 4 || movement == 5 || movement == 6 || movement == 7) {
					animation.getSprite(3, 1).draw(canvas);
				} else if(movement == 8 || movement == 9 || movement == 10 || movement == 11) {
					animation.getSprite(3, 2).draw(canvas);
				} else if(movement == 12 || movement == 13 || movement == 14 || movement == 15) {
					animation.getSprite(3,3).draw(canvas);
				}
			} else {
				if(getOrientation() == Orientation.DOWN && running) {
					if(movement == 0 || movement == 1) {
						animation.getSprite(0,0).draw(canvas);
					} else if(movement == 2 || movement == 3) {
						animation.getSprite(0, 1).draw(canvas);
					} else if(movement == 4 || movement == 5) {
						animation.getSprite(0, 2).draw(canvas);
					} else if(movement == 6 || movement == 7) {
						animation.getSprite(0,3).draw(canvas);
					}

				}
				if(getOrientation() == Orientation.LEFT && running) {
					if(movement == 0 || movement == 1) {
						animation.getSprite(1,0).draw(canvas);
					} else if(movement == 2 || movement == 3) {
						animation.getSprite(1, 1).draw(canvas);
					} else if(movement == 4 || movement == 5) {
						animation.getSprite(1, 2).draw(canvas);
					} else if(movement == 6 || movement == 7) {
						animation.getSprite(1,3).draw(canvas);
					}

				}
				if(getOrientation() == Orientation.UP && running) {
					if(movement == 0 || movement == 1) {
						animation.getSprite(2,0).draw(canvas);
					} else if(movement == 2 || movement == 3) {
						animation.getSprite(2, 1).draw(canvas);
					} else if(movement == 4 || movement == 5) {
						animation.getSprite(2, 2).draw(canvas);
					} else if(movement == 6 || movement == 7) {
						animation.getSprite(2,3).draw(canvas);
					}

				}
				if(getOrientation() == Orientation.RIGHT && running) {
					if(movement == 0 || movement == 1) {
						animation.getSprite(3,0).draw(canvas);
					} else if(movement == 2 || movement == 3) {
						animation.getSprite(3, 1).draw(canvas);
					} else if(movement == 4 || movement == 5) {
						animation.getSprite(3, 2).draw(canvas);
					} else if(movement == 6 || movement == 7) {
						animation.getSprite(3,3).draw(canvas);
					}
				}
			}
		}

		textHP.draw(canvas);
		textGold.draw(canvas);
	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
	}

	@Override
	public boolean wantsCellInteraction() {
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		Keyboard keyboard = getOwnerArea().getKeyboard();
		Button keyL = keyboard.get(Keyboard.L);
		if (keyL.isPressed()) {
			return true;
		}
		return false;
	}

	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	public int getHP() {
		return hp;
	}

	public int getGold() {
		return gold;
	}

	protected void setGold(int newGold) {
		this.gold += newGold;
	}

	public boolean getDeathInteract() {
		return deathInteract;
	}

	public boolean getDeathInteract2() {
		return deathInteract2;
	}

	public boolean getOldInteract() {
		return oldInteract;
	}

	public boolean getPannelInteract() {
		return pannelInteract;
	}

	/**
	 * Specific interaction handler for an EnigmePlayer
	 */
	private class EnigmePlayerHandler implements EnigmeInteractionVisitor {

		@Override
		public void interactWith(Door door) {
			setIsPassingDoor(door);
		}

		@Override
		public void interactWith(Apple apple) {
			apple.setCollected(true);
		}

		@Override
		public void interactWith(Key key) {
			key.setCollected(true);
		}

		public void interactWith(ElderWand wand) {
			wand.setCollected(true);
		}

		public void interactWith(InvisibilityCape cape) {
			cape.setCollected(true);
		}

		public void interactWith(Cake cake) {
			cake.setCollected(true);
		}

		public void interactWith(ResurrectionStone stone) {
			stone.setCollected(true);
		}


		public void interactWith(Death death) {
			if(getDeathInteract()) {
				deathInteract = false;
			} else {
				deathInteract = true;
			}
		}

		public void interactWith(OldMan old) {
			if(getOldInteract()) {
				oldInteract = false;
			} else {
				oldInteract = true;
			}
		}


		public void interactWith(Pannel pannel) {
			if(getPannelInteract()) {
				pannelInteract = false;
			} else {
				pannelInteract = true;
			}
		}

		@Override
		public void interactWith(Torch torch) {
			torch.setSwitched(!torch.getSwitched());
		}

		@Override
		public void interactWith(Lever lever) {
			lever.setSwitched(!lever.getSwitched());
		}
		@Override
		public void interactWith(PressureSwitch pressure) {
			if (setIsLeavingPressure(pressure)) {
				pressure.setSwitched(!pressure.getSwitched());
			}
		}

		@Override
		public void interactWith(PressurePlate plate) {
			if (setIsLeavingPressure(plate)) {
				plate.setSwitched(!plate.getSwitched());
			}
		}

		public void interactWith(EnigmePlayer player) {
		}

		public void interactWith(Lava lava) {
			hp -= 1;
			if(hp<=30) {
				lava.setHarmed(true);
			}

		}

		public void interactWith(Shoes shoes) {
			shoes.setCollected(true);
			shoe = true;
		}

		public void interactWith(Gold gold) {
			gold.setCollected(true);
			setGold(20);
		}

		public void interactWith(Potion potion) {
			if(gold>=30 && hp<100) {
				gold -= 30;
				hp += 20;
				if(hp > 100) {
					hp = 100;
				}
			}
		}

	}

}
