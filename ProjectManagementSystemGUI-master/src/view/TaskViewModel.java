package view;

/**
 * @author group 3
 * @version 1.0
 */
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

    /**
     * A one argument constructor creating a task view model
     * @param task the task
     */
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

    /**
     * A method returning the task id
     * @return id
     */
    public StringProperty getTaskIDProperty(){
        return taskIDProperty;
    }

    /**
     * A method returning the id
     * @return id
     */
    public StringProperty getRequirementIDProperty(){
        return requirementIDProperty;
    }

    /**
     * A method returning the name
     * @return name
     */
    public StringProperty getLabelNameProperty(){
        return labelNameProperty;
    }

    /**
     * A method returning the dead line
     * @return deadline
     */
    public ObjectProperty<Date> getDeadlineProperty(){
        return deadlineProperty;
    }

    /**
     * A method returning the estimated hours
     * @return
     */
    public DoubleProperty getEstimatedHoursProperty(){
        return estimatedHoursProperty;
    }

    /**
     * A method returning the description
     * @return description
     */
    public StringProperty getDescriptionProperty(){return descriptionProperty;}

    /**
     * A method returning the spent hours
     * @return spent hours
     */
    public DoubleProperty getSpentHoursProperty(){
        return spentHoursProperty;
    }

    /**
     * A method returning the status
     * @return status
     */
    public ObjectProperty<Status> getStatusProperty(){
        return statusProperty;
    }

    /**
     * A method returning team members
     * @return team members
     */
    public StringProperty getTeamMembersProperty(){return teamMembersProperty;}
}
