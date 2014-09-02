package name.tomflucke.dragNdrop;

import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.util.Comparator;

import name.tomflucke.arrays.Sort;

public class LayoutComponentMover extends ComponentMover
{
	private LayoutManager layout;

	@Override
	public void mousePressed(MouseEvent me)
	{
		layout = me.getComponent().getParent().getLayout();
		me.getComponent().getParent().setLayout(null);
		super.mousePressed(me);
	}

	@Override
	public void mouseReleased(MouseEvent me)
	{
		super.mouseReleased(me);
		Container parent = me.getComponent().getParent();
		Component[] comps = parent.getComponents();
		parent.removeAll();
		Sort.mergeSort(comps, new Comparator<Component>()
		{
			@Override
			public int compare(Component A, Component B)
			{
				return A.getLocation().y - B.getLocation().y;
			}
		});
		for (Component c : comps)
		{
			parent.add(c);
		}
		parent.setLayout(layout);
		parent.revalidate();
		parent.repaint();
	}
}
