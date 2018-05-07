import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;

public class ColorChanger implements ActionListener {
	private Color[] color;
	private Color colorto;
	
	public ColorChanger(Color[] color, Color colorto) {
		this.color = color;
		this.colorto = colorto;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.color[0] = colorto;
	}

}
