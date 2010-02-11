package eu.sweetlygeek.loto.graphical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.log4j.Logger;

import eu.sweetlygeek.loto.enumeration.Type;
import eu.sweetlygeek.loto.model.BaseCarton;

/** Point d'entrée général.
 * @author bishiboosh
 *
 */
@SuppressWarnings("serial")
public final class Loto extends JFrame {

	private static final String LAF_ERROR = "Error while choosing LAF";

	private static final Logger LOGGER = Logger.getLogger(Loto.class);

	private BaseCarton baseCarton;
	private final List<JButton> buttons;
	private Type mode;
	private static final Loto INSTANCE = new Loto();

	private Loto() {
		super("Loto 2.0");

		setLookAndFeel();
		this.baseCarton = BaseCarton.BASE_VIDE;
		this.buttons = new ArrayList<JButton>();
		this.mode = Type.LIGNE;
		final Container content = getContentPane();
		final JMenuBar barreMenu = createMenuBar();
		final JPanel buttonPanel = createButtonPanel();
		content.add(barreMenu, BorderLayout.NORTH);
		content.add(buttonPanel, BorderLayout.CENTER);
		addComponentListener(new SizeListener(buttons));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}

	private void setLookAndFeel() {
		String laf = UIManager.getCrossPlatformLookAndFeelClassName();
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("nimbus".equalsIgnoreCase(info.getName())) {
				laf = info.getClassName();
			}
		}
		try {
			UIManager.setLookAndFeel(laf);
		} catch (ClassNotFoundException e) {
			LOGGER.error(LAF_ERROR, e);
		} catch (InstantiationException e) {
			LOGGER.error(LAF_ERROR, e);
		} catch (IllegalAccessException e) {
			LOGGER.error(LAF_ERROR, e);
		} catch (UnsupportedLookAndFeelException e) {
			LOGGER.error(LAF_ERROR, e);
		}
	}

	private JMenuBar createMenuBar() {
		final JMenuBar result = new JMenuBar();
		result.add(createFileMenu());
		result.add(createPlayMenu());
		return result;
	}

	private JMenu createFileMenu() {
		final JMenu result = new JMenu("Fichier");
		final JMenuItem open = new JMenuItem("Ouvrir un fichier");
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					final JFileChooser fc = new JFileChooser();
					final int result = fc.showOpenDialog(INSTANCE);
					if (result == JFileChooser.APPROVE_OPTION) {
						baseCarton = new BaseCarton(fc.getSelectedFile());
					}
				} catch (HeadlessException e1) {
					LOGGER.error("Error with file dialog", e1);
				} catch (FileNotFoundException e1) {
					LOGGER.error("Error with file dialog", e1);
					JOptionPane.showMessageDialog(INSTANCE, 
							"Erreur sur le fichier",
							"Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		result.add(open);
		final JMenuItem empty = new JMenuItem("Vider la liste des cartons");
		empty.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				baseCarton = BaseCarton.BASE_VIDE;
			}
		});
		result.add(empty);
		final JMenuItem quit = new JMenuItem("Quitter");
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				INSTANCE.dispose();		
			}
		});
		result.add(quit);
		return result;
	}

	private JMenu createPlayMenu() {
		final JMenu result = new JMenu("Partie");
		final JMenuItem newPlay = new JMenuItem("Nouvelle partie");
		newPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				baseCarton.clear();
				for (JButton button : buttons) {
					button.setBackground(null);
				}
			}
		});
		result.add(newPlay);
		final JMenuItem changeMode = new JMenuItem("Changer mode");
		changeMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final Object[] choix = { "Carton", "Ligne" };
				final String answer = (String) JOptionPane.showInputDialog(INSTANCE,
						null, "Choisir mode", JOptionPane.QUESTION_MESSAGE,
						null, choix, "Ligne");
				mode = "Carton".equals(answer) ? Type.CARTON : Type.LIGNE;
			}
		});
		result.add(changeMode);
		return result;
	}

	private JPanel createButtonPanel() {
		final JPanel result = new JPanel(new GridLayout(9, 10));
		for (int i = 1; i <= 90; i++) {
			final JButton button = createButton(i);
			buttons.add(button);
			result.add(button);
		}
		return result;
	}

	private JButton createButton(final int num) {
		final JButton button = new JButton(String.valueOf(num));
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				if (baseCarton.isEmpty()) {
					JOptionPane.showMessageDialog(INSTANCE,
							"Il n'y a pas de cartons chargés.\nVeuillez charger au moins un fichier de cartons",
							"Pas de cartons", JOptionPane.WARNING_MESSAGE);
				} else {
					final JButton source = (JButton) event.getSource();
					source.setBackground(source.getBackground().equals(Color.YELLOW) ? null : Color.YELLOW);
					final Collection<Integer> winners = baseCarton.toggle(num, mode);
					if (!winners.isEmpty()) {
						final StringBuilder buffer = new StringBuilder(winners.size() == 1 
								? "Carton gagnant : " : "Cartons gagnants : ");
						for (Integer i : winners) {
							buffer.append(i.toString());
							buffer.append(" ");
						}
						JOptionPane.showMessageDialog(INSTANCE, buffer.toString());
					}
				}
			}
		});
		return button;
	}

	/** Méthode globale.
	 * @param args arguments
	 */
	public static void main(final String[] args) {
		INSTANCE.setVisible(true);
	}
}
