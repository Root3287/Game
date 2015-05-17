package com.rootmc.timothy.game.graphics;

public class Sprite {
	
	public final int SIZE;
	private int x,y;
	public int[] pixels;
	private SpriteSheet sheet;
	static int DefaultSpriteSize = 16;
	
	//This is a Sprite Template
	// public static Sprite grass = new Sprite(16, 0, 0, null);
	//public static Sprtie {name} = new Sprite(16,0x)
	public static Sprite grass= new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16,1,0,SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16,2,0,SpriteSheet.tiles);
	public static Sprite wall = new Sprite(16,3,0,SpriteSheet.tiles);
	public static Sprite voidTile= new Sprite(16,4,0,SpriteSheet.tiles);
	
	public static Sprite player = new Sprite(16, 0, 0, SpriteSheet.player);
	public static Sprite player_walk = new Sprite(16,0,1, SpriteSheet.player);
	public static Sprite player_left = new Sprite(16,1,0, SpriteSheet.player);
	public static Sprite player_left_walk = new Sprite(16,1,1, SpriteSheet.player);
	public static Sprite player_right = new Sprite(16,2,0, SpriteSheet.player);
	public static Sprite player_right_walk = new Sprite(16,2,1, SpriteSheet.player);
	public static Sprite player_back = new Sprite(16,3,0, SpriteSheet.player);
	public static Sprite player_back_walk = new Sprite(16,3,1, SpriteSheet.player);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet){
		SIZE = size;
		pixels=new int[SIZE*SIZE];
		this.x=x*size;
		this.y=y*size;
		this.sheet =sheet;
		load();
	}
	
	public Sprite(int size,int color){
		SIZE=size;
		pixels = new int[SIZE*SIZE];
		setColor(color);
	}
	
	public void setColor(int color){
		for (int i=0;i<SIZE*SIZE;i++){
			pixels[i]=color;
		}
	}
	
	private void load(){
		for(int y=0;y<SIZE;y++){
			for(int x=0;x<SIZE;x++){
				pixels[x+y*SIZE]= sheet.pixels[(x+this.x)+(y+this.y)*sheet.SIZE];
			}
		}
	}
}
