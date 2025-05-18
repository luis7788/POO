package spotifyum;

import spotifyum.mvc.SpotifyumController;
import spotifyum.mvc.SpotifyumModel;
import spotifyum.mvc.SpotifyumView;

/**
 * Classe principal do SpotifyUM
 * 
 * Esta classe contém o método main que inicia a aplicação.
 */
public class Main {
    public static void main(String[] args) {
        SpotifyumModel model = new SpotifyumModel();
        SpotifyumController controller = new SpotifyumController(model);
        SpotifyumView view = new SpotifyumView(controller);
        controller.setView(view);
        model.setController(controller);

        view.run();
    }
}
