package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;

public class SetTaskNameViewController {
    @FXML private TextField taskID;
    @FXML private TextField taskName;
    @FXML private TextField newTaskName;
    @FXML private Label errorLabel;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private Region root;
    private ViewState viewState;
    public SetTaskNameViewController(){

    }
    public void init(ViewHandler viewHandler, ColourITModel model, Region root, ViewState viewState){
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewState=viewState;
        reset();
    }
    public void reset(){
        this.taskID.setText("");
        this.taskName.setText("");
        this.newTaskName.setText("");
        this.errorLabel.setText("");
        try{
            this.taskID.setText(model.getTaskID(viewState.getSelectedTask(), viewState.getSelectedRequirement(), viewState.getSelectedProject()));
            this.taskID.setEditable(false);
            this.taskName.setText(model.getLabelNameOfTheTask(viewState.getSelectedTask(), viewState.getSelectedRequirement(), viewState.getSelectedProject())+"");
            this.taskName.setEditable(false);

        }catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }
    public Region getRoot(){
        return root;
    }
    @FXML private void backButtonPressed(){
        viewHandler.openView("taskDetails");
    }
    @FXML private void submitButtonPressed(){
        errorLabel.setText("");
        try
        {
            model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByID(viewState.getSelectedTask()).setLabelName(newTaskName.getText());
            viewHandler.openView("taskDetails");
        }
        catch (IllegalArgumentException e)
        {
            errorLabel.setText("Illegal " + e.getMessage());
        }
        catch (Exception e)
        {
            errorLabel.setText(e.getMessage());
        }
    }
}
