package com.tayloraliss.liftlanders.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.tayloraliss.liftlanders.Config;
import com.tayloraliss.liftlanders.LiftLanders;

public class DesktopLauncher {
	public static void main (String[] arg) {

		Config.write();
		Config.parse();

		Game myGame = new LiftLanders();
		LwjglApplication launcher = new LwjglApplication(myGame, "Lift Landers", 1274, 600);

	}
}
