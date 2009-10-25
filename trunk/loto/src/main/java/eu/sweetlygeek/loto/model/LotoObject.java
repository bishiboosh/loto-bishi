package eu.sweetlygeek.loto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class LotoObject<T> {
	
	protected Map<T, Boolean> content;
	
	public LotoObject(List<T> content)
	{
		this.content = new HashMap<T, Boolean>();
		for (T cObject : content)
		{
			this.content.put(cObject, false);
		}
	}
	
	protected boolean isFull()
	{
		return !content.values().contains(false);
	}
	
	public void clear()
	{
		for (T key : content.keySet())
		{
			content.put(key, false);
		}
	}
}
