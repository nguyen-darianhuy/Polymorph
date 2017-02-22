/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package durianhln.polymorph;

import com.badlogic.gdx.math.Vector2;
import java.awt.Dimension;

/**
 *
 * @author Darian
 */
public class Map extends Scrollable {
    public Map(Vector2 position, Vector2 velocity, Dimension size) {
        super(position, velocity, size);
    }

    @Override
    public void reset(Vector2 position) {
        setPosition(position);
        setScrolled(false);
    }
}
