package com.politicalfun.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.politicalfun.FPHelpers.AssetLoader;
import com.politicalfun.gameobjects.Neta;
import com.politicalfun.gameobjects.ScrollHandler;

public class GameWorld {

    private Neta neta;
    private ScrollHandler scroller;
    private Rectangle ground;
    private int score = 0;
    private float runTime = 0;
    private int credScore = 100;
    
    private int midPointY;
    
    
  
    public int getMidPointY() {
		return midPointY;
	}

	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER
    }
    
    private GameState currentState;


    public GameWorld(int midPointY) {
    	currentState = GameState.READY;
        neta = new Neta(33, midPointY - 5, 25, 18);
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 137, 11);
        this.midPointY = midPointY;
    }
    
    public void update(float delta) {
        runTime += delta;

        switch (currentState) {
        case READY:
        case MENU:
            updateReady(delta);
            break;

        case RUNNING:
            updateRunning(delta);
            break;
        default:
            break;
        }

    }
    
    private void updateReady(float delta) {
        neta.updateReady(runTime);
        scroller.updateReady(delta);
    }


    private void updateRunning(float delta) {
    	
    	if (delta > .15f) {
            delta = .15f;
        }
    	
        neta.update(delta);
        scroller.update(delta);
        
        if (!neta.isCollided() && scroller.collides(neta)) {
            scroller.stop();
            neta.die();
            currentState = GameState.GAMEOVER;
            AssetLoader.dead.play();
          
        }
        
        if(!neta.isCollided() && credScore <=0) {
        	credScore = 0;
        	neta.die();
        	currentState = GameState.GAMEOVER;
        	scroller.stop();
        	AssetLoader.dead.play();
        	
        }
        
        if (Intersector.overlaps(neta.getBoundingCircle2(), ground)) {
            scroller.stop();
            neta.die();
            currentState = GameState.GAMEOVER;
            
        }

    }
    
    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        credScore = 100;
        neta.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public Neta getNeta() {
        return neta;

    }
    
    public ScrollHandler getScroller() {
        return scroller;
    }
    
    public int getScore() {
        return score;
    }
    
    public int getCredibilityScore() {
    	return credScore;
    }

    public void addScore(int increment) {
        score += increment;
    }
    
    public void decreaseCredibilityScore(int decrement) {
        credScore -= decrement;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public void ready() {
        currentState = GameState.READY;
    }
    

}
