package ch.epfl.cs107.play.game.demo1;
import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.window.Canvas;

public class Demo1 implements Game {
	
	private Actor actor1;
	private Window window ;
	private FileSystem fileSystem ;
	float radius = 0.2f;
	private TextGraphics text1;
	
	private Actor actor2;
	
	
	
	/**
	 * Getter for the name of this game
	 */
	public String getTitle () {
		String title = "Demo1";
		return title;
	}
	
	/**
	 * Returns the frameRate
	 */
	public int getFrameRate() {
		int frameRate = 24;
		return frameRate;
	}
	

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		
		this.window = window;
		this.fileSystem = fileSystem;
		text1 = new TextGraphics("BOUM!" , 0.04f, Color.RED);
		
		
		actor1 = new GraphicsEntity(Vector.ZERO ,new ShapeGraphics(new Circle(radius), null ,Color.RED , 0.005f));
		actor2 = new MovingRock(Vector.ZERO, "Hello, I'm a moving rock !", window);
		
		return true;
	}


	@Override
	public void end() {
	}


	@Override
	public void update(float deltaTime) {
		actor1.draw(window);
		actor2.draw(window);
		actor2.update(deltaTime);
		
		float d = (float) actor2.getPosition().getDistance(actor1.getPosition());
		
		if(d <= radius) {
			text1.draw(window);
		}
	}
	
	
}