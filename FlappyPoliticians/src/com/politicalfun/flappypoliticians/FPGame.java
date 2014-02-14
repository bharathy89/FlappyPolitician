package com.politicalfun.flappypoliticians;

import com.badlogic.gdx.Game;
import com.politicalfun.screens.GameScreen;

public class FPGame  extends Game {
	
	@Override
	public void create() {		
		setScreen(new GameScreen());
	}
}
