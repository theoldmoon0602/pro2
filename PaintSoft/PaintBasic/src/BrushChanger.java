import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BrushChanger implements ActionListener {
	private Drawer drawer;
	private Brush brush;

	public BrushChanger(Drawer drawer, Brush brush) {
		this.drawer = drawer;
		this.brush = brush;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		drawer.setBrush(brush);
	}

}
