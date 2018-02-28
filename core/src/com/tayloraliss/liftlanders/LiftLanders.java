package com.tayloraliss.liftlanders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

public class LiftLanders extends GameBeta {

	// Declare actors here
	private GreenBot greenbot;
	private Platform platform;
	private Group groupA;

	public void initialize(){
		// Initialize actors here
		greenbot = new GreenBot(Gdx.graphics.getWidth()/2, 500, mainStage);
		greenbot.moveBy(-greenbot.getWidth()/2,0);
		platform = new Platform(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, mainStage);
		platform.moveBy(-platform.getWidth()/2, -platform.getHeight()/2);
	}

	public void update(float dt){
		greenbot.preventOverlap(platform);
	}

}
