package name.tomflucke.dragNdrop;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.event.ChangeEvent;

public class CollectiveComponentMover extends ComponentMover
{
	protected ArrayList<Component> selected;
	private Map<Component, Point> origins;
	private int addSelectMask = 0;
	private int selectMask = Integer.MAX_VALUE;
	private int deselectMask = 0;
	private boolean fireonDrag = false;
	
	public CollectiveComponentMover()
	{
		selected = new ArrayList<Component>();
		origins = new HashMap<Component, Point>();
	}
	
	public CollectiveComponentMover(Component... selected)
	{
		this();
		select(selected);
	}
	
	public void setAddSelectKeys(int mask)
	{
		addSelectMask = mask;
	}
	
	public boolean isFiringOnDrag()
	{
		return fireonDrag;
	}
	
	public void setFireOnDrag(boolean flag)
	{
		fireonDrag = flag;
	}
	
	public int getAddSelectKeys()
	{
		return addSelectMask;
	}
	
	public void setSelectKeys(int mask)
	{
		selectMask = mask;
	}
	
	public int getSelectKeys()
	{
		return selectMask;
	}
	
	public void setDeselectKeys(int mask)
	{
		deselectMask = mask;
	}
	
	public int getDeselectKeys()
	{
		return deselectMask;
	}
	
	public void clearSelected()
	{
		selected.clear();
		origins.clear();
	}
	
	public void select(Component comp)
	{
		selected.add(comp);
		origins.put(comp, comp.getLocation());
	}
	
	public void select(Component[] comps)
	{
		for (Component comp : comps)
		{
			select(comp);
		}
	}
	
	public void deselect(Component comp)
	{
		selected.remove(comp);
		origins.remove(comp);
	}
	
	public void deselect(Component[] comps)
	{
		for (Component comp : comps)
		{
			deselect(comp);
		}
	}
	
	public boolean isSelect(Component comp)
	{
		return selected.contains(comp);
	}
	
	public Component[] getSelected()
	{
		return selected.toArray(new Component[0]);
	}
	
	@Override
	public void deregisterComponent(Component... comp)
	{
		super.deregisterComponent(comp);
		deselect(comp);
	}
	
	@Override
	public void mousePressed(MouseEvent me)
	{
		if (!isSelect(me.getComponent())
		        && (me.getModifiers() & (selectMask | addSelectMask)) == 0)
		{
			return;
		}
		else if ((me.getModifiers() & deselectMask) == deselectMask
		        || (!selected.contains(destination) && (me.getModifiers() & addSelectMask) != addSelectMask))
		{
			selected.clear();
			origins.clear();
		}
		super.mousePressed(me);
		if (destination != null)
		{
			select(destination);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent me)
	{
		Point dragged = me.getLocationOnScreen();
		int dragX = getDragDistance(dragged.x, pressed.x, snapSize.width);
		int dragY = getDragDistance(dragged.y, pressed.y, snapSize.height);
		
		if (fireonDrag)
		{
			for (Component comp : selected)
			{
				moveComponent(comp, origins.get(comp), dragX, dragY);
				fireChangeEvent(new ChangeEvent(comp));
			}
		}
		else
		{
			for (Component comp : selected)
			{
				moveComponent(comp, origins.get(comp), dragX, dragY);
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent me)
	{
		super.mouseReleased(me);
		for (Component comp : selected)
		{
			fireChangeEvent(new ChangeEvent(comp));
		}
	}
}
