import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;


public class RainbowBrush extends BasicBrush {
	private float[] hsb;
	private int alpha; 
	public RainbowBrush(Paint p) {
		super(p);
		hsb = new float[3];
		storeHSB();
	}
	
	public void storeHSB() {
		Color c = p.getColor();
		Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsb);
		alpha = c.getAlpha() << 24;
	}
	
	@Override
	public void mouseDragged(Graphics2D g, MouseEvent e) {
		hsb[0] = (float) (((hsb[0]*100 + 1) % 100) / 100.0); // next hue
		int rgba = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);	// hue to rgb
		rgba = rgba | alpha; // set alpha value
		p.setColor(new Color(rgba));
		super.mouseDragged(g, e);
	}
	
	@Override
	public void processMessage(Message m, Paint p) {
		switch (m) {
		case COLORCHANGE:
			storeHSB();
			break;
		}
		super.processMessage(m, p);
	}

}
