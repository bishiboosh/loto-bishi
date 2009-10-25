package eu.sweetlygeek.loto.graphical.listener;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.util.List;

import javax.swing.JButton;

public class SizeListener implements ComponentListener {
	
	private List<JButton> buttons;
	private double pHeight;
	private double pWidth;

	public SizeListener(List<JButton> buttons)
	{
		this.buttons = buttons;
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// Nothing			
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// Nothing			
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Component c = e.getComponent();

		if (c.isShowing() && pHeight != 0 && pWidth != 0)
		{
			resizeButtons(buttons);
		}

		this.pWidth = c.getSize().width;
		this.pHeight = c.getSize().height;			
	}

	@Override
	public void componentShown(ComponentEvent e) {
		Component c = e.getComponent();
		this.pWidth = c.getSize().width;
		this.pHeight = c.getSize().height;
	}
	
	private void resizeButtons(List<JButton> buttons)
	{
		JButton last = buttons.get(buttons.size() - 1);
		double mult = computeMult(last) * 0.8;
		for (JButton button : buttons)
		{
			resizeButton(button, mult);
		}
	}
	
	private double computeMult(JButton button)
	{
		button.setFont(button.getFont().deriveFont(new AffineTransform()));
		
		FontMetrics fm = button.getGraphics().getFontMetrics();
		double cHeight = fm.getHeight();
		double cWidth = fm.stringWidth(button.getText());
		
		Dimension bSize = button.getSize();
		double bHeight = bSize.height;
		double bWidth = bSize.width;
		
		double mHeight = (bHeight - 10) / cHeight;
		double mWidth = (bWidth - 10) / cWidth;
		
		return Math.min(mHeight, mWidth);
	}
	
	private void resizeButton(JButton button, double mult)
	{
		Font cFont = button.getFont();
		AffineTransform at = AffineTransform.getScaleInstance(mult, mult);
		Font newFont = cFont.deriveFont(at);
		button.setFont(newFont);
	}

}
