package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ColourITModel;
import model.ProjectListModel;
import model.Project;

public class ProjectListViewModel
{
    private ObservableList<ProjectViewModel> list;
    private ColourITModel model;

    public ProjectListViewModel(ColourITModel model)
    {
        this.model = model;
        this.list = FXCollections.observableArrayList();
        update();
    }

    public void update()
    {
        list.clear();
        for (int i = 0; i < model.getNumberOfProjects(); i++)
        {
            list.add(new ProjectViewModel(model.getProjectByIndex(i)));
        }
    }

    public ObservableList<ProjectViewModel> getList()
    {
        return list;
    }

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

    public void add(Project project)
    {
        list.add(new ProjectViewModel(project));
    }
}