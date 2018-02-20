package game;

import java.awt.Graphics2D;

public class Map {
	
	private Player player;
	
	private Obstacle[] objects;
	
	public Map() {
		
		player = new Player(100,100);
		
	}
	
	public void update() {
		
		player.update();
		
	}
	
	public void draw(Graphics2D g) {
		player.draw(g);
	}
	
	public void updatePlayer(double direction, double speed) {
		player.updateMovement(direction, speed);
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
