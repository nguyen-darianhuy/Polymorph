package durianhln.polymorph.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import durianhln.polymorph.gameobject.Polymorph;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Polymorph";
        config.width = 360;
        config.height = 640;
        config.resizable = false;
        new LwjglApplication(new Polymorph(), config);
    }
}
