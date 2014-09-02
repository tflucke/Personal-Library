package name.tomflucke.io.textFields;

import java.util.ArrayList;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;

public class ChainedFilter extends DocumentFilter
	{
		public static void putFilter(JTextComponent comp)
			{
				((AbstractDocument) comp.getDocument()).setDocumentFilter(new ChainedFilter());
			}

		private final ArrayList<FilterMethods> filters;
		
		public ChainedFilter()
			{
				filters = new ArrayList<FilterMethods>();
			}

		public void addFilter(FilterMethods fm)
			{
				filters.add(fm);
			}

		public void addFilter(FilterMethods fm, int i)
			{
				filters.add(i, fm);
			}

		public void removeFilter(FilterMethods fm)
			{
				filters.remove(fm);
			}
		
		public void removeFilter(int i)
			{
				filters.remove(i);
			}

		public FilterMethods getFilter(int i)
			{
				return filters.get(i);
			}

		public int getIndex(FilterMethods fm)
			{
				return filters.indexOf(fm);
			}
		
		@Override
		public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
			throws BadLocationException
			{
				String newStr = str;
				for (FilterMethods fm : filters)
					{
						newStr = fm.filterInsertedString(fb, offs, newStr, a);
					}
				super.insertString(fb, offs, newStr, a);
			}

		@Override
		public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
			throws BadLocationException
			{
				String newStr = str;
				for (FilterMethods fm : filters)
					{
						newStr = fm.filterInsertedString(fb, offs, newStr, a);
					}
				super.insertString(fb, offs, newStr, a);
			}
	}
