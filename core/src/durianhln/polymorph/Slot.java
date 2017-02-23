/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package durianhln.polymorph;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.awt.Dimension;

/**
 *
 * @author Darian
 */
public class Slot extends Entity {
    public Slot(Vector2 position, Vector2 velocity, Dimension size, TextureRegion texture) {
        super(position, velocity, size, null);
    }
    @Override
    public void update(float delta) {
        getPosition().add(getVelocity().cpy().scl(delta));

    }
}
