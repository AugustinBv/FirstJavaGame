package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.Shoes;
import ch.epfl.cs107.play.game.enigme.area.Brothers;
import ch.epfl.cs107.play.game.enigme.area.Level1;
import ch.epfl.cs107.play.game.enigme.area.Level2;
import ch.epfl.cs107.play.game.enigme.area.Level3;
import ch.epfl.cs107.play.game.enigme.area.Level4;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme extends AreaGame {

	
	// Areas included in Enigme
	private Area levelSelector = new LevelSelector();
	private Area level1 = new Level1();
	private Area level2 = new Level2();
	private Area level3 = new Level3();
	private Area level4 = new Level4();
	private Area brothers = new Brothers();
	
	private Window window ;
	private FileSystem fileSystem ;
	private Area currentArea;
	private int frameRate = 24;
	private EnigmePlayer player;
	private DiscreteCoordinates positionLevelSelector;
	private Dialog dialogDeath;
	private Dialog dialogDeath2;
	private Dialog dialogOld;
	private Dialog dialogPannel;
	
	

    @Override
    public String getTitle() {
        return "Enigme";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
    	super.begin(window, fileSystem);
    	this.window = window;
		
    	addArea(levelSelector);
    	addArea(level1);
    	addArea(level2);
    	addArea(level3);
    	addArea(level4);
    	addArea(brothers);
    	
    	positionLevelSelector = new DiscreteCoordinates(5,5);
    	setCurrentArea(levelSelector.getTitle(), true);
		player = new EnigmePlayer(levelSelector, positionLevelSelector);
		dialogDeath = new Dialog("Only the Master of Death can pass, collect my Hallows", "dialog.1", getCurrentArea());
		dialogDeath2 = new Dialog("Congrats, you're the Master of Death. So what ?", "dialog.1", getCurrentArea());
		dialogOld = new Dialog("If you are hurt, take this potion. (30 golds)", "dialog.1", getCurrentArea());
		dialogPannel = new Dialog("I open at the close", "dialog.1", getCurrentArea());
		getCurrentArea().setViewCandidate(player);
        return true;
    }

    @Override
    public void update(float deltaTime) {
    	super.update(deltaTime);
    	
    	if(player.getDeathInteract()){
    		dialogDeath.draw(getWindow());
    	}
    	if(player.getDeathInteract2()) {
    		dialogDeath2.draw(getWindow());
    	}
    	if(player.getOldInteract()) {
    		dialogOld.draw(getWindow());
    	}
    	if(player.getPannelInteract()) {
    		dialogPannel.draw(getWindow());
    	}
    	if(player.isGoingThrough()) {
    		player.leaveArea(getCurrentArea());
    		setCurrentArea(player.passedDoor().getNextArea(), false);
    		player.enterArea(getCurrentArea(), player.passedDoor().getArrivalPosition());
    		getCurrentArea().setViewCandidate(player);
    		
    	}

    }


    @Override
    public int getFrameRate() {
        return frameRate;
    }
}