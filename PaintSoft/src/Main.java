import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("paint");
		frame.setSize(500, 500); // default size
		
		Painter painter = new Painter();
		frame.addMouseListener(painter);
		frame.addMouseMotionListener(painter);
		
		frame.setVisible(true);
	}
}
