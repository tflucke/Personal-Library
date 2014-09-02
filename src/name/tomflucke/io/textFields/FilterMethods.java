package name.tomflucke.io.textFields;

import javax.swing.text.AttributeSet;
import javax.swing.text.DocumentFilter.FilterBypass;

public interface FilterMethods
	{
		public String filterInsertedString(FilterBypass fb, int offs, String str, AttributeSet a);
		public String filterReplacedString(FilterBypass fb, int offset, int length, String text, AttributeSet attrs);
	}
