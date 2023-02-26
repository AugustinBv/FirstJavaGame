package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;

public class Animation extends Sprite {

	private Sprite[][] spriteAnimation;

	/**
	 * Creates the animation table
	 * @param name (String) : Name of the sprite
	 * @param width (Float) : width of the table
	 * @param height (Float) : height of the table
	 * @param parent (Positionable) : parent on wich set an anchor
	 */
	public Animation(String name, float width, float height, Positionable parent) {
		super(name, width, height, parent);
		spriteAnimation = new Sprite[4][4];
		Vector anchor = new Vector (0.15f, 0.1f) ;
		for(int i = 0; i<=3; i++) {
			for(int j = 0; j<=3; j++) {
				System.out.println(i);
				spriteAnimation[i][j] = new Sprite(name, width, height, this, new RegionOfInterest(i*16, (j*21), 16, 21), anchor);
			}
		}
	}

	/**
	 * Getter for a sprite in the table
	 * @param i (int) : first dimension of the table
	 * @param j (int) : second
	 * @return (Sprite) : sprite in cell[i][j] of the tab
	 */
	public Sprite getSprite(int i, int j) {
		return spriteAnimation[i][j];
	}
}
