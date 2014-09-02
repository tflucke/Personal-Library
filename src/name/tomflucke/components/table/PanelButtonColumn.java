package name.tomflucke.components.table;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

public class PanelButtonColumn extends ButtonColumn
{
	private static final long serialVersionUID = 1115668583999162385L;

	private JPanel panel;

	public PanelButtonColumn(JTable table, ActionListener action)
	{
		super(table, action);
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		originalBorder = editButton.getBorder();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
			int row, int column)
	{
		panel.removeAll();
		editButton = (JButton) super.getTableCellEditorComponent(table, value, isSelected, row, column);
		panel.add(editButton);
		if (isSelected)
		{
			panel.setBackground(table.getSelectionBackground());
			panel.setForeground(table.getSelectionForeground());
		}
		else
		{
			panel.setBackground(table.getBackground());
			panel.setForeground(table.getForeground());
		}
		return panel;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column)
	{
		panel.removeAll();
		renderButton = (JButton) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		panel.add(renderButton);
		if (isSelected)
		{
			panel.setBackground(table.getSelectionBackground());
			panel.setForeground(table.getSelectionForeground());
		}
		else
		{
			panel.setBackground(table.getBackground());
			panel.setForeground(table.getForeground());
		}
		return panel;
	}
}
