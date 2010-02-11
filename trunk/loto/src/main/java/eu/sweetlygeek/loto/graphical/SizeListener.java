package eu.sweetlygeek.loto.graphical;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.util.List;

import javax.swing.JButton;

class SizeListener implements ComponentListener {

	private int pHeight;
	private int pWidth;
	private final List<JButton> buttons;
	
	SizeListener(final List<JButton> buttons) {
		this.buttons = buttons;
	}

	@Override
	public void componentShown(final ComponentEvent e) {
		final Component c = e.getComponent();
		this.pWidth = c.getWidth();
		this.pHeight = c.getHeight();
	}
	@Override
	public void componentResized(final ComponentEvent e) {
		final Component c = e.getComponent();
		if (c.isShowing() && pHeight != 0 && pWidth != 0) {
			resizeButtons(buttons);
		}
		this.pWidth = c.getSize().width;
		this.pHeight = c.getSize().height;					
	}

	private void resizeButtons(final List<JButton> buttons) {
		final double mult = computeMult(buttons.get(buttons.size() - 1)) * 0.8;
		for (JButton button : buttons) {
			resizeButton(button, mult);
		}
	}

	private double computeMult(final JButton button) {
		button.setFont(button.getFont().deriveFont(new AffineTransform()));
		final FontMetrics fm = button.getGraphics().getFontMetrics();
		final double cHeight = fm.getHeight();
		final double cWidth = fm.stringWidth(button.getText());
		final double bHeight = button.getHeight();
		final double bWidth = button.getWidth();
		final double mHeight = (bHeight - 10) / cHeight;
		final double mWidth = (bWidth - 10) / cWidth;
		return Math.min(mHeight, mWidth);
	}

	private void resizeButton(final JButton button, final double mult) {
		final Font newFont = button.getFont().deriveFont(AffineTransform.getScaleInstance(mult, mult));
		button.setFont(newFont);
	}

	@Override
	public void componentMoved(final ComponentEvent e) {
		//Nothing
	}
	@Override
	public void componentHidden(final ComponentEvent e) {
		// Nothing
	}

}
