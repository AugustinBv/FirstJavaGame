package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Lava;
import ch.epfl.cs107.play.game.enigme.actor.Shoes;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level1 extends EnigmeArea {
	
	static String title = "Level1";
	private Door door;
	
	public Level1() {
		super(title);
	}
	
	public boolean begin(Window window, FileSystem filesystem) {
		super.begin(window, filesystem);
		door = new Door(this, "LevelSelector", new DiscreteCoordinates(1,6), Orientation.DOWN, new DiscreteCoordinates(5,0));
		enterAreaCells(door, door.getCurrentCells());
		return true;
	}
	
}
