import org.hootengine.core.Game;

import static org.hootengine.scene.SceneType.*;

public class DemoGame {

    public static void main(String[] args) {

        Game game = new Game();

        //Add scenes
        game.getSceneManager().addScene(new DemoScene(game), GAME);

        game.start();

    }

}
