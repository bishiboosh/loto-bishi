package eu.sweetlygeek.loto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LotoObject<T> {
	
	protected final Map<T, Boolean> content;
	
	protected LotoObject(final List<T> content) {
		this.content = new HashMap<T, Boolean>();
		for (T cObject : content) {
			this.content.put(cObject, false);
		}
	}
	
	protected boolean isFull() {
		return !content.values().contains(false);
	}
	
	public void clear() {
		for (Map.Entry<T, Boolean> entry : content.entrySet()) {
			entry.setValue(false);
		}
	}
}
