package eu.sweetlygeek.loto.graphical;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import eu.sweetlygeek.loto.enumeration.Type;
import eu.sweetlygeek.loto.graphical.actions.ButtonClickedAction;
import eu.sweetlygeek.loto.graphical.listener.SizeListener;
import eu.sweetlygeek.loto.graphical.utils.Utils;
import eu.sweetlygeek.loto.model.BaseCarton;

@SuppressWarnings("serial")
public class Loto extends JFrame {
	
	private BaseCarton baseCarton;
	private List<JButton> buttons;
	private Type mode;
	
	private Loto()
	{
		super("Loto 2.0");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// Nothing
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		this.baseCarton = new BaseCarton();
		this.buttons = new ArrayList<JButton>();
		this.mode = Type.LIGNE;
		
		Container content = getContentPane();
		
		JMenuBar barreMenu = createMenuBar();
		JPanel buttonPanel = createButtonPanel();
		
		content.add(barreMenu, BorderLayout.NORTH);
		content.add(buttonPanel, BorderLayout.CENTER);
		
		addComponentListener(new SizeListener(buttons));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
		setVisible(true);
	}

	private JMenuBar createMenuBar() {
		JMenuBar result = new JMenuBar();
		result.add(Utils.createFileMenu(this));
		result.add(Utils.createPlayMenu(this));
		return result;
	}

	private JPanel createButtonPanel() {
		JPanel result = new JPanel(new GridLayout(9, 10));
		
		for (int i = 1; i <= 90; i++)
		{
			JButton button = new JButton(Integer.toString(i));
			button.addActionListener(new ButtonClickedAction(button, this, i));
			buttons.add(button);
			result.add(button);
		}
		
		return result;
	}

	public BaseCarton getBaseCarton() {
		return baseCarton;
	}
	
	public void setBaseCarton(BaseCarton bc)
	{
		this.baseCarton = bc;
	}

	public List<JButton> getButtons() {
		return buttons;
	}

	public Type getMode() {
		return mode;
	}

	public void setMode(Type mode) {
		this.mode = mode;
	}
	
	public static void main(String[] args) {
		new Loto();
	}
}
