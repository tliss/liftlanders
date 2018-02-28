package com.tayloraliss.liftlanders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import static com.tayloraliss.liftlanders.Lift.location.*;
import static com.tayloraliss.liftlanders.Lift.row.*;

public class Lift extends BaseActor {

    enum location {LEFT, RIGHT, NONE}
    enum row {TOP, MIDDLE, BOTTOM, NONE}
    location location;
    row row;

    Action moveUp = Actions.moveBy(0, 100, 1);
    Action moveDown = Actions.moveBy(0, -100, 1);

    public Lift(float x, float y, Stage s, location l, row r)
    {
        super(x,y,s);
        loadTexture("lift.png");
        location = l;
        row = r;
    }

    public void act(float dt)
    {
        // if we don't call this, the default Actions won't work
        super.act(dt);

        if (this.getActions().size == 0) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                if (location == LEFT && (row == TOP || row == MIDDLE)) {
                    this.addAction(moveDown);
                    if (row == TOP) {
                        row = MIDDLE;
                    } else if (row == MIDDLE) {
                        row = BOTTOM;
                    }
                }
                if (location == RIGHT && (row == BOTTOM || row == MIDDLE)) {
                    this.addAction(moveUp);
                    if (row == BOTTOM) {
                        row = MIDDLE;
                    } else if (row == MIDDLE) {
                        row = TOP;
                    }
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {

                if (location == RIGHT && (row == TOP || row == MIDDLE)) {
                    this.addAction(moveDown);
                    if (row == TOP) {
                        row = MIDDLE;
                    } else if (row == MIDDLE) {
                        row = BOTTOM;
                    }
                }
                if (location == LEFT && (row == BOTTOM || row == MIDDLE)) {
                    this.addAction(moveUp);
                    if (row == BOTTOM) {
                        row = MIDDLE;
                    } else if (row == MIDDLE) {
                        row = TOP;
                    }
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            }
        }
    }
}
