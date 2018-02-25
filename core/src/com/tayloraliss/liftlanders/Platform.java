package com.tayloraliss.liftlanders;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Platform extends Solid {

    public Platform(float x, float y, Stage s)
    {
        super(x, y, s);
        loadTexture("log.png");
        setScale(0.125f, 0.125f);
        setWidth(this.getWidth());
        setHeight(this.getHeight());
        setBoundaryRectangle();
    }

    public void act(float dt)
    {

    }
}
