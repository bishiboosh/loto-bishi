package eu.sweetlygeek.loto.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import eu.sweetlygeek.loto.enumeration.Type;

/** Ensemble de cartons, éventuellement chargé depuis un fichier.
 * @author bishiboosh
 *
 */
public class BaseCarton {

	private List<Carton> cartons;
	/** Base vide. */
	public static final BaseCarton BASE_VIDE = new BaseCarton();

	private BaseCarton() {
		this.cartons = new ArrayList<Carton>();
	}

	/** Charge un ensemble de cartons depuis un fichier.
	 * Le fichier est un ensemble de numéros séparés par des espaces.
	 * @param file fichier de cartons
	 * @throws FileNotFoundException si le fichier n'est pas valide
	 */
	public BaseCarton(final File file) throws FileNotFoundException {
		final Scanner scanner = new Scanner(file);
		int count = 0;
		final List<Ligne> cCarton = new ArrayList<Ligne>();
		final List<Integer> cLigne = new ArrayList<Integer>();
		int numCarton = 0;

		while (scanner.hasNextInt()) {
			final int num = scanner.nextInt();
			count++;
			cLigne.add(num);
			if (count % 5 == 0) {
				// On a fini la ligne
				cCarton.add(new Ligne(cLigne)); // NOPMD
				cLigne.clear();

				if (count % 3 == 0) {
					// On a fini le carton
					numCarton++;
					cartons.add(new Carton(cCarton, numCarton)); // NOPMD
					cCarton.clear();
				}
			}
		}
	}

	/**
	 * Vide la liste des cartons.
	 */
	public void clear() {
		for (Carton c : cartons) {
			c.clear();
		}
	}

	/** Coche un numéro sur la liste des cartons.
	 * @param num numéro à cocher
	 * @param type type de jeu
	 * @return cartons gagnants selon le type de jeu
	 */
	public Collection<Integer> toggle(final int num, final Type type) {
		final Set<Integer> result = new HashSet<Integer>();

		for (Carton c : cartons) {
			final Type rType = c.toggle(num);
			if (!rType.equals(Type.RIEN) && rType.equals(type)) {
				result.add(c.getNumCarton());
			}
		}

		return result;
	}

	/** Retourne vrai si il n'y a pas de carton chargé.
	 * @return vrai si il n'y a aucun carton
	 */
	public boolean isEmpty() {
		return cartons.isEmpty();
	}
}