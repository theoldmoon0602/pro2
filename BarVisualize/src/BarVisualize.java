import java.awt.Color;
import javax.swing.*;
import javax.swing.border.LineBorder;
public class BarVisualize {
	public static void main(String[] args) {
		// crete a frame
		JFrame frame = new JFrame("値入力");
		frame.setSize(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		
		Integer v = 0;
		
		// add meter value label
		JLabel vlabel = new JLabel(v.toString());
		vlabel.setBounds(100, 10, 30, 20);
		frame.add(vlabel);
		
		// add meter box label
		JLabel vmeter = new JLabel("");
		vmeter.setBounds(10, 80, v, 20);
		vmeter.setBorder(new LineBorder(Color.BLACK));
		frame.add(vmeter);
		
		// add range bar
		JScrollBar range = new JScrollBar(JScrollBar.HORIZONTAL, v, 0, 0, 100);
		range.setBounds(10, 40, 180, 20);
		range.addAdjustmentListener(new RangeListener(v, vlabel, vmeter));
		frame.add(range);
		
		// show the frame
		frame.setVisible(true);
	}
}
