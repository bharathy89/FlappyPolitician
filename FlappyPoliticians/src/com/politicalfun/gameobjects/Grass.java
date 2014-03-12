package com.politicalfun.gameobjects;


public class Grass extends Scrollable {

    // When Grass's constructor is invoked, invoke the super (Scrollable)
    // constructor
    public Grass(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);

    }
    
    public boolean collides(Neta neta) {
        if (position.y <= neta.getY() + neta.getHeight()) {
           return true;
        }
        return false;
    }
    
    public void onRestart(float x, float scrollSpeed) {
        position.x = x;
        velocity.x = scrollSpeed;
    }

}