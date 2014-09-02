package name.tomflucke.history;

public interface Historical<T>
{
	public T createHistoryPoint();
	
	public void restore(T historyPoint);
}
