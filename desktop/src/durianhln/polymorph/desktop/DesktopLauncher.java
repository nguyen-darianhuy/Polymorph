package durianhln.polymorph.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import durianhln.polymorph.Polymorph;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Polymorph";
        config.width = 504;
        config.height = 896;
        new LwjglApplication(new Polymorph(), config);
    }
}
