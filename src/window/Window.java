package window;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;

public abstract class Window extends Canvas{

	private static final long serialVersionUID = 1L;

	public int width, height;
	protected static String title;
	public static JFrame frame;

	public Window(int width, int height, String title) {
		this.width = width;
		this.height = height;
		Window.title = title;
		frame = new JFrame();
		setFocusable(true);
		requestFocus();
		applyLookAndFeel();
//		WIN_CLASS();
	}
	
	public void WIN_CLASS() {
		try {
			Toolkit xToolkit = Toolkit.getDefaultToolkit();
			java.lang.reflect.Field awtAppClassNameField = xToolkit.getClass().getDeclaredField("awtAppClassName");
			awtAppClassNameField.setAccessible(true);
			awtAppClassNameField.set(xToolkit, title + "-omkar");
		} catch (Exception e) {

		}
	}

	public abstract void render(Graphics2D g);
	
	public void paint(Graphics gr) {
		Graphics2D g = (Graphics2D) gr;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		render(g);
	}
	
	public void applyLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setIcon(String path) {
		try {
			URL url = getClass().getResource("/" + path);
			Image imgicon = ImageIO.read(url);
			frame.setIconImage(imgicon);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void display() {
		frame.add(this);
		frame.pack();
		frame.setSize(width, height + frame.getInsets().top);
		frame.setTitle(title);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}