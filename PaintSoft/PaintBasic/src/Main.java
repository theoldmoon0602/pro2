import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("paint");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// add menu bar
		JMenuBar menuBar = new JMenuBar();
		frame.add(menuBar);
		
		//
		JButton redButton = new JButton("red");
		redButton.setBounds(10, 10, 60, 30);
		frame.add(redButton);
		
		JButton blackButton = new JButton("black");
		blackButton.setBounds(80, 10, 60, 30);
		frame.add(blackButton);
		
		
		JButton lineButton = new JButton("line");
		lineButton.setBounds(150, 10, 60, 30);
		frame.add(lineButton);
		
		JButton triangleButton = new JButton("triangle");
		triangleButton.setBounds(220, 10, 60, 30);
		frame.add(triangleButton);		
		
		frame.setVisible(true);
		
		//
		Drawer drawer = new Drawer(frame.getGraphics());
		Brush basicBrush = new BasicBrush();
		Brush lineBrush = new PolygonBrush(2);
		Brush triangleBrush = new PolygonBrush(3);
		drawer.setBrush(basicBrush);
		
		redButton.addActionListener(new ColorChanger(drawer, Color.RED));
		blackButton.addActionListener(new ColorChanger(drawer, Color.BLACK));
		lineButton.addActionListener(new BrushChanger(drawer, lineBrush));
		triangleButton.addActionListener(new BrushChanger(drawer, triangleBrush));
		
		
		frame.addMouseListener(drawer);
		frame.addMouseMotionListener(drawer);
	}

}
