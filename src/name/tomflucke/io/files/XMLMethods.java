package name.tomflucke.io.files;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public abstract class XMLMethods
{
	public static void removeWhitespace(Element e)
	{
		NodeList children = e.getChildNodes();
		for (int i = children.getLength() - 1; i >= 0; i--)
		{
			Node child = children.item(i);
			if (child instanceof Text && ((Text) child).getData().trim().length() == 0)
			{
				e.removeChild(child);
			}
			else if (child instanceof Element)
			{
				removeWhitespace((Element) child);
			}
		}
	}
}
