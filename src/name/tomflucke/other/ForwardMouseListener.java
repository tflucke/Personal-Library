package name.tomflucke.other;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class ForwardMouseListener implements MouseListener, MouseMotionListener
{
	private final ArrayList<MouseListener> mouseListeners;
	private final ArrayList<MouseMotionListener> mouseMotionListeners;
	
	public ForwardMouseListener()
	{
		mouseListeners = new ArrayList<MouseListener>();
		mouseMotionListeners = new ArrayList<MouseMotionListener>();
	}

	public void addMouseListener(MouseListener ml)
	{
		mouseListeners.add(ml);
	}
	public void addMouseMotionListener(MouseMotionListener mml)
	{
		mouseMotionListeners.add(mml);
	}
	public void removeMouseListener(MouseListener ml)
	{
		mouseListeners.remove(ml);
	}
	public void removeMouseMotionListener(MouseMotionListener mml)
	{
		mouseMotionListeners.remove(mml);
	}
	
	public void registerComponent(Component... components)
	{
		for (Component component : components)
		{
			registerComponent(component);
		}
	}
	public void registerComponent(Component comp)
	{
		comp.addMouseListener(this);
		comp.addMouseMotionListener(this);
	}
	public void deregisterComponent(Component... components)
	{
		for (Component component : components)
		{
			deregisterComponent(component);
		}
	}
	public void deregisterComponent(Component comp)
	{
		comp.removeMouseListener(this);
		comp.removeMouseMotionListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent me)
	{
		Component parent = me.getComponent().getParent();
		MouseEvent me2 = new MouseEvent(parent, me.getID(), me.getWhen(), me.getModifiers(), me.getX(), me.getY(),
				me.getXOnScreen(), me.getYOnScreen(), me.getClickCount(), me.isPopupTrigger(), me.getButton());
		for (MouseMotionListener mml : mouseMotionListeners)
		{
			mml.mouseDragged(me2);
		}
	}

	@Override
	public void mouseMoved(MouseEvent me)
	{
		Component parent = me.getComponent().getParent();
		MouseEvent me2 = new MouseEvent(parent, me.getID(), me.getWhen(), me.getModifiers(), me.getX(), me.getY(),
				me.getXOnScreen(), me.getYOnScreen(), me.getClickCount(), me.isPopupTrigger(), me.getButton());
		for (MouseMotionListener mml : mouseMotionListeners)
		{
			mml.mouseMoved(me2);
		}
	}

	@Override
	public void mouseClicked(MouseEvent me)
	{
		Component parent = me.getComponent().getParent();
		MouseEvent me2 = new MouseEvent(parent, me.getID(), me.getWhen(), me.getModifiers(), me.getX(), me.getY(),
				me.getXOnScreen(), me.getYOnScreen(), me.getClickCount(), me.isPopupTrigger(), me.getButton());
		for (MouseListener ml : mouseListeners)
		{
			ml.mouseClicked(me2);
		}
	}

	@Override
	public void mouseEntered(MouseEvent me)
	{
		Component parent = me.getComponent().getParent();
		MouseEvent me2 = new MouseEvent(parent, me.getID(), me.getWhen(), me.getModifiers(), me.getX(), me.getY(),
				me.getXOnScreen(), me.getYOnScreen(), me.getClickCount(), me.isPopupTrigger(), me.getButton());
		for (MouseListener ml : mouseListeners)
		{
			ml.mouseEntered(me2);
		}
	}

	@Override
	public void mouseExited(MouseEvent me)
	{
		Component parent = me.getComponent().getParent();
		MouseEvent me2 = new MouseEvent(parent, me.getID(), me.getWhen(), me.getModifiers(), me.getX(), me.getY(),
				me.getXOnScreen(), me.getYOnScreen(), me.getClickCount(), me.isPopupTrigger(), me.getButton());
		for (MouseListener ml : mouseListeners)
		{
			ml.mouseExited(me2);
		}
	}

	@Override
	public void mousePressed(MouseEvent me)
	{
		Component parent = me.getComponent().getParent();
		MouseEvent me2 = new MouseEvent(parent, me.getID(), me.getWhen(), me.getModifiers(), me.getX(), me.getY(),
				me.getXOnScreen(), me.getYOnScreen(), me.getClickCount(), me.isPopupTrigger(), me.getButton());
		for (MouseListener ml : mouseListeners)
		{
			ml.mousePressed(me2);
		}
	}

	@Override
	public void mouseReleased(MouseEvent me)
	{
		Component parent = me.getComponent().getParent();
		MouseEvent me2 = new MouseEvent(parent, me.getID(), me.getWhen(), me.getModifiers(), me.getX(), me.getY(),
				me.getXOnScreen(), me.getYOnScreen(), me.getClickCount(), me.isPopupTrigger(), me.getButton());
		for (MouseListener ml : mouseListeners)
		{
			ml.mouseReleased(me2);
		}
	}
}
