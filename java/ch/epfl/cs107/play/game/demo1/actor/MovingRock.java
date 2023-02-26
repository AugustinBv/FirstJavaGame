package ch.epfl.cs107.play.game.demo1.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class MovingRock extends GraphicsEntity {

	private Window window;
	private final TextGraphics text;
	
	/**
	 * MovingRock constructor
	 * @param position (Vector) : where the MovingRock appears
	 * @param text (String) : Text following the Rock
	 * @param window (Window) : Where the Rock is drawn
	 */
	public MovingRock(Vector position, String text, Window window) {
		super(position, new ImageGraphics(ResourcePath.getSprite("rock.3"), 0.1f,0.1f, null , Vector.ZERO , 1.0f, -Float.MAX_VALUE));
		
		this.text = new TextGraphics(text , 0.04f, Color.BLUE);
		this.text.setParent(this);
		this.text.setAnchor(new Vector(-0.3f, 0.1f)) ;
		this.window = window;
	}


	public void draw(Canvas canvas) {
		if(getGraphics() != null) {
			getGraphics().draw(canvas);
			text.draw(canvas);
		}
	}
	
	@Override
	public void update(float deltaTime){

// This is how the movements should work, but for the step one we only want the downArrow, and not to go straight
//		Keyboard keyboard = window.getKeyboard() ;
//		Button downArrow = keyboard.get(Keyboard.DOWN) ;
//		if(downArrow.isLastPressed()) {
//			setCurrentPosition(getPosition().sub(0, 0.005f)) ;
//		}
//		
//		Keyboard keyboard1 = window.getKeyboard() ;
//		Button upArrow = keyboard1.get(Keyboard.UP) ;
//		if(upArrow.isLastPressed()) {
//			setCurrentPosition(getPosition().add(0, 0.005f)) ;
//		}
//		
//		Keyboard keyboard2 = window.getKeyboard() ;
//		Button leftArrow = keyboard2.get(Keyboard.LEFT) ;
//		if(leftArrow.isLastPressed()) {
//			setCurrentPosition(getPosition().sub(0.005f, 0)) ;
//		}
//		
//		Keyboard keyboard3 = window.getKeyboard() ;
//		Button rightArrow = keyboard3.get(Keyboard.RIGHT) ;
//		if(rightArrow.isLastPressed()) {
//			setCurrentPosition(getPosition().add(0.005f, 0)) ;
//		}
		
		Keyboard keyboard = window.getKeyboard() ;
		Button downArrow = keyboard.get(Keyboard.DOWN) ;
		if(downArrow.isLastPressed()) {
			setCurrentPosition(getPosition().sub(0.005f, 0.005f)) ;
		}
	}

	
}

