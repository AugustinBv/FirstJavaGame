package ch.epfl.cs107.play.game.enigme.area.demo2;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Demo2Area extends Area {

	
	private String areaTitle;
	private Window window ;
	private FileSystem fileSystem ;
	
	public Demo2Area(String title) {
		areaTitle = title;
	}
	
	
	public boolean begin(Window window, FileSystem fileSystem) {
		// TODO Auto-generated method stub
		
		super.begin(window, fileSystem);
		
		setBehavior(new Demo2Behavior(window , getTitle())) ;
		registerActor(new Background(this)) ;
		
		
		return true;
	}
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return areaTitle;
	}

	@Override
	public float getCameraScaleFactor() {
		// TODO Auto-generated method stub
		return 24;
	}


	@Override
	protected boolean vetoFromGrid() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	protected boolean agreeToAdd(Actor a) {
		// TODO Auto-generated method stub
		//JS
		return true;
	}
	
}