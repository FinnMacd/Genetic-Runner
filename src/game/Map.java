package game;

import java.awt.Graphics2D;

public class Map {
	
	private Player player;
	
	private Obstacle[] objects;
	
	private int numUps, updateCap = 800, startX = 60, startY = 240;
	
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
		else numUps++;
		
	}
	
	public void draw(Graphics2D g) {
		
		for(Obstacle o:objects) {
			o.draw(g);
		}
		player.draw(g);
				
		
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
