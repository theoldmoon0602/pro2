import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;


public class BasicBrush extends Brush {
	private Point point;

	public BasicBrush(Paint p) {
		super(p);
		point = null;
	}

	
	@Override
	public void mouseReleased(Graphics2D g, MouseEvent e) {
		point = null;
		p.postMessage(Message.DRAW);
	}

	@Override
	public void mouseDragged(Graphics2D g, MouseEvent e) {
		super.mouseDragged(g, e);
		if (point == null) {
			point = e.getPoint();
		}
		g.setColor(p.getColor());
		g.setStroke(new BasicStroke((float)p.getThickness()));
		g.drawLine((int)point.getX(), (int)point.getY(), e.getX(), e.getY());
		
		point = e.getPoint();
	}

}
