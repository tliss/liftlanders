package com.tayloraliss.liftlanders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LiftLanders extends GameBeta {

	// Declare actors here
	private GreenBot greenbot;
	private Platform platform;

	public void initialize(){
		// Initialize actors here
		greenbot = new GreenBot(340, 600, mainStage);
		platform = new Platform(0, 0, mainStage);
		platform.centerAtPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
	}

	public void update(float dt){
		greenbot.preventOverlap(platform);

	}

}
