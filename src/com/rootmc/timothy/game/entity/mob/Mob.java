package com.rootmc.timothy.game.entity.mob;

import com.rootmc.timothy.game.entity.Entity;
import com.rootmc.timothy.game.graphics.Sprite;

public abstract class Mob extends Entity{
	
	protected Sprite sprite;
	protected int dir = 2;
	protected boolean moveing = false;
	
	public void move(int xa, int ya){
		if(xa !=0 && ya!=0){
			move(xa, 0);
			move(0, ya);
			return;
		}
		if(xa > 0)dir=1;
		if(xa<0)dir =3;
		if(ya>0) dir=2;
		if(ya<0)dir = 0;
		
		if(!(collision(xa, ya))){
			x+=xa;
			y+=ya;
		}
	}
	
	public void update(){
		
	}
	
	private boolean collision(int xa, int ya){
		boolean solid = false;
		for(int c=0; c<4; c++){
			int xt = ((x+xa)+c%2*13-1)/16;
			int yt = ((y+ya)+c/2*15+1)/16;
			if(level.getTile(xt, yt).solid()){ solid = true;}
		}
		return solid;
	}
	
	public void render(){
		
	}

}