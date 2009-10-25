package eu.sweetlygeek.loto.graphical.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eu.sweetlygeek.loto.graphical.Loto;
import eu.sweetlygeek.loto.model.BaseCarton;

public class EmptyAction implements ActionListener {
	
	private Loto main;
	
	public EmptyAction(Loto main)
	{
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		main.setBaseCarton(new BaseCarton());
	}

}
