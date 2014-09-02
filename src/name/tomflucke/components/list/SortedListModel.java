package name.tomflucke.components.list;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;

public class SortedListModel<T> extends AbstractListModel<T>
	{
		private static final long serialVersionUID = -9171279194646583184L;
		
		// Define a SortedSet
		SortedSet<T> model;

		public SortedListModel()
			{
				// Create a TreeSet
				// Store it in SortedSet variable
				model = new TreeSet<T>();
			}

		// ListModel methods
		public int getSize()
			{
				// Return the model size
				return model.size();
			}

		@SuppressWarnings("unchecked")
		public T getElementAt(int index)
			{
				// Return the appropriate element
				return (T) model.toArray()[index];
			}

		// Other methods
		public void add(T element)
			{
				if (model.add(element))
					{
						fireContentsChanged(this, 0, getSize());
					}
			}

		public void addAll(Object elements[])
			{
				@SuppressWarnings("unchecked")
				Collection<T> c = (Collection<T>) Arrays.asList(elements);
				model.addAll(c);
				fireContentsChanged(this, 0, getSize());
			}

		public void clear()
			{
				model.clear();
				fireContentsChanged(this, 0, getSize());
			}

		public boolean contains(Object element)
			{
				return model.contains(element);
			}

		public Object firstElement()
			{
				// Return the appropriate element
				return model.first();
			}

		public Iterator<T> iterator()
			{
				return model.iterator();
			}

		public Object lastElement()
			{
				// Return the appropriate element
				return model.last();
			}

		public boolean removeElement(Object element)
			{
				boolean removed = model.remove(element);
				if (removed)
					{
						fireContentsChanged(this, 0, getSize());
					}
				return removed;
			}
	}
