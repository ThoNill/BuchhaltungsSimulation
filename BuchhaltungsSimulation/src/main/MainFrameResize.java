package main;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import buchhaltung.views.FontResize;


public class MainFrameResize extends FontResize {
	private JFrame frame;
	
	public MainFrameResize(boolean verkleinern, JFrame frame) {
		super(verkleinern);
		this.frame = frame;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		super.actionPerformed(arg0);
		frame.setVisible(false);
		new MainFrame();
		frame.removeAll();
	}
}
