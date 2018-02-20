package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Obstacle {
	
	private int x,y, size;
	private Color colour;
	
	public Obstacle(int x, int y) {
		this.x = x;
		this.y = y;
		colour = Color.BLUE;
		size = 50;
	}
	
	public void draw(Graphics2D g) {
		
	}
	
}
