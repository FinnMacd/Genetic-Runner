package game;

import java.awt.Graphics2D;

import screen.ScreenController;

public class Map {
	
	private Player player;
	
	private Obstacle[] objects;
	
	private int numUps, updateCap = 800, startX = 60, startY = 240, screenX = 0;
	
	public Map() {
		
		player = new Player(startX,startY);
		objects = new Obstacle[0];
		
	}
	
	public Map(Obstacle[] o) {
		
		player = new Player(startX,startY);
		objects = o;
		
	}
	
	public void start() {
		numUps = 0;
		player.start();
	}
	
	public void reset() {
		player.reset(startX, startY);
		player.randomizeDirection();
		screenX = 0;
		updateCap = 800;
	}
	
	public void randomize() {
		
		for(Obstacle o: objects) {
			o.setLocation((int)(200 + Math.random()*440), (int)(Math.random()*480));
		}
		
	}
	
	public void update() {
		
		player.update();
		
		for(Obstacle o:objects) {
			if(!o.isSafe(player))player.kill();
			if(o.shouldCheck(player))player.addSightPoints(o.sightPoints(player));
		}
		
		player.checkSight();
		
		if(numUps > updateCap)player.kill();
		else if(player.getX() > 600 && player.numUpgrades == 0) {
			player.numUpgrades++;
			updateCap += 600;
		}else if(player.getX() > 1200 && player.numUpgrades == 1) {
			player.numUpgrades++;
			updateCap += 600;
		}else numUps++;
		
		if(player.getX() - screenX > ScreenController.width/2)screenX += ((player.getX() - screenX)-ScreenController.width/2)*0.09;
		else if(player.getX() - screenX < ScreenController.width/2 && screenX > 0)screenX -= (ScreenController.width/2 -(player.getX() - screenX))*0.09;
		
	}
	
	public void draw(Graphics2D g) {
		
		for(Obstacle o:objects) {
			o.draw(g, screenX);
		}
		player.draw(g, screenX);
				
		
	}
	
	public void updatePlayer(double direction, double speed) {
		player.updateMovement(direction, speed);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setObstacles(Obstacle[] o) {
		objects = o;
	}
	
	public int getTime() {
		return (updateCap+60-numUps)/60;
	}
	
}
