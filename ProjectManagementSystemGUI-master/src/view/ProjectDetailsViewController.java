package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;

public class ProjectDetailsViewController
{
    @FXML private TextField nameTextField;
    @FXML private TextField projectIDTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField estimatedHoursTextField;

    @FXML private TextField day;
    @FXML private TextField month;
    @FXML private TextField year;
    @FXML private Label errorLabel;

    private Region root;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private ViewState viewState;

    public ProjectDetailsViewController()
    {
        // Called by FXMLLoader
    }

    public void init(ViewHandler viewHandler, ColourITModel model, Region root, ViewState viewState)
    {
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewState=viewState;
        load();
    }

    public void load()
    {
        this.nameTextField.setText("");
        this.projectIDTextField.setText("");
        this.descriptionTextField.setText("");
        this.estimatedHoursTextField.setText("");
        this.day.setText("");
        this.month.setText("");
        this.year.setText("");

        try {
            this.nameTextField.setText(model.getProjectByID(viewState.getSelectedProject()).getName());
            this.projectIDTextField.setText(model.getProjectByID(viewState.getSelectedProject()).getProjectID());
            this.descriptionTextField.setText(model.getProjectByID(viewState.getSelectedProject()).getDescription());
            this.estimatedHoursTextField.setText(model.getProjectByID(viewState.getSelectedProject()).getEstimatedHours()+"");
            this.day.setText(model.getProjectByID(viewState.getSelectedProject()).getDeadline().getDay()+"");
            this.month.setText(model.getProjectByID(viewState.getSelectedProject()).getDeadline().getMonth()+"");
            this.year.setText(model.getProjectByID(viewState.getSelectedProject()).getDeadline().getYear()+"");
        } catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }

    public Region getRoot()
    {
        return root;
    }


    @FXML private void UpdateProjectSubmitButton()
    {
        errorLabel.setText("");
        try
        {
            viewState.setSelectedProject("");
            viewHandler.openView("projectlist");
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


    @FXML private void backButtonPressed()
    {
        viewState.setSelectedProject("");
        viewHandler.openView("projectlist");
    }


}