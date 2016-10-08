package buchhaltung.views;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.AbstractAction;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * Vergrößern, verkleinern des Fonts
 * 
 * @author Thomas Nill
 * 
 */
public class FontResize extends AbstractAction {
    private static final long serialVersionUID = -8497261599407576036L;
    private boolean verkleinern = true;
    private FontScale scale;

    public FontResize(boolean verkleinern,FontScale scale) {
        super((verkleinern) ? "Schrift--" : "Schrift++");
        this.verkleinern = verkleinern;
        this.scale = scale;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        scale.expand((verkleinern) ? 1 / 1.2f : 1.2f);
    }


    public  float getFontScale() {
        return scale.getScale();
    }

 

 
}
