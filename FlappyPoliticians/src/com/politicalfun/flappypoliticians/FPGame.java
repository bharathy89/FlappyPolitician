package com.politicalfun.flappypoliticians;

import com.badlogic.gdx.Game;
import com.politicalfun.FPHelpers.AssetLoader;
import com.politicalfun.screens.SplashScreen;

public class FPGame  extends Game {
	
	@Override
    public void create() {
        AssetLoader.load();
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
