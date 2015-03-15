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

	private static float scale = 0.0f;
	private boolean verkleinern = true;
	

	public FontResize(boolean verkleinern) {
		super((verkleinern) ? "Schrift--" : "Schrift++");
		this.verkleinern = verkleinern;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		fontResize(scale *= ((verkleinern) ? 1 / 1.2 : 1.2));
	}

	public static void initFontScale() {
		int screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
		int fontSize = (int) Math.round(12.0 * screenRes / 72.0);
		Font font = getFont();

		scale = fontSize / font.getSize2D();
		fontResize(scale);
	}

	public static float getFontScale() {
		if (scale == 0.0f) {
			initFontScale();
		}
		return scale;
	}

	private static void fontResize(float scale) {
		UIDefaults defaults = UIManager.getDefaults();
		Enumeration<Object> keys = defaults.keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = defaults.get(key);
			if (value != null && value instanceof Font) {
				UIManager.put(key, null);
				Font font = UIManager.getFont(key);
				if (font != null) {
					float size = font.getSize2D();
					UIManager.put(key,
							new FontUIResource(font.deriveFont(size * scale)));
				}
			}
		}
	}

	private static Font getFont() {
		UIDefaults defaults = UIManager.getDefaults();
		Enumeration<Object> keys = defaults.keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = defaults.get(key);
			if (value != null && value instanceof Font) {
				Font font = UIManager.getFont(key);
				if (font != null) {
					return font;
				}
			}
		}
		return null;
	}
}
