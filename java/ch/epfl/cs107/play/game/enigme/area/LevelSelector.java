package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Door2;
import ch.epfl.cs107.play.game.enigme.actor.Pannel;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class LevelSelector extends EnigmeArea {
	
	static String title = "LevelSelector";
	private Door door1;
	private Door door2;
	private Door door3;
	private Door door4;
	private Door door5;
	private Door2 door6;
	private Door2 door7;
	private Door2 door8;
	
	
	public LevelSelector() {
		super(title);
	}
	
	public boolean begin(Window window, FileSystem filesystem) {
		super.begin(window, filesystem);
		door1 = new Door(this, "Level1", new DiscreteCoordinates(1,3), Orientation.DOWN, new DiscreteCoordinates(1,7));
		door1.enterArea(this, door1.getMainPosition());
		door2 = new Door(this, "Level2", new DiscreteCoordinates(1,3), Orientation.DOWN, new DiscreteCoordinates(2,7));
		door2.enterArea(this, door2.getMainPosition());
		door3 = new Door(this, "Level3", new DiscreteCoordinates(5,1), Orientation.DOWN, new DiscreteCoordinates(3,7));
		door3.enterArea(this, door3.getMainPosition());
		door4 = new Door(this, "Level4", new DiscreteCoordinates(5,1), Orientation.DOWN, new DiscreteCoordinates(4,7));
		door4.enterArea(this, door4.getMainPosition());
		door5 = new Door(this, "Brothers", new DiscreteCoordinates(20, 1), Orientation.DOWN, new DiscreteCoordinates(5,7));
		door5.enterArea(this, door5.getMainPosition());
		door6 = new Door2(this, "", new DiscreteCoordinates(5,5), Orientation.DOWN, new DiscreteCoordinates(6,7));
		door6.enterArea(this, door6.getMainPosition());
		door7 = new Door2(this, "", new DiscreteCoordinates(5,5), Orientation.DOWN, new DiscreteCoordinates(7,7));
		door7.enterArea(this, door7.getMainPosition());
		door8 = new Door2(this, "", new DiscreteCoordinates(5,5), Orientation.DOWN, new DiscreteCoordinates(8,7));
		door8.enterArea(this, door8.getMainPosition());
		return true;
	}
	
	
}
