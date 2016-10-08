package main;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import buchhaltung.views.FontResize;
import buchhaltung.views.FontScale;

public class MainFrameResize extends FontResize {
    private JFrame frame;

    public MainFrameResize(boolean verkleinern, JFrame frame,FontScale scale) {
        super(verkleinern, scale);
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
