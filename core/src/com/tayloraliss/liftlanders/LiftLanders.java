package com.tayloraliss.liftlanders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.compression.lzma.Base;

public class LiftLanders extends GameBeta {

	// Declare actors here
	private GreenBot greenbot;
	private Lift liftLeft;
	private Lift liftRight;

	private static final int PLATFORM_TOTAL = 12;
	private static final int PLATFORM_WIDTH = 182;
	private static final int PLATFORM_START_HEIGHT = 400;
	private static final int PLATFORM_END_HEIGHT = PLATFORM_START_HEIGHT - 200;

	public void initialize(){
		// Initialize actors here
		greenbot = new GreenBot(Gdx.graphics.getWidth()/2, 500, mainStage);
		greenbot.moveBy(-greenbot.getWidth()/2,0);

		for (int i = 0; i <= PLATFORM_TOTAL-1; i++) {
			for(int y = PLATFORM_START_HEIGHT; y >= PLATFORM_END_HEIGHT; y -= 100) {
				for (int x = 0; x < Gdx.graphics.getWidth(); x += PLATFORM_WIDTH * 2) {
					new Platform(x, y, mainStage);
				}
			}
		}

		liftLeft = new Lift(PLATFORM_WIDTH, 400, mainStage);
		liftRight = new Lift(PLATFORM_WIDTH * 3, 200, mainStage);

	}

	public void update(float dt){
		for (BaseActor platform : BaseActor.getList(mainStage, "Platform"))
			greenbot.preventOverlap(platform);
		for (BaseActor lift : BaseActor.getList(mainStage, "Lift"))
			greenbot.preventOverlap(lift);
	}

}
