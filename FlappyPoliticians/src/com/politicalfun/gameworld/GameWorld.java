package com.politicalfun.gameworld;

import com.politicalfun.gameobjects.Neta;
import com.politicalfun.gameobjects.ScrollHandler;

public class GameWorld {

    private Neta neta;
    private ScrollHandler scroller;

    public GameWorld(int midPointY) {
        neta = new Neta(33, midPointY - 5, 25, 18);
        scroller = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta) {
        neta.update(delta);
        scroller.update(delta);
    }

    public Neta getNeta() {
        return neta;

    }
    
    public ScrollHandler getScroller() {
        return scroller;
    }
}
