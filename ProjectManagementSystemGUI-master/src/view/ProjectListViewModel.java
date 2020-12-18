package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ColourITModel;
import model.Project;

/**
 * @author group 3
 * @version 1.0
 */
public class ProjectListViewModel
{
    private ObservableList<ProjectViewModel> list;
    private ColourITModel model;

    /**
     * A 2 argument constructor creating a project list view window
     * @param model the model
     * @param viewState the view state
     */
    public ProjectListViewModel(ColourITModel model, ViewState viewState)
    {
        this.model = model;
        this.list = FXCollections.observableArrayList();
        update();
    }

    /**
     * A update method updating the tex fields
     */
    public void update()
    {
        list.clear();
        for (int i = 0; i < model.getNumberOfProjects(); i++)
        {
            list.add(new ProjectViewModel(model.getProjectByIndex(i)));
        }
    }


    /**
     * A method getting a project view model list
     * @return the list
     */
    public ObservableList<ProjectViewModel> getList()
    {
        return list;
    }

    /**
     * A method removing the project
     * @param project the project
     */
    public void remove(Project project)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getProjectIDProperty().get().equals(project.getProjectID()))
            {
                list.remove(i);
                break;
            }
        }
    }

    /**
     * A method adding the project
     * @param project the project
     */
    public void add(Project project)
    {
        list.add(new ProjectViewModel(project));
    }
}