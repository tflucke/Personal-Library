package name.tomflucke.components.table;

public class BooleanButtonRenderer extends ButtonRenderer
{
	private static final long serialVersionUID = 1758170620389334800L;
	
	private final Object trueValue;
	private final Object falseValue;
	
	public BooleanButtonRenderer(Object trueValue, Object falseValue)
	{
		this.trueValue = trueValue;
		this.falseValue = falseValue;
	}
	
	@Override
	protected Object getDisplay(Object value, boolean isSelected, int row,
	        int column)
	{
		return ((boolean) value) ? trueValue : falseValue;
	}
	
	@Override
	protected Object onClick(Object value, boolean isSelected, int row,
	        int column)
	{
		return !((boolean) value);
	}
	
}
