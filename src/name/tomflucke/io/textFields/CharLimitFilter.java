package name.tomflucke.io.textFields;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class CharLimitFilter extends DocumentFilter implements FilterMethods
	{
		private final int limit;
		
		public CharLimitFilter(int limit)
			{
				this.limit = limit;
			}

		@Override
		public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
			throws BadLocationException
			{
				super.insertString(fb, offs, filterInsertedString(fb, offs, str, a), a);
			}

		@Override
		public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
			throws BadLocationException
			{
				super.replace(fb, offs, length, filterReplacedString(fb, offs, length, str, a), a);
			}

		@Override
		public String filterInsertedString(FilterBypass fb, int offs,
				String str, AttributeSet a)
			{
				if (str == null) return "";
				int remaining = limit - fb.getDocument().getLength();
				if (remaining > str.length())
					{
						return str;
					}
				else
					{
						return str.substring(0, remaining);
					}
			}

		@Override
		public String filterReplacedString(FilterBypass fb, int offset,
				int length, String str, AttributeSet attrs)
			{
				if (str == null) return "";
				int remaining = limit - fb.getDocument().getLength() + length;
				if (remaining > str.length())
					{
						return str;
					}
				else
					{
						return str.substring(0, remaining);
					}
			}
	}
