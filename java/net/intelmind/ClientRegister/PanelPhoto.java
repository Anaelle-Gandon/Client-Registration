package net.intelmind.ClientRegister;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PanelPhoto extends JPanel {

	BufferedImage bi;
	
	public PanelPhoto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g.drawImage(bi, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	public BufferedImage getBi() {
		return bi;
	}

	public void setBi(BufferedImage bi) {
		this.bi = bi;
	}
	
	
}
