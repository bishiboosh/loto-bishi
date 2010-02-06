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

public class BaseCarton {

	private List<Carton> cartons;
	public static final BaseCarton BASE_VIDE = new BaseCarton();

	private BaseCarton()
	{
		this.cartons = new ArrayList<Carton>();
	}

	public BaseCarton(final File file) throws FileNotFoundException
	{
		final Scanner scanner = new Scanner(file);
		int count = 0;
		final List<Ligne> cCarton = new ArrayList<Ligne>();
		final List<Integer> cLigne = new ArrayList<Integer>();
		int numCarton = 0;

		while (scanner.hasNextInt())
		{
			final int num = scanner.nextInt();
			count++;
			cLigne.add(num);
			if (count % 5 == 0)
			{
				// On a fini la ligne
				cCarton.add(new Ligne(cLigne));
				cLigne.clear();

				if (count % 3 == 0)
				{
					// On a fini le carton
					numCarton++;
					cartons.add(new Carton(cCarton, numCarton));
					cCarton.clear();
				}
			}
		}
	}

	public void clear()
	{
		for (Carton c : cartons)
		{
			c.clear();
		}
	}

	public Collection<Integer> toggle(final int num, final Type type)
	{
		final Set<Integer> result = new HashSet<Integer>();

		for (Carton c : cartons)
		{
			final Type rType = c.toggle(num);
			if (!rType.equals(Type.RIEN) && rType.equals(type))
			{
				result.add(c.getNumCarton());
			}
		}

		return result;
	}

	public boolean isEmpty() {
		return cartons.isEmpty();
	}
}