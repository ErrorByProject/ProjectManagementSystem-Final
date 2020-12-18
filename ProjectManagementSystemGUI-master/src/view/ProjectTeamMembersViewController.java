package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.ColourITModel;
import model.Project;

/**
 * @author group 3
 * @version 1.0
 */
public class ProjectTeamMembersViewController {
    @FXML private TextField ProjectID;
    @FXML private Label errorLabel;
    @FXML private TextArea teamMemberList;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private Region root;
    private ViewState viewState;
    /**
     * A 0 argument constructor for the window
     */
    public ProjectTeamMembersViewController(){

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
        this.ProjectID.setText("");
        this.errorLabel.setText("");
        try{
            this.ProjectID.setText(model.getProjectByID(viewState.getSelectedProject()).getProjectID());
            this.ProjectID.setEditable(false);
            this.teamMemberList.setText(model.getProjectByID(viewState.getSelectedProject()).getTeamMembers());

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
        viewState.setSelectedProject("");
        viewHandler.openView("projectlist");
    }

    /**
     * A method for the button adding the team members
     *
     */
    @FXML private void submitButtonPressed() {
        errorLabel.setText("");
        try {
            model.getProjectByID(viewState.getSelectedProject()).setTeamMembers(teamMemberList.getText());
            viewHandler.openView("projectlist");
        } catch (NumberFormatException e) {
            errorLabel.setText("Illegal " + e.getMessage());
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }








}
