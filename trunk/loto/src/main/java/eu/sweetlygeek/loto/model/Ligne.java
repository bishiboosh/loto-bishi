package eu.sweetlygeek.loto.model;

import java.util.List;

/** Ligne basique, composée de 5 numéros.
 * @author bishiboosh
 *
 */
class Ligne extends LotoObject<Integer> {
		
	public Ligne(final List<Integer> content) {
		super(content);
	}
	
	public boolean toggle(final int num) {
		final Boolean cState = content.get(num);
		boolean result = false;
		if (Boolean.FALSE.equals(cState)) {
			content.put(num, cState.equals(Boolean.TRUE) 
					? Boolean.FALSE : Boolean.TRUE);
			result = isFull();
		}
		return result;
	}
}
