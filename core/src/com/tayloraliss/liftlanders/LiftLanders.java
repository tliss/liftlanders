package com.tayloraliss.liftlanders;

import com.badlogic.gdx.Gdx;

import static com.tayloraliss.liftlanders.Lift.Location.*;
import static com.tayloraliss.liftlanders.Lift.Row.*;

public class LiftLanders extends GameBeta {

	// Declare actors here
	private GreenBot greenbot;

	private static final int PLATFORM_TOTAL = 12;
	public static final int PLATFORM_WIDTH = 182;
	private static final int PLATFORM_START_HEIGHT = 400;
	private static final int PLATFORM_END_HEIGHT = PLATFORM_START_HEIGHT - 200;

	public void initialize(){
		// Initialize actors here

		new Lift(PLATFORM_WIDTH, 400, mainStage, LEFT, TOP);
		new Lift(PLATFORM_WIDTH * 3, 200, mainStage, RIGHT, BOTTOM);

		greenbot = new GreenBot(Gdx.graphics.getWidth()/2, 500, mainStage);
		greenbot.moveBy(-greenbot.getWidth()/2,0);

		for (int i = 0; i <= PLATFORM_TOTAL-1; i++) {
			for(int y = PLATFORM_START_HEIGHT; y >= PLATFORM_END_HEIGHT; y -= 100) {
				for (int x = 0; x < Gdx.graphics.getWidth(); x += PLATFORM_WIDTH * 2) {
					new Platform(x, y, mainStage);
				}
			}
		}
	}

	public void update(float dt){
		for (BaseActor platform : BaseActor.getList(mainStage, "Platform"))
			greenbot.preventOverlap(platform);
		for (BaseActor lift : BaseActor.getList(mainStage, "Lift")) {
			if (lift.isSolid()){
				greenbot.preventOverlap(lift);
			}
		}
	}
}
