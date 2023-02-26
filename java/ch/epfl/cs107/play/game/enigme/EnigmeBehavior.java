package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class EnigmeBehavior extends AreaBehavior {

	/**
	 * Constructor for EnigmeBehavior
	 * @param window : game window
	 * @param fileName : name of the behavior map
	 */
	public EnigmeBehavior(Window window, String fileName) {
		super(window, fileName);

		Cell[][] cells = getCells();
		for (int x = 0 ; x< getBehaviorMap().getWidth(); ++x) {
			for (int y = 0 ; y < getBehaviorMap().getHeight(); ++y) {
				EnigmeCellType cellType = EnigmeCellType.toType(getBehaviorMap().getRGB(getHeight()-1-y,x));
				cells[x][y] = new EnigmeCell(x, y, cellType);
				System.out.print(x + " " + y + cellType);
			}
			System.out.println();
		}



	}

	public enum EnigmeCellType {
		NULL(0),
		WALL ( -16777216), // RGB code of black
		DOOR(-65536), // RGB code of red
		WATER ( -16776961), // RGB code of blue
		INDOOR_WALKABLE(-1),
		OUTDOOR_WALKABLE ( -14112955);

		final int type;


		/**
		 * Constructor for EnigmeCellType
		 * @param a int corresponding to a type
		 */
		EnigmeCellType(int type){
			this.type = type ;
		}

		/**
		 * Getter for the type of cell type
		 * @return type : the type
		 */
		public int getType() {
			return type;
		}


		/**
		 * Converts a int into a EnigmeCellType
		 * @param type : the int to convert
		 * @return
		 */
		private static EnigmeCellType toType(int type) {
			for (EnigmeCellType val: EnigmeCellType.values()) {
				if (val.type == type) return val;
			}
			return NULL;
		}

	}



	public class EnigmeCell extends Cell {

		EnigmeCellType type;

		/**
		 * Constructor for EnigmeCell
		 * @param x : the x coordinate of the cell
		 * @param y : the y coordinate of the cell
		 * @param type : the type of the cell
		 */
		private EnigmeCell(int x, int y, EnigmeCellType type) {
			super(x,y);
			this.type = type;
		}

		@Override
		protected boolean canEnter(Interactable entity) {

			if (type == EnigmeCellType.NULL || type == EnigmeCellType.WALL) {				
				return false;
			}

			for (Interactable inter : interactable) {
				if (inter.takeCellSpace()) {
					return false;
				}
			}

			return true;
		}

		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}

		public EnigmeCellType getType() {
			return type;
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			((EnigmeInteractionVisitor)v).interactWith(this);
		}

	}


}	