package com.rootmc.timothy.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.rootmc.timothy.game.entity.mob.player.Player;
import com.rootmc.timothy.game.graphics.Screen;
import com.rootmc.timothy.game.input.Keyboard;
import com.rootmc.timothy.game.input.Mouse;
import com.rootmc.timothy.game.level.Level;
import com.rootmc.timothy.game.level.SpawnLevel;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	
	/*
	* 	Defined Vars for game
	*/
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	
	private Thread thread; 
	private JFrame frame; 
	private Keyboard key;
	public boolean running = false;
	private Screen screen;
	private Player player;
	
	private Level level;
	
	private int XSpawn = 8;
	private int YSpawn = 8;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	

	// Main constructor for game

	public Game(){
		Mouse mouse = new Mouse();
		Dimension size = new Dimension(width*scale, height*scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		level = new SpawnLevel("/images/new/Level/SpawnLevel.png");
		
		key = new Keyboard();
		player = new Player(XSpawn*16,YSpawn*16,key);
		player.init(level);
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	// This is called when the game starts

	public synchronized void start(){
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	// This is called when the game stops

	public synchronized void stop(){
		running = false;
		try{
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	// The "Brains" of the game
	// Runs 60/sec

	public void run() {
		long lastTime = System.nanoTime();
		long Timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta =0;
		int Frames = 1;
		int updates = 0;
		
		requestFocus();
		while(running){
			long now = System.nanoTime();
			delta += (now-lastTime)/ ns;
			lastTime = now;
			while(delta >=1){
				update();
				updates++;
				delta--;
			}
			render();
			Frames++;
			
			if(System.currentTimeMillis() - Timer > 1000){
				Timer+= 1000;
				//System.out.println(updates+" ups, "+Frames+" Frames for every seconds");
				frame.setTitle("Game || "+ updates+" updates per seconds, "+Frames+" Frames for every seconds");
				updates = 0;
				Frames = 0;
			}
		}
		stop();
	}
	//int x=0,y=0;
	public void update(){
		key.update();
		player.update();
	}

	// Render 3 fames before the current screen
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		//screen.render(x,y);
		int xScroll = player.x-screen.width/2;
		int yScroll = player.y-screen.height/2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		
		for(int i=0; i< pixels.length; i++){
			pixels[i]= screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0,0, getWidth(),getHeight(),null);
		
		g.setFont(new Font("Verdana",0,15));
		g.setColor(Color.BLUE);
		g.drawString("Version: Alpha 0.0.3", 0, 25);
		g.drawString("X:"+player.x+" Y: "+player.y, 0, 45);
		g.drawString("Project Page: http://timothy.rootmc.com", 0, 35);
		
		g.drawRect(Mouse.getX(), Mouse.getY(), 32, 32);
		
		g.dispose();
		bs.show();
		
	}

	// Calls Everything in the game
	
	public static void main(String[] args){
		//System.out.println(info+" System.getProperty('os.name') == "+System.getProperty("os.name")+";");
		//System.out.println(info+" System.getProperty('os.version')== "+System.getProperty("os.version")+";");
		//System.out.println(info+" System.getProperty('os.arch')== "+System.getProperty("os.arch")+";");
		//System.out.println(info+" System.getProperty('java.version') == "+System.getProperty("java.version")+";");
		//System.out.println(info+" System.getProperty('java.vendor) == "+System.getProperty("java.vendor")+";");
		//System.out.println(info+" System.getProperty('sun.arch.data.model') == "+System.getProperty("sun.arch.data.model")+";");
		Game game = new Game();
		game.frame.setResizable(true);
		//game.frame.setTitle("Game");
		game.frame.setAlwaysOnTop(false);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}
}