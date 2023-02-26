package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level2 extends EnigmeArea {
	
	static String title = "Level2";
	private Door door;
	private Apple apple;
	
	public Level2() {
		super(title);
	}
	
	public boolean begin(Window window, FileSystem filesystem) {
		super.begin(window, filesystem);
		door = new Door(this, "LevelSelector", new DiscreteCoordinates(2,6), Orientation.DOWN, new DiscreteCoordinates(5,0));
		enterAreaCells(door, door.getCurrentCells());
		apple = new Apple(this, new DiscreteCoordinates(3, 5));
		return true;
	}
	
}
