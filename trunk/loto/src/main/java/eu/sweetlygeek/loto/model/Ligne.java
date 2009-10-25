package eu.sweetlygeek.loto.model;

import java.util.List;

/** Ligne basique
 * @author bishiboosh
 *
 */
public class Ligne extends LotoObject<Integer> {
		
	
	public Ligne(List<Integer> content) {
		super(content);
	}

	public Boolean toggle(int num)
	{
		if (content.containsKey(num))
		{
			content.put(num, !content.get(num));
			return isFull();
		}
		else
		{
			return null;
		}
	}
}
