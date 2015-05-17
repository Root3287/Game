package com.rootmc.timothy.game.level.tile;

import com.rootmc.timothy.game.graphics.Screen;
import com.rootmc.timothy.game.graphics.Sprite;

public class WallTile extends Tile{

	public WallTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	public void render(int x,int y,Screen screen){
		screen.renderTile(x<<4, y<<4, this);
	}
	
	public boolean solid(){
		return true;
	}
}
