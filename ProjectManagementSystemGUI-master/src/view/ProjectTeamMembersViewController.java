package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.ColourITModel;
import model.Project;


public class ProjectTeamMembersViewController {
    @FXML private TextField ProjectID;
    @FXML private Label errorLabel;
    @FXML private TextArea teamMemberList;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private Region root;
    private ViewState viewState;
    public ProjectTeamMembersViewController(){

    }
    public void init(ViewHandler viewHandler, ColourITModel model, Region root, ViewState viewState){
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewState=viewState;
        reset();
    }
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
    public Region getRoot(){
        return root;
    }

    @FXML private void backButtonPressed(){
        viewState.setSelectedProject("");
        viewHandler.openView("projectlist");
    }

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
