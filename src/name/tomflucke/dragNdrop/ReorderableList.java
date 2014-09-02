package name.tomflucke.dragNdrop;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

public class ReorderableList<E> extends JList<E> implements Reorderable
{
	private static final long serialVersionUID = -8903772041728552684L;

	private static class OrderListener<E> extends MouseAdapter
	{
		private boolean mouseDragging = false;
		private ReorderableList<E> source;
		private int dragSourceIndex;

		@SuppressWarnings("unchecked")
		@Override
		public void mousePressed(MouseEvent me)
		{
			if (SwingUtilities.isLeftMouseButton(me))
			{
				Component comp = me.getComponent();
				if (comp instanceof JList<?>)
				{
					source = ((ReorderableList<E>) comp);
					dragSourceIndex = source.getSelectedIndex();
					mouseDragging = true;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent me)
		{
			mouseDragging = false;
		}

		@Override
		public void mouseDragged(MouseEvent me)
		{
			if (mouseDragging)
			{
				int currentIndex = source.locationToIndex(me.getPoint());
				if (currentIndex != dragSourceIndex)
				{
					int dragTargetIndex = source.getSelectedIndex();
					source.reorder(dragSourceIndex, dragTargetIndex);
					dragSourceIndex = currentIndex;
					source.fireOrderChange(new OrderEvent(source.getModel().getElementAt(
							dragSourceIndex), dragSourceIndex, dragTargetIndex));
				}
			}
		}
	}

	private final ArrayList<ChangeOrderListener> listeners;

	{
		listeners = new ArrayList<ChangeOrderListener>();
		MouseAdapter ol = new OrderListener<E>();
		addMouseListener(ol);
		addMouseMotionListener(ol);
	}

	public ReorderableList()
	{}

	public ReorderableList(E[] listData)
	{
		super(listData);
	}

	public ReorderableList(ListModel<E> dataModel)
	{
		super(dataModel);
	}

	public ReorderableList(Vector<? extends E> listData)
	{
		super(listData);
	}

	public void addOrderChangeListener(ChangeOrderListener col)
	{
		listeners.add(col);
	}

	public void removeOrderChangeListener(ChangeOrderListener col)
	{
		listeners.remove(col);
	}

	private void fireOrderChange(OrderEvent oe)
	{
		for (ChangeOrderListener col : listeners)
		{
			col.orderChanged(oe);
		}
	}

	@Override
	public void reorder(int fromIndex, int toIndex)
	{
		int j = 0;
		E dragElement = getModel().getElementAt(fromIndex);
		@SuppressWarnings("unchecked")
		E[] newData = (E[]) Array.newInstance(dragElement.getClass(), getModel().getSize());
		for (int i = 0; i < newData.length; i++)
		{
			if (j == toIndex)
			{
				newData[j++] = dragElement;
			}
			if (i != fromIndex)
			{
				newData[j++] = getModel().getElementAt(i);
			}
		}
		if (j == toIndex)
		{
			newData[j++] = dragElement;
		}
		setListData(newData);
	}
}
