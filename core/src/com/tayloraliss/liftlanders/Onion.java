package com.tayloraliss.liftlanders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Onion extends BaseActor {

    public Onion(float x, float y, Stage s)
    {
        super(x,y,s);
        loadAnimationFromSheet("onion.png", 1, 6, 0.15f, true, false, 0, 6);

        setAcceleration(1000);
        setMaxSpeed(100);
        setDeceleration(400);
    }

    public void act(float dt)
    {
        super.act(dt);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            accelerateAtAngle(180);
            setScaleX(1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            accelerateAtAngle(0);
            setScaleX(-1);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            accelerateAtAngle(90);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            accelerateAtAngle(270);
        }

        accelerationVec.add(0, -gravity);

        applyPhysics(dt);

        setAnimationPaused( !isMoving() );
    }
}
