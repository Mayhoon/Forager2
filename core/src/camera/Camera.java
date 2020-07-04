package camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera {
    private float cameraSpeed = 60f;
    private float x, y;

    public Camera() {
        float width = 300*Gdx.graphics.getHeight()/Gdx.graphics.getWidth();
        float height = 300*Gdx.graphics.getHeight()/Gdx.graphics.getWidth();
//        super.setToOrtho(false, width, height);
        super.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        super.position.x = 0;
        super.position.y = 0;
        super.update();
    }
//
//    public void move(Vector2 pos) {
//        this.position.x = pos.x + 20;
//        this.position.y = pos.y + 65;
//    }
}
