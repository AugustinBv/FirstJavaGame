package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicNumber;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Normal;
import ch.epfl.cs107.play.signal.logic.Or;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea implements Logic{
	
	static String title = "Level3";
	private SignalDoor door;
	private Door door1;
	private Key key;
	private Torch torch;
	private Lever lever1;
	private Lever lever2;
	private Lever lever3;
	private PressureSwitch pressure1;
	private PressureSwitch pressure2;
	private PressureSwitch pressure3;
	private PressureSwitch pressure4;
	private PressureSwitch pressure5;
	private PressureSwitch pressure6;
	private PressureSwitch pressure7;
	private PressurePlate plate;
	private SignalRock rock1;
	private SignalRock rock2;
	private SignalRock rock3;

	
	
	public Level3() {
		super(title);
	}
	
	
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		key = new Key(this, new DiscreteCoordinates(1,3));
		
		door1 = new Door(this, "LevelSelector", new DiscreteCoordinates(3,6), Orientation.DOWN, new DiscreteCoordinates(5,9));
		door1.enterArea(this, door1.getMainPosition());
		door = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(3,6), Orientation.DOWN, new DiscreteCoordinates(5,9), key.getSignal(), key);
		door.enterArea(this, door.getMainPosition());
		
		torch = new Torch(this, new DiscreteCoordinates(7,5));
		enterAreaCells(torch, torch.getCurrentCells());
		
		lever1 = new Lever(this, new DiscreteCoordinates(10,5));
		enterAreaCells(lever1, lever1.getCurrentCells());
		lever2 = new Lever(this, new DiscreteCoordinates(9,5));
		enterAreaCells(lever2, lever2.getCurrentCells());
		lever3 = new Lever(this, new DiscreteCoordinates(8,5));
		enterAreaCells(lever3, lever3.getCurrentCells());
		
		pressure1 = new PressureSwitch(this, new DiscreteCoordinates(4,4));
		enterAreaCells(pressure1, pressure1.getCurrentCells());
		pressure2 = new PressureSwitch(this, new DiscreteCoordinates(5,4));
		enterAreaCells(pressure2, pressure2.getCurrentCells());
		pressure3 = new PressureSwitch(this, new DiscreteCoordinates(6,4));
		enterAreaCells(pressure3, pressure3.getCurrentCells());
		pressure4 = new PressureSwitch(this, new DiscreteCoordinates(5,5));
		enterAreaCells(pressure4, pressure4.getCurrentCells());
		pressure5 = new PressureSwitch(this, new DiscreteCoordinates(4,6));
		enterAreaCells(pressure5, pressure5.getCurrentCells());
		pressure6 = new PressureSwitch(this, new DiscreteCoordinates(5,6));
		enterAreaCells(pressure6, pressure6.getCurrentCells());
		pressure7 = new PressureSwitch(this, new DiscreteCoordinates(6,6));
		enterAreaCells(pressure7, pressure7.getCurrentCells());

		plate = new PressurePlate(this, new DiscreteCoordinates(9,8));
		enterAreaCells(plate, plate.getCurrentCells());
		
		Logic signalRock1 = new Normal(plate);
		Logic signalRock2 = new Or(new LogicNumber(5,lever1, lever2, lever3), torch);
		Logic signalRock3 = new MultipleAnd(pressure1, pressure2, pressure3, pressure4, pressure5, pressure6, pressure7);
		
		
		rock1 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(6,8), signalRock1);
		enterAreaCells(rock1, rock1.getCurrentCells());
		rock2 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(5,8), signalRock2);
		enterAreaCells(rock2, rock2.getCurrentCells());
		rock3 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(4,8), signalRock3);
		enterAreaCells(rock3, rock3.getCurrentCells());
		
		return true;
	}
	
}
