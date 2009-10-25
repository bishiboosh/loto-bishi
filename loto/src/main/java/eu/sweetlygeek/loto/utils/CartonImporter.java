package eu.sweetlygeek.loto.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import eu.sweetlygeek.loto.model.Carton;
import eu.sweetlygeek.loto.model.Ligne;

public class CartonImporter {
	
	public static List<Carton> importFromFile(File f) throws FileNotFoundException
	{
		List<Carton> cartons = new ArrayList<Carton>();
		
		Scanner scanner = new Scanner(f);
		
		int count = 0;
		
		List<Ligne> cCarton = new ArrayList<Ligne>();
		List<Integer> cLigne = new ArrayList<Integer>();
		
		int numCarton = 0;
		
		while (scanner.hasNextInt())
		{
			int num = scanner.nextInt();
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
		
		return cartons;
	}

}
