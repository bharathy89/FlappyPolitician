package com.politicalfun.FPHelpers;
import com.badlogic.gdx.InputProcessor;
import com.politicalfun.gameobjects.Neta;

public class InputHandler implements InputProcessor {
    private Neta neta;

    // Ask for a reference to the neta when InputHandler is created.
    public InputHandler(Neta neta) {
        // neta now represents the gameWorld's neta.
        this.neta = neta;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        neta.onClick();
        return true; // Return true to say we handled the touch.
    }

    @Override
    public boolean keyDown(int keycode) {
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
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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

}