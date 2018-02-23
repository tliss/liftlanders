package com.tayloraliss.liftlanders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class BaseActor extends Actor
{
    private Animation<TextureRegion> animation;
    private float elapsedTime;
    private boolean animationPaused;
    protected Vector2 velocityVec;
    protected Vector2 accelerationVec;
    private float acceleration;
    private float maxSpeed;
    private float deceleration;
    private Polygon boundaryPolygon;
    private boolean leftFacing;

    public BaseActor(float x, float y, Stage s)
    {
        // call constructor from Actor class
        super();
        // perform additional initialization tasks
        setPosition(x,y);
        s.addActor(this);
        animation = null;
        elapsedTime = 0;
        animationPaused = false;
        velocityVec = new Vector2(0,0);
        accelerationVec = new Vector2(0, 0);
        acceleration = 0;
        maxSpeed = 1000;
        deceleration = 0;
        leftFacing = false;
    }

    public void setAnimation(Animation<TextureRegion> anim){
        animation = anim;
        TextureRegion tr = animation.getKeyFrame(0);
        float w = tr.getRegionWidth();
        float h = tr.getRegionHeight();
        setSize(w, h);
        setOrigin(w/2, h/2);

        if (boundaryPolygon == null){
            setBoundaryRectangle();
        }
    }

    public void setAnimationPaused(boolean pause){
        animationPaused = pause;
    }

    public void act(float dt){
        super.act(dt);

        if (!animationPaused){
            elapsedTime += dt;
        }
    }

    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);

        // apply color tint effect
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        boolean frameFlipped = false;

        if (animation != null && isVisible()) {
            setleftFacing();
            if (getLeftFacing()){
                animation.getKeyFrame(elapsedTime).flip(true, false);
                frameFlipped = true;
            }
            batch.draw(animation.getKeyFrame(elapsedTime),
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation()
            );
            if (frameFlipped){
                animation.getKeyFrame(elapsedTime).flip(true, false);
            }
        }
    }

    //For creating an animation from separate images
    public Animation<TextureRegion> loadAnimationFromFiles(String[] fileNames,
                                                           float frameDuration,
                                                           boolean loop)
    {
        int fileCount = fileNames.length;
        Array<TextureRegion> textureArray = new Array<TextureRegion>();

        for (int n=0; n < fileCount; n++){
            String fileName = fileNames[n];
            Texture texture = new Texture(Gdx.files.internal(fileName));
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            textureArray.add(new TextureRegion(texture));
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArray);

        if (loop){
            anim.setPlayMode(Animation.PlayMode.LOOP);
        } else {
            anim.setPlayMode(Animation.PlayMode.NORMAL);
        }

        if (animation == null){
            setAnimation(anim);
        }

        return anim;
    }

    // For creating an animation from a single spritesheet
    public Animation<TextureRegion> loadAnimationFromSheet(String fileName, int rows, int cols, float frameDuration, boolean loop, boolean pingPong, int startFrame, int endFrame){
        Texture texture = new Texture(Gdx.files.internal(fileName), true);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        int frameWidth = texture.getWidth() / cols;
        int frameHeight = texture.getHeight() / rows;

        TextureRegion[][] temp = TextureRegion.split(texture, frameWidth, frameHeight);

        Array<TextureRegion> textureArray = new Array <TextureRegion>();

        for (int r = 0; r < rows; r++){
            for (int c=0; c < cols; c++){
                textureArray.add(temp[r][c]);
            }
        }

        System.out.println(textureArray.size);

        if (endFrame < textureArray.size){
            textureArray.removeRange(endFrame+1, textureArray.size-1);
        }

        if (startFrame > 0) {
            textureArray.removeRange(0, startFrame-1);
        }

        System.out.println(textureArray.size);

        Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArray);

        if (loop){
            anim.setPlayMode(Animation.PlayMode.LOOP);
        } else if (pingPong) {
            anim.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        } else {
            anim.setPlayMode(Animation.PlayMode.NORMAL);
        }

        if (animation == null){
            setAnimation(anim);
        }

        return anim;
    }

    //For game objects represented by a single image that do not require animation
    public Animation<TextureRegion> loadTexture(String fileName){
        String[] fileNames = new String[1];
        fileNames[0] = fileName;
        return loadAnimationFromFiles(fileNames, 1, true);
    }

    //Check if animation is finished
    public boolean isAnimationFinished(){
        return animation.isAnimationFinished(elapsedTime);
    }

    public void setSpeed(float speed){
        // if length is zero, then assume motion angle is zero degrees
        if (velocityVec.len() == 0){
            velocityVec.set(speed, 0);
        } else {
            velocityVec.setLength(speed);
        }
    }

    public float getSpeed(){
        return velocityVec.len();
    }

    public void setMotionAngle(float angle){
        velocityVec.setAngle(angle);
    }

    public float getMotionAngle(){
        return velocityVec.angle();
    }

    public boolean isMoving(){
        return (getSpeed() > 0);
    }

    public void setAcceleration(float acc){
        acceleration = acc;
    }

    public void accelerateAtAngle(float angle){
        accelerationVec.add(new Vector2(acceleration, 0).setAngle(angle));
    }

    public void accelerateForward(){
        accelerateAtAngle(getRotation());
    }

    public void setMaxSpeed(float ms){
        maxSpeed = ms;
    }

    public void setDeceleration(float dec){
        deceleration = dec;
    }

    public void applyPhysics(float dt){
        // apply acceleration
        velocityVec.add(accelerationVec.x * dt, accelerationVec.y * dt);

        float speed = getSpeed();

        // decrease speed (decelerate) when not accelerating
        if (accelerationVec.len() == 0){
            speed -= deceleration * dt;
        }

        // keep speed within set bounds
        speed = MathUtils.clamp(speed, 0, maxSpeed);

        // update velocity
        setSpeed(speed);

        // apply velocity
        moveBy(velocityVec.x * dt, velocityVec.y * dt);

        // reset acceleration
        accelerationVec.set(0,0);
    }

    public void setBoundaryRectangle(){
        float w = getWidth();
        float h = getHeight();
        float[] vertices = {0,0, w,0, w,h, 0,h};
        boundaryPolygon = new Polygon(vertices);
    }

    public void setBoundaryPolygon(int numSides){
        float w = getWidth();
        float h = getHeight();

        float[] vertices = new float[2*numSides];
        for (int i = 0; i < numSides; i++){
            float angle = i * 6.28f / numSides;
            // x-coordinate
            vertices[2*i] = w/2 * MathUtils.cos(angle) + w/2;
            // y-coordinates
            vertices[2*i+1] = h/2 * MathUtils.sin(angle) + h/2;
        }
        boundaryPolygon = new Polygon(vertices);
    }

    public Polygon getBoundaryPolygon(){
        boundaryPolygon.setPosition(getX(), getY());
        boundaryPolygon.setOrigin(getOriginX(), getOriginY());
        boundaryPolygon.setRotation(getRotation());
        boundaryPolygon.setScale(getScaleX(), getScaleY());
        return boundaryPolygon;
    }

    public void setleftFacing(){
        if ((getMotionAngle() > 90 && getMotionAngle() < 270) ||
                (getMotionAngle() == 0 && getSpeed() ==0 && leftFacing)) {
            leftFacing = true;
        } else {
            leftFacing = false;
        }
    }

    public boolean getLeftFacing(){
        return leftFacing;
    }
}
