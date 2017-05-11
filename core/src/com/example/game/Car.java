package com.example.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import java.util.Random;


public class Car extends Sprite implements Disposable {

    public static final String[] CAR_MODELS = {"YellowCar.png", "BlueCar.png", "GreenCar.png", "GreyCar.png", "BlackCar.png", "BrownCar.png", "PurpleCar.png","OrangeCar.png" };
    private Vector2 position;
    private Vector2 velocity;
    private float width;
    private float length;
    private Texture texture;

    public Car(Texture texture, float x, float y, float width, float length) {
        super(texture);
        this.texture = texture;
        this.position = new Vector2(x,y);
        this.width = width;
        this.length = length;
    }

    public void update(float dt){
        position.x += velocity.x;
        position.y += velocity.y;

    }

    public void setVelocity(Vector2 velocity){
        this.velocity = velocity;
    }

    public void moveLeft(float dx){
        position.x -= dx;
        if(position.x < 120f){
            position.x = 120f;
        }
    }

    public void moveRight(float dx){
        position.x += dx;
        if(position.x > 360f){
            position.x = 360f;
        }
    }

    public boolean collidesWith(Car car) {

        boolean bottom = this.position.x <= car.position.x && this.position.x + width >= car.position.x && this.position.y + length >= car.position.y && this.position.y <= car.position.y;
        boolean top = this.position.x <= car.position.x && (this.position.x + width) >= car.position.x && this.position.y + length >= car.position.y + length && this.position.y <= car.position.y + length;

        return (bottom || top);
    }

    public Vector2 getPosition() {
        return position;
    }


    @Override
    public float getWidth() {
        return width;
    }

    public float getLength() {
        return length;
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }


    public static Car makeRandom(float x, float y, float width, float length){
        Random random = new Random();;
        Texture texture = new Texture(CAR_MODELS[random.nextInt(CAR_MODELS.length)]);

        return new Car(texture,x,y,width,length);
    }
}
