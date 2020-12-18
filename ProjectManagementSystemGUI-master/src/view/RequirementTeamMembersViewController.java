package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;


public class RequirementTeamMembersViewController {

    @FXML private TextField RequirementID;
    @FXML private Label errorLabel;
    @FXML private TextArea teamMemberList;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private Region root;
    private ViewState viewState;
    public RequirementTeamMembersViewController(){

    }

    public void init(ViewHandler viewHandler, ColourITModel model, Region root, ViewState viewState){
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewState=viewState;
        reset();
    }
    public void reset(){
        this.RequirementID.setText("");
        this.errorLabel.setText("");
        try{
            this.RequirementID.setText(model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).getRequirementID());
            this.RequirementID.setEditable(false);
            this.teamMemberList.setText(model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).getTeamMembers());

        }catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }
    public Region getRoot(){
        return root;
    }
    @FXML private void backButtonPressed(){
        viewState.setSelectedRequirement("");
        viewHandler.openView("RequirementList");
    }
    @FXML private void submitButtonPressed(){
        errorLabel.setText("");
        try
        {
            model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).setTeamMembers(teamMemberList.getText());
            viewState.setSelectedRequirement("");
            viewHandler.openView("RequirementList");
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
