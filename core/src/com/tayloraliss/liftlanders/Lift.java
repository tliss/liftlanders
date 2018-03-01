package com.tayloraliss.liftlanders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AfterAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
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

    private Location location;
    private Row Row;

    public Lift(float x, float y, Stage s, Location l, Row r)
    {
        super(x,y,s);
        loadTexture("lift.png");
        location = l;
        Row = r;
    }

    public boolean isInAction(){
        return !(this.getActions().size == 0);
    }

    private void switchPosition() {

        Action moveRight = Actions.moveBy(LiftLanders.PLATFORM_WIDTH * 4, 0, 1);
        Action twirl = Actions.rotateBy(540, 1);
        Action grow = Actions.scaleBy(2, 2, 0.5f);
        Action shrink = Actions.scaleBy(-2, -2, 0.5f);
        SequenceAction growAndShrink = sequence(grow, shrink);

        ParallelAction fancyMove = new ParallelAction(moveRight, twirl, growAndShrink);

        Action solidify = new Action(){
            @Override
            public boolean act(float delta) {
                Lift.this.setSolid(true);
                return true;
            }
        };

        if (!isInAction() && this.location == LEFT){
            if (this.getActions().size == 0) {
                this.setSolid(false);
                this.addAction(sequence(fancyMove, solidify));
            }
        }
    }

    public void act(float dt)
    {
        // if we don't call this, the default Actions won't work
        super.act(dt);

        Action moveUp = Actions.moveBy(0, 100, 1);
        Action moveDown = Actions.moveBy(0, -100, 1);

        if (!isInAction()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                // Left moves the left lift UP
                if (location == LEFT && (Row == BOTTOM || Row == MIDDLE)) {
                    this.addAction(moveUp);
                    Row = Row.above();
                }
                // Left moves the right lift DOWN
                if (location == RIGHT && (Row == TOP || Row == MIDDLE)) {
                    System.out.println(this.getActions().size);
                    this.addAction(moveDown);
                    System.out.println(this.getActions().size);
                    Row = Row.below();
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                // Right moves the right lift UP
                if (location == RIGHT && (Row == BOTTOM || Row == MIDDLE)) {
                    this.addAction(moveUp);
                    Row = Row.above();
                }
                // Right moves the left lift DOWN
                if (location == LEFT && (Row == TOP || Row == MIDDLE)) {
                    this.addAction(moveDown);
                    Row = Row.below();
                }
            }
            // Up switches the platforms
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                 switchPosition();
            }
        }
    }
}
