package name.tomflucke.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import name.tomflucke.dragNdrop.CollectiveComponentMover;
import name.tomflucke.dragNdrop.ComponentResizer;

public class DragDropPanel extends JPanel
{
	private static final long serialVersionUID = 8085669591522147899L;

	private final CollectiveComponentMover dragger;
	private final ComponentResizer resizer;
	private final ArrayList<ChangeListener> listeners;
	
	private Color highlightColor;
	private int borderSize;

	private Rectangle highlightRectangle;

	public DragDropPanel()
	{
		super(null);

		dragger = new CollectiveComponentMover();
		dragger.setDragInsets(new Insets(5, 5, 5, 5));
		resizer = new ComponentResizer();

		listeners = new ArrayList<ChangeListener>();
		
		highlightColor = new Color(0, 0, 255, 255 / 4);
		borderSize = 4;
		highlightRectangle = null;

		MouseAdapter ma = new MouseAdapter()
		{
			private Point start;

			@Override
			public void mousePressed(MouseEvent me)
			{
				start = me.getPoint();
				highlightRectangle = new Rectangle(start);
				dragger.clearSelected();
				repaint();
			}

			@Override
			public void mouseDragged(MouseEvent me)
			{
				if (start != null)
				{
					highlightRectangle = new Rectangle(start);
					highlightRectangle.add(me.getPoint());
					repaint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent me)
			{
				if (start != null)
				{
					for (Component comp : getComponents())
					{
						if (highlightRectangle.contains(comp.getBounds()))
						{
							dragger.select(comp);
						}
					}
					highlightRectangle = null;
					repaint();
				}
			}
		};
		addMouseListener(ma);
		addMouseMotionListener(ma);
	}

	private void fireChange(ChangeEvent ce)
	{
		for (ChangeListener cl : listeners)
		{
			cl.stateChanged(ce);
		}
	}
	
	public void setSelectKeys(int mask)
	{
		dragger.setSelectKeys(mask);
	}

	public int getSelectKeys()
	{
		return dragger.getSelectKeys();
	}

	public void setDeselectKeys(int mask)
	{
		dragger.setDeselectKeys(mask);
	}

	public int getDeselectKeys()
	{
		return dragger.getDeselectKeys();
	}

	public void setBorderSize(int px)
	{
		borderSize = px;
	}

	public void setBorderColor(Color c)
	{
		highlightColor = c;
	}

	public void selectComponent(Component comp)
	{
		dragger.select(comp);
	}

	public boolean isSelected(Component comp)
	{
		return dragger.isSelect(comp);
	}

	public Component[] getSelected()
	{
		return dragger.getSelected();
	}

	public void deselectAll()
	{
		dragger.clearSelected();
	}

	@Override
	public Component add(Component comp)
	{
		dragger.registerComponent(comp);
		resizer.registerComponent(comp);
		fireChange(new ChangeEvent(comp));
		return super.add(comp);
	}

	public Component add(Component comp, boolean draggable, boolean resizable)
	{
		if (draggable)
		{
			dragger.registerComponent(comp);
		}
		if (resizable)
		{
			resizer.registerComponent(comp);
		}
		fireChange(new ChangeEvent(comp));
		return super.add(comp);
	}

	@Override
	public void remove(Component comp)
	{
		super.remove(comp);
		fireChange(new ChangeEvent(comp));
		dragger.deregisterComponent(comp);
		resizer.deregisterComponent(comp);
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		for (Component comp : dragger.getSelected())
		{
			g.setColor(highlightColor);
			Rectangle rect = comp.getBounds();
			rect.add(rect.x - borderSize, rect.y - borderSize);
			rect.setSize(rect.width + borderSize, rect.height + borderSize);
			((Graphics2D) g).setStroke(new BasicStroke(borderSize));
			((Graphics2D) g).draw(rect);
		}
		if (highlightRectangle != null)
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(highlightColor);
			g2d.fill(highlightRectangle);
		}
	}

	public void addChangeListener(ChangeListener cl)
	{
		listeners.add(cl);
		dragger.addChangeListener(cl);
		resizer.addChangeListener(cl);
	}

	public void removeChangeListener(ChangeListener cl)
	{
		listeners.remove(cl);
		dragger.removeChangeListener(cl);
		resizer.removeChangeListener(cl);
	}

	public void setGridSize(int size)
	{
		Dimension d = new Dimension(size, size);
		dragger.setSnapSize(d);
		resizer.setSnapSize(d);
	}

	public int getGridSize()
	{
		return dragger.getSnapSize().height;
	}
}