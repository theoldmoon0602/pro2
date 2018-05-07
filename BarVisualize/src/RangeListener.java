import javax.swing.JLabel;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class RangeListener implements AdjustmentListener {
	private Integer v;
	private JLabel vlabel;
	private JLabel vmeter;
	public RangeListener(Integer v, JLabel vlabel, JLabel vmeter) {
		this.v = v;
		this.vlabel = vlabel;
		this.vmeter = vmeter;
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		v = e.getValue();
		vlabel.setText(v.toString());
		vmeter.setSize(v, (int) vmeter.getSize().getHeight());
	}

}
