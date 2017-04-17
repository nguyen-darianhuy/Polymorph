/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package durianhln.polymorph.util;

/**
 *
 * @author Evan
 */
public class Dimension {
    public float width;
    public float height;

    public Dimension(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format("W: %.2f, H: %.2f\n", width, height);
    }
}
