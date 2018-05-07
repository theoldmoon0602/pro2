import java.awt.Graphics;
import java.awt.event.MouseEvent;


public interface Brush {
	default void mouseClicked(Graphics g, MouseEvent e) {}
	default void mousePressed(Graphics g, MouseEvent e) {}
	default void mouseReleased(Graphics g, MouseEvent e) {}
	default void mouseDragged(Graphics g, MouseEvent e) {}
}
