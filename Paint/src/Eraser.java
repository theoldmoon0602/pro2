import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;



public class Eraser extends BasicBrush {

	public Eraser(Paint p) {
		super(p);
	}
	
	@Override
	public void mouseDragged(Graphics2D g, MouseEvent e) {
		g.setComposite(AlphaComposite.Clear);
		super.mouseDragged(g, e);
	}
}
