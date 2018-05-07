import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public abstract class Brush implements MessageProcessor {
	protected Paint p;
	public Brush(Paint p) {
		this.p = p;
	}
	
	public void mouseEntered(Graphics2D g, MouseEvent e) {
		setGraphicsProperty(g);
	}
	public void mouseExited(Graphics2D g, MouseEvent e) {
		setGraphicsProperty(g);
	}
	public void mouseClicked(Graphics2D g, MouseEvent e) {
		setGraphicsProperty(g);
	}
	public void mouseMoved(Graphics2D g, MouseEvent e) {
		setGraphicsProperty(g);
	}
	public void mouseDragged(Graphics2D g, MouseEvent e) {
		setGraphicsProperty(g);
	}
	public void mouseReleased(Graphics2D g, MouseEvent e) {
		setGraphicsProperty(g);
	}
	public void mousePressed(Graphics2D g, MouseEvent e) {
		setGraphicsProperty(g);
	}
	
	public void setGraphicsProperty(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	@Override
	public void processMessage(Message m, Paint p) {
		switch (m) {
	
		case DRAW:	
			p.getLayers().reflect();
			
		default:
			break;
		}		
	}
}
