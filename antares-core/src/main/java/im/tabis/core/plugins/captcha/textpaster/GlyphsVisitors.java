package im.tabis.core.plugins.captcha.textpaster;

import java.awt.geom.Rectangle2D;

public interface GlyphsVisitors {
	 void visit(Glyphs glyphs, Rectangle2D backroundBounds);
}
