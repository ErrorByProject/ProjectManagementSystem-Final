package view;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.scene.layout.Region;
import java.awt.*;

public class AddRequirementController {
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
    private Project project;
    public AddRequirementController(){
        //
    }

    public void init(ViewHandler viewHandler, ColourITModel model, Region root,Project project){
        this.viewHandler = viewHandler;
        this.model = model;
        this.root = root;
        this.project=project;
        reset();
    }

    public void reset(){
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
    }

    public Region getRoot(){return root;}

    @FXML private void AddBpressed(){
        errorLabel.setText("");
        try {
            UserStory Story = new UserStory(what.getText(), how.getText(), who.getText());
            Date date = new Date(Integer.parseInt(Day.getText()), Integer.parseInt(Month.getText()), Integer.parseInt(Year.getText()));
            Requirement R = new Requirement(projectID.getText(), requirementID.getText(), name.getText(), Story,
                    Integer.parseInt(estimatedHours.getText()), date, Integer.parseInt(orderNum.getText()));
            model.addRequirement(R);
            project.addRequirement(R);
            viewHandler.openView("RequirementList",project);


        }catch (IllegalArgumentException e){
            errorLabel.setText(e.getMessage());

        } catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML private void backButtonPressed(){viewHandler.openView("RequirementList",project);}












}
