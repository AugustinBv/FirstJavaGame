package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Cake;
import ch.epfl.cs107.play.game.enigme.actor.Death;
import ch.epfl.cs107.play.game.enigme.actor.ElderWand;
import ch.epfl.cs107.play.game.enigme.actor.InvisibilityCape;
import ch.epfl.cs107.play.game.enigme.actor.Lava;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.OldMan;
import ch.epfl.cs107.play.game.enigme.actor.Pannel;
import ch.epfl.cs107.play.game.enigme.actor.Potion;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.ResurrectionStone;
import ch.epfl.cs107.play.game.enigme.actor.Shoes;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicNumber;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Normal;
import ch.epfl.cs107.play.window.Window;

public class Brothers extends EnigmeArea implements Logic{
	static String title = "Brothers";
	private OldMan oldMan;
	private Death death;
	private SignalRock rock1;
	private SignalRock rock2;
	private SignalRock rock3;
	private SignalRock rock4;
	private SignalRock rock5;
	private SignalRock rock6;
	private SignalRock rock7;
	private SignalRock rock8;
	private SignalRock rock9;
	private SignalRock rock10;
	private SignalRock rock11;
	private SignalRock rock12;
	private SignalRock rock13;
	private SignalRock rock14;
	private SignalRock rock15;
	private SignalRock rock16;
	private SignalRock rock17;
	private SignalRock rock18;
	private SignalRock rock19;
	private SignalRock rock20;
	private SignalRock rock21;
	private SignalRock rock22;
	private SignalRock rock23;
	private SignalRock rock24;
	private SignalRock rock25;
	private SignalRock rock26;
	private SignalRock rock27;
	private SignalRock rock28;
	private SignalRock rock29;
	private SignalRock rock30;
	private SignalRock rock31;
	private SignalRock rock32;
	private SignalRock rock33;
	private SignalRock rock34;
	private SignalRock rock35;
	private Lever lever1;
	private Lever lever2;
	private Lever lever3;
	private PressureSwitch pressure1;
	private PressureSwitch pressure2;
	private PressureSwitch pressure3;
	private PressureSwitch pressure4;
	private ElderWand wand;
	private InvisibilityCape cape;
	private ResurrectionStone stone;
	private Lava lava;
	private Potion potion;
	private Pannel pannel;
	private Shoes shoes;
	private Cake cake;

	public Brothers() {
		super(title);
	}
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		
		
		wand = new ElderWand(this, new DiscreteCoordinates(33,8));
		enterAreaCells(wand, wand.getCurrentCells());
		stone = new ResurrectionStone(this, new DiscreteCoordinates(34,17));
		enterAreaCells(stone, stone.getCurrentCells());
		cape = new InvisibilityCape(this, new DiscreteCoordinates(1,28));
		enterAreaCells(cape, cape.getCurrentCells());
		
		Logic deathSignal = new MultipleAnd(wand, cape, stone);
				
		oldMan = new OldMan(this, new DiscreteCoordinates(24,14));
		enterAreaCells(oldMan, oldMan.getCurrentCells());
		potion = new Potion(this, new DiscreteCoordinates(24,13)); 
		rock1 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(19,29), deathSignal);
		enterAreaCells(rock1, rock1.getCurrentCells());
		rock2 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(21,29), deathSignal);
		enterAreaCells(rock2, rock2.getCurrentCells());
		death = new Death(this, new DiscreteCoordinates(20,29));
		enterAreaCells(death, death.getCurrentCells());
		lava = new Lava(this, new DiscreteCoordinates(27,22));
		enterAreaCells(lava, lava.getCurrentCells());
		pannel = new Pannel(this, new DiscreteCoordinates(25,21));
		shoes = new Shoes(this, new DiscreteCoordinates(17,1));
		
		Logic lavaSignal = new Normal(lava);
		
		lever1 = new Lever(this, new DiscreteCoordinates(32,3));
		enterAreaCells(lever1, lever1.getCurrentCells());
		lever2 = new Lever(this, new DiscreteCoordinates(33,3));
		enterAreaCells(lever2, lever2.getCurrentCells());
		lever3 = new Lever(this, new DiscreteCoordinates(34,3));
		enterAreaCells(lever3, lever3.getCurrentCells());
		
		pressure1 = new PressureSwitch(this, new DiscreteCoordinates(38,12));
		enterAreaCells(pressure1, pressure1.getCurrentCells());
		pressure2 = new PressureSwitch(this, new DiscreteCoordinates(27,12));
		enterAreaCells(pressure2, pressure2.getCurrentCells());
		pressure3 = new PressureSwitch(this, new DiscreteCoordinates(38,1));
		enterAreaCells(pressure3, pressure3.getCurrentCells());
		pressure4 = new PressureSwitch(this, new DiscreteCoordinates(27,1));
		enterAreaCells(pressure4, pressure4.getCurrentCells());
		
		Logic leverSignal = new LogicNumber(5,lever1, lever2, lever3);
		Logic pressureSignal = new MultipleAnd(pressure1, pressure2, pressure3, pressure4);
		
		rock3 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(30,25), Logic.FALSE);
		enterAreaCells(rock3, rock3.getCurrentCells());
		rock4 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(35,26), Logic.FALSE);
		enterAreaCells(rock4, rock4.getCurrentCells());
		rock5 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(28,16), Logic.FALSE);
		enterAreaCells(rock5, rock5.getCurrentCells());
		rock6 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(37,23), Logic.FALSE);
		enterAreaCells(rock6, rock6.getCurrentCells());
		rock7 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(34,17), lavaSignal);
		enterAreaCells(rock7, rock7.getCurrentCells());
		rock8 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(28,27), Logic.FALSE);
		enterAreaCells(rock8, rock8.getCurrentCells());
		rock9 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(29,20), Logic.FALSE);
		enterAreaCells(rock9, rock9.getCurrentCells());
		rock10 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(37,19), Logic.FALSE);
		enterAreaCells(rock10, rock10.getCurrentCells());
		rock11 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(35,21), Logic.FALSE);
		enterAreaCells(rock11, rock11.getCurrentCells());
		
		
		
		rock12 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(33,7), leverSignal);
		enterAreaCells(rock12, rock12.getCurrentCells());
		rock13 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(34,7), leverSignal);
		enterAreaCells(rock13, rock13.getCurrentCells());
		rock14 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(32,7), leverSignal);
		enterAreaCells(rock14, rock14.getCurrentCells());
		rock15 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(34,8), leverSignal);
		enterAreaCells(rock15, rock15.getCurrentCells());
		rock16 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(32,8), leverSignal);
		enterAreaCells(rock16, rock16.getCurrentCells());
		rock17 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(32,9), leverSignal);
		enterAreaCells(rock17, rock17.getCurrentCells());
		rock18 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(33,9), leverSignal);
		enterAreaCells(rock18, rock18.getCurrentCells());
		rock19 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(34,9), leverSignal);
		enterAreaCells(rock19, rock19.getCurrentCells());
		
		
		rock20 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(33,6), pressureSignal);
		enterAreaCells(rock20, rock20.getCurrentCells());
		rock21 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(34,6), pressureSignal);
		enterAreaCells(rock21, rock21.getCurrentCells());
		rock22 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(32,6), pressureSignal);
		enterAreaCells(rock22, rock22.getCurrentCells());
		rock23 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(31,6), pressureSignal);
		enterAreaCells(rock23, rock23.getCurrentCells());
		rock24 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(35,6), pressureSignal);
		enterAreaCells(rock24, rock24.getCurrentCells());
		rock25 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(35,7), pressureSignal);
		enterAreaCells(rock25, rock25.getCurrentCells());
		rock26 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(31,7), pressureSignal);
		enterAreaCells(rock26, rock26.getCurrentCells());
		rock27 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(35,8), pressureSignal);
		enterAreaCells(rock27, rock27.getCurrentCells());
		rock28 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(31,8), pressureSignal);
		enterAreaCells(rock28, rock28.getCurrentCells());
		rock29 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(35,9), pressureSignal);
		enterAreaCells(rock29, rock29.getCurrentCells());
		rock30 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(31,9), pressureSignal);
		enterAreaCells(rock30, rock30.getCurrentCells());
		rock31 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(32,10), pressureSignal);
		enterAreaCells(rock31, rock31.getCurrentCells());
		rock32 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(33,10), pressureSignal);
		enterAreaCells(rock32, rock32.getCurrentCells());
		rock33 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(34,10), pressureSignal);
		enterAreaCells(rock33, rock33.getCurrentCells());
		rock34 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(35,10), pressureSignal);
		enterAreaCells(rock34, rock34.getCurrentCells());
		rock35 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(31,10), pressureSignal);
		enterAreaCells(rock35, rock35.getCurrentCells());
		
		cake = new Cake(this, new DiscreteCoordinates(20,38));
		
		return true;
	}
}