package screens;

import Enums.Entity;
import camera.Camera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import config.Paths;
import networking.ServerClientWrapper;
import player.Player;
import tools.FpsDisplay;

public class Game extends ScreenAdapter {
    private Camera camera, ui_camera;
    private SpriteBatch batch;
    private Player player, player2;
    private Texture groundTexture;
    private Sprite groundSprite;
    private FpsDisplay fpsDisplay;


    public Game(ServerClientWrapper serverClientWrapper, SpriteBatch batch) {
        this.batch = batch;

        camera = new Camera();
        camera.zoom -= 0.7f;
        camera.position.x = 0;
        camera.position.y = 0;
        camera.update();

        ui_camera = new Camera();
        fpsDisplay = new FpsDisplay();

        player = new Player(Entity.Player, serverClientWrapper, camera);
        player2 = new Player(Entity.Opponent, serverClientWrapper, camera);

        groundTexture = new Texture(Paths.GROUND);
        groundSprite = new Sprite(groundTexture);
    }



    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            System.exit(0);
        }

        Gdx.gl.glClearColor(99/255, 155/255, 255/255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Render world
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.render(batch);
        player2.render(batch);
        batch.draw(groundSprite, 0, -17);

        //Render ui
        batch.setProjectionMatrix(ui_camera.combined);
        fpsDisplay.update(batch);

        batch.end();
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
