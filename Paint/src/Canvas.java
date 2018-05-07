import java.awt.Graphics;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	private Paint p;
	
	public Canvas(Paint p) {
		super();
		this.p = p;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.clearRect(1, 1, this.getWidth()-2, this.getHeight()-2);
		g.drawImage(p.getTmpBuffer(), 0, 0, this);
	}
}
