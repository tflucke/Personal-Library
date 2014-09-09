package name.tomflucke.components.table;

import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.AbstractCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class PanelRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor
{
	private static final long serialVersionUID = 595795585148652553L;

	protected final JTable table;

	protected final TableCellRenderer baseRenderer;
	protected final TableCellEditor baseEditor;

	private final JPanel panel;
	
	protected Component renderer;
	protected Component editor;
	
	public PanelRenderer(JTable table, TableCellRenderer renderer, TableCellEditor editor)
	{
		this.table = table;
		baseRenderer = renderer;
		baseEditor = editor;
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
	}
	
	@Override
	public Object getCellEditorValue()
	{
		return baseEditor.getCellEditorValue();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
			int row, int column)
	{
		panel.removeAll();
		editor = baseEditor.getTableCellEditorComponent(table, value, isSelected, row, column);
		panel.add(editor);
		panel.setBackground(table.getSelectionBackground());
		panel.setForeground(table.getSelectionForeground());
		return panel;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column)
	{
		panel.removeAll();
		renderer = baseRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		panel.add(renderer);
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
