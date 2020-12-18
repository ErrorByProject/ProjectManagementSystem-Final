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
    private ViewState viewState;
    public OpenRequirementController(){
        //
    }

    public void init(ViewHandler viewHandler, ColourITModel model, Region root,ViewState viewState){
        this.viewHandler = viewHandler;
        this.model = model;
        this.root = root;
        this.viewState=viewState;
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
            this.projectID.setText(model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).getProjectID());
            this.name.setText(model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).getName());
            this.requirementID.setText(model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).getRequirementID());
            this.who.setText(model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).getDescription().getWho());
            this.what.setText(model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).getDescription().getWhat());
            this.how.setText(model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).getDescription().getHow());
            this.estimatedHours.setText(model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).getEstimatedHours()+"");
            this.Day.setText(model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).getDeadline().getDay()+"");
            this.Month.setText(model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).getDeadline().getMonth()+"");
            this.Year.setText(model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).getDeadline().getYear()+"");
            this.orderNum.setText(model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).getOrderNum()+"");





        }catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }

    public Region getRoot(){return root;}

    @FXML private void UptadeBpressed(){
        errorLabel.setText("");

        try {

            UserStory Story = new UserStory(what.getText(), how.getText(), who.getText());
            Date date = new Date(Integer.parseInt(Day.getText()), Integer.parseInt(Month.getText()), Integer.parseInt(Year.getText()));
            model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).setDescription(Story);
            model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).setDeadline(date);
            model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).setName(name.getText());
            model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).setEstimatedHours(Double.parseDouble(estimatedHours.getText()));
            model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).setOrderNum(Integer.parseInt(orderNum.getText()));
            viewState.setSelectedRequirement("");
            viewHandler.openView("RequirementList");



        }catch (IllegalArgumentException e){
            errorLabel.setText(e.getMessage());

        } catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML private void backButtonPressed(){
        viewState.setSelectedRequirement("");
        viewHandler.openView("RequirementList");}


}
