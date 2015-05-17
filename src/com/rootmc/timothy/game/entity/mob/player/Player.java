package com.rootmc.timothy.game.entity.mob.player;

import com.rootmc.timothy.game.entity.mob.Mob;
import com.rootmc.timothy.game.graphics.Screen;
import com.rootmc.timothy.game.graphics.Sprite;
import com.rootmc.timothy.game.input.Keyboard;

public class Player extends Mob{
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking;
	
	public Player(int x, int y, Keyboard input){
		this.x=x;
		this.input = input;
		this.y=y;
	}
	
	public Player(Keyboard input){
		this.input = input;
	}
	
	public void update(){
		int xa=0,ya=0;
		if(anim < 7500) anim++;
		else anim = 0;
		if(input.up)ya--;
		if(input.down)ya++;
		if(input.left)xa--;
		if(input.right)xa++;
		if(input.exit)System.exit(1);
		
		if(xa!=0||ya!=0){walking = true; move(xa,ya);}else{walking=false;}
	}
	public void render(Screen screen){
		if(dir == 0){ 
			sprite = Sprite.player_back;
			if(walking){
				if(anim %20>10){
					sprite = Sprite.player_back;
				}else{
					sprite = Sprite.player_back_walk;
				}
			}
		}
		if(dir == 3){
			sprite = Sprite.player_left ;
			if(walking){
				if(anim %20>10){
					sprite = Sprite.player_left;
				}else{
					sprite = Sprite.player_left_walk;
				}
			}
		}
		if(dir == 2){ 
			sprite = Sprite.player;
			if(walking){
				if(anim %20>10){
					sprite = Sprite.player;
				}else{
					sprite = Sprite.player_walk;
				}
			}
		}
		if(dir == 1){
			sprite = Sprite.player_right;
			if(walking){
				if(anim %20>10){
					sprite = Sprite.player_right;
				}else{
					sprite = Sprite.player_right_walk;
				}
			}
		}
		screen.renderPlayer(x, y, sprite);
	}

}
