package camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera {
    private float cameraSpeed = 60f;
    private float x, y;

    public Camera() {
        super.setToOrtho(false, Gdx.graphics.getWidth() * 2, Gdx.graphics.getHeight() * 2);
        super.position.x = 0;
        super.position.y = 0;
        super.update();
    }

    public void update() {
        super.translate((x * Gdx.graphics.getDeltaTime() * cameraSpeed), (y * Gdx.graphics.getDeltaTime()) * cameraSpeed);
        super.update();
    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
