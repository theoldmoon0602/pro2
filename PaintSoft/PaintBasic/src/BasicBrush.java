import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;


public class BasicBrush implements Brush {
	private Point p;
	public BasicBrush() {
		p = null;
	}
	
	public void mousePressed(Graphics g, MouseEvent e) {
		p = e.getPoint();
	}
	
	public void mouseReleased(Graphics g, MouseEvent e) {
		p = null;
	}
	
	public void mouseDragged(Graphics g, MouseEvent e) {
		if (p == null) {
			p = e.getPoint();
		}
		g.drawLine((int)p.getX(), (int)p.getY(), (int)e.getX(), (int)e.getY());
		p = e.getPoint();
	}

	public void mouseClicked(Graphics g, MouseEvent e) {
		
	}
}
