package name.tomflucke.dragNdrop;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class DragDropListener<T> extends MouseAdapter
{
	public static final short DRAG_ZONE = 1;
	public static final short DROP_ZONE = 2;

	private final Map<Component, Integer> zoneTypes;
	private final ArrayList<Component> registered;

	private T dragData;
	private Component releaseComp;

	public DragDropListener()
	{
		zoneTypes = new HashMap<Component, Integer>();
		registered = new ArrayList<Component>();
		for (Window w : Window.getOwnerlessWindows())
		{
			w.addMouseListener(this);
		}
	}

	public DragDropListener(Component... comps)
	{
		this();
		register(comps);
	}

	public void register(Component... comps)
	{
		for (Component comp : comps)
		{
			register(comp);
		}
	}

	public void register(Component comp)
	{
		register(DROP_ZONE | DRAG_ZONE, comp);
	}

	public void register(int zone, Component... comps)
	{
		for (Component comp : comps)
		{
			register(zone, comp);
		}
	}

	public void register(int zone, Component comp)
	{
		comp.addMouseListener(this);
		comp.addMouseMotionListener(this);
		zoneTypes.put(comp, zone);
		registered.add(comp);
	}

	public void deregister(Component... comps)
	{
		for (Component comp : comps)
		{
			deregister(comp);
		}
	}

	public void deregister(Component comp)
	{
		comp.removeMouseListener(this);
		comp.removeMouseMotionListener(this);
		registered.remove(comp);
	}

	private void setCursor(Cursor newCursor)
	{
		for (Component comp : registered)
		{
			comp.setCursor(newCursor);
		}
	}

	@Override
	public void mousePressed(MouseEvent me)
	{
		if (zoneTypes.containsKey(me.getComponent())
				&& (zoneTypes.get(me.getComponent()) & DRAG_ZONE) != 0)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
			dragData = drag(me.getComponent(), me.getPoint());
			releaseComp = me.getComponent();
		}
	}

	@Override
	public void mouseEntered(MouseEvent me)
	{
		releaseComp = me.getComponent();
	}

	@Override
	public void mouseExited(MouseEvent me)
	{
		releaseComp = null;
	}

	@Override
	public void mouseReleased(MouseEvent me)
	{
		if (releaseComp != null && dragData != null && zoneTypes.containsKey(releaseComp)
				&& (zoneTypes.get(releaseComp) & DROP_ZONE) != 0)
		{
			Point screenLoc = me.getLocationOnScreen(), compLoc = releaseComp.getLocationOnScreen();
			drop(releaseComp, new Point(screenLoc.x - compLoc.x, screenLoc.y - compLoc.y), dragData);
		}
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		dragData = null;
		releaseComp = null;
		endDrag();
	}

	public abstract T drag(Component comp, Point p);

	public abstract void drop(Component comp, Point p, T data);

	public abstract void endDrag();
}