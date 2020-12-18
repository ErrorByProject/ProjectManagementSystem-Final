package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;
/**
 * @author group 3
 * @version 1.0
 */
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
    /**
     * A 0 argument constructor for the window
     */
    public ProjectDetailsViewController()
    {
        // Called by FXMLLoader
    }

    /**
     * A initializer initializing the components
     * @param viewHandler the view handler
     * @param model the model
     * @param root the root
     * @param viewState the view state
     */
    public void init(ViewHandler viewHandler, ColourITModel model, Region root, ViewState viewState)
    {
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewState=viewState;
        load();
    }

    /**
     * A load method loading the tex fields
     */
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

    /**
     * A method getting the root
     * @return the root
     */
    public Region getRoot()
    {
        return root;
    }


    /**
     * A method for the button updating the details
     *
     */
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


    /**
     * A method canceling the process
     */
    @FXML private void backButtonPressed()
    {
        viewState.setSelectedProject("");
        viewHandler.openView("projectlist");
    }


}