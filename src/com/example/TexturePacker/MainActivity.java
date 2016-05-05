package com.example.TexturePacker;
/*
 * TexturePacker example
 * AndEngine GLES2-AnchorCenter
 * Agung Cahyawan (agung dot cahyawan at unud dot ac dot id)
 */


import java.io.IOException;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.debug.Debug;
import org.andengine.util.texturepack.TexturePack;
import org.andengine.util.texturepack.TexturePackLoader;
import org.andengine.util.texturepack.TexturePackTextureRegionLibrary;
import org.andengine.util.texturepack.exception.TexturePackParseException;

/**
 * @author ajung
 *
 */
public class MainActivity extends SimpleBaseGameActivity {

	private static final int CAMERA_WIDTH = 320;
	private static final int CAMERA_HEIGHT = 480;

	private Camera m_Camera;

	private TexturePackTextureRegionLibrary texturePackLibrary;
	private TexturePack texturePack;

	private ITextureRegion textureRegionBanana;
	private ITextureRegion textureRegionCherries;
	private ITextureRegion textureRegionOrange;
	private ITextureRegion textureRegion_3;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		m_Camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions en = new EngineOptions(true,
				ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(
						CAMERA_WIDTH, CAMERA_HEIGHT), m_Camera);

		return en;
	}

	@Override
	protected void onCreateResources() throws IOException {
		loadGraphics();
		
	}

	@Override
	protected Scene onCreateScene() {
		final Scene scene = new Scene();
		scene.setBackground(new Background(100/255f, 150/255f, 200/255f));
		Sprite banana = new Sprite(CAMERA_WIDTH/2, CAMERA_HEIGHT*1/6, textureRegionBanana, getVertexBufferObjectManager());
		Sprite cherries = new Sprite(CAMERA_WIDTH/2, CAMERA_HEIGHT*3/6, textureRegionCherries, getVertexBufferObjectManager());
		Sprite orange = new Sprite(CAMERA_WIDTH/2, CAMERA_HEIGHT*5/6, textureRegionOrange, getVertexBufferObjectManager());
		scene.attachChild(banana);
		scene.attachChild(cherries);
		scene.attachChild(orange);
		return scene;
	}

	private void loadGraphics()
	{
	    try 
	    {
			texturePack = new TexturePackLoader(getAssets(),
					getTextureManager()).loadFromAsset("gfx/our_texture.xml","gfx/");
			texturePack.loadTexture();
			texturePackLibrary = texturePack
					.getTexturePackTextureRegionLibrary();
	    } 
	    catch (final TexturePackParseException e) 
	    {
	        Debug.e(e);
	    }
	    
	    textureRegionBanana = texturePackLibrary.get(OurTexture.BANANA_ID);
	    textureRegionCherries = texturePackLibrary.get(OurTexture.CHERRIES_ID);
	    textureRegionOrange = texturePackLibrary.get(OurTexture.ORANGE_ID);

	}
}
