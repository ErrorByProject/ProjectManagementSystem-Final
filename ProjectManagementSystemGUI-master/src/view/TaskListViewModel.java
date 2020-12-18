package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ColourITModel;
import model.Task;

/**
 * @author group 3
 * @version 1.0
 */
public class TaskListViewModel {
    private ObservableList<TaskViewModel> list;
    private ColourITModel model;
    private ViewState viewState;

    /**
     * A 2 argument constructor creating a task list model
     * @param model the model
     * @param viewState the view state
     */
    public TaskListViewModel(ColourITModel model,ViewState viewState){
        this.model=model;
        this.list= FXCollections.observableArrayList();
        this.viewState=viewState;
        update();
    }

    /**
     * A update method updating the tex fields
     */
    public void update(){
        list.clear();
        for(int i=0;i< model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getSize();i++){
            list.add(new TaskViewModel( model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByIndex(i)));
        }
    }

    /**
     * A method getting the task list
     * @return list
     */
    public ObservableList<TaskViewModel> getList(){
        return list;
    }

    /**
     * A method removing a task
     * @param task the task
     */
    public void remove(Task task){
        for (int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getTaskIDProperty().get().equals(task.getTaskID()) && list.get(i).getStatusProperty().get().equals(task.getStatus())
                    && list.get(i).getLabelNameProperty().get().equals(task.getLabelName()) && list.get(i).getDeadlineProperty().get().equals(task.getDeadline())
                    && list.get(i).getEstimatedHoursProperty().get()==task.getEstimatedHours() && list.get(i).getSpentHoursProperty().get()==task.getTimeSpent()
                    && list.get(i).getRequirementIDProperty().get().equals(task.getRequirementID()) && list.get(i).getDescriptionProperty().get().equals(task.getDescription())){
                list.remove(i);
                break;
            }
        }
    }

    /**
     * A method adding the task
     * @param task
     */
    public void add(Task task){
        list.add(new TaskViewModel( task));
    }
}
