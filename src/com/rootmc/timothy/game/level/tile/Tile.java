package com.rootmc.timothy.game.level.tile;

import com.rootmc.timothy.game.graphics.Screen;
import com.rootmc.timothy.game.graphics.Sprite;

public class Tile {
	public int x,y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile Flower = new FlowerTile(Sprite.flower);
	public static Tile Rock = new RockTile(Sprite.rock);
	public static Tile Wall = new WallTile(Sprite.wall);
	public static Tile voidTile = new voidTile(Sprite.voidTile);
	
	public Tile(Sprite sprite){
		this.sprite=sprite;
	}
	public void render(int x,int y,Screen screen){
	}
	public boolean solid(){
		return false;
	}
}
