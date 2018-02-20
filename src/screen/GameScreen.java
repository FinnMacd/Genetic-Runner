package screen;

import java.awt.Graphics2D;

import game.Map;

public class GameScreen extends Screen{
	
	private Map map;
	
	public GameScreen(ScreenController screen) {
		super(screen);
	}
	
	public void init() {
		map = new Map();
		map.getPlayer().updateMovement(0, 1);
	}
	
	public void update() {
		
		if(!ScreenController.input.focus)return;
		
		
		if(ScreenController.input.left)map.getPlayer().incrementDirection(0.01);
		if(ScreenController.input.right)map.getPlayer().incrementDirection(-0.01);
		
		map.update();
		
		
	}
	
	public void draw(Graphics2D g){
		map.draw(g);
	}
	
}
