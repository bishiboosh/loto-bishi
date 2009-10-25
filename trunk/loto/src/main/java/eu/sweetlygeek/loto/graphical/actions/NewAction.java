package eu.sweetlygeek.loto.graphical.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import eu.sweetlygeek.loto.graphical.Loto;
import eu.sweetlygeek.loto.model.BaseCarton;

public class NewAction implements ActionListener {
	
	private BaseCarton baseCarton;
	private List<JButton> buttons;

	public NewAction(Loto main) {
		this.baseCarton = main.getBaseCarton();
		this.buttons = main.getButtons();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		baseCarton.clear();
		for (JButton button : buttons)
		{
			button.setBackground(null);
		}
	}

}
