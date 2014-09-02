package name.tomflucke.dragNdrop;

public class OrderEvent
{
	private static int nextId;
	
	private final int id;
	private final Object item;
	private final int oldIndex;
	private final int newIndex;
	
	public OrderEvent(Object item, int oldIndex, int newIndex)
	{
		id = nextId++;
		this.item = item;
		this.oldIndex = oldIndex;
		this.newIndex = newIndex;
	}

	public int getId()
	{
		return id;
	}
	public Object getItem()
	{
		return item;
	}
	public int getOldIndex()
	{
		return oldIndex;
	}
	public int getNewIndex()
	{
		return newIndex;
	}
}
