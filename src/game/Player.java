package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	
	private int maxSpeed = 5, radius = 20;
	private double x,y,direction, speed;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.direction = 0;
		this.speed = 0;
	}
	
	public void update() {
		x += Math.cos(direction*Math.PI*2)*speed;
		y += Math.sin(direction*Math.PI*2)*speed;
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.GREEN);
		g.fillOval((int)x-radius, (int)y-radius, 2*radius, 2*radius);
		g.setColor(Color.red);
		g.drawLine((int)x, (int)y, (int)(x+30*Math.cos(direction*Math.PI*2)), (int)(y+30*Math.sin(direction*Math.PI*2)));
		
	}
	
	public void updateMovement(double direction, double speed) {
		this.direction = direction;
		this.speed = speed;
	}
	
	public void incrementDirection(double i) {
		direction += i;
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
	
}
