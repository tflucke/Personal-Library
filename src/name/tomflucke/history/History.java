package name.tomflucke.history;

import java.util.ArrayList;

public class History<T>
{
	private final Historical<T> obj;
	private final ArrayList<T> historyPoints;

	private int size;
	private int currentPoint;

	public History(Historical<T> obj, int size)
	{
		this.obj = obj;
		this.size = size;
		historyPoints = new ArrayList<T>(size);
		addHistoryPoint();
		currentPoint = 0;
	}

	public History(Historical<T> obj)
	{
		this(obj, 20);
	}

	public int getSize()
	{
		return size;
	}
	public void setSize(int newSize)
	{
		size = newSize;
	}

	public void addHistoryPoint()
	{
		clearTo(currentPoint);
		if (historyPoints.size() == size)
		{
			historyPoints.remove(size-1);
		}
		historyPoints.add(0, obj.createHistoryPoint());
	}
	public void clear()
	{
		historyPoints.clear();
		currentPoint = 0;
	}
	public void clearTo(int point)
	{
		if (point > historyPoints.size())
		{
			historyPoints.clear();
			return;
		}
		for (int i = 1; i <= point; i++)
		{
			historyPoints.remove(0);
		}
		if (point < currentPoint)
		{
			currentPoint -= point;
		}
		else
		{
			currentPoint = 0;
		}
	}
	public int getCurrentPoint()
	{
		return currentPoint;
	}
	public void restoreTo(int pointIndex)
	{
		if (pointIndex >= 0 && pointIndex < historyPoints.size())
		{
			currentPoint = pointIndex;
			obj.restore(historyPoints.get(currentPoint));
		}
	}
}