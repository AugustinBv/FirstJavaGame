package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class Demo2Behavior extends AreaBehavior {

	/**
	 * Behavior of the Demo2 game
	 * @param window (Window) : Window of the game
	 * @param fileName (String) : Name of behavior map
	 */
	public Demo2Behavior(Window window, String fileName) {
		super(window, fileName);

		Cell[][] cells = getCells();
		for (int x = 0 ; x< getBehaviorMap().getWidth(); ++x) {
			for (int y = 0 ; y < getBehaviorMap().getHeight(); ++y) {
				// JS correction
				Demo2CellType cellType = Demo2CellType.toType(getBehaviorMap().getRGB(getHeight()-1-y,x));
				cells[x][y] = new Demo2Cell(x, y, cellType);
				System.out.print(x + " " + y + cellType);
			}
			System.out.println();
		}



	}

	public enum Demo2CellType {
		NULL(0),
		WALL ( -16777216), // RGB code of black
		DOOR(-65536), // RGB code of red
		WATER ( -16776961), // RGB code of blue
		INDOOR_WALKABLE(-1),
		OUTDOOR_WALKABLE ( -14112955);

		final int type;

		/**
		 * Constructor
		 * @param type (Int) : one of the enum
		 */
		Demo2CellType(int type){
			this.type = type ;
		}

		/**
		 * getter
		 * @return type (Demo2CellType) : Type of the cell
		 */
		public int getType() {
			return type;
		}

		/**
		 * converts int into a Demo2CellType
		 * @param type
		 * @return
		 */
		private static Demo2CellType toType(int type) {
			for (Demo2CellType val: Demo2CellType.values()) {
				if (val.type == type) return val;
			}
			return NULL;
		}

	}



	public class Demo2Cell extends Cell {

		Demo2CellType type;

		/**
		 * Constructor
		 * @param x (int) : X of the cell
		 * @param y (int) : Y of the cell
		 * @param type (Demo2CellType) : Type of the cell
		 */
		private Demo2Cell(int x, int y, Demo2CellType type) {
			super(x,y);
			this.type = type;
		}

		@Override
		protected boolean canEnter(Interactable entity) {
			// TODO Auto-generated method stub

			if (type == Demo2CellType.NULL || type == Demo2CellType.WALL) {
				System.out.println(getCurrentCells());
				System.out.println(type);

				return false;
			}

			for (Interactable inter : interactable) {
				if (!inter.takeCellSpace()) {
					return false;
				}
			}

			return true;
		}

		@Override
		protected boolean canLeave(Interactable entity) {
			// TODO Auto-generated method stub
			return true;
		}

		/**
		 * getter
		 * @return type (Demo2CellType)
		 */
		public Demo2CellType getType() {
			return type;
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			// TODO Auto-generated method stub

		}

	}


}	