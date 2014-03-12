package com.politicalfun.FPHelpers;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.politicalfun.gameobjects.Neta;
import com.politicalfun.gameworld.GameWorld;
import com.politicalfun.ui.SimpleButton;

public class InputHandler implements InputProcessor {
    private Neta neta;
    private GameWorld myWorld;

    private List<SimpleButton> menuButtons;

    private SimpleButton restartButton;
    private SimpleButton menuButton;

    private float scaleFactorX;
    private float scaleFactorY;

    public InputHandler(GameWorld myWorld, float scaleFactorX,
            float scaleFactorY) {
        this.myWorld = myWorld;
        neta = myWorld.getNeta();

        int midPointY = myWorld.getMidPointY();

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        menuButtons = new ArrayList<SimpleButton>();
        restartButton = new SimpleButton(
               10,
                midPointY + 39, 29, 16, AssetLoader.playButtonUp,
                AssetLoader.playButtonDown);
        menuButton = new SimpleButton(
                136  - 30,
                midPointY + 39, 29, 16, AssetLoader.playButtonUp,
                AssetLoader.playButtonDown);
        menuButtons.add(restartButton);
        menuButtons.add(menuButton);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);
        System.out.println(screenX + " " + screenY);
        if (myWorld.isReady()) {
            myWorld.start();
        }

        neta.onClick();

        if (myWorld.isGameOver()) {
        	if( restartButton.isTouchDown(screenX, screenY)) {
        		myWorld.restart();
        	} else if(menuButton.isTouchDown(screenX, screenY)) {
        		
        	}
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (myWorld.isGameOver()) {
            if (restartButton.isTouchUp(screenX, screenY)) {
                myWorld.ready();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {

//        // Can now use Space Bar to play the game
//        if (keycode == Keys.SPACE) {
//
//            if (myWorld.isMenu()) {
//                myWorld.ready();
//            } else if (myWorld.isReady()) {
//                myWorld.start();
//            }
//
//            neta.onClick();
//
//            if (myWorld.isGameOver()) {
//                myWorld.restart();
//            }
//
//        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }
}