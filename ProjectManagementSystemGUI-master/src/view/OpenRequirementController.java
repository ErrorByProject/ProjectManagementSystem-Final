package view;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.scene.layout.Region;
import java.awt.*;

public class OpenRequirementController {
    @FXML private TextField projectID;
    @FXML private TextField requirementID;
    @FXML private TextField name;
    @FXML private TextField estimatedHours;

    @FXML private TextField Day;
    @FXML private TextField Month;
    @FXML private TextField Year;

    @FXML private TextField orderNum;

    @FXML private TextField what;
    @FXML private TextField who;
    @FXML private TextField how;

    @FXML private Label errorLabel;

    private Region root;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private Requirement requirement;
    private Project project;
    public OpenRequirementController(){
        //
    }

    public void init(ViewHandler viewHandler, ColourITModel model, Region root,Requirement requirement, Project project){
        this.viewHandler = viewHandler;
        this.model = model;
        this.root = root;
        this.requirement=requirement;
        this.project= project;
        load();
    }

    public void load(){
        this.errorLabel.setText("");
        this.projectID.setText("");
        this.requirementID.setText("");
        this.name.setText("");
        this.who.setText("");
        this.how.setText("");
        this.what.setText("");
        this.estimatedHours.setText("");
        this.Day.setText("");
        this.Month.setText("");
        this.Year.setText("");
        this.orderNum.setText("");

        try{

            this.projectID.setEditable(false);
            this.requirementID.setEditable(false);
            this.projectID.setText(requirement.getProjectID());
            this.name.setText(requirement.getName());
            this.requirementID.setText(requirement.getRequirementID());
            this.who.setText(requirement.getDescription().getWho());
            this.what.setText(requirement.getDescription().getWhat());
            this.how.setText(requirement.getDescription().getHow());
            this.estimatedHours.setText(requirement.getEstimatedHours()+"");
            this.Day.setText(requirement.getDeadline().getDay()+"");
            this.Month.setText(requirement.getDeadline().getMonth()+"");
            this.Year.setText(requirement.getDeadline().getYear()+"");
            this.orderNum.setText(requirement.getOrderNum()+"");





        }catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }

    public Region getRoot(){return root;}

    @FXML private void UptadeBpressed(){
        errorLabel.setText("");

        try {

            model.removeRequirement(requirementID.getText());
            project.getRequirementsByImportance().removeRequirement(requirementID.getText());
            UserStory Story = new UserStory(what.getText(), how.getText(), who.getText());
            Date date = new Date(Integer.parseInt(Day.getText()), Integer.parseInt(Month.getText()), Integer.parseInt(Year.getText()));
            Requirement updatedRequirement = new Requirement(projectID.getText(),requirementID.getText(),name.getText(),Story,Double.parseDouble(estimatedHours.getText()),date,Integer.parseInt(orderNum.getText()));
            model.addRequirement(updatedRequirement);
            project.addRequirement(requirement);
            viewHandler.openView("RequirementList",project);



        }catch (IllegalArgumentException e){
            errorLabel.setText(e.getMessage());

        } catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML private void backButtonPressed(){viewHandler.openView("RequirementList",project);}


}
