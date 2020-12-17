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
    private Requirement requirement;
    private Project project;
    public RequirementTeamMembersViewController(){

    }

    public void init(ViewHandler viewHandler, ColourITModel model, Region root, Requirement requirement, Project project){
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;
        this.requirement=requirement;
        this.project = project;
        reset();
    }
    public void reset(){
        this.RequirementID.setText("");
        this.errorLabel.setText("");
        try{
            this.RequirementID.setText(requirement.getRequirementID());
            this.RequirementID.setEditable(false);
            this.teamMemberList.setText(requirement.getTeamMembers());
            System.out.println(requirement.getTeamMembers());

        }catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }
    public Region getRoot(){
        return root;
    }
    @FXML private void backButtonPressed(){
        viewHandler.openView("RequirementList",project);
    }
    @FXML private void submitButtonPressed(){
        errorLabel.setText("");
        try
        {
            model.removeRequirement(requirement.getRequirementID());
            project.getRequirementsByImportance().removeRequirement(requirement.getRequirementID());
            requirement.setTeamMembers(teamMemberList.getText());
            model.addRequirement(requirement);
            project.addRequirement(requirement);
            viewHandler.openView("RequirementList",project);
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
