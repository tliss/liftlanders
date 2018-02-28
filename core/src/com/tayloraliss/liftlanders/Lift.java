package com.tayloraliss.liftlanders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import static com.tayloraliss.liftlanders.Lift.Location.*;
import static com.tayloraliss.liftlanders.Lift.Row.*;

public class Lift extends BaseActor {

    public enum Location {LEFT, RIGHT}

    public enum Row { TOP, MIDDLE, BOTTOM;
        private static Row[] vals = values();
        public Row below(){
            return vals[(this.ordinal()+1)];
        }
        public Row above(){
            return vals[(this.ordinal()-1)];
        }
    }

    private Location Location;
    private Row Row;

    private Action moveUp = Actions.moveBy(0, 100, 1);
    private Action moveDown = Actions.moveBy(0, -100, 1);

    public Lift(float x, float y, Stage s, Location l, Row r)
    {
        super(x,y,s);
        loadTexture("lift.png");
        Location = l;
        Row = r;
    }

    public void act(float dt)
    {
        // if we don't call this, the default Actions won't work
        super.act(dt);

        if (this.getActions().size == 0) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                // Left moves the left lift UP
                if (Location == LEFT && (Row == BOTTOM || Row == MIDDLE)) {
                    this.addAction(moveUp);
                    Row = Row.above();
                }
                // Left moves the right lift DOWN
                if (Location == RIGHT && (Row == TOP || Row == MIDDLE)) {
                    this.addAction(moveDown);
                    Row = Row.below();
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                // Right moves the right lift UP
                if (Location == RIGHT && (Row == BOTTOM || Row == MIDDLE)) {
                    this.addAction(moveUp);
                    Row = Row.above();
                }
                // Right moves the left lift DOWN
                if (Location == LEFT && (Row == TOP || Row == MIDDLE)) {
                    this.addAction(moveDown);
                    Row = Row.below();
                }
            }
        }
    }
}
