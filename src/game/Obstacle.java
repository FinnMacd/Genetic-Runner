package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Obstacle {
	
	private int x,y, radius;
	private Color colour;
	
	public Obstacle(int x, int y) {
		this.x = x;
		this.y = y;
		colour = Color.BLUE;
		radius = 75;
	}
	
	public void draw(Graphics2D g, int screen) {
		
		x-= screen;
		
		g.setColor(colour);
		g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
		
		x+= screen;
		
	}
	
	public void setLocation(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	public boolean isSafe(Player p) {
		
		return !containsPoint(p.getX(),p.getY(),p.getRadius());
	}
	
	public boolean shouldCheck(Player p) {
		return containsPoint(p.checkPoint(),p.getCheckRadius());
	}
	
	public int[][] sightPoints(Player p) {
		
		int[][] ret = new int[3][3];
		
		for(int i = 0; i < 3; i++) {
		
			double length = 0;
			ret[i] = p.getSightPoints(i, length);
			while(!containsPoint(ret[i], 0) && length <= 1) {
				length += 0.05;
				ret[i] = p.getSightPoints(i, length);
			}
			
		}
		
		return ret;
		
	}
	
	private boolean containsPoint(int xt, int yt, int radius) {
		int xDiff = xt-x;
		int yDiff = yt-y;
		
		return	Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)) < this.radius + radius;
	}
	
	private boolean containsPoint(int[] xy, int radius) {
		return containsPoint(xy[0],xy[1],radius);
	}
	
}
