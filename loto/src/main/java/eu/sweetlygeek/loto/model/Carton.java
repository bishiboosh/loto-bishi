package eu.sweetlygeek.loto.model;

import java.util.Iterator;
import java.util.List;

import eu.sweetlygeek.loto.enumeration.Type;
import eu.sweetlygeek.loto.utils.ImmutableSingletonMap;

public class Carton extends LotoObject<Ligne> {

	private int numCarton;

	public Carton(List<Ligne> content, int numCarton) {
		super(content);
		this.numCarton = numCarton;
	}

	public ImmutableSingletonMap<Type, Integer> toggle(int num) {
		boolean lineFull = false;
		Iterator<Ligne> lineIt = content.keySet().iterator();
		while (lineIt.hasNext() && !lineFull)
		{
			Ligne ligne = lineIt.next();
			Boolean found = ligne.toggle(num);
			if (found != null)
			{
				// On a trouvé le numéro dans la ligne, on regarde si la ligne est pleine
				lineFull = found;
				content.put(ligne, lineFull);
			}
		}
		if (lineFull)
		{
			Type status = isFull() ? Type.CARTON : Type.LIGNE;
			return new ImmutableSingletonMap<Type, Integer>(status, numCarton);
		}
		else
		{
			return null;
		}
	}

	@Override
	public void clear() {
		super.clear();
		for (Ligne ligne : content.keySet())
		{
			ligne.clear();
		}
	}
}
