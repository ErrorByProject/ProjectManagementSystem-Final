package view;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.scene.layout.Region;
import java.awt.*;

/**
 * @author group 3
 * @version 1.0
 */

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
    private ViewState viewState;

    /**
     * A 0 argument constructor for the window
     */
    public AddRequirementController(){
        //
    }

    /**
     * A initializer method initializing the components
     * @param viewHandler the view handler
     * @param model the model
     * @param root the root
     * @param viewState the view state
     */
    public void init(ViewHandler viewHandler, ColourITModel model, Region root,ViewState viewState){
        this.viewHandler = viewHandler;
        this.model = model;
        this.root = root;
        this.viewState=viewState;
        reset();
    }

    /**
     * A reset method resetting the tex fields
     */
    public void reset(){
        this.errorLabel.setText("");
        this.projectID.setText(viewState.getSelectedProject());
        this.projectID.setEditable(false);
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

    /**
     * A method getting the root
     * @return the root
     */
    public Region getRoot(){return root;}

    /**
     * A method for the button adding the requirement
     *
     */
    @FXML private void AddBpressed(){
        errorLabel.setText("");
        try {
            UserStory Story = new UserStory(what.getText(), how.getText(), who.getText());
            Date date = new Date(Integer.parseInt(Day.getText()), Integer.parseInt(Month.getText()), Integer.parseInt(Year.getText()));
            Requirement R = new Requirement(projectID.getText(), requirementID.getText(), name.getText(), Story,
                    Integer.parseInt(estimatedHours.getText()), date, Integer.parseInt(orderNum.getText()));
            model.addRequirement(R,viewState.getSelectedProject());
            viewHandler.openView("RequirementList");


        }catch (IllegalArgumentException e){
            errorLabel.setText(e.getMessage());

        } catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }

    /**
     * A method canceling the process
     */
    @FXML private void backButtonPressed(){viewHandler.openView("RequirementList");}












}
