package name.tomflucke.swixml;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;

public class FrameProperties extends Component
{
	private static final long serialVersionUID = -7674082407658447705L;
	
	private JFrame window;
	private Map<String, Object> values;
	
	public FrameProperties()
	{
		values = new HashMap<>();
		addHierarchyListener(new HierarchyListener()
		{
			@Override
			public void hierarchyChanged(HierarchyEvent he)
			{
				setWindow();
				if (window == null)
				{
					return;
				}
				try
				{
					for (Entry<String, Object> e : values.entrySet())
					{
						Class<?> type = e.getValue().getClass();
						switch(type.getName())
						{
							case "java.lang.Boolean":
								type = boolean.class;
								break;
							case "java.lang.Character":
								type = char.class;
								break;
							case "java.lang.Byte":
								type = byte.class;
								break;
							case "java.lang.Short":
								type = short.class;
								break;
							case "java.lang.Integer":
								type = int.class;
								break;
							case "java.lang.Long":
								type = long.class;
								break;
							case "java.lang.Float":
								type = float.class;
								break;
							case "java.lang.Double":
								type = double.class;
								break;
						}
						JFrame.class.getMethod(e.getKey(), type).invoke(window, e.getValue());
					}
				}
				catch (NoSuchMethodException nsme)
				{
					// TODO Auto-generated catch block
					nsme.printStackTrace();
				}
				catch (SecurityException se)
				{
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
				catch (IllegalAccessException iae)
				{
					// TODO Auto-generated catch block
					iae.printStackTrace();
				}
				catch (IllegalArgumentException iae)
				{
					// TODO Auto-generated catch block
					iae.printStackTrace();
				}
				catch (InvocationTargetException ite)
				{
					// TODO Auto-generated catch block
					ite.printStackTrace();
				}
			}
		});
	}

	public void setWindow()
	{
		Container comp = this.getParent();
		while (comp != null && !(comp instanceof JFrame))
		{
			comp = comp.getParent();
		}
		window = (JFrame) comp;
	}
	public void clearWindow()
	{
		window = null;
	}
	
	public void setAlwaysOnTop(boolean value)
	{
		if (window != null)
		{
			window.setAlwaysOnTop(value);
		}
		else
		{
			values.put("setAlwaysOnTop", value);
		}
	}
	public boolean isAlwaysOnTop()
	{
		return window.isAlwaysOnTop();
	}
	
	public void setAutoRequestFocus(boolean value)
	{
		if (window != null)
		{
			window.setAutoRequestFocus(value);
		}
		else
		{
			values.put("setAutoRequestFocus", value);
		}
	}
	public boolean isAutoRequestFocus()
	{
		return window.isAutoRequestFocus();
	}
	
	public void setBackground(Color value)
	{
		if (window != null)
		{
			window.setBackground(value);
		}
		else
		{
			values.put("setBackground", value);
		}
	}
	@Override
	public Color getBackground()
	{
		return new Color(0, 0, 0, 0);
	}

	public void setBounds(Rectangle value)
	{
		if (window != null)
		{
			window.setBounds(value);
		}
		else
		{
			values.put("setBounds", value);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void setCursor(int value)
	{
		if (window != null)
		{
			window.setCursor(value);
		}
		else
		{
			values.put("setCursor", value);
		}
	}
	public Cursor getCursor()
	{
		return window.getCursor();
	}

	public void setDefaultCloseOperation(int value)
	{
		if (window != null)
		{
			window.setDefaultCloseOperation(value);
		}
		else
		{
			values.put("setDefaultCloseOperation", value);
		}
	}
	public int getDefaultCloseOperation()
	{
		return window.getDefaultCloseOperation();
	}
	
	public void setDefaultLookAndFeelDecorated(boolean value)
	{
		JFrame.setDefaultLookAndFeelDecorated(value);
	}
	public boolean isDefaultLookAndFeelDecorated()
	{
		return JFrame.isDefaultLookAndFeelDecorated();
	}
	
	public void setEnabled(boolean value)
	{
		if (window != null)
		{
			window.setEnabled(value);
		}
		else
		{
			values.put("setEnabled", value);
		}
	}
	@Override
	public boolean isEnabled()
	{
		return false;
	}
	
	public void setExtendedState(int value)
	{
		if (window != null)
		{
			window.setExtendedState(value);
		}
		else
		{
			values.put("setExtendedState", value);
		}
	}
	public int getExtendedState()
	{
		return window.getExtendedState();
	}
	
	public void setFocusCycleRoot(boolean value)
	{
		if (window != null)
		{
			window.setFocusCycleRoot(value);
		}
		else
		{
			values.put("setFocusCycleRoot", value);
		}
	}
	public boolean isFocusCycleRoot()
	{
		return window.isFocusCycleRoot();
	}
	
	public void setFocusTraversalKeysEnabled(boolean value)
	{
		if (window != null)
		{
			window.setFocusTraversalKeysEnabled(value);
		}
		else
		{
			values.put("setFocusTraversalKeysEnabled", value);
		}
	}
	public boolean getFocusTraversalKeysEnabled()
	{
		return window.getFocusTraversalKeysEnabled();
	}
	
	public void setFocusTraversalPolicyProvider(boolean value)
	{
		if (window != null)
		{
			window.setFocusTraversalPolicyProvider(value);
		}
		else
		{
			values.put("setFocusTraversalPolicyProvider", value);
		}
	}
	public boolean isFocusTraversalPolicyProvider()
	{
		return window.isFocusTraversalPolicyProvider();
	}
	
	public void setFocusable(boolean value)
	{
		if (window != null)
		{
			window.setFocusable(value);
		}
		else
		{
			values.put("setFocusable", value);
		}
	}
	public boolean isFocusable()
	{
		return window.isFocusable();
	}
	
	public void setFocusableWindowState(boolean value)
	{
		if (window != null)
		{
			window.setFocusableWindowState(value);
		}
		else
		{
			values.put("setFocusableWindowState", value);
		}
	}
	public boolean getFocusableWindowState()
	{
		return window.getFocusableWindowState();
	}
	
	public void setFont(Font value)
	{
		if (window != null)
		{
			window.setFont(value);
		}
		else
		{
			values.put("setFont", value);
		}
	}
	public Font getFont()
	{
		return window.getFont();
	}
	
	public void setForeground(Color value)
	{
		if (window != null)
		{
			window.setForeground(value);
		}
		else
		{
			values.put("setForeground", value);
		}
	}
	@Override
	public Color getForeground()
	{
		return new Color(0, 0, 0, 0);
	}
	
	public void setGlassPane(Component value)
	{
		if (window != null)
		{
			window.setGlassPane(value);
		}
		else
		{
			values.put("setGlassPane", value);
		}
	}
	public Component getGlassPane()
	{
		return window.getGlassPane();
	}
	
	public void setIconImage(Image value)
	{
		if (window != null)
		{
			window.setIconImage(value);
		}
		else
		{
			values.put("setIconImage", value);
		}
	}
	public Image getIconImage()
	{
		return window.getIconImage();
	}
	
	public void setIgnoreRepaint(boolean value)
	{
		if (window != null)
		{
			window.setIgnoreRepaint(value);
		}
		else
		{
			values.put("setIgnoreRepaint", value);
		}
	}
	public boolean getIgnoreRepaint()
	{
		return window.getIgnoreRepaint();
	}
	
	public void setLocale(Locale value)
	{
		if (window != null)
		{
			window.setLocale(value);
		}
		else
		{
			values.put("setLocale", value);
		}
	}
	public Locale getLocale()
	{
		return window.getLocale();
	}
	
	public void setLocation(Point value)
	{
		if (window != null)
		{
			window.setLocation(value);
		}
		else
		{
			values.put("setLocation", value);
		}
	}
	
	public void setLocationByPlatform(boolean value)
	{
		if (window != null)
		{
			window.setLocationByPlatform(value);
		}
		else
		{
			values.put("setLocationByPlatform", value);
		}
	}
	public boolean isLocationByPlatform()
	{
		return window.isLocationByPlatform();
	}
	
	public void setLocationRelativeTo(Component value)
	{
		if (window != null)
		{
			window.setLocationRelativeTo(value);
		}
		else
		{
			values.put("setLocationRelativeTo", value);
		}
	}
	
	public void setMaximizedBounds(Rectangle value)
	{
		if (window != null)
		{
			window.setMaximizedBounds(value);
		}
		else
		{
			values.put("setMaximizedBounds", value);
		}
	}
	public Rectangle getMaximizedBounds()
	{
		return window.getMaximizedBounds();
	}
	
	public void setMaximumSize(Dimension value)
	{
		if (window != null)
		{
			window.setMaximumSize(value);
		}
		else
		{
			values.put("setMaximumSize", value);
		}
	}
	@Override
	public Dimension getMaximumSize()
	{
		return new Dimension(0, 0);
	}
	
	public void setMinimumSize(Dimension value)
	{
		if (window != null)
		{
			window.setMinimumSize(value);
		}
		else
		{
			values.put("setMinimumSize", value);
		}
	}
	@Override
	public Dimension getMinimumSize()
	{
		return new Dimension(0, 0);
	}
	
	public void setName(String value)
	{
		if (window != null)
		{
			window.setName(value);
		}
		else
		{
			values.put("setName", value);
		}
	}
	public String getName()
	{
		return window.getName();
	}
	
	public void setOpacity(float value)
	{
		if (window != null)
		{
			window.setOpacity(value);
		}
		else
		{
			values.put("setOpacity", value);
		}
	}
	public float getOpacity()
	{
		return window.getOpacity();
	}
	
	public void setPreferredSize(Dimension value)
	{
		if (window != null)
		{
			window.setPreferredSize(value);
		}
		else
		{
			values.put("setPreferredSize", value);
		}
	}
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(0, 0);
	}
	
	public void setResizable(boolean value)
	{
		if (window != null)
		{
			window.setResizable(value);
		}
		else
		{
			values.put("setResizable", value);
		}
	}
	public boolean isResizable()
	{
		return window.isResizable();
	}
	
	public void setShape(Shape value)
	{
		if (window != null)
		{
			window.setShape(value);
		}
		else
		{
			values.put("setShape", value);
		}
	}
	public Shape getShape()
	{
		return window.getShape();
	}
	
	public void setSize(Dimension value)
	{
		if (window != null)
		{
			window.setSize(value);
		}
		else
		{
			values.put("setSize", value);
		}
	}
	@Override
	public Dimension getSize()
	{
		return new Dimension(0, 0);
	}
	
	public void setState(int value)
	{
		if (window != null)
		{
			window.setState(value);
		}
		else
		{
			values.put("setState", value);
		}
	}
	public int getState()
	{
		return window.getState();
	}
	
	public void setTitle(String value)
	{
		if (window != null)
		{
			window.setTitle(value);
		}
		else
		{
			values.put("setTitle", value);
		}
	}
	public String getTitle()
	{
		return window.getTitle();
	}
	
	public void setUndecorated(boolean value)
	{
		if (window != null)
		{
			window.setUndecorated(value);
		}
		else
		{
			values.put("setUndecorated", value);
		}
	}
	public boolean isUndecorated()
	{
		return window.isUndecorated();
	}
	
	public void setVisible(boolean value)
	{
		if (window != null)
		{
			window.setVisible(value);
		}
		else
		{
			values.put("setVisible", value);
		}
	}
	@Override
	public boolean isVisible()
	{
		return false;
	}
}
