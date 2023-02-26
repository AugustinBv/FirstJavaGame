package ch.epfl.cs107.play.game.enigme.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Cake;
import ch.epfl.cs107.play.game.enigme.actor.Death;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.ElderWand;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.Gold;
import ch.epfl.cs107.play.game.enigme.actor.InvisibilityCape;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lava;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.OldMan;
import ch.epfl.cs107.play.game.enigme.actor.Pannel;
import ch.epfl.cs107.play.game.enigme.actor.Potion;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.ResurrectionStone;
import ch.epfl.cs107.play.game.enigme.actor.Shoes;
import ch.epfl.cs107.play.game.enigme.actor.Torch;


public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {

    /// Add Interaction method with all non Abstract Interactable

    /**
     * Default interaction between something and an interactable
     * Notice: if this method is used, then you probably forget to cast the AreaInteractionVisitor into its correct child
     * @param other (Interactable): interactable to interact with, not null
     */
    default void interactWith(Apple apple){
        // by default the interraction is empty
    }
    
    default void interactWith(Door door){
        // by default the interraction is empty
    }
    
    default void interactWith(EnigmeBehavior.EnigmeCell cell){
        // by default the interraction is empty
    }
    
    default void interactWith(EnigmePlayer player){
    	
    }
    
    default void interactWith(Shoes shoes) {
    	
    }
    
    default void interactWith(PressurePlate plate) {
    	
    }
    
    default void interactWith(PressureSwitch pressure) {
    	
    }

    default void interactWith(Key key) {
	
    }

    default void interactWith(Lever lever) {
	
    }

    default void interactWith(Torch torch) {
	
    }
    
    default void interactWith(Lava lava) {
    	
    }
    
    default void interactWith(Gold gold) {
    	
    }
    
    default void interactWith(Potion potion) {
    	
    }
    
    default void interactWith(ResurrectionStone stone) {
    	
    }
    
    default void interactWith(ElderWand wand) {
    	
    }
    
    default void interactWith(InvisibilityCape cape) {
    	
    }
    
    default void interactWith(Cake cake) {
    	
    }
    
    default void interactWith(OldMan old) {
    	
    }
    
    default void interactWith(Death death) {
    	
    }
    
    default void interactWith(Pannel pannel) {
    	
    }
    
    
    
}
