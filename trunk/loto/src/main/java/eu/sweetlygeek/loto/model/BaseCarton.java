package eu.sweetlygeek.loto.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import eu.sweetlygeek.loto.enumeration.Type;
import eu.sweetlygeek.loto.utils.CartonImporter;
import eu.sweetlygeek.loto.utils.ImmutableSingletonMap;

public class BaseCarton {

	private List<Carton> cartons;

	public BaseCarton()
	{
		this.cartons = new ArrayList<Carton>();
	}

	public void importer(File file) throws FileNotFoundException
	{
		List<Carton> result = CartonImporter.importFromFile(file);
		cartons.addAll(result);
	}

	public void clear()
	{
		for (Carton c : cartons)
		{
			c.clear();
		}
	}

	public List<Integer> toggle(int num, Type type)
	{
		List<Integer> result = new ArrayList<Integer>();

		for (Carton c : cartons)
		{
			ImmutableSingletonMap<Type, Integer> rCarton = c.toggle(num);
			if (rCarton != null)
			{
				if (type == Type.LIGNE || type == rCarton.getKey())
				{
					result.add(rCarton.getValue());
				}
			}
		}

		return result;
	}

	public List<Carton> getCartons() {
		return cartons;
	}
}
