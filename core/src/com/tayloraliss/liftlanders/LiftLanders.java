package com.tayloraliss.liftlanders;

import com.badlogic.gdx.Gdx;

import static com.tayloraliss.liftlanders.Lift.Location.*;
import static com.tayloraliss.liftlanders.Lift.Row.*;

public class LiftLanders extends GameBeta {

	private Onion onion;

	private static final int PLATFORM_TOTAL = 12;
	public static final int PLATFORM_WIDTH = 182;
	private static final int PLATFORM_START_HEIGHT = 400;
	private static final int PLATFORM_END_HEIGHT = PLATFORM_START_HEIGHT - 200;

	public void initialize(){
		// Add moving lifts
		new Lift(PLATFORM_WIDTH, 400, mainStage, LEFT, TOP).setName("flipper");
		new Lift(PLATFORM_WIDTH * 3, 200, mainStage, RIGHT, BOTTOM).setName("center");

		// Add onion man
		onion = new Onion(Gdx.graphics.getWidth()/2, 500, mainStage);
		onion.moveBy(-onion.getWidth()/2,0);

		// Add platforms
		for (int i = 0; i <= PLATFORM_TOTAL-1; i++) {
			for(int y = PLATFORM_START_HEIGHT; y >= PLATFORM_END_HEIGHT; y -= 100) {
				for (int x = 0; x < Gdx.graphics.getWidth(); x += PLATFORM_WIDTH * 2) {
					new Platform(x, y, mainStage);
				}
			}
		}
	}

	public void update(float dt){
		// Prevent onion from colliding with platforms
		for (BaseActor platform : BaseActor.getList(mainStage, "Platform"))
			onion.preventOverlap(platform);

		// Prevent onion from colliding with lifts
		for (BaseActor lift : BaseActor.getList(mainStage, "Lift")) {
			if (lift.isSolid()){
				onion.preventOverlap(lift);
			}
		}

	}
}
