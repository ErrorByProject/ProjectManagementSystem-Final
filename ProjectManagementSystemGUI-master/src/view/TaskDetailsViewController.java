package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;

public class TaskDetailsViewController
{
    @FXML private TextField taskID;
    @FXML private TextField requirementID;
    @FXML private TextField labelName;
    @FXML private TextField description;
    @FXML private TextField numberOfTeamMembers;
    @FXML private TextField spentHours;
    @FXML private TextField estimatedHours;
    @FXML private TextField deadline;
    @FXML private Label errorLabel;

    private Region root;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private Task task;
    private Requirement requirement;
    private Project project;
    public TaskDetailsViewController()
    {
        // Called by FXMLLoader
    }

    public void init(ViewHandler viewHandler, ColourITModel model, Region root, Task task,Requirement requirement,Project project)
    {
        this.model = model;
        this.requirement=requirement;
        this.project=project;
        this.viewHandler = viewHandler;
        this.root = root;
        this.task=task;
        reset();
    }

    public void reset()
    {
        this.taskID.setText("");
        this.requirementID.setText("");
        this.labelName.setText("");
        this.description.setText("");
        this.numberOfTeamMembers.setText("");
        this.estimatedHours.setText("");
        this.deadline.setText("");
        try
        {
            this.taskID.setText(model.getTaskID(task));
            this.taskID.setEditable(false);
            this.requirementID.setText(model.getRequirementIDOfTheTask(task));
            this.requirementID.setEditable(false);
            this.labelName.setText(model.getLabelNameOfTheTask(task));
            this.labelName.setEditable(false);
            this.requirementID.setEditable(false);
            this.description.setText(model.getDescriptionOfTheTask(task));
            this.description.setEditable(false);
            this.numberOfTeamMembers.setText(0+"");
            this.numberOfTeamMembers.setEditable(false);
            this.spentHours.setText(model.getSpentHoursOfTheTask(task)+"");
            this.spentHours.setEditable(false);
            this.estimatedHours.setText(model.getEstimatedHoursOfTheTask(task)+"");
            this.estimatedHours.setEditable(false);
            this.deadline.setText(model.getDeadlineOfTheTask(task)+"");
            this.deadline.setEditable(false);
            this.errorLabel.setText("");
        }
        catch (Exception e)
        {
            this.errorLabel.setText(e.getMessage());
        }
    }

    public Region getRoot()
    {
        return root;
    }

    @FXML private void submitButtonPressed(){
        errorLabel.setText("");
        try
        {
            model.removeTask(task);
            //project.getRequirements().getRequirement(requirement).getTasks().removeTask(task);
            model.addTask(task);
            viewHandler.openView("taskList",requirement,project);
        }
        catch (NumberFormatException e)
        {
            errorLabel.setText("Illegal " + e.getMessage());
        }
        catch (Exception e)
        {
            errorLabel.setText(e.getMessage());
        }
    }
    @FXML private void backButtonPressed()
    {
        viewHandler.openView("taskList",requirement,project);
    }
    @FXML private void addTimeSpentButtonPressed(){
        model.removeTask(task);
        viewHandler.openView("addHoursSpent",requirement,project,task);
          }
    @FXML private void editDeadlineButtonPressed(){
        model.removeTask(task);
        viewHandler.openView("editDeadline",requirement,project,task);
    };
    @FXML private void setTaskIDButtonPressed(){
        model.removeTask(task);
        viewHandler.openView("setTaskID",requirement,project,task);
    }
    @FXML private void setTaskNameButtonPressed(){
        model.removeTask(task);
        viewHandler.openView("setTaskName",requirement,project,task);
    }
    @FXML private void setTaskDescriptionButtonPressed(){
        model.removeTask(task);
        viewHandler.openView("setTaskDescription",requirement,project,task);
    }@FXML private void setEstimatedHoursButtonPressed(){
    model.removeTask(task);
    viewHandler.openView("setTaskEstimatedHours",requirement,project,task);
}
    @FXML private void finishTaskButtonPressed(){
        model.removeTask(task);
        task.setStatus(Status.ENDED);
        model.addTask(task);
        viewHandler.openView("taskList",requirement,project);
    }
}

