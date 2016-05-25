package com.example.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mirola on 5/23/2016.
 */
public class Car extends Sprite{

    private Vector2 position;
    private Vector2 velocity;
    private float width;
    private float height;

    public Car(float x, float y, float width, float height, String carType) {
        super(new Texture(carType));
        this.position = new Vector2(x,y);
        this.width = width;
        this.height = height;
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

        boolean bottom = this.position.x <= car.position.x && this.position.x + width >= car.position.x && this.position.y + height >= car.position.y && this.position.y <= car.position.y;
        boolean top = this.position.x <= car.position.x && (this.position.x + width) >= car.position.x && this.position.y + height >= car.position.y + height && this.position.y <= car.position.y + height;

        return (bottom || top);
    }

    public Vector2 getPosition() {
        return position;
    }


}
