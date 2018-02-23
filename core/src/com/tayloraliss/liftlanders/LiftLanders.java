package com.tayloraliss.liftlanders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LiftLanders extends GameBeta {

	// Declare actors here
	private GreenBot greenbot;

	public void initialize(){
		// Initialize actors here
		greenbot = new GreenBot(300, 300, mainStage);

	}

	public void update(float dt){


	}

}
