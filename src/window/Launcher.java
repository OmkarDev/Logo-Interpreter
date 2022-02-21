package window;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JTextField;

import turtle.Turtle;

public class Launcher extends Window implements KeyListener {
	private static final long serialVersionUID = 1L;

	public String commands = "cs";

	Turtle turtle;
	JTextField text_field;

	ArrayList<String> history = new ArrayList<String>();

	public Launcher(int width, int height, String title) {
		super(width, height, title);
		turtle = new Turtle(this, commands.toLowerCase());
		repaint();
		history.add("repeat 8[ repeat 4[rt 90 fd 100 ]bk 100 lt 45]");
		text_field = new JTextField("");
		text_field.setSize(width, 35);
		text_field.setVisible(true);
		text_field.addKeyListener(this);
		frame.add(text_field);
	}

	public void render(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		turtle.draw(g);
	}

	public static void main(String[] args) {
		Launcher logo = new Launcher(1280, 550, "LOGO Interpreter");
		logo.display();
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			text_field.setText(history.get(index));
			if (index > 0) {
				index--;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			index++;
			try {
				text_field.setText(history.get(index));
			} catch (Exception ex) {
				text_field.setText("");
				index = history.size() - 1;
			}
		}
	}

	int index = 0;

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String commands = text_field.getText();
			text_field.setText("");
			if (!history.get(history.size() - 1).equals(commands.toLowerCase())) {
				history.add(commands.toLowerCase());
			}
			turtle.execute(history.get(history.size() - 1));
			index = history.size() - 1;
			repaint();
		}
	}
}
