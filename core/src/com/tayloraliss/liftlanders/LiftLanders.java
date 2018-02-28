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
		greenbot = new GreenBot(340, 600, mainStage);
		platform = new Platform(0, 0, mainStage);
		platform.setPosition(platform.getX(), platform.getY() + 300);
		System.out.println(Gdx.graphics.getWidth());
		System.out.println(platform.getWidth());

	}

	public void update(float dt){
		greenbot.preventOverlap(platform);
	}

}
