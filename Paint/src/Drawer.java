import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Drawer implements MouseMotionListener, MouseListener, MessageProcessor {
	private Paint p;
	public Drawer(Paint p) {
		this.p = p;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Graphics2D g = (Graphics2D)p.getTmpBuffer().createGraphics();
		p.getBrush().mouseClicked(g, arg0);
		p.getCanvas().repaint();
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		Graphics2D g = (Graphics2D)p.getTmpBuffer().createGraphics();
		p.getBrush().mouseEntered(g, arg0);
		p.getCanvas().repaint();
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		Graphics2D g = (Graphics2D)p.getTmpBuffer().createGraphics();
		p.getBrush().mouseExited(g, arg0);
		p.getCanvas().repaint();
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		Graphics2D g = (Graphics2D)p.getTmpBuffer().createGraphics();
		p.getBrush().mousePressed(g, arg0);
		p.getCanvas().repaint();
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		Graphics2D g = (Graphics2D)p.getTmpBuffer().createGraphics();
		p.getBrush().mouseReleased(g, arg0);
		p.getCanvas().repaint();
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		Graphics2D g = (Graphics2D)p.getTmpBuffer().createGraphics();
		p.getBrush().mouseDragged(g, arg0);
		p.getCanvas().repaint();
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		Graphics2D g = (Graphics2D)p.getTmpBuffer().createGraphics();
		p.getBrush().mouseMoved(g, arg0);
		p.getCanvas().repaint();
	}
	@Override
	public void processMessage(Message m, Paint p) {
		switch (m) {
		case TIMER:
//			Graphics g = p.clearCanvas();
//			g.drawImage(p.getTmpBuffer(), 0, 0, null);
			
			break;
		}
		p.getBrush().processMessage(m, p);		
	}

}
