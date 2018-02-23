package screen;

import java.awt.Graphics2D;

import game.Map;
import game.Obstacle;

public class GameScreen extends Screen{
	
	private Map map;
	
	public GameScreen(ScreenController screen) {
		super(screen);
	}
	
	public void init() {
		super.init();
		map = new Map(new Obstacle[] {new Obstacle(300,100), new Obstacle(300,200)});
		map.getPlayer().updateMovement(0, 0);
	}
	
	public void update() {
		
		if(!ScreenController.input.focus)return;
		
		
		if(ScreenController.input.left)map.getPlayer().incrementDirection(-0.01);
		if(ScreenController.input.right)map.getPlayer().incrementDirection(0.01);
		if(ScreenController.input.up)map.getPlayer().setSpeed(2);
		else if(ScreenController.input.down)map.getPlayer().setSpeed(-2);
		else map.getPlayer().setSpeed(0);
		
		map.update();
		
		
	}
	
	public void draw(Graphics2D g){
		map.draw(g);
	}
	
}
