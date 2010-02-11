package eu.sweetlygeek.loto.model;

import static eu.sweetlygeek.loto.enumeration.Type.CARTON;
import static eu.sweetlygeek.loto.enumeration.Type.LIGNE;
import static eu.sweetlygeek.loto.enumeration.Type.RIEN;

import java.util.List;
import java.util.Map;

import eu.sweetlygeek.loto.enumeration.Type;

/** Carton, constitué d'un ensemble de 3 lignes.
 * @author bishiboosh
 *
 */
class Carton extends LotoObject<Ligne> {

	private final int numCarton;

	Carton(final List<Ligne> content, final int numCarton) {
		super(content);
		this.numCarton = numCarton;
	}
	
	public int getNumCarton() {
		return this.numCarton;
	}

	public Type toggle(final int num) {
		for (Map.Entry<Ligne, Boolean> entry : content.entrySet()) {
			final Ligne ligne = entry.getKey();
			if (ligne.toggle(num)) {
				entry.setValue(Boolean.TRUE);
				return isFull() ? CARTON : LIGNE; // NOPMD
			}
		}
		return RIEN;
	}

	@Override
	public void clear() {
		super.clear();
		for (Ligne ligne : content.keySet()) {
			ligne.clear();
		}
	}
}
