package view;

import javafx.beans.property.*;
import model.Date;
import model.Status;
import model.Task;

public class TaskViewModel {
    private StringProperty taskIDProperty;
    private StringProperty requirementIDProperty;
    private StringProperty labelNameProperty;
    private StringProperty descriptionProperty;
    private ObjectProperty<Date> deadlineProperty;
    private DoubleProperty estimatedHoursProperty;
    private DoubleProperty spentHoursProperty;
    private ObjectProperty<Status> statusProperty;
    private StringProperty teamMembersProperty;
    public TaskViewModel(Task task){
        taskIDProperty=new SimpleStringProperty(task.getTaskID());
        requirementIDProperty = new SimpleStringProperty(task.getRequirementID());
        labelNameProperty = new SimpleStringProperty(task.getLabelName());
        descriptionProperty = new SimpleStringProperty(task.getDescription());
        deadlineProperty = new SimpleObjectProperty<Date>(task.getDeadline());
        estimatedHoursProperty = new SimpleDoubleProperty(task.getEstimatedHours());
        spentHoursProperty = new SimpleDoubleProperty(task.getTimeSpent());
        statusProperty = new SimpleObjectProperty<Status>(task.getStatus());
        teamMembersProperty = new SimpleStringProperty(task.getTeamMembers());
    }
    public StringProperty getTaskIDProperty(){
        return taskIDProperty;
    }
    public StringProperty getRequirementIDProperty(){
        return requirementIDProperty;
    }
    public StringProperty getLabelNameProperty(){
        return labelNameProperty;
    }
    public ObjectProperty<Date> getDeadlineProperty(){
        return deadlineProperty;
    }
    public DoubleProperty getEstimatedHoursProperty(){
        return estimatedHoursProperty;
    }
    public StringProperty getDescriptionProperty(){return descriptionProperty;}
    public DoubleProperty getSpentHoursProperty(){
        return spentHoursProperty;
    }
    public ObjectProperty<Status> getStatusProperty(){
        return statusProperty;
    }
    public StringProperty getTeamMembersProperty(){return teamMembersProperty;}
}
