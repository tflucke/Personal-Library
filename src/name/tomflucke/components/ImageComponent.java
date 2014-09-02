package name.tomflucke.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JComponent;

public class ImageComponent extends JComponent
{
	private static final long serialVersionUID = 5643099405324781421L;

	private final Image img;
	
	public ImageComponent(Image img)
	{
		this.img = img;
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setMinimumSize(getPreferredSize());
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 0, getWidth(), getHeight(), null);
	}
}
