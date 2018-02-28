package com.tayloraliss.liftlanders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static java.lang.Math.abs;

public class GreenBot extends BaseActor {

    public GreenBot(float x, float y, Stage s)
    {
        super(x,y,s);
        loadAnimationFromSheet("greenbot.png", 4, 7, 0.1f, false, true, 9, 12);
        setScale(2, 2);

        setAcceleration(1000);
        setMaxSpeed(100);
        setDeceleration(400);
    }

    public void act(float dt)
    {
        super.act(dt);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            accelerateAtAngle(180);
            setScaleX(-2);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            accelerateAtAngle(0);
            setScaleX(2);

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
