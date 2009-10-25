package eu.sweetlygeek.loto.graphical.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import eu.sweetlygeek.loto.enumeration.Type;
import eu.sweetlygeek.loto.graphical.Loto;

public class ChangeModeAction implements ActionListener {
	
	private Loto main;

	public ChangeModeAction(Loto main) {
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object[] choix = { "Carton", "Ligne" };
		String answer = (String) JOptionPane.showInputDialog(main, null, "Choisir mode", JOptionPane.QUESTION_MESSAGE, null, choix, "Ligne");
		Type newType = "Carton".equals(answer) ? Type.CARTON : Type.LIGNE;
		main.setMode(newType);
	}

}
