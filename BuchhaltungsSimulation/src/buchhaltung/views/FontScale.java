package buchhaltung.views;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.Enumeration;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class FontScale {
    private float scale = 0.0f;

    public FontScale() {
        initFontScale();
    }

    
    private void initFontScale() {
        int screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
        int fontSize = (int) Math.round(12.0 * screenRes / 72.0);
        Font font = getFont();

        float scale = fontSize / font.getSize2D();
        fontResize(scale);
    }
    
    private Font getFont() {
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
    
    public  void fontResize(float scale) {
        this.scale=scale;
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


    public float getScale() {
        if (scale <= 0.01f) {
            initFontScale();
        }
        return scale;
    }
    
    public void expand(float faktor) {
        fontResize(scale * faktor);
    }
}
