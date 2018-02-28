package com.tayloraliss.liftlanders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Platform extends BaseActor {

    Group groupA;

    public Platform(float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("girder.png");
//        setScale(0.125f, 0.125f);
        float newWidth = (getWidth()-(getWidth() * .125f)) / 2;
        float newHeight = (getHeight()-(getHeight() * .125f)) / 2;
        setPosition(-newWidth, -newHeight);

        groupA = new Group();
        groupA.setDebug(true);
        groupA.setSize(getWidth(), getHeight());
        groupA.setPosition(getX(), getY());
        Gdx.gl.glLineWidth(2);
        s.addActor(groupA);
    }

    public void act(float dt)
    {

    }
}
