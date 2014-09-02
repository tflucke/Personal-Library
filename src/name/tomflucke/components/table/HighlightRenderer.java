package name.tomflucke.components.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class HighlightRenderer extends DefaultTableCellRenderer
{
	private static final long serialVersionUID = -3227241521641407116L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column)
	{
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		if (isSelected)
		{
			setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.BLACK));
			setBackground(Color.YELLOW);
			setOpaque(true);
		}
		else
		{
			setOpaque(false);
		}
		return this;
	}
}