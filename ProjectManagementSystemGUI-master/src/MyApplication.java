import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import view.ViewHandler;

public class MyApplication extends Application
{
    public void start(Stage primaryStage)
    {
        ColourITModel colourITModel = new ColourITModelManager();
        TeamModel teamModel = new TeamModelManager();
        ProjectListModel projectListModel = new ProjectListModelManager();
        RequirementListModel requirementListModel = new RequirementListModelManager();
        TaskListModel taskListModel = new TaskListModelManager();
        ViewHandler view = new ViewHandler(colourITModel);
        view.start(primaryStage);
    }


}
