package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class EnigmeArea extends Area {
		
		private String areaTitle;
		// Context objects
		private Window window;
		private FileSystem fileSystem;
		
		public EnigmeArea(String title) {
			areaTitle = title;
		}

		public String getTitle() {
			return areaTitle;
		}

		public float getCameraScaleFactor() {
			return 24;
		}
		
		public boolean begin(Window window, FileSystem fileSystem) {
			super.begin(window, fileSystem);
			setBehavior(new EnigmeBehavior(window , getTitle())) ;
			registerActor(new Background(this));
			
			return true;
		}

		@Override
		protected boolean vetoFromGrid() {
			return false;
		}

		@Override
		protected boolean agreeToAdd(Actor a) {
			return true;
		}
		
		
	}
