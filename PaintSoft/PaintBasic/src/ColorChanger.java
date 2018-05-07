import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;


public class ColorChanger implements ActionListener {
	private Drawer drawer;
	private Color color;
	public ColorChanger(Drawer drawer, Color color) {
		this.drawer = drawer;
		this.color = color;
	}

	public void actionPerformed(ActionEvent arg0) {
		drawer.getGraphics().setColor(color);
	}

}
