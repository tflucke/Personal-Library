package name.tomflucke.components.table;

import java.awt.Component;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRenderer extends DefaultTableCellRenderer
{
	private static final long serialVersionUID = 3872572871379642132L;

	private JLabel lbl;
	private ImageIcon icon;

	public ImageRenderer()
	{
		lbl = new JLabel();
		icon = new ImageIcon();
		lbl.setIcon(icon);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column)
	{
		if (value instanceof Image)
		{
			icon.setImage((Image) value);
			lbl.setIcon(icon);
		}
		else if (value instanceof Icon)
		{
			lbl.setIcon((Icon) value);;
		}
		if (isSelected)
		{
			lbl.setBackground(table.getSelectionBackground());
			lbl.setForeground(table.getSelectionForeground());
		}
		else
		{
			lbl.setBackground(table.getBackground());
			lbl.setForeground(table.getForeground());
		}
		return lbl;
	}
}