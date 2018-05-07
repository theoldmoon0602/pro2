import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncrementListener implements ActionListener {
	private JLabel label;
	
	public IncrementListener(JLabel label) {
		this.label = label;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int v = Integer.valueOf(this.label.getText());
		this.label.setText(String.valueOf(v+1));
	}

}
