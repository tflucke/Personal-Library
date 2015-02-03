package name.tomflucke.components.table;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public abstract class ButtonRenderer extends AbstractCellEditor implements
        TableCellRenderer, TableCellEditor, MouseListener
{
	private static final long serialVersionUID = -5256901799955814537L;
	
	private JTable table;
	private final ButtonRendererAction buttonAction;
	private Object editValue;
	private boolean isButtonColumnEditor;
	
	{
		buttonAction = new ButtonRendererAction();
	}
	
	protected abstract Object onClick(Object value, boolean isSelected,
	        int row, int column);
	
	protected abstract Object getDisplay(Object value, boolean isSelected,
	        int row, int column);
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
	        boolean isSelected, boolean hasFocus, int row, int column)
	{
		return getTableCellEditorComponent(table, value, isSelected, row,
		        column);
	}
	
	/*
	 * TODO:
	 * Optimize this method a bit.  It's constantly creating redundant objects.
	 * Also, make creating it inside a panel optional
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
	        boolean isSelected, int row, int column)
	{
		Object display = getDisplay(value, isSelected, row, column);
		JButton button = new JButton();
		if (display instanceof Icon)
		{
			button.setIcon((Icon) display);
		}
		else if (display instanceof String)
		{
			button.setText((String) display);
		}
		buttonAction.setVariables(value, isSelected, row, column);
		button.addActionListener(buttonAction);
		button.addMouseListener(this);
		this.table = table;
		JPanel result = new JPanel();
		result.setLayout(new GridBagLayout());
		result.add(button);
		return result;
	}
	
	@Override
	public Object getCellEditorValue()
	{
		return editValue;
	}
	
	@Override
	public void mousePressed(MouseEvent me)
	{
		if (table.isEditing() && table.getCellEditor() == this)
		{
			isButtonColumnEditor = true;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent me)
	{
		if (isButtonColumnEditor && table.isEditing())
		{
			table.getCellEditor().stopCellEditing();
		}
		isButtonColumnEditor = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent me)
	{
	}
	
	@Override
	public void mouseEntered(MouseEvent me)
	{
	}
	
	@Override
	public void mouseExited(MouseEvent me)
	{
	}
	
	private class ButtonRendererAction implements ActionListener
	{
		private Object value;
		private boolean isSelected;
		private int row, column;

		public void setVariables(Object value, boolean isSelected, int row, int column)
		{
			this.value = value;
			this.isSelected = isSelected;
			this.row = row;
			this.column = column;
		}
		
		@Override
        public void actionPerformed(ActionEvent ae)
        {
			editValue = onClick(value, isSelected, row, column);
        }
	}
}