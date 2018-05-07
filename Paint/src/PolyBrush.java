import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;



public class PolyBrush extends Brush {
	private int n;
	private int[] xs;
	private int[] ys;
	private int cnt;
	
	public PolyBrush(Paint p, int n) {
		super(p);
		this.n = n;
		xs = new int[n];
		ys = new int[n];
		cnt = 0;
	}
	
	@Override
	public void mouseClicked(Graphics2D g, MouseEvent e) {
		super.mouseClicked(g, e);
		xs[cnt] = e.getX();
		ys[cnt] = e.getY();
		cnt++;
		if (cnt == n) {
			g = (Graphics2D)p.getLayers().newTmpBuf().createGraphics();
			g.setColor(p.getColor());
			g.setStroke(new BasicStroke(p.getThickness()));
			g.drawPolygon(xs, ys, n);
			p.postMessage(Message.DRAW);
			
			xs = new int[n];
			ys = new int[n];
			cnt = 0;
		}
	}
	
	
	@Override
	public void mouseMoved(Graphics2D g, MouseEvent e) {
		super.mouseMoved(g, e);
		if (cnt > 0) {
			g = (Graphics2D)p.getLayers().newTmpBuf().createGraphics();
			g.setColor(p.getColor());
			g.setStroke(new BasicStroke(p.getThickness()));
			xs[cnt] = e.getX();
			ys[cnt] = e.getY();
			if (cnt == n-1) {
				g.drawPolygon(xs, ys, n);
			}
			else {
				g.drawPolyline(xs, ys, cnt+1);
			}
		}
	}

}
