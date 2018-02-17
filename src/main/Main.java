package main;

import javax.swing.JFrame;

import screen.Screen;

public class Main {
	
	public static void main(String[] args) {
		
		JFrame main = new JFrame("Gennetic Runner || pre alpha");
		
		Screen screen = new Screen();
		
		main.add(screen);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setResizable(false);
		main.pack();
		main.setLocationRelativeTo(null);
		
		main.setVisible(true);
		
		screen.start();
		
	}
	
}
