package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;

/**
 * @author group 3
 * @version 1.0
 */
public class TaskTeamMembersViewController {
    @FXML private TextField taskID;
    @FXML private Label errorLabel;
    @FXML private TextArea teamMemberList;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private Region root;
    private ViewState viewState;
    /**
     * A 0 argument constructor for the window
     */
    public TaskTeamMembersViewController(){

    }
    /**
     * A initializer initializing the components
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
        this.errorLabel.setText("");
        this.teamMemberList.setText("");
        try{
            this.taskID.setText(model.getTaskID(viewState.getSelectedTask(),viewState.getSelectedRequirement(),viewState.getSelectedProject()));
            this.taskID.setEditable(false);
            this.teamMemberList.setText(model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByID(viewState.getSelectedTask()).getTeamMembers());

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
     * A method for the back button
     */
    @FXML private void backButtonPressed(){
        viewState.setSelectedTask("");
        viewHandler.openView("taskList");
    }

    /**
     * A method for the submit button
     */
    @FXML private void submitButtonPressed(){
        errorLabel.setText("");
        try
        {
            model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByID(viewState.getSelectedTask()).setTeamMembers(teamMemberList.getText());
            if(teamMemberList.getText()!=null && !teamMemberList.getText().equals("")) {
                model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByID(viewState.getSelectedTask()).setStatus(Status.STARTED);
            }viewState.setSelectedTask("");
            viewHandler.openView("taskList");
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
