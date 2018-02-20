package game;

import java.awt.Graphics2D;

public class Map {
	
	private Player player;
	
	private Obstacle[] objects;
	
	private int numUps, updateCap = 600;
	
	public Map() {
		
		player = new Player(100,100);
		objects = new Obstacle[0];
		
	}
	
	public Map(Obstacle[] o) {
		
		player = new Player(100,100);
		objects = o;
		
	}
	
	public void start() {
		numUps = 0;
		player.start();
	}
	
	public void reset() {
		player.reset(100, 100);
	}
	
	public void update() {
		
		player.update();
		
		for(Obstacle o:objects) {
			if(!o.isSafe(player))player.kill();
			if(o.shouldCheck(player))player.addSightPoints(o.sightPoints(player));
		}
		
		player.checkSight();
		
		if(numUps > updateCap)player.kill();
		numUps++;
		
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
	
}
