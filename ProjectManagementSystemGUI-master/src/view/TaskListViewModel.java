package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ColourITModel;
import model.Task;

public class TaskListViewModel {
    private ObservableList<TaskViewModel> list;
    private ColourITModel model;
    private ViewState viewState;
    public TaskListViewModel(ColourITModel model,ViewState viewState){
        this.model=model;
        this.list= FXCollections.observableArrayList();
        this.viewState=viewState;
        update();
    }
    public void update(){
        list.clear();
        for(int i=0;i< model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getSize();i++){
            list.add(new TaskViewModel( model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByIndex(i)));
        }
    }
    public ObservableList<TaskViewModel> getList(){
        return list;
    }
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
    public void add(Task task){
        list.add(new TaskViewModel( task));
    }
}
