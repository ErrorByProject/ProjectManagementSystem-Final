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
    private ViewState viewState;
    public TaskDetailsViewController()
    {
        // Called by FXMLLoader
    }

    public void init(ViewHandler viewHandler, ColourITModel model, Region root, ViewState viewState)
    {
        this.model = model;
        this.viewState=viewState;
        this.viewHandler = viewHandler;
        this.root = root;
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
            this.taskID.setText(model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByID(viewState.getSelectedTask()).getTaskID());
            this.taskID.setEditable(false);
            this.requirementID.setText(model.getRequirementIDOfTheTask(viewState.getSelectedTask(),viewState.getSelectedRequirement(),viewState.getSelectedProject()));
            this.requirementID.setEditable(false);
            this.labelName.setText(model.getLabelNameOfTheTask(viewState.getSelectedTask(),viewState.getSelectedRequirement(),viewState.getSelectedProject()));
            this.labelName.setEditable(false);
            this.requirementID.setEditable(false);
            this.description.setText(model.getDescriptionOfTheTask(viewState.getSelectedTask(),viewState.getSelectedRequirement(),viewState.getSelectedProject()));
            this.description.setEditable(false);
            this.numberOfTeamMembers.setText(0+"");
            this.numberOfTeamMembers.setEditable(false);
            this.spentHours.setText(model.getSpentHoursOfTheTask(viewState.getSelectedTask(),viewState.getSelectedRequirement(),viewState.getSelectedProject())+"");
            this.spentHours.setEditable(false);
            this.estimatedHours.setText(model.getEstimatedHoursOfTheTask(viewState.getSelectedTask(),viewState.getSelectedRequirement(),viewState.getSelectedProject())+"");
            this.estimatedHours.setEditable(false);
            this.deadline.setText(model.getDeadlineOfTheTask(viewState.getSelectedTask(),viewState.getSelectedRequirement(),viewState.getSelectedProject())+"");
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

    @FXML private void backButtonPressed()
    {
        viewState.setSelectedTask("");
        viewHandler.openView("taskList");
    }
    @FXML private void addTimeSpentButtonPressed(){
        viewHandler.openView("addHoursSpent");
          }
    @FXML private void editDeadlineButtonPressed(){
        viewHandler.openView("editDeadline");
    };
    @FXML private void setTaskIDButtonPressed(){
        viewHandler.openView("setTaskID");
    }
    @FXML private void setTaskNameButtonPressed(){
        viewHandler.openView("setTaskName");
    }
    @FXML private void setTaskDescriptionButtonPressed(){
        viewHandler.openView("setTaskDescription");
    }@FXML private void setEstimatedHoursButtonPressed(){
    viewHandler.openView("setTaskEstimatedHours");
}
    @FXML private void finishTaskButtonPressed(){
       model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByID(viewState.getSelectedTask()).setStatus(Status.ENDED);
        viewState.setSelectedTask("");
       viewHandler.openView("taskList");
    }
}

