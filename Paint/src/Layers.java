import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public class Layers implements MessageProcessor {
	private List<Layer> layers;
	private BufferedImage tmpbuf;
	private int current;
	private int w, h;
	
	public Layers(int w, int h) {
		this.w = w;
		this.h = h;
		layers = new ArrayList<>();
		current = -1;
	}
	
	public Layer getCurrentLayer() {
		return layers.get(current);
	}
	
	public void select(int number) {
		current = number;
	}

	public void addLayer() {
		layers.add(new Layer(w, h));
	}
	
	public BufferedImage getTmpBuf() {
		if (tmpbuf == null) {
			return newTmpBuf();
		}
		return tmpbuf;
	}
	
	public BufferedImage newTmpBuf() {
		tmpbuf = Layer.copyImage(getCurrentLayer().getBuffer());
		return tmpbuf;
	}
	
	public void setTmpBuf(BufferedImage image) {
		tmpbuf = Layer.copyImage(image);		
	}
	
	@Override
	public void processMessage(Message m, Paint p) {
		for (Layer layer : layers) {
			layer.processMessage(m, p);
			
		}
	}

	public void reflect() {
		getCurrentLayer().push();
		getCurrentLayer().setBuffer(tmpbuf);
		
		tmpbuf = null;
	}
}
