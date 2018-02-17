package screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Screen extends Canvas implements Runnable{
	
	public static int width = 640, height = 480;
	public static double scale = 2.0;
	
	private Thread screenThread;
	private boolean isRunning = false;
	
	private BufferedImage image;
	private Graphics2D g2d;
	
	public Screen() {
		
		setPreferredSize(new Dimension((int)(width*scale), (int)(height*scale)));
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g2d = (Graphics2D)image.getGraphics();
		
		
	}
	
	public void start() {
		if(isRunning)return;
		
		isRunning = true;
		screenThread = new Thread(this);
		screenThread.start();
		
	}
	
	public void stop() {
		if(!isRunning)return;
		try {
			screenThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		double UPS = 60.0;
		double ns = 1000000000.0/UPS;
		double lastTime = System.nanoTime();
		double carry = 0;
		
		int updates = 0, frames = 0;
		long printTime = System.currentTimeMillis();
		
		while(isRunning) {
			
			carry += System.nanoTime() - lastTime;
			lastTime = System.nanoTime();
			
			while(carry >= ns) {
				update();
				updates++;
				carry -= ns;
			}
			
			draw();
			drawToScreen();
			frames ++;
			
			if(System.currentTimeMillis() - printTime >= 1000) {
				System.out.println("UPS: " + updates + " || Frames: " + frames);
				updates = frames = 0;
				printTime = System.currentTimeMillis();
			}
			
		}
		
	}
	
	private void update() {
		
	}
	
	private void draw() {
		
		g2d.setColor(Color.blue);
		g2d.fillRect(0, 0, width, height);
		
	}
	
	private void drawToScreen() {
		
		BufferStrategy bs = getBufferStrategy();
		
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, (int)(width*scale), (int)(height*scale), null);
		g.dispose();
		
		bs.show();
		
	}
	
}
