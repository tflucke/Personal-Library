package name.tomflucke.io.textFields;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;

public class NumericFilter extends DocumentFilter implements FilterMethods
{
	public static void putFilter(JTextComponent comp)
	{
		((AbstractDocument) comp.getDocument()).setDocumentFilter(new NumericFilter());
	}

	public static void putFilter(JTextComponent comp, boolean decimal)
	{
		((AbstractDocument) comp.getDocument()).setDocumentFilter(new NumericFilter(decimal));
	}

	public static void putFilter(JTextComponent comp, boolean decimal, boolean negative)
	{
		((AbstractDocument) comp.getDocument()).setDocumentFilter(new NumericFilter(decimal, negative));
	}

	private final boolean decimal;
	private final boolean negative;

	public NumericFilter(boolean decimal, boolean negative)
	{
		this.decimal = decimal;
		this.negative = negative;
	}

	public NumericFilter(boolean decimal)
	{
		this(decimal, false);
	}

	public NumericFilter()
	{
		this(false, false);
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
	public String filterInsertedString(FilterBypass fb, int offs, String str, AttributeSet a)
	{
		String result = new String();
		if (negative && offs == 0 && str.charAt(0) == '-')
		{
			result = "-";
		}
		try
		{
			Document doc = fb.getDocument();
			int firstDecimal = str.indexOf('.');
			if (decimal && firstDecimal != -1 && doc.getText(0, doc.getLength()).indexOf('.') == -1)
			{
				result += str.substring(0, firstDecimal).replaceAll("\\D", "");
				result += '.';
				result += str.substring(firstDecimal+1).replaceAll("\\D", "");
			}
			else
			{
				result += str.replaceAll("\\D", "");
			}
		}
		catch (BadLocationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String filterReplacedString(FilterBypass fb, int offset,
			int length, String str, AttributeSet attrs)
	{
		return filterInsertedString(fb, offset, str, attrs);
	}
}
