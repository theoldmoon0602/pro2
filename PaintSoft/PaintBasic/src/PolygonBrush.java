import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;


public class PolygonBrush implements Brush {
	private int nside;
	private List<Point> pts;
	
	public PolygonBrush(int i) {
		nside = i;
		pts = new ArrayList<>();
	}

	public void mousePressed(Graphics g, MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(Graphics g, MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseDragged(Graphics g, MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseClicked(Graphics g, MouseEvent e) {
		pts.add(e.getPoint());
		System.out.println(pts.size());
		if (pts.size() == nside) {
			for (int i = 0; i <= nside; i++) {
				g.drawLine((int)pts.get(i%nside).getX(), (int)pts.get(i%nside).getY(),
						(int)pts.get((i+1)%nside).getX(), (int)pts.get((i+1)%nside).getY());
			}
			pts.clear();
		}
	}

}
