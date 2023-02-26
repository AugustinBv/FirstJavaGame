package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Gold;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level4 extends EnigmeArea {
		
		static String title = "Level4";
		private Door door;
		private Gold gold;
		private Gold gold1;
		private Gold gold2;
		private Gold gold3;
		private Gold gold4;
		private Gold gold5;
		private Gold gold6;
		private Gold gold7;
		private Gold gold8;
		private Gold gold9;

		
		
		public Level4() {
			super(title);
		}
		
		public boolean begin(Window window, FileSystem filesystem) {
			super.begin(window, filesystem);
			door = new Door(this, "LevelSelector", new DiscreteCoordinates(4,6), Orientation.DOWN, new DiscreteCoordinates(5,9));
			enterAreaCells(door, door.getCurrentCells());
			gold = new Gold(this, new DiscreteCoordinates(2,6));
			gold1 = new Gold(this, new DiscreteCoordinates(3,6));
			gold2 = new Gold(this, new DiscreteCoordinates(4,6));
			gold3 = new Gold(this, new DiscreteCoordinates(5,6));
			gold4 = new Gold(this, new DiscreteCoordinates(6,6));
			gold5 = new Gold(this, new DiscreteCoordinates(7,6));
			gold6 = new Gold(this, new DiscreteCoordinates(8,6));
			gold7 = new Gold(this, new DiscreteCoordinates(9,6));
			gold8 = new Gold(this, new DiscreteCoordinates(1,6));
			gold9 = new Gold(this, new DiscreteCoordinates(10,6));
			
			return true;
		}
		
	}


