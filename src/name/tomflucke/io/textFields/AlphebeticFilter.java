package name.tomflucke.io.textFields;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;

public class AlphebeticFilter extends DocumentFilter implements FilterMethods
	{
		public static void putFilter(JTextComponent comp)
			{
				((AbstractDocument) comp.getDocument()).setDocumentFilter(new AlphebeticFilter());
			}
		public static void putFilter(JTextComponent comp, boolean whitespace)
			{
				((AbstractDocument) comp.getDocument()).setDocumentFilter(new AlphebeticFilter(whitespace));
			}
		
		private final boolean whitespace;

		public AlphebeticFilter(boolean whitespace)
			{
				this.whitespace = whitespace;
			}

		public AlphebeticFilter()
			{
				this(false);
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
				String result = new String();
				for (char c : str.toCharArray())
					{
						if (Character.isAlphabetic(c) || (whitespace && Character.isWhitespace(c)))
							{
								result += c;
							}
					}
				return result;
			}
		@Override
		public String filterReplacedString(FilterBypass fb, int offset,
				int length, String str, AttributeSet attrs)
			{
				String result = new String();
				for (char c : str.toCharArray())
					{
						if (Character.isAlphabetic(c) || (whitespace && Character.isWhitespace(c)))
							{
								result += c;
							}
					}
				return result;
			}
	}
