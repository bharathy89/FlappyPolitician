package com.politicalfun.FPHelpers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture, logoTexture;
	public static TextureRegion bg, grass;

	public static Animation netaAnimation;
	public static TextureRegion neta, netaDown, netaUp;

	public static TextureRegion logo, zbLogo, skullUp, skullDown, bar, 
		playButtonUp, playButtonDown;
	public static Sound dead, flap, coin;
	
	public static BitmapFont font, shadow;
	
	public static BitmapFont sfont, sshadow;

	public static void load() {
		
		logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        
        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

		texture = new Texture(Gdx.files.internal("data/background.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        zbLogo = new TextureRegion(texture, 0, 55, 135, 24);
        zbLogo.flip(false, true);

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

		texture = new Texture(Gdx.files.internal("data/polit.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		netaDown = new TextureRegion(texture, 0, 0, 256, 163);
		netaDown.flip(false, true);

		neta = new TextureRegion(texture, 0, 0, 256, 163);
		neta.flip(false, true);

		netaUp = new TextureRegion(texture, 0, 0, 256, 163);
		netaUp.flip(false, true);

		TextureRegion[] netas = { netaDown, neta, netaUp };
		netaAnimation = new Animation(0.06f, netas);
		netaAnimation.setPlayMode(Animation.LOOP_PINGPONG);
		
		dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
		flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
		coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));

		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font.setScale(.25f, -.25f);
		
		
		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.setScale(.25f, -.25f);
		
		sfont = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		sfont.setScale(.15f, -.15f);
		
		sshadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		sshadow.setScale(.15f, -.15f);
	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		texture.dispose();
		dead.dispose();
		flap.dispose();
		coin.dispose();
		font.dispose();
		shadow.dispose();
	}

}