package name.tomflucke.components;

import java.awt.Graphics;
import java.awt.Image;

public class TiledImageComponent extends ImageComponent
{
	private static final long serialVersionUID = -5739339310458142503L;

	public TiledImageComponent(Image img)
	{
		super(img);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int width = getWidth(), height = getHeight();
		for (int x = 0; x < width; x += img.getWidth(null))
		{  
			for (int y = 0; y < height; y += img.getHeight(null))
			{  
				g.drawImage(img, x, y, this);  
            }
		}
	}
}
