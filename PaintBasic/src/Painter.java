import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

public class Painter implements MouseInputListener {
	private JFrame frame;
	private Color[] paintColor;
	private Mode[] mode;
	private List<Point> pts;
	public Painter(JFrame frame, Color[] paintColor, Mode[] mode) {
		this.frame = frame;
		this.paintColor = paintColor;
		this.mode = mode;
		pts = new ArrayList<>();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(mode[0].toString());
		
		// TODO 自動生成されたメソッド・スタブ
		if (mode[0] == Mode.LINE) {
			pts.clear();
			pts.add(e.getPoint());
			mode[0] = Mode.LINE_START;
		}
		else if (mode[0] == Mode.LINE_START) {
			Graphics g = frame.getGraphics();
			g.setColor(paintColor[0]);
			g.drawLine((int)pts.get(0).getX(), (int)pts.get(0).getY(), e.getX(), e.getY());
			mode[0] = Mode.LINE;
		}
		
		if (mode[0] == Mode.TRIANGLE) {
			pts.clear();
			pts.add(e.getPoint());
			mode[0] = Mode.TRIANGLE_START;
		}
		else if (mode[0] == Mode.TRIANGLE_START) {
			pts.add(e.getPoint());
			mode[0] = Mode.TRIANGLE_INTER;
		}
		else if (mode[0] == Mode.TRIANGLE_INTER) {
			Graphics g = frame.getGraphics();
			g.setColor(paintColor[0]);
			g.drawLine((int)pts.get(0).getX(), (int)pts.get(0).getY(), (int)pts.get(1).getX(), (int)pts.get(1).getY());
			g.drawLine((int)pts.get(1).getX(), (int)pts.get(1).getY(), e.getX(), e.getY());
			g.drawLine((int)pts.get(0).getX(), (int)pts.get(0).getY(), e.getX(), e.getY());
			mode[0] = Mode.TRIANGLE;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (mode[0] != Mode.DRAW) {
			return;
		}
		
		Graphics g = frame.getGraphics();
		g.setColor(this.paintColor[0]);
		g.fillOval(e.getX(), e.getY(), 2, 2);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

}
