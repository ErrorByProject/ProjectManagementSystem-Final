package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;

public class EditDeadlineViewController {
    @FXML private TextField taskID;
    @FXML private TextField currentDeadline;
    @FXML private TextField day;
    @FXML private TextField month;
    @FXML private TextField year;
    @FXML private Label errorLabel;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private Region root;
    private Task task;
    private Project project;
    private Requirement requirement;
    public EditDeadlineViewController(){

    }
    public void init(ViewHandler viewHandler, ColourITModel model, Region root, Task task,Requirement requirement,Project project){
         this.model = model;
         this.project = project;
         this.requirement= requirement;
        this.viewHandler = viewHandler;
        this.root = root;
        this.task=task;
        reset();
    }
    public void reset(){
        this.taskID.setText("");
        this.currentDeadline.setText("");
        this.day.setText("");
        this.month.setText("");
        this.year.setText("");
        this.errorLabel.setText("");
        try{
            this.taskID.setText(model.getTaskID(task));
            this.taskID.setEditable(false);
            this.currentDeadline.setText(model.getDeadlineOfTheTask(task)+"");
            this.currentDeadline.setEditable(false);

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

            task.setDeadline(new Date(Integer.parseInt(day.getText()),Integer.parseInt(month.getText()),Integer.parseInt(year.getText())));
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
