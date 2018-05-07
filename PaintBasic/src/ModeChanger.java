import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeChanger implements ActionListener {
	private Mode[] mode;
	private Mode modeto;
	public ModeChanger(Mode[] mode, Mode modeto) {
		this.mode = mode;
		this.modeto = modeto;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.mode[0] = modeto;
	}

}
