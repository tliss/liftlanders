package com.tayloraliss.liftlanders;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Solid extends BaseActor {
        private boolean enabled;

    public Solid(float x, float y, float width, float height, Stage s) {
            super(x,y,s);
            setSize(width, height);
            setBoundaryRectangle();
            enabled = true;
    }

    public Solid(float x, float y, Stage s) {
        super(x,y,s);
        enabled = true;
    }

    public void setEnabled(boolean b)
    {
        enabled = b;
    }

    public boolean isEnabled()
    {
        return enabled;
    }
}
