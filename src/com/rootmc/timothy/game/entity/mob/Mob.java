package com.rootmc.timothy.game.entity.mob;

import com.rootmc.timothy.game.entity.Entity;
import com.rootmc.timothy.game.graphics.Sprite;

public abstract class Mob extends Entity{
	
	protected Sprite sprite;
	protected int dir = 2;
	protected boolean moveing = false;
	
	public void move(int xa, int ya){
		if(xa > 0)dir=1;
		if(xa<0)dir =3;
		if(ya>0) dir=2;
		if(ya<0)dir = 0;
		
		if(!(collision())){
			x+=xa;
			y+=ya;
		}
	}
	
	public void update(){
		
	}
	
	private boolean collision(){
		return false;
	}
	
	public void render(){
		
	}

}