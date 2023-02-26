package ch.epfl.cs107.play.game.areagame;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior {

	/// The behavior is an Image of size height x width
	private final Image behaviorMap ;
	private final int width, height ;
	/// We will convert the image into an array of cells
	private final Cell[][] cells ;




	/**
	 * Each game will have its own Cell extension.
	 */
	public abstract class Cell implements Interactable{
		public Set<Interactable> interactable;
		public final DiscreteCoordinates coord;

		/**
		 * constructor for the class Cell
		 * @param x (int) : x coordinate of the cell
		 * @param y (int) : y coordinate of the cell
		 */
		public Cell (int x, int y) {
			coord = new DiscreteCoordinates(x,y);
			interactable = new HashSet<>();
		}


		/**
		 * adds an interactable to the cell
		 * @param entity (Interactable) : entity to put in the cell
		 */
		private void enter(Interactable entity) {
			interactable.add(entity);
		}


		/**
		 * removes an interactable from the cell
		 * @param entity (Interactable) : entity to remove from the cell
		 */
		private void leave(Interactable entity) {
			interactable.remove(entity);
		}

		/**
		 * checks if the entity can enter the cell
		 * @param entity (Interactable) : the entity that wants to enter the cell
		 * @return boolean : true if the entity can enter the cell
		 */
		protected abstract boolean canEnter(Interactable entity);


		/**
		 * checks if the entity can leave the cell
		 * @param entity (Interactable) : the entity that wants to leave the cell
		 * @return boolean : true if the entity can leave the cell
		 */
		protected abstract boolean canLeave(Interactable entity);


		@Override
		public List<DiscreteCoordinates> getCurrentCells() {

			List<DiscreteCoordinates> cellsCoord = new LinkedList<>();
			cellsCoord.add(coord);

			return cellsCoord;
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
			return true;
		}


		/**
		 * lets the interactor interact with contact with the interactable if it accepts cell interactions
		 * @param interactor (Interactor) :  the interactor that wants to interact with the interactable
		 */
		private void cellInteractionOf(Interactor interactor) {
			for(Interactable interactable : interactable){
				if(interactable.isCellInteractable())
					interactor.interactWith(interactable);
			}
		}

		/**
		 * lets the interactor interact from a distance with the interactable if it accepts view interactions
		 * @param interactor (Interactor) : the interactor that wants to interact with the interactable
		 */
		private void viewInteractionOf(Interactor interactor) {
			for(Interactable interactable : interactable){
				if(interactable.isViewInteractable())
					interactor.interactWith(interactable);
			}
		}
	}
	//END OF CELL


	/**
	 * lets the interactor interact with contact with the cells if they accept cell interactions
	 * @param interactor (Interactor) : the interactor that wants to interact with the cells
	 */
	public void cellInteractionOf(Interactor interactor) {
		for(DiscreteCoordinates coord : interactor.getCurrentCells()) {
			cells[coord.x][coord.y].cellInteractionOf(interactor);
		}
	}


	/**
	 * lets the interactor interact from a distance with the cells if they accept view interactions
	 * @param interactor (Interactor) : the interactor that wants to interact with the cells
	 */
	public void viewInteractionOf(Interactor interactor) {
		for(DiscreteCoordinates coord : interactor.getFieldOfViewCells()) {
			cells[coord.x][coord.y].viewInteractionOf(interactor);
		}
	}


	/**
	 * adds the entity to the areabehavior
	 * @param entity (Interactable) : the entity to add
	 * @param coordinates (List<DiscreteCoordinates>) : the coordinates of the cells in which to add the entity
	 */
	protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {

		for(DiscreteCoordinates coordinate : coordinates) {
			cells[coordinate.x][coordinate.y].enter(entity);
		}
	}


	/**
	 * removes the entity from the areabehavior
	 * @param entity (Interactable) : the entity to remove
	 * @param coordinates (List<DiscreteCoordinates>) : the coordinates of the cells from which to remove the entity
	 */
	protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for(DiscreteCoordinates coordinate : coordinates) {
			cells[coordinate.x][coordinate.y].leave(entity);
		}
	}


	/**
	 * Checks if an entity can enter an areabehavior
	 * @param entity : the entity that wants to enter
	 * @param coordinates : the coordinates of the cells of the area behavior
	 * @return true if the entity can enter the area behavior 
	 */
	public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates) {
		System.out.println("coordinates " + coordinates.get(0));
		for (DiscreteCoordinates position : coordinates) {
			if (position.getX()<0 || position.getY()<0 
					|| position.getX()>= width || position.getY()>height 
					|| !cells[position.x][position.y].canEnter(entity)) {
				return false;
			}		
		}
		return true;
	}


	/**
	 * Checks if an entity can leave an areabehavior
	 * @param entity : the entity that wants to leave
	 * @param coordinates : the coordinates of the cells of the area behavior
	 * @return true if the entity can leave the area behavior 
	 */
	public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {

		boolean canLeave = true;

		for (DiscreteCoordinates position : coordinates) {
			if (!cells[position.x][position.y].canLeave(entity)) {
				canLeave = false;
			}		
		}
		return canLeave;
	}


	/**
	 *  getter for the behavior mapp
	 * @return the behavior map
	 */
	public Image getBehaviorMap() {
		return behaviorMap;
	}

	/**
	 * getter for the width of the behavior map
	 * @return the width of the behaior map
	 */
	public int getWidth() {

		return behaviorMap.getWidth();

	}

	/**
	 * getter for the width of the behavior map
	 * @return the width of the behaior map
	 */
	public int getHeight() {
		return behaviorMap.getHeight();
	}


	/**
	 * getter for the cells of the behavior map
	 * @return the cells of the behavior map
	 */
	public Cell[][] getCells(){
		return cells;
	}

	/**
	 * transforms coordinates in cells
	 * @param coordinates the coordinates to transform
	 * @return
	 */
	public Cell toCell(DiscreteCoordinates coordinates) {
		return cells[coordinates.getX()][coordinates.getY()];
	}





	/**
	 * Default AreaBehavior Constructor
	 * @param window (Window): graphic context, not null
	 * @param fileName (String): name of the file containing the behavior image, not null
	 */
	public AreaBehavior(Window window, String fileName){

		behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null ,false);

		width = behaviorMap.getWidth();
		height = behaviorMap.getHeight();

		cells = new Cell[width][height];

	}




}
