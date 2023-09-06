import org.hootengine.core.Game;

import static org.hootengine.scene.SceneType.*;

public class DemoGame {

    public static void main(String[] args) {

        Game game = new Game();
        game.getConfig().setTitle("Demo Game");
        game.getConfig().setBackgroundColor(255, 255, 255, 1.0);

        //Add scenes
        game.getSceneManager().addScene(new DemoScene(game), BASIC);

        //Start the game
        game.start();

    }

}
