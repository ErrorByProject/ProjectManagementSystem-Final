import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import view.ViewHandler;

public class MyApplication extends Application
{
    public void start(Stage primaryStage)
    {
        ColourITModel colourITModel = new ColourITModelManager();
        ViewHandler view = new ViewHandler(colourITModel);
        view.start(primaryStage);
    }


}
