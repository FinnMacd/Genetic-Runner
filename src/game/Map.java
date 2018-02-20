package game;

import java.awt.Graphics2D;

public class Map {
	
	private Player player;
	
	private Obstacle[] objects;
	
	public Map() {
		
		player = new Player(100,100);
		objects = new Obstacle[0];
		
	}
	
	public Map(Obstacle[] o) {
		
		player = new Player(100,100);
		objects = o;
		
	}
	
	public void update() {
		
		player.update();
		
	}
	
	public void draw(Graphics2D g) {
		player.draw(g);
		
		for(Obstacle o:objects) {
			o.draw(g);
			if(!o.isSafe(player))player.setPos(100, 100);
			if(o.shouldCheck(player))player.addSightPoints(o.sightPoints(player));
		}
		
		player.checkSight();
		
	}
	
	public void updatePlayer(double direction, double speed) {
		player.updateMovement(direction, speed);
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
