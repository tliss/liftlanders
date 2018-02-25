package com.tayloraliss.liftlanders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.awt.Shape;

import static java.lang.Math.abs;

public class GreenBot extends BaseActor {

    private BaseActor belowSensor;

    private Animation walk;

    private float walkAcceleration;
    private float walkDeceleration;
    private float maxHorizontalSpeed;
    private float gravity;
    private float maxVerticalSpeed;

    public GreenBot(float x, float y, Stage s)
    {
        super(x,y,s);
        walk = loadAnimationFromSheet("greenbot.png", 4, 7, 0.1f, false, true, 9, 12);
        setScale(2, 2);

        maxHorizontalSpeed = 100;
        walkAcceleration   = 200;
        walkDeceleration   = 200;
        gravity            = 700;
        maxVerticalSpeed   = 1000;

        belowSensor = new BaseActor(0,0, s);
        belowSensor.loadTexture("white.png");
        belowSensor.setSize( this.getWidth() * 2, 8 );
        belowSensor.setBoundaryRectangle();
        belowSensor.setVisible(true);
    }

    public void act(float dt)
    {
        super.act(dt);

        // get keyboard input
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            accelerationVec.add( -walkAcceleration, 0 );

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            accelerationVec.add( walkAcceleration, 0 );

        // decelerate when not accelerating
        if ( !Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                && !Gdx.input.isKeyPressed(Input.Keys.LEFT)  )
        {
            float decelerationAmount = walkDeceleration * dt;

            float walkDirection;

            if ( velocityVec.x > 0 )
                walkDirection = 1;
            else
                walkDirection = -1;

            float walkSpeed = Math.abs( velocityVec.x );

            walkSpeed -= decelerationAmount;

            if (walkSpeed < 0)
                walkSpeed = 0;

            velocityVec.x = walkSpeed * walkDirection;
        }

        accelerationVec.add(0, -gravity);

        velocityVec.add( accelerationVec.x * dt, accelerationVec.y * dt );

        velocityVec.x = MathUtils.clamp( velocityVec.x,
                -maxHorizontalSpeed, maxHorizontalSpeed );

        moveBy( velocityVec.x * dt, velocityVec.y * dt );

        // reset acceleration
        accelerationVec.set(0,0);

        belowSensor.setPosition( getX() - this.getWidth()/2, getY() - 16 );

        // not really managing animations
        if ( this.isOnSolid() ) {
            belowSensor.setColor( Color.GREEN );
        } else {
            belowSensor.setColor( Color.RED );
        }

        if ( velocityVec.x > 0 ) { // face right
            setScaleX(2);
            System.out.println(this.getX() + " | " + this.getY());

        }

        if ( velocityVec.x < 0 ) {// face left
            setScaleX(-2);
            System.out.println(this.getX() + " | " + this.getY());
        }
    }

    public boolean isFalling()
    {
        return (velocityVec.y < 0);
    }

    public boolean isOnSolid()
    {
        for (BaseActor actor : BaseActor.getList( getStage(), "Solid" ))
        {
            Solid solid = (Solid)actor;
            if ( belowOverlaps(solid) && solid.isEnabled() )
                return true;
        }

        return false;
    }

    public boolean belowOverlaps(BaseActor actor)
    {
        return belowSensor.overlaps(actor);
    }
}
