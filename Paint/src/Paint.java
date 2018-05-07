import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

public class Paint implements ActionListener, MessageProcessor {
	private JFrame window;
	private Canvas canvas;
	private JMenuBar menubar;
	private JButton undoButton;
	private JComboBox<String> brushSelector;
	private JButton colorSelector;
	private JButton rainbowPenButton;
	private JButton eraserButton;
	private JSlider thicknessSlider;
	private JButton resetButton;
	private Timer timer;
	
	private Color color;
	
	private int w;
	private int h;
	
	private Brush brush;
	private Layers layers;
	private Drawer drawer;
	
	private List<MessageProcessor> receivers;
	
	public static void main(String[] args) {
		Paint paint = new Paint();
		paint.run();
	}
	
	public Paint() {
		
		w = 600;
		h = 600;
		
		receivers = new ArrayList<>();
		drawer = new Drawer(this);
		window = new JFrame();
		brush = new BasicBrush(this);
		color = Color.BLACK;
		layers = new Layers(w, h);
		canvas = new Canvas(this);
		menubar = new JMenuBar();
		undoButton = new JButton("UNDO");
		colorSelector = new JButton("");
		brushSelector = new JComboBox<String>(new String[]{
				"Pen", "Line", "Triangle"
		});
		rainbowPenButton = new JButton("rainbow pen");
		eraserButton = new JButton("eraser");
		thicknessSlider = new JSlider(1, 20);
		resetButton = new JButton("Reset");
		timer = new Timer(60, this);
		
		init();
	}
	public void init() {
	
		window.setLayout(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(700,700);
		
		canvas.setBounds(100, 10, w, h);
		canvas.setBorder(new LineBorder(Color.BLACK, 1));
		canvas.addMouseListener(drawer);
		canvas.addMouseMotionListener(drawer);
		window.add(canvas);
		
		receivers.add(drawer);
		
		layers.addLayer();
		layers.select(0);
		receivers.add(layers);		
		
		window.setJMenuBar(menubar);
		
		
		undoButton.setBounds(10, 10, 80, 30);
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				postMessage(Message.UNDO);
				
			}
		});
		window.add(undoButton);
		
		colorSelector.setBackground(getColor());
		colorSelector.setBounds(10, 50, 80, 30);
		colorSelector.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color c = JColorChooser.showDialog(window, "Color", getColor());
				setColor(c);
			}
		});
		window.add(colorSelector);
		
		
		brushSelector.setBounds(10, 90, 80, 30);
		brushSelector.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch((String)getBrushSelector().getSelectedItem()) {
				case "Pen":
					setBrush(new BasicBrush(getThis()));
					break;
				case "Line":
					setBrush(new PolyBrush(getThis(), 2));
					break;
				case "Triangle":
					setBrush(new PolyBrush(getThis(), 3));
					break;
				
				}
			}
		});	
		window.add(brushSelector);		

		rainbowPenButton.setBounds(10, 130, 80, 30);
		rainbowPenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setBrush(new RainbowBrush(getThis()));
			}
		});
		window.add(rainbowPenButton);
		
		eraserButton.setBounds(10, 170, 80, 30);
		eraserButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setBrush(new Eraser(getThis()));
			}
		});
		window.add(eraserButton);
		
		thicknessSlider.setBounds(10, 210, 80, 30);
		window.add(thicknessSlider);
		
		resetButton.setBounds(10, 250, 80, 30);
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getLayers().getCurrentLayer().push();
				BufferedImage image = getLayers().getTmpBuf();
				getLayers().setTmpBuf(new BufferedImage(image.getWidth(), image.getHeight(), image.getType()));
				getLayers().getCurrentLayer().push();
			}
		});
		window.add(resetButton);
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem load = new JMenuItem("Load");
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser saveFileChooser = new JFileChooser();
				if (saveFileChooser.showSaveDialog(window) == JFileChooser.APPROVE_OPTION) {
					File of = saveFileChooser.getSelectedFile();
					String ext = Paint.getExtension(of.getName());
					
					BufferedImage buf;
					if (ext.equals("png")) {
						buf = new BufferedImage(getBuffer().getWidth(), getBuffer().getHeight(), BufferedImage.TYPE_INT_ARGB);
						buf.createGraphics().drawImage(getBuffer(), 0, 0, null);
					}
					else {
						buf = new BufferedImage(getBuffer().getWidth(), getBuffer().getHeight(), BufferedImage.TYPE_INT_RGB);
						buf.createGraphics().drawImage(getBuffer(), 0, 0, Color.WHITE, null);
					}
					
					
					try {
						ImageIO.write(buf, ext, of);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}								
			}
		});
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser openFileChooser = new JFileChooser();
				openFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("jpegファイル", "jpg", "jpeg"));
				openFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("pngファイル", "png"));
				openFileChooser.addChoosableFileFilter(openFileChooser.getAcceptAllFileFilter());
				if (openFileChooser.showOpenDialog(window) == JFileChooser.APPROVE_OPTION) {
					File in = openFileChooser.getSelectedFile();
					try {
						getLayers().setTmpBuf(ImageIO.read(in));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		fileMenu.add(save);
		fileMenu.add(load);
		menubar.add(fileMenu);
		
		window.setJMenuBar(menubar);
		
	}	
	
	public void run() {
		window.setVisible(true);
		timer.start();
	}
	
	public void postMessage(Message m) {
		processMessage(m, this);
		for (MessageProcessor receiver : receivers) {
			receiver.processMessage(m, this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		postMessage(Message.TIMER);
	}

	public Layers getLayers() {
		return layers;
	}

	public void setLayers(Layers layers) {
		this.layers = layers;
	}

	public JPanel getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public BufferedImage getBuffer() {
		return getLayers().getCurrentLayer().getBuffer();
	}

	
	public BufferedImage getTmpBuffer() {
		return getLayers().getTmpBuf();
	}
	
	
	public Graphics clearCanvas() {
		Graphics g = canvas.getGraphics();
		g.clearRect(1, 1, canvas.getWidth()-2, canvas.getHeight()-2);
		return g;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		postMessage(Message.COLORCHANGE);
	}

	public int getThickness() {
		return thicknessSlider.getValue();
	}


	public JComboBox<String> getBrushSelector() {
		return brushSelector;
	}

	public Brush getBrush() {
		return brush;
	}

	public void setBrush(Brush brush) {
		this.brush = brush;
	}

	@Override
	public void processMessage(Message m, Paint p) {
		switch(m) {
		case COLORCHANGE:
			colorSelector.setBackground(getColor());
			break;
		}
	}
	
	public Paint getThis() {
		return this;
	}
	
	private static String getExtension(String fname) {
		int i = fname.lastIndexOf('.');
		return fname.substring(i+1);
	}
	
}
