import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;


public class Drawer implements MouseInputListener {
	private Graphics g;
	private Brush brush;
	
	public Drawer(Graphics g) {
		this.g = g;
		this.brush = null;
	}
	
	public Graphics getGraphics() {
		return g;
	}
	
	public void setBrush(Brush brush) {
		this.brush = brush;
	}

	public void mouseClicked(MouseEvent arg0) {
		brush.mouseClicked(g, arg0);
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		brush.mousePressed(g, arg0);
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		brush.mouseReleased(g, arg0);
	}

	public void mouseDragged(MouseEvent arg0) {
		brush.mouseDragged(g, arg0);
	}

	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
