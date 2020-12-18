import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import view.ViewHandler;
/**
 * @author group 3
 * @version 1.0
 */
public class MyApplication extends Application
{
    /**
     * A method starting the application
     * @param primaryStage the stage
     */
    public void start(Stage primaryStage)
    {
        ColourITModel colourITModel = new ColourITModelManager();
        ViewHandler view = new ViewHandler(colourITModel);
        view.start(primaryStage);
    }


}
