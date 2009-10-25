package eu.sweetlygeek.loto.graphical.actions;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

import eu.sweetlygeek.loto.graphical.Loto;
import eu.sweetlygeek.loto.graphical.utils.Utils;

public class OpenAction implements ActionListener {
	
	private Loto main;
	
	public OpenAction(Loto main)
	{
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			JFileChooser fc = new JFileChooser();
			int result = fc.showOpenDialog(main);
			if (result == JFileChooser.APPROVE_OPTION)
			{
				File f = fc.getSelectedFile();
				main.getBaseCarton().importer(f);
			}
		} catch (HeadlessException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			Utils.showError(main, "Erreur sur le fichier");
		}
	}

}
