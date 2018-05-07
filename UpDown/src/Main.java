import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("加減算器");
		frame.setSize(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);
		
		// label
		JLabel label = new JLabel("0");
		label.setBounds(90, 10, 20, 10);
		frame.add(label);
		
		// increment/decrement buttons
		JButton incbutton = new JButton("+1");
		incbutton.setBounds(10, 40, 50, 30);
		incbutton.addActionListener(new IncrementListener(label));
		frame.add(incbutton);
		
		JButton decbutton = new JButton("-1");
		decbutton.setBounds(130, 40, 50, 30);
		decbutton.addActionListener(new DecrementListener(label));
		frame.add(decbutton);
		
		frame.setVisible(true);		
	}
}
