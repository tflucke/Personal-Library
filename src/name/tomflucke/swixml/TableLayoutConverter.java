package name.tomflucke.swixml;

import java.awt.LayoutManager;

import name.tomflucke.layouts.TableLayout;

import org.swixml.Attribute;
import org.swixml.LayoutConverter;
import org.swixml.LayoutConverterLibrary;
import org.w3c.dom.Element;

public class TableLayoutConverter implements LayoutConverter
{
	private final String numberRegex = "[\\+-]?\\d*\\.?\\d+";
	
	static
	{
		LayoutConverterLibrary.getInstance()
				.register(TableLayout.class, new TableLayoutConverter());
	}

	@Override
	public Object convertConstraintsAttribute(Attribute attr)
	{
		return attr.getValue();
	}

	@Override
	public Object convertConstraintsElement(Element elm)
	{
		return null;
	}

	@Override
	public LayoutManager convertLayoutAttribute(Attribute attr)
	{
		String str = attr.getValue().replaceAll("\\s", "").toLowerCase();
		str = str.substring(0, "tablelayout".length());
		str = str.replaceAll("(tablelayout\\.)?fill", String.valueOf(TableLayout.FILL));
		str = str.replaceAll("(tablelayout\\.)?preferred", String.valueOf(TableLayout.PREFERRED));
		if (str.equals("") || str.equals("()"))
		{
			return new TableLayout();
		}
		else if (str.matches("\\(\\{\\{"+numberRegex+"(,"+numberRegex+")\\},\\{"+numberRegex+"(,"+numberRegex+")\\}\\}\\)"))
		{
			str = str.replaceAll("[(\\(\\{)(\\}\\))]", "");
			String[] xNy = str.split("\\},\\{");
			String[] x = xNy[0].split(",");
			String[] y = xNy[1].split(",");
			double[][] xNyNum = new double[2][];
			xNyNum[0] = new double[x.length];
			for (int i = 0; i < x.length; i++)
			{
				xNyNum[0][i] = Double.valueOf(x[i]);
			}
			xNyNum[1] = new double[y.length];
			for (int i = 0; i < y.length; i++)
			{
				xNyNum[1][i] = Double.valueOf(y[i]);
			}
			return new TableLayout(xNyNum);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	@Override
	public LayoutManager convertLayoutElement(Element elm)
	{
		double[][] args = new double[2][0];
		if (elm.hasAttribute("cols"))
		{
			String cols = elm.getAttribute("cols").toLowerCase().trim();
			cols = cols.replaceAll("(tablelayout\\.)?fill", String.valueOf(TableLayout.FILL));
			cols = cols.replaceAll("(tablelayout\\.)?preferred", String.valueOf(TableLayout.PREFERRED));
			if (cols.matches("\\{.*\\}"))
			{
				cols = cols.substring(1, cols.length()-1);
			}
			String[] x;
			if (cols.matches(numberRegex+"(\\s*,\\s*"+numberRegex+")*"))
			{
				x = cols.split("\\s*,\\s*");
			}
			else
			{
				x = cols.split("\\s+");
			}
			args[0] = new double[x.length];
			for (int i = 0; i < x.length; i++)
			{
				args[0][i] = Double.valueOf(x[i]);
			}
		}
		if (elm.hasAttribute("rows"))
		{
			String rows = elm.getAttribute("rows").toLowerCase().trim();
			rows = rows.replaceAll("(tablelayout\\.)?fill", String.valueOf(TableLayout.FILL));
			rows = rows.replaceAll("(tablelayout\\.)?preferred", String.valueOf(TableLayout.PREFERRED));
			if (rows.matches("\\{.*\\}"))
			{
				rows = rows.substring(1, rows.length()-1);
			}
			String[] y;
			if (rows.matches(numberRegex+"(\\s*,\\s*"+numberRegex+")*"))
			{
				y = rows.split("\\s*,\\s*");
			}
			else
			{
				y = rows.split("\\s+");
			}
			args[1] = new double[y.length];
			for (int i = 0; i < y.length; i++)
			{
				args[1][i] = Double.valueOf(y[i]);
			}
		}
		return new TableLayout(args);
	}

	@Override
	public String getID()
	{
		return "tablelayout";
	}

}
