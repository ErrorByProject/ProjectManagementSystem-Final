package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;

/**
 * @author group 3
 * @version 1.0
 */
public class AddTimeSpentViewController {
    @FXML private TextField taskID;
    @FXML private TextField hoursSpent;
    @FXML private TextField addHoursSpent;
    @FXML private Label errorLabel;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private Region root;
    private ViewState viewState;
    /**
     * A 0 argument constructor for the window
     */
    public AddTimeSpentViewController(){

    }

    /**
     * A initializer method initializing the components
     * @param viewHandler the view handler
     * @param model the model
     * @param root the root
     * @param viewState the view state
     */
    public void init(ViewHandler viewHandler, ColourITModel model, Region root, ViewState viewState){
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewState=viewState;
        reset();
    }

    /**
     * A reset method resetting the tex fields
     */
    public void reset(){
        this.taskID.setText("");
        this.hoursSpent.setText("");
        this.addHoursSpent.setText("");
        this.errorLabel.setText("");
        try{
            this.taskID.setText(model.getTaskID(viewState.getSelectedTask(),viewState.getSelectedRequirement(),viewState.getSelectedProject()));
            this.taskID.setEditable(false);
            this.hoursSpent.setText(model.getSpentHoursOfTheTask(viewState.getSelectedTask(),viewState.getSelectedRequirement(),viewState.getSelectedProject())+"");
            this.hoursSpent.setEditable(false);

        }catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }
    /**
     * A method getting the root
     * @return the root
     */
    public Region getRoot(){
        return root;
    }
    /**
     * A method canceling the process
     */
    @FXML private void backButtonPressed(){
        viewHandler.openView("taskDetails");
    }
    /**
     * A method for the button adding the time spent
     *
     */
    @FXML private void submitButtonPressed(){
        errorLabel.setText("");
        try
        {
            model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByID(viewState.getSelectedTask()).setHoursSpent(Double.parseDouble(addHoursSpent.getText()));
            viewHandler.openView("taskDetails");
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
