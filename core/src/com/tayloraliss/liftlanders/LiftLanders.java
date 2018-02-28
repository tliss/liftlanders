package com.tayloraliss.liftlanders;

import com.badlogic.gdx.Gdx;

public class LiftLanders extends GameBeta {

	// Declare actors here
	private GreenBot greenbot;
	private static final int PLATFORM_TOTAL = 12;

	public void initialize(){
		// Initialize actors here
		greenbot = new GreenBot(Gdx.graphics.getWidth()/2, 500, mainStage);
		greenbot.moveBy(-greenbot.getWidth()/2,0);

		for (int i = 0; i <= PLATFORM_TOTAL-1; i++) {
			for(int y = 500; y >= 300; y -= 100) {
				for (int x = 0; x < Gdx.graphics.getWidth(); x += 182 * 2) {
					new Platform(x, y, mainStage);
				}
			}
		}

	}

	public void update(float dt){
		for (BaseActor platform : BaseActor.getList(mainStage, "Platform"))
			greenbot.preventOverlap(platform);
	}

}
