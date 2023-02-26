package ch.epfl.cs107.play.game.areagame;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;


/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and a List of Actors
 */
public abstract class Area implements Playable {

	// Context objects
	private Window window ;
	/// List of Actors inside the area
	private List <Actor> actors ;
	/// Waiting Lists
	protected List <Actor> registeredActors ;
	private List <Actor> unregisteredActors ;

	// Camera Parameter
	// actor on which the view is centered
	private Actor viewCandidate ;
	// effective center of the view
	private Vector viewCenter ;

	/// The behavior Map
	private AreaBehavior areaBehavior ;

	/// Staus of the area, false = never visited or reseted
	private boolean status;
	// Lists of interactables and interactors
	private Map <Interactable , List <DiscreteCoordinates >> interactablesToEnter ;
	private Map <Interactable , List <DiscreteCoordinates >> interactablesToLeave ;
	private LinkedList<Interactor> interactors;





	/**
	 * sets the behavior that corresponds to the area
	 * @param ab (AreaBehavior) : the behavior to set
	 */
	protected final void setBehavior(AreaBehavior ab) {
		areaBehavior = ab;
	}




	/** @return (float): camera scale factor, assume it is the same in x and y direction */
	public abstract float getCameraScaleFactor();


	/**
	 * Add an actor to the actors list
	 * @param a (Actor): the actor to add, not null
	 * @param forced (Boolean): if true, the method ends
	 */
	private void addActor(Actor a, boolean forced) {

		boolean errorOccured = !actors.add(a) ;
		if(a instanceof Interactor) {
			errorOccured = errorOccured || !interactors.add((Interactor) a) ;
		}
		if(a instanceof Interactable) {
			errorOccured = errorOccured || !enterAreaCells (((Interactable) a), ((Interactable) a).getCurrentCells()) ;
		}

		if(errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely added , so remove it from where it was") ;
			removeActor(a, true) ;
		}

	}

	protected abstract boolean vetoFromGrid();

	protected abstract boolean agreeToAdd(Actor a);


	/**
	 * Remove an actor form the actor list
	 * @param a (Actor): the actor to remove, not null
	 * @param forced (Boolean): if true, the method ends
	 */
	private void removeActor(Actor a, boolean forced){
		boolean errorOccured = !actors.remove(a) ;
		if(a instanceof Interactor) {
			errorOccured = errorOccured || !interactors.remove((Interactor) a) ;
		}
		if(a instanceof Interactable) {
			errorOccured = errorOccured || !leaveAreaCells (((Interactable) a), ((Interactable) a).getCurrentCells()) ;

		}

		if(errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely removed , so leave it where it was") ;
			addActor(a, true) ;
		}
	}


	/**
	 * Register an actor : will be added at next update
	 * @param a (Actor): the actor to register, not null
	 * @return (boolean): true if the actor is correctly registered
	 */
	public final boolean registerActor(Actor a){

		boolean errorOccured = !registeredActors.add(a) ;
		boolean correct = true;

		if (errorOccured) {
			correct = false;
		}

		return correct;
	}

	/**
	 * Unregister an actor : will be removed at next update
	 * @param a (Actor): the actor to unregister, not null
	 * @return (boolean): true if the actor is correctly unregistered
	 */
	public final boolean unregisterActor(Actor a){

		boolean errorOccured = !unregisteredActors.add(a) ;
		boolean correct = true;

		if (errorOccured) {
			correct = false;
		}

		return correct;
	}



	/**
	 * adds to or removes from the area the actors/interactables/interactors wanted   
	 */
	private final void purgeRegistration() {

		if (registeredActors != null) {
			for (Actor actor : registeredActors) {
				addActor(actor, false);
			}
			registeredActors.clear();
		}

		if (unregisteredActors != null) {
			for (Actor actor : unregisteredActors) {
				removeActor(actor, false);
			}
			unregisteredActors.clear();
		}

		if (interactablesToEnter != null) {
			for (Interactable key : this.interactablesToEnter.keySet()) {
				areaBehavior.enter(key,  key.getCurrentCells());
			}
			interactablesToEnter.clear();
		}
		if (interactablesToLeave != null) {
			for (Interactable key : this.interactablesToLeave.keySet()) {
				leaveAreaCells(key, key.getCurrentCells());
			}
			interactablesToLeave.clear();
		}

	}


