package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	
	private int maxSpeed = 5, radius = 40, checkRadius = 60;
	private double x,y,direction, speed, sightAngle = 0.08;
	
	private int[][] sightPoints;
	private boolean sightCheck = false, isAlive = false;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.direction = 0;
		this.speed = 0;
		sightPoints = new int[3][3];
	}
	
	public void update() {
		if(!isAlive)return;
		x += Math.cos(direction*Math.PI*2)*speed;
		y += Math.sin(direction*Math.PI*2)*speed;
		if(direction > 1.0)direction--;
		if(direction < 0.0)direction++;
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.GREEN);
		g.fillOval((int)x-radius, (int)y-radius, 2*radius, 2*radius);
		g.setColor(Color.red);
		
		g.drawLine((int)(x+radius*Math.cos(direction*Math.PI*2)), (int)(y+radius*Math.sin(direction*Math.PI*2)), 
				(int)(x+radius*3*Math.cos(direction*Math.PI*2)), (int)(y+radius*3*Math.sin(direction*Math.PI*2)));
		g.drawLine((int)(x+radius*Math.cos((direction+sightAngle)*Math.PI*2)), (int)(y+radius*Math.sin((direction+sightAngle)*Math.PI*2)), 
				(int)(x+radius*3*Math.cos((direction+sightAngle)*Math.PI*2)), (int)(y+radius*3*Math.sin((direction+sightAngle)*Math.PI*2)));
		g.drawLine((int)(x+radius*Math.cos((direction-sightAngle)*Math.PI*2)), (int)(y+radius*Math.sin((direction-sightAngle)*Math.PI*2)), 
				(int)(x+radius*3*Math.cos((direction-sightAngle)*Math.PI*2)), (int)(y+radius*3*Math.sin((direction-sightAngle)*Math.PI*2)));
		
		for(int [] pos:sightPoints) {
			g.fillOval((int)x + pos[0]-5, (int)y + pos[1] - 5, 10, 10);
		}
		
	}
	
	public void updateMovement(double direction, double speed) {
		this.direction = direction;
		this.speed = speed;
	}
	
	public void addSightPoints(int [][] points) {
		if(!sightCheck)sightPoints = points;
		for(int k = 0; k < 3; k++) {
			int[] i = sightPoints[k];
			if(sightCheck && i[2] >= points[k][2]) {
				sightPoints[k] = points[k];
			}
			if(!sightCheck || sightPoints[k] == points[k]){
				sightPoints[k][0] -= x;
				sightPoints[k][1] -= y;
			}
		}
		sightCheck = true;
	}
	
	public void checkSight() {
		if(!sightCheck) {
			for(int i = 0; i < 3; i++) {
				sightPoints[i] = getSightPoints(i,1.0);
				sightPoints[i][0] -= x;
				sightPoints[i][1] -= y;
			}
		}
		sightCheck = false;
	}
	
	public void setSpeed(double speed) {
		this.speed = (speed-0.5)*4;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void start() {
		isAlive = true;
	}
	
	public void reset(int x, int y) {
		isAlive = false;
		setPos(x,y);
		speed = 0;
		direction = 0;
	}
	
	public void incrementDirection(double i) {
		direction += (i-0.5)*0.02;
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public double getSightAngle() {
		return sightAngle;
	}
	
	public double getDirection() {
		return direction;
	}
	
	public int getCheckRadius() {
		return checkRadius;
	}
	
	public void kill() {
		isAlive = false;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public int[] checkPoint() {
		return new int[] {(int)(x+90*Math.cos((direction)*Math.PI*2)), (int)(y+90*Math.sin((direction)*Math.PI*2))};
	}
	
	public int[] getSightPoints(int i, double length) {
		
		double currAngle = direction-sightAngle + i*sightAngle;
		
		int[] ret = new int[3];
		
		ret[0] = (int)(x+(1.0 + length*2)*radius*Math.cos((currAngle)*Math.PI*2));
		ret[1] = (int)(y+(1.0 + length*2)*radius*Math.sin((currAngle)*Math.PI*2));
		ret[2] = (int)(length*100);
		
		return ret;
		
	}
	
	public double getSightLength(int i) {
		return ((double)sightPoints[i][2]/100.0);
	}
	
}
