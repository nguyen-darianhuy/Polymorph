package durianhln.polymorph.game;

import com.badlogic.gdx.graphics.Color;

/**
 * Represents a shape's color and transparency.
 * @author Evan
 */
public enum ShapeColor {
	RED(new Color(1, 0.3f, 0, 0.7f)),
	GREEN(new Color(0, 1, 0.5f, 0.7f)),
	BLUE(new Color(0, 0.5f, 1, 0.7f));
	
	public final Color color;
	ShapeColor(Color color) {
		this.color = color;
	}
}
