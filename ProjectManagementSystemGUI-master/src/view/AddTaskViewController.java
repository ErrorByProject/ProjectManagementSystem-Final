package view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.*;

/**
 * @author gropup 3
 * @version 1.0
 */
public class AddTaskViewController
{
    @FXML private TextField taskIDTextField;
    @FXML private TextField requirementIDTextField;
    @FXML private TextField labelNameTextField;
    @FXML private TextField taskDescriptionTextField;
    @FXML private TextField day;
    @FXML private TextField month;
    @FXML private TextField year;
    @FXML private TextField estimatedHoursTextField;
    @FXML private Label errorLabel;

    private Region root;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private ViewState viewState;

    /**
     * A 0 argument constructor for the window
     */
    public AddTaskViewController()
    {
        // Called by FXMLLoader
    }

    /**
     * A initializer method initializing the components
     * @param viewHandler the view handler
     * @param model the model
     * @param root the root
     * @param viewState the view state
     */
    public void init(ViewHandler viewHandler, ColourITModel model, Region root,ViewState viewState)
    {
        this.model = model;
        this.viewState=viewState;
        this.viewHandler = viewHandler;
        this.root = root;
        reset();
    }

    /**
     * A reset method resetting the tex fields
     */
    public void reset()
    {
        this.errorLabel.setText("");
        this.taskIDTextField.setText("");
        this.requirementIDTextField.setText(viewState.getSelectedRequirement());
        this.requirementIDTextField.setEditable(false);
        this.estimatedHoursTextField.setText("");
        this.labelNameTextField.setText("");
        this.day.setText("");
        this.month.setText("");
        this.year.setText("");
        this.taskDescriptionTextField.setText("");
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
     * A method for the button adding the task
     *
     */
    @FXML private void addTaskSubmitButtonPressed()
    {
        errorLabel.setText("");
        try
        {

            Task task = new Task(taskIDTextField.getText(),requirementIDTextField.getText(),labelNameTextField.getText(),taskDescriptionTextField.getText(),new Date(Integer.parseInt(day.getText()),Integer.parseInt(month.getText()),Integer.parseInt(year.getText())),Double.parseDouble(estimatedHoursTextField.getText()),Status.STARTED );
            model.addTask(task,viewState.getSelectedRequirement(),viewState.getSelectedProject());
            viewHandler.openView("taskList");
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
    @FXML private void addTaskCancelButtonPressed()
    {
        viewHandler.openView("taskList");
    }


}
