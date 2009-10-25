package eu.sweetlygeek.loto.graphical.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import eu.sweetlygeek.loto.graphical.Loto;

public class ButtonClickedAction implements ActionListener {

	private int num;
	private JButton button;
	private Loto main;

	public ButtonClickedAction(JButton button, Loto main, int num) {
		super();
		this.button = button;
		this.main = main;
		this.num = num;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (main.getBaseCarton().getCartons().isEmpty())
		{
			JOptionPane.showMessageDialog(main, "Il n'y a pas de cartons chargés.\nVeuillez charger au moins un fichier de cartons", "Pas de cartons", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			toggleBackground();
			List<Integer> winners = main.getBaseCarton().toggle(num, main.getMode());
			if (!winners.isEmpty())
			{
				String title = winners.size() == 1 ? "Carton gagnant : " : "Cartons gagnants : "; 
				StringBuffer buffer = new StringBuffer(title);
				Iterator<Integer> wIt = winners.iterator();
				while (wIt.hasNext())
				{
					int winner = wIt.next();
					buffer.append(Integer.toString(winner));
					char sep = wIt.hasNext() ? ',' : '.';
					buffer.append(sep);
				}
				JOptionPane.showMessageDialog(main, buffer.toString());
			}
		}
	}

	private void toggleBackground()
	{
		Color foregroundColor = button.getForeground();
		if (foregroundColor == Color.YELLOW)
		{
			button.setForeground(null);
		}
		else
		{
			button.setForeground(Color.YELLOW);
		}
	}

}
