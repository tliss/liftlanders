package com.tayloraliss.liftlanders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Platform extends BaseActor {

//    Group groupA;

    public Platform(float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("girder.png");

//        groupA = new Group();
//        groupA.setDebug(true);
//        groupA.setSize(getWidth(), getHeight());
//        groupA.setPosition(getX(), getY());
//        Gdx.gl.glLineWidth(2);
//        s.addActor(groupA);
    }

    public void act(float dt)
    {

    }
}
