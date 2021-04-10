package com.github.kl0ck.guaibaraid.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.kl0ck.guaibaraid.GameCanvas;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Gua\u00EDba Raid";
		config.forceExit = false;
		config.vSyncEnabled = false;
		new LwjglApplication(new GameCanvas(), config);
	}

}
