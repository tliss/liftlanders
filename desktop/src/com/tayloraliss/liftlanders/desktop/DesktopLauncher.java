package com.tayloraliss.liftlanders.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.tayloraliss.liftlanders.LiftLanders;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Game myGame = new LiftLanders();
		LwjglApplication launcher = new LwjglApplication(myGame, "Lift Landers", 1274, 600);

	}
}
