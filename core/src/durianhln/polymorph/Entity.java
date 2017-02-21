/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package durianhln.polymorph;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Darian
 */
public abstract class Entity implements Updatable {
    private Vector2 position;
    public Entity(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }
}
