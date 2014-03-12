package com.politicalfun.gameworld;

import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.politicalfun.FPHelpers.AssetLoader;
import com.politicalfun.FPHelpers.InputHandler;
import com.politicalfun.gameobjects.Grass;
import com.politicalfun.gameobjects.Neta;
import com.politicalfun.gameobjects.Pipe;
import com.politicalfun.gameobjects.ScrollHandler;
import com.politicalfun.ui.SimpleButton;
import com.politicalfun.ui.Value;
import com.politicalfun.ui.ValueAccessor;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batcher;

	private int midPointY;
	private int gameHeight;

	private Neta neta;
	private ScrollHandler scroller;
	private Grass frontGrass, backGrass;
	private Pipe pipe1, pipe2, pipe3;

	private TextureRegion bg, grass;
	private Animation netaAnimation;
	private TextureRegion netaMid, netaDown, netaUp;
	private TextureRegion skullUp, skullDown, bar;

	// Tween stuff
	private TweenManager manager;
	private Value alpha = new Value();

	// Buttons
    private List<SimpleButton> menuButtons;


	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;

		 this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
	                .getMenuButtons();
		// The word "this" refers to this instance.
		// We are setting the instance variables' values to be that of the
		// parameters passed in from GameScreen.
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		// Call helper methods to initialize instance variables
		initGameObjects();
		initAssets();

	}

    private void drawNeta(float runTime) {

        if (neta.shouldntFlap()) {
            batcher.draw(netaMid, neta.getX(), neta.getY(),
                    neta.getWidth() / 2.0f, neta.getHeight() / 2.0f,
                    neta.getWidth(), neta.getHeight(), 1, 1, neta.getRotation());

        } else {
            batcher.draw(netaAnimation.getKeyFrame(runTime), neta.getX(),
                    neta.getY(), neta.getWidth() / 2.0f,
                    neta.getHeight() / 2.0f, neta.getWidth(), neta.getHeight(),
                    1, 1, neta.getRotation());
        }

    }


	public void render(float delta, float runTime) { 
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Filled);

		// Draw Background color
		shapeRenderer.setColor(51 / 255.0f, 51 / 255.0f, 51 / 255.0f, 1);
		shapeRenderer.rect(0, 0, 136, midPointY + 66);

		// Draw Grass
		shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
		shapeRenderer.rect(0, midPointY + 66, 136, 11);

		// Draw Dirt
		shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
		shapeRenderer.rect(0, midPointY + 77, 136, 52);

		shapeRenderer.end();

		batcher.begin();
		batcher.disableBlending();
		batcher.draw(bg, 0, 0 , 186, 128);

		// 1. Draw Grass
		drawGrass();

		// 2. Draw Pipes
		drawPipes();
		batcher.enableBlending();

		// 3. Draw Skulls (requires transparency)
		drawSkulls();
		
		drawNeta(runTime);
		gameState();
		
		

		batcher.end();

	}

	private void gameState() {
		if (myWorld.isReady()) {
			// Draw shadow first
			AssetLoader.shadow.draw(batcher, "Ready ?", (136 / 2)
					- (42), 76);
			// Draw text
			AssetLoader.font.draw(batcher, "Ready ?", (136 / 2)
					- (42 - 1), 75);
			
		} else {
			if(myWorld.isGameOver()) {
				drawButtonsForGameOver();

				String gameOver = "Game Over!";
				String vote= "#votewisely";
				String restart = "Restart?";

				AssetLoader.shadow.draw(batcher, "" + gameOver, (float) ((136 / 2) - (4.5 * gameOver.length())), gameHeight/2 - 12 );

				AssetLoader.font.draw(batcher, "" +gameOver , (float) ((136 / 2) - (4.5 * gameOver.length() - 1)),gameHeight/2- 11);

				AssetLoader.shadow.draw(batcher, "" + vote, (float) ((136 / 2) - (4.5 * vote.length())), gameHeight/2 + 11);

				AssetLoader.font.draw(batcher, "" +vote , (float) ((136 / 2) - (4.5 * vote.length() - 1)),gameHeight/2 + 12);

				AssetLoader.sshadow.draw(batcher, "" + restart, 10, gameHeight/2 + 40);

				AssetLoader.sfont.draw(batcher, "" +restart , 10,gameHeight/2 + 39);

				AssetLoader.sshadow.draw(batcher, "menu", 136 - 30, gameHeight/2 + 40);

				AssetLoader.sfont.draw(batcher, "menu" , 136 - 30,gameHeight/2 + 39);
			}
			// Convert integer into String
			String score = myWorld.getScore() + "";

			// Draw shadow first
			AssetLoader.shadow.draw(batcher, "Scams :" + myWorld.getScore(), 10, 11);
			// Draw text
			AssetLoader.font.draw(batcher, "Scams :" + myWorld.getScore(), 10, 12);

			// Draw shadow first
			AssetLoader.shadow.draw(batcher, "Goodness :" + myWorld.getCredibilityScore(), 10, gameHeight - 30);
			// Draw text
			AssetLoader.font.draw(batcher, "Goodness :" + myWorld.getCredibilityScore(), 10, gameHeight - 29);

		}
	}

	private void initGameObjects() {
		neta = myWorld.getNeta();
		scroller = myWorld.getScroller();
		frontGrass = scroller.getFrontGrass();
		backGrass = scroller.getBackGrass();
		pipe1 = scroller.getPipe1();
		pipe2 = scroller.getPipe2();
		pipe3 = scroller.getPipe3();
	}


	private void initAssets() {
		bg = AssetLoader.bg;
		grass = AssetLoader.grass;
		netaAnimation = AssetLoader.netaAnimation;
		netaMid = AssetLoader.neta;
		netaDown = AssetLoader.netaDown;
		netaUp = AssetLoader.netaUp;
		skullUp = AssetLoader.skullUp;
		skullDown = AssetLoader.skullDown;
		bar = AssetLoader.bar;
	}

	private void drawGrass() {
		// Draw the grass
		batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
				frontGrass.getWidth(), frontGrass.getHeight());
		batcher.draw(grass, backGrass.getX(), backGrass.getY(),
				backGrass.getWidth(), backGrass.getHeight());
	}

	private void drawSkulls() {
		// Temporary code! Sorry about the mess :)
		// We will fix this when we finish the Pipe class.

		batcher.draw(skullUp, pipe1.getX() - 1,
				pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
		batcher.draw(skullDown, pipe1.getX() - 1,
				pipe1.getY() + pipe1.getHeight() + Pipe.VERTICAL_GAP, 24, 14);

		batcher.draw(skullUp, pipe2.getX() - 1,
				pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
		batcher.draw(skullDown, pipe2.getX() - 1,
				pipe2.getY() + pipe2.getHeight() +  Pipe.VERTICAL_GAP, 24, 14);

		batcher.draw(skullUp, pipe3.getX() - 1,
				pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
		batcher.draw(skullDown, pipe3.getX() - 1,
				pipe3.getY() + pipe3.getHeight() +  Pipe.VERTICAL_GAP, 24, 14);
	}

	private void drawPipes() {
		// Temporary code! Sorry about the mess :)
		// We will fix this when we finish the Pipe class.
		batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
				pipe1.getHeight());
		batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() +  Pipe.VERTICAL_GAP,
				pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() +  Pipe.VERTICAL_GAP));

		batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
				pipe2.getHeight());
		batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() +  Pipe.VERTICAL_GAP,
				pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() +  Pipe.VERTICAL_GAP));

		batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
				pipe3.getHeight());
		batcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() +  Pipe.VERTICAL_GAP,
				pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() +  Pipe.VERTICAL_GAP));
	}

	private void drawButtonsForGameOver() {

		for (SimpleButton button : menuButtons) {
			button.draw(batcher);
		}

	}

	private void drawTransition(float delta) {
		if (alpha.getValue() > 0) {
			manager.update(delta);
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(1, 1, 1, alpha.getValue());
			shapeRenderer.rect(0, 0, 136, 300);
			shapeRenderer.end();
			Gdx.gl.glDisable(GL10.GL_BLEND);

		}
	}

}
