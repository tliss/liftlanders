package com.tayloraliss.liftlanders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.tayloraliss.liftlanders.Lift.Location.*;
import static com.tayloraliss.liftlanders.Lift.Row.*;

public class Lift extends BaseActor {

    private Location location;
    private Row row;
    private Boolean isCenter;
    private Boolean isFlipping;

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

    public Lift(float x, float y, Stage s, Location l, Row r)
    {
        super(x,y,s);
        loadTexture("lift.png");
        location = l;
        row = r;
        isFlipping = false;
    }

    private void switchPosition() {

        Action moveRight = Actions.moveBy(LiftLanders.PLATFORM_WIDTH * 4, 0, 1);
        Action moveLeft = Actions.moveBy(-LiftLanders.PLATFORM_WIDTH * 4, 0, 1);
        Action twirlClockwise = Actions.rotateBy(720, 1);
        Action twirlCounterclockwise = Actions.rotateBy(-720, 1);
        Action grow = Actions.scaleBy(0.5f, 0.5f, 0.5f);
        Action shrink = Actions.scaleBy(-0.5f, -0.5f, 0.5f);
        Action moveUp = Actions.moveBy(0, 100, 0.5f);
        Action moveDown = Actions.moveBy(0, -100, 0.5f);

        SequenceAction shrinkAndGrow = sequence(shrink, grow);
        SequenceAction moveUpAndDown = sequence(moveUp, moveDown);

        ParallelAction fancyMoveRight = new ParallelAction(moveRight, twirlClockwise, shrinkAndGrow, moveUpAndDown);
        ParallelAction fancyMoveLeft = new ParallelAction(moveLeft, twirlCounterclockwise, shrinkAndGrow, moveUpAndDown);

        Action solidify = new Action(){
            @Override
            public boolean act(float delta) {
                Lift.this.setSolid(true);
                Lift.this.isFlipping = false;
                return true;
            }
        };


        //TODO: Prevent center platform from moving while other platform is
        if (!isInAction()) {
            System.out.println(this.getName());
            isFlipping = true;
            if (this.location == LEFT && this.getName().equals("center")) {
                this.setSolid(false);
                this.addAction(sequence(fancyMoveRight, solidify));
                this.location = RIGHT;
            } else if (this.location == RIGHT && this.getName().equals("center")) {
                this.setSolid(false);
                this.addAction(sequence(fancyMoveLeft, solidify));
                this.location = LEFT;
            } else if (isCenter && this.location == LEFT){
                this.location = RIGHT;
            } else if (isCenter && this.location == RIGHT){
                this.location = LEFT;
            }
        }
    }

    private boolean roomAbove(){
        return (row == BOTTOM || row == MIDDLE);
    }

    private boolean roomBelow(){
        return (row == TOP || row == MIDDLE);
    }

    public void act(float dt)
    {
        // if we don't call this, the default Actions won't work
        super.act(dt);

        Action moveUp = Actions.moveBy(0, 100, 1);
        Action moveDown = Actions.moveBy(0, -100, 1);

        Lift center = this.getStage().getRoot().findActor("center");
        Lift flipper = this.getStage().getRoot().findActor("flipper");

        if (!isInAction()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                if (location == LEFT && roomAbove()) {
                    this.addAction(moveUp);
                    row = row.above();
                }
                if (location == RIGHT && roomBelow()) {
                    this.addAction(moveDown);
                    row = row.below();
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                if (location == RIGHT && roomAbove()) {
                    this.addAction(moveUp);
                    row = row.above();
                }
                if (location == LEFT && roomBelow()) {
                    this.addAction(moveDown);
                    row = row.below();
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                 switchPosition();
            }
        }

        if (flipper.isFlipping){
            center.clearActions();
        }

    }
}