	/**
	 * gets the behavior corresponding to the area
	 * @return the behavior of the area
	 */
	public AreaBehavior getBehavior() {
		return areaBehavior;
	}




	/**
	 * Getter for the area width
	 * @return (int) : the width in number of cols
	 */
	public final int getWidth(){
		int width = areaBehavior.getWidth(); 
		return width;
	}

	/**
	 * Getter for the area height
	 * @return (int) : the height in number of rows
	 */
	public final int getHeight(){
		int height = areaBehavior.getHeight();
		return height;
	}

	/** @return the Window Keyboard for inputs */
	public final Keyboard getKeyboard () {
		return window.getKeyboard();
	}



	/**
	 * Getter for status
	 * @param status (boolean) : true if the player has visite the area
	 */
	void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Setter for status
	 * @return status (boolean) : true if the player has visite the area
	 */
	public boolean getStatus() {
		return status;
	}


	/**
	 * Sets the camera view on an actor 
	 * @param a (Actor) : the actor to set the view on
	 */
	public final void setViewCandidate(Actor a){
		this.viewCandidate = a ;
	}


	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		this.window = window;

		actors = new LinkedList <>() ;
		registeredActors = new LinkedList <>() ;
		unregisteredActors = new LinkedList <>() ;
		interactablesToEnter = new HashMap<>();
		interactablesToLeave = new HashMap<>();
		interactors = new LinkedList<>();
		viewCandidate = null ;
		viewCenter = Vector.ZERO;

		return true;
	}

	/**
	 * Resume method: Can be overridden
	 * @param window (Window): display context, not null
	 * @param fileSystem (FileSystem): given file system, not null
	 * @return (boolean) : if the resume succeed, true by default
	 */
	public boolean resume(Window window, FileSystem fileSystem){
		return true;
	}

	@Override
	public void update(float deltaTime) {
		updateCamera(viewCandidate);
		purgeRegistration();
		for(Actor actor : actors) {
			actor.update(deltaTime);
			actor.draw(window);
		}

		for (Interactor interactor : interactors) {
			if (interactor.wantsCellInteraction()) {
				areaBehavior.cellInteractionOf(interactor);
			}
			if (interactor.wantsViewInteraction()) {
				areaBehavior.viewInteractionOf(interactor);
			}
		}
	}

	/**
	 * updates the camera view center with respect to an actor
	 * @param a (Actor) : the actor set as the viewcenter
	 */
	private void updateCamera (Actor a) {
		if (viewCandidate != null) {
			viewCenter = a.getPosition();
		}
		Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter) ;
		window.setRelativeTransform(viewTransform) ;
	}

	/**
	 * Suspend method: Can be overridden, called before resume other
	 */
	public void suspend(){

		purgeRegistration();
	}


	/**
	 * checks if an entity leaves the cells of the area and adds it to the interactables to let out of the area 
	 * @param entity (Interactable) : the entity that wants to leave the area cells
	 * @param coordinates (List<DiscreteCoorinates>) : the list of coordinates of the cells 
	 * @return boolean : wether the entiy left the area cells
	 */
	public final boolean leaveAreaCells(Interactable entity, List <DiscreteCoordinates > coordinates) {
		if (areaBehavior.canLeave(entity, coordinates)) {
			interactablesToLeave.put(entity , coordinates) ;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks if an entity enters the cells of the area and adds it to the interactables to let in the area 
	 * @param entity (Interactable) : the entity that wants to enter the area cells
	 * @param coordinates (List<DiscreteCoorinates>) : the list of coordinates of the cells
	 * @return boolean : wether the entiy enterred the area cells
	 */
	public final boolean enterAreaCells(Interactable entity, List <DiscreteCoordinates > coordinates) {
		if (areaBehavior.canEnter(entity, coordinates)) {
			interactablesToEnter.put(entity , coordinates) ;
			return true;
		} else {
			return false;
		}
	}


	@Override
	public void end() {
	}
}
