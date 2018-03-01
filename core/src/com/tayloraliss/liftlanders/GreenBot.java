package com.tayloraliss.liftlanders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

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

            //---Not working correctly--

            Vector2 cp[] = new Vector2[]{
                    new Vector2(4,2.5f),
                    new Vector2(1, 1),
                    new Vector2(3, 1),
                    new Vector2(4, 2.5f)
            };
            MoveAlongAction action1 = MoveAlongAction.obtain(new CatmullRomSpline<Vector2>(cp, true));
            this.addAction(action1);

            //--------------------------

        }

        accelerationVec.add(0, -gravity);

        applyPhysics(dt);

        setAnimationPaused( !isMoving() );
    }
}
