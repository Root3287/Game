package com.rootmc.timothy.game.graphics;

import java.util.Random;

import com.rootmc.timothy.game.level.tile.Tile;

public class Screen {
	// The comment Below is for a later Stage...
	//pixels[x+y*width]=0x9B8C75;
	public int width,height;
	public final int MAP_SIZE= 64;
	public final int MAP_MASK = MAP_SIZE-1;
	public int[] pixels;
	public int[] tiles = new int[MAP_SIZE*MAP_SIZE];
	public int xOffset, yOffset;
	
	private Random random = new Random();
	
	public Screen(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		for(int i = 0; i<MAP_SIZE*MAP_SIZE; i++){
			tiles[i]=random.nextInt(0xFFFFFF);
			tiles[0] = 0;
		}
	}
	
	public void clear(){
		for(int i =0; i<pixels.length; i++){
			pixels[i]=0;
		}
	}
	
	/*public void render(int xOffset, int yOffset){
		for(int y = 0; y < height; y++){
			int yp= y+yOffset;
			if(yp<0||yp>=height)continue;
			for(int x = 0; x < width; x++){
				int xp = x+xOffset;
				if(xp<0||xp>=width)continue;
				pixels[xp+yp*width]= Sprite.grass.pixels[(x&15)+(y&15)* Sprite.grass.SIZE];
			}
		}
	}*/
	
	public void renderTile(int xp,int yp, Tile tile){
		xp -= xOffset;
		yp -= yOffset;
		for(int y=0; y<tile.sprite.SIZE; y++){
			int ya= y+yp;
			for(int x=0; x<tile.sprite.SIZE; x++){
				int xa= x+xp;
				if(xa< -tile.sprite.SIZE || xa>=width || ya<0 || ya>=height)break;
				if(xa<0)xa=0;
				pixels[xa+ya*width]=tile.sprite.pixels[x+y*tile.sprite.SIZE];
			}
		}
	}
	
	public void renderPlayer(int xp,int yp, Sprite sprite){
		xp -= xOffset;
		yp -= yOffset;
		for(int y=0; y<16; y++){
			int ya= y+yp;
			for(int x=0; x<16; x++){
				int xa= x+xp;
				if(xa< -16 || xa>=width || ya<0 || ya>=height)break;
				if(xa<0)xa=0;
				int col = sprite.pixels[x+y*16];
				if(col !=0xffff00ff && col !=0xFFFE00FE && col !=0xFFFC00FC && col !=0xFFFD00FD && col !=0xFFF211E0)
				pixels[xa+ya*width]=col;
			}
		}
	}
	
	public void setoffset(int xoffset, int yOffSet){
		this.xOffset = xoffset;
		this.yOffset = yOffSet;
	}
}