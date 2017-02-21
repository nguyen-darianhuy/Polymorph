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
public class Game implements Updatable {
    private Player player;

    public Game(Dimension screenSize) {
        player = new Player(new Vector2(screenSize.width/2, screenSize.height/6));
    }

    @Override
    public void update(float delta) {
        player.update(delta);
    }

    public Player getPlayer() {
        return player;
    }
}
