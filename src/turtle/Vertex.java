package turtle;

import java.awt.Color;

public class Vertex {

	double x, y;
	Color c;

	public Vertex(int x, int y) {
		this(x, y, Color.black);
	}

	public Vertex(double x, double y) {
		this(x, y, Color.black);
	}

	public Vertex(double x, double y, Color c) {
		this.x = x;
		this.y = y;
		this.c = c;
	}

}
