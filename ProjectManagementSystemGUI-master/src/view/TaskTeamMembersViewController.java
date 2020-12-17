package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;

public class TaskTeamMembersViewController {
    @FXML private TextField taskID;
    @FXML private Label errorLabel;
    @FXML private TextArea teamMemberList;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private Region root;
    private Task task;
    private Requirement requirement;
    private Project project;
    public TaskTeamMembersViewController(){

    }
    public void init(ViewHandler viewHandler, ColourITModel model, Region root, Task task,Requirement requirement,Project project){
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;
        this.task=task;
        this.requirement=requirement;
        this.project=project;
        reset();
    }
    public void reset(){
        this.taskID.setText("");
        this.errorLabel.setText("");
        this.teamMemberList.setText("");
        try{
            this.taskID.setText(task.getTaskID());
            this.taskID.setEditable(false);
            this.teamMemberList.setText(task.getTeamMembers());

        }catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }
    public Region getRoot(){
        return root;
    }
    @FXML private void backButtonPressed(){
        viewHandler.openView("taskList",requirement,project);
    }
    @FXML private void submitButtonPressed(){
        errorLabel.setText("");
        try
        {
            model.removeTask(task);
            task.setTeamMembers(teamMemberList.getText());
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
}
