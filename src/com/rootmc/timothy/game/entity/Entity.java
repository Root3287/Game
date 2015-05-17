package com.rootmc.timothy.game.entity;

import java.util.Random;

import com.rootmc.timothy.game.graphics.Screen;
import com.rootmc.timothy.game.level.Level;

public abstract class Entity {
	public int x,y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update(){
		
	}
	public void render(Screen screen){
		
	}
	public void remove(){
		// Remove From Level
		removed = true;
	}	
	public boolean isRemove(){
		return removed;
	}
	public void init(Level level){
		this.level = level;
	}
}
