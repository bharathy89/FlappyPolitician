package com.politicalfun.gameobjects;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.politicalfun.FPHelpers.AssetLoader;

public class Neta {

	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;

	private float rotation;
	private int width;
	private int height;
	 private float originalY;

	private Circle boundingCircle1;
	private Circle boundingCircle2;

	private boolean collided = false;


	public Neta(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		this.originalY = y;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 460);
		boundingCircle1 = new Circle();
		boundingCircle2 = new Circle();
		collided = false;
	}

	public void update(float delta) {

		velocity.add(acceleration.cpy().scl(delta));

		if (velocity.y > 200) {
			velocity.y = 200;
		}

		// CEILING CHECK 
		if (position.y < -13) {
			position.y = -13;
			velocity.y = 0;
		}

		position.add(velocity.cpy().scl(delta));

		// Set the circle's center to be (9, 6) with respect to the bird.
		// Set the circle's radius to be 6.5f;
		boundingCircle1.set(position.x + 12 , position.y + 10 , 4f);
		
		boundingCircle2.set(position.x + 12, position.y + 10, 9.0f);


		// Rotate counterclockwise
		if (velocity.y < 0) {
			rotation -= 600 * delta;

			if (rotation < -20) {
				rotation = -20;
			}
		}

		// Rotate clockwise
		if (isFalling() ) {
			rotation += 480 * delta;
			if (rotation > 90) {
				rotation = 90;
			}

		}

	}
	
	public void updateReady(float runTime) {
        position.y = 2 * (float) Math.sin(7 * runTime) + originalY;
    }


	public void onClick() {
		if(!collided) {
			System.out.println(collided);
			AssetLoader.flap.play();
			velocity.y = -140;
		}
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getRotation() {
		return rotation;
	}

	public boolean isFalling() {
		return velocity.y > 110;
	}

	public boolean shouldntFlap() {
		return velocity.y > 70 || !collided;
	}

	public Circle getBoundingCircle1() {
		return boundingCircle1;
	}
	
	public Circle getBoundingCircle2() {
		return boundingCircle2;
	}

	public boolean isCollided() {
		return collided;
	}

	public void die() {
		collided = true;
	}
	
	public void onRestart(int y) {
        rotation = 0;
        position.y = y;
        velocity.x = 0;
        velocity.y = 0;
        acceleration.x = 0;
        acceleration.y = 460;
        collided = false;
    }

}