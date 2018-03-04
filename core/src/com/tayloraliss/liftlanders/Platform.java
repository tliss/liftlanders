package com.tayloraliss.liftlanders;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Platform extends BaseActor {

    public Platform(float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("girder.png");

//        this.setDebug(true);
//        Gdx.gl.glLineWidth(2);

    }

    public void act(float dt)
    {

    }
}
