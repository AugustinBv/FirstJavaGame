package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame {

	private int frameRate = 24;

	private Room0 room0 = new Room0();
	private Room1 room1 = new Room1();

	private Demo2Player player;

	private DiscreteCoordinates positionRoom1;
	private DiscreteCoordinates positionRoom0;


	@Override
	public int getFrameRate() {
		return frameRate;
	}

	@Override
	public String getTitle() {
		return "Demo2";
	}


	@Override
	public boolean begin(Window window, FileSystem fileSystem) {

		super.begin(window, fileSystem);
		Area room0 = new Room0();
		Area room1 = new Room1();
		positionRoom1 = new DiscreteCoordinates(5,2);
		positionRoom0 = new DiscreteCoordinates(5,5);

		addArea(room0);
		addArea(room1);

		setCurrentArea(room0.getTitle(), false);

		player = new Demo2Player(room0, positionRoom0);

		player.enterArea(getCurrentArea(), player.getCurrentMainCellCoordinates());
		getCurrentArea().setViewCandidate(player);

		return true;
	}


	public void update(float deltaTime) {

		super.update(deltaTime);


		if(player.getGoThroughDoor() && getCurrentArea() instanceof Room0 ) {
			player.leaveArea(getCurrentArea());
			setCurrentArea(room1.getTitle(), false);
			player.enterArea(getCurrentArea(), positionRoom1);
			getCurrentArea().registerActor(player);
			getCurrentArea().setViewCandidate(player);
			player.setIsGoingThrough(false);
		}

		if(player.getGoThroughDoor() && getCurrentArea() instanceof Room1 ) {
			player.leaveArea(getCurrentArea());
			setCurrentArea(room0.getTitle(), false);
			player.enterArea(getCurrentArea(), positionRoom0);
			getCurrentArea().registerActor(player);
			getCurrentArea().setViewCandidate(player);
			player.setIsGoingThrough(false);
		}


	}

}