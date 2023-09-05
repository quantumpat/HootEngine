import org.hootengine.core.Game;

import static org.hootengine.scene.SceneType.*;

public class DemoGame {

    public static void main(String[] args) {

        Game game = new Game();
        game.getConfig().setTitle("Demo Game");

        //Add scenes
        game.getSceneManager().addScene(new DemoScene(game), BASIC);

        //Start the game
        game.start();

    }

}
