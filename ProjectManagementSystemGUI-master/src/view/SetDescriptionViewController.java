package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;

public class SetDescriptionViewController {
    @FXML private TextField taskID;
    @FXML private TextField taskDescription;
    @FXML private TextField newTaskDescription;
    @FXML private Label errorLabel;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private Region root;
    private Task task;
    private Requirement requirement;
    private Project project;
    public SetDescriptionViewController(){

    }
    public void init(ViewHandler viewHandler, ColourITModel model, Region root, Task task,Requirement requirement,Project project){
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;
        this.task=task;
        this.project=project;
        this.requirement=requirement;
        reset();
    }
    public void reset(){
        this.taskID.setText("");
        this.taskDescription.setText("");
        this.newTaskDescription.setText("");
        this.errorLabel.setText("");
        try{
            this.taskID.setText(model.getTaskID(task));
            this.taskID.setEditable(false);
            this.taskDescription.setText(model.getDescriptionOfTheTask(task)+"");
            this.taskDescription.setEditable(false);

        }catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }
    public Region getRoot(){
        return root;
    }
    @FXML private void backButtonPressed(){
        viewHandler.openView("taskDetails",requirement,project,task);
    }
    @FXML private void submitButtonPressed(){
        errorLabel.setText("");
        try
        {
            task.setDescription(newTaskDescription.getText());
            viewHandler.openView("taskDetails",requirement,project,task);
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
