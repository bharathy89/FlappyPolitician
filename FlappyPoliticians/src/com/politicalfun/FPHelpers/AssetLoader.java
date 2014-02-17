package com.politicalfun.FPHelpers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture;
	public static TextureRegion bg, grass;

	public static Animation netaAnimation;
	public static TextureRegion neta, netaDown, netaUp;

	public static TextureRegion skullUp, skullDown, bar;

	public static void load() {

		texture = new Texture(Gdx.files.internal("data/background.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		bg = new TextureRegion(texture, 0, 0, 512, 256);
		bg.flip(false, true);

		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		grass = new TextureRegion(texture, 0, 43, 143, 11);
		grass.flip(false, true);

		//        netaDown = new TextureRegion(texture, 136, 0, 17, 12);
		//        netaDown.flip(false, true);
		//
		//        neta = new TextureRegion(texture, 153, 0, 17, 12);
		//        neta.flip(false, true);
		//
		//        netaUp = new TextureRegion(texture, 170, 0, 17, 12);
		//        netaUp.flip(false, true);
		//
		//        TextureRegion[] netas = { netaDown, neta, netaUp };
		//        netaAnimation = new Animation(0.06f, netas);
		//        netaAnimation.setPlayMode(Animation.LOOP_PINGPONG);

		texture = new Texture(Gdx.files.internal("data/pipes.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		skullUp = new TextureRegion(texture, 0, 0, 24, 14);
		// Create by flipping existing skullUp
		skullDown = new TextureRegion(skullUp);
		skullDown.flip(false, true);

		bar = new TextureRegion(texture, 0, 14, 22, 3);
		bar.flip(false, true);

		texture = new Texture(Gdx.files.internal("data/birdie.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		netaDown = new TextureRegion(texture, 0, 0, 64, 64);
		netaDown.flip(false, true);

		neta = new TextureRegion(texture, 0, 0, 64, 64);
		neta.flip(false, true);

		netaUp = new TextureRegion(texture, 0, 0, 64, 64);
		netaUp.flip(false, true);

		TextureRegion[] netas = { netaDown, neta, netaUp };
		netaAnimation = new Animation(0.06f, netas);
		netaAnimation.setPlayMode(Animation.LOOP_PINGPONG);


	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		texture.dispose();
	}

}