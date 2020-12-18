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

public class AddProjectViewController
{
    @FXML private TextField nameTextField;
    @FXML private TextField projectIDTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField estimatedHoursTextField;
    @FXML private TextField statusTextField;
    @FXML private TextField day;
    @FXML private TextField month;
    @FXML private TextField year;
    @FXML private Label errorLabel;


    private Region root;
    private ColourITModel model;
    private ViewHandler viewHandler;


    /**
     * A 0 argument constructor for the window
     */
    public AddProjectViewController()
    {
        // Called by FXMLLoader
    }

    /**
     * A initializer method initializing the components
     * @param viewHandler the view handler
     * @param model the model
     * @param root the root
     */
    public void init(ViewHandler viewHandler, ColourITModel model, Region root)
    {
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;
    }

    /**
     * A reset method resetting the tex fields
     */
    public void reset()
    {
        this.nameTextField.setText("");
        this.projectIDTextField.setText("");
        this.descriptionTextField.setText("");
        this.estimatedHoursTextField.setText("");
        this.day.setText("");
        this.month.setText("");
        this.year.setText("");
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
     * A method for the button adding the project
     *
     */
    @FXML private void addProjectSubmitButton()
    {
        errorLabel.setText("");
        try
        {
            Project project = new Project(nameTextField.getText(),
                    projectIDTextField.getText(), descriptionTextField.getText(),
                    new Date(Integer.parseInt(day.getText()),Integer.parseInt(month.getText()),Integer.parseInt(year.getText())), Double.parseDouble(estimatedHoursTextField.getText()), Status.NOTSTARTED);
            model.addProject(project);
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
    @FXML private void addProjectCancelButton()
    {
        viewHandler.openView("projectlist");
    }

}
