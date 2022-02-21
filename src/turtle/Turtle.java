package turtle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import window.Launcher;

public class Turtle {

	public ArrayList<Vertex> vertices;

	int x, y;
	double angle;

	Parser parser;

	boolean penup, showTurtle;

	BufferedImage turtle;

	Launcher game;

	public Turtle(Launcher game, String commands) {
		this.game = game;
		reset();
		execute(commands);
	}
	
	public void execute(String commands) {
		parser = new Parser(this, commands.trim());
		try {
			turtle = ImageIO.read(getClass().getResource("/turtle.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reset() {
		vertices = new ArrayList<>();
		home();
		penup = false;
		showTurtle = true;
	}

	public void home() {
		x = game.width / 2;
		y = game.height / 2;
		vertices.add(new Vertex(x, y));
		angle = -90;
	}

	public void fd(int value) {
		Vertex last = vertices.get(vertices.size() - 1);
		double xn = last.x + value * Math.cos(Math.toRadians(angle));
		double yn = last.y + value * Math.sin(Math.toRadians(angle));
		Vertex vert = new Vertex(xn, yn);
		if (penup) {
			vert.c = new Color(0,0,0,0);
		} else {
			vert.c = Color.black;
		}
		vertices.add(vert);
	}

	public void rt(int value) {
		angle += value;
		angle %= 360;
	}

	public void penup() {
		penup = true;
	}

	public void pendown() {
		penup = false;
	}

	public void draw(Graphics2D g) {
		g.setStroke(new BasicStroke(1f));
		for (int i = vertices.size() - 1; i >= 1; i--) {
			Vertex a = vertices.get(i);
			g.setColor(a.c);
			Vertex b = vertices.get(i - 1);
			g.drawLine((int) (a.x), (int) (a.y), (int) (b.x), (int) (b.y));
		}
		if (showTurtle) {
			Vertex last = vertices.get(vertices.size() - 1);
			int r = 8;
			int x = (int) (last.x + r * Math.cos(Math.toRadians(angle)));
			int y = (int) (last.y + r * Math.sin(Math.toRadians(angle)));
			AffineTransform old = g.getTransform();
			g.rotate(Math.toRadians(angle + 90), x, y);
			g.drawImage(turtle, x - 8, y - 8, 15, 20, null);
			g.setTransform(old);
		}
	}

	public void clearScreen() {
		reset();
	}

}
