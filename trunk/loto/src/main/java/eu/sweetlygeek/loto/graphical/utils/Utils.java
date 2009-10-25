package eu.sweetlygeek.loto.graphical.utils;

import java.awt.Component;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import eu.sweetlygeek.loto.graphical.Loto;
import eu.sweetlygeek.loto.graphical.actions.ChangeModeAction;
import eu.sweetlygeek.loto.graphical.actions.EmptyAction;
import eu.sweetlygeek.loto.graphical.actions.NewAction;
import eu.sweetlygeek.loto.graphical.actions.OpenAction;
import eu.sweetlygeek.loto.graphical.actions.QuitAction;

public class Utils {
	
	public static JMenu createFileMenu(Loto main)
	{
		JMenu result = new JMenu("Fichier");
		
		JMenuItem open = new JMenuItem("Ouvrir un fichier");
		open.addActionListener(new OpenAction(main));
		result.add(open);
		
		JMenuItem empty = new JMenuItem("Vider la liste des cartons");
		empty.addActionListener(new EmptyAction(main));
		result.add(empty);
		
		JMenuItem quit = new JMenuItem("Quitter");
		quit.addActionListener(new QuitAction());
		result.add(quit);
		
		return result;
	}
	
	public static JMenu createPlayMenu(Loto main)
	{
		JMenu result = new JMenu("Partie");
		
		JMenuItem newPlay = new JMenuItem("Nouvelle partie");
		newPlay.addActionListener(new NewAction(main));
		result.add(newPlay);
		
		JMenuItem changeMode = new JMenuItem("Changer mode");
		changeMode.addActionListener(new ChangeModeAction(main));
		result.add(changeMode);
		
		return result;
	}
	
	public static void showError(Component parent, String message)
	{
		JOptionPane.showMessageDialog(parent, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	

}
