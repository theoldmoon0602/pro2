import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public class Layer implements MessageProcessor {
	private List<BufferedImage> undolist;
	private List<BufferedImage> redolist;
	private BufferedImage image;

	public Layer(int w, int h) {
		undolist = new ArrayList<>();
		redolist = new ArrayList<>();
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	}
	
	public static BufferedImage copyImage(BufferedImage image) {
		ColorModel cm = image.getColorModel();
		boolean isAlphaPremultiplied = image.isAlphaPremultiplied();
		WritableRaster raster = image.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	public BufferedImage getBuffer() {
		return image;
	}
	
	public void setBuffer(BufferedImage image) {
		this.image = copyImage(image);
	}

	public void push() {
		undolist.add(Layer.copyImage(image));
	}
	
	public BufferedImage pop() {
		BufferedImage tmp = undolist.get(undolist.size()-1);
		undolist.remove(undolist.size()-1);
		return tmp;
	}
	
	@Override
	public void processMessage(Message m, Paint p) {
		switch (m) {
		case UNDO:
			if (undolist.size() < 1) {
				break;
			}
			// redolist.add(copyImage(image));
			image = pop();
			p.getLayers().newTmpBuf();
			p.getCanvas().repaint();
			break;

		default:
			break;
		}
	}

}
