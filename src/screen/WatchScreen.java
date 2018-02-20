package screen;

import java.awt.Graphics2D;

import game.Map;
import game.Obstacle;
import math.Matrix;
import network.Network;

public class WatchScreen extends Screen{
	
	private Network network;
	
	private Map map;
	
	public WatchScreen(ScreenController screen) {
		super(screen);
	}
	
	public void init() {
		network = new Network(4,7,2);
		map = new Map();
		map.setObstacles(new Obstacle[] {
				new Obstacle(200,300)
		});
		map.start();
	}
	
	public void update(){
		
		if(map.getPlayer().isAlive()) {
			Matrix next = network.simpleTest(Matrix.rowMatrix(new double[] {
					map.getPlayer().getSightLength(0),
					map.getPlayer().getSightLength(1),
					map.getPlayer().getSightLength(2), 
					map.getPlayer().getDirection()}));
			map.getPlayer().incrementDirection(next.getAttribute(0, 0));
			map.getPlayer().setSpeed(next.getAttribute(1, 0));
		}
		
		if(ScreenController.input.space) {
			map.reset();
			map.start();
		}
		if(ScreenController.input.ctrl)network = new Network(4,7,2);
		
		map.update();
		
	}
	
	public void draw(Graphics2D g) {
		map.draw(g);
	}
	
}
