package tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class WorldDebugger {
    private ShapeDrawer shapeDrawer;
    private boolean render = true;

    public WorldDebugger(SpriteBatch batch) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.drawPixel(0, 0);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);
        shapeDrawer = new ShapeDrawer(batch, region);
    }

    public void renderWorld() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
           if( render == true) {
               render = false;
           }else {
               render = true;
           }
        }

        if(render) {
            shapeDrawer.rectangle(-250,0, 500, 0);
            shapeDrawer.rectangle(0,-250,0, 500);
        }
    }
}
