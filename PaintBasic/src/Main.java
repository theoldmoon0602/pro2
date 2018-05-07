import java.awt.Color;

import javax.swing.*;

public class Main {
	

	public static void main(String[] args) {
		// create frame and set
		JFrame frame = new JFrame("ぺいんと");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// state
		Color[] paintColor = { Color.BLACK }; // 渡した先で変更したいのでこんなことをしている
		Mode[] mode = { Mode.DRAW };
		
		// set color button
		JButton redbutton = new JButton("赤");
		redbutton.setBounds(10, 10, 60, 30);
		redbutton.addActionListener(new ColorChanger(paintColor, Color.RED));
		frame.add(redbutton);
		
		JButton blackbutton = new JButton("黒");
		blackbutton.setBounds(80, 10, 60, 30);
		blackbutton.addActionListener(new ColorChanger(paintColor, Color.BLACK));
		frame.add(blackbutton);
		
		// draw mode button
		JButton linebutton = new JButton("直線");
		linebutton.setBounds(150, 10, 60, 30);
		linebutton.addActionListener(new ModeChanger(mode, Mode.LINE));
		frame.add(linebutton);
		
		JButton trianglebutton = new JButton("三角形");
		trianglebutton.setBounds(220, 10, 60, 30);
		trianglebutton.addActionListener(new ModeChanger(mode, Mode.TRIANGLE));
		frame.add(trianglebutton);
		
		JButton normalline = new JButton("線");
		normalline.setBounds(290, 10, 60, 30);
		normalline.addActionListener(new ModeChanger(mode, Mode.DRAW));
		frame.add(normalline);
		
		// set mouse listener
		Painter painter = new Painter(frame, paintColor, mode);
		
		frame.addMouseListener(painter);
		frame.addMouseMotionListener(painter);
		
		// show frame
		frame.setVisible(true);

	}

}
