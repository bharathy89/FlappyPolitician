package com.politicalfun.flappypoliticians;

import com.badlogic.gdx.Game;
import com.politicalfun.FPHelpers.AssetLoader;
import com.politicalfun.screens.GameScreen;

public class FPGame  extends Game {
	
	@Override
    public void create() {
        System.out.println("ZBGame Created!");
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
