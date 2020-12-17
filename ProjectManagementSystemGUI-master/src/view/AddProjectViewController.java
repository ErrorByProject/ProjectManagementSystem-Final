package view;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;

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

    public AddProjectViewController()
    {
        // Called by FXMLLoader
    }

    public void init(ViewHandler viewHandler, ColourITModel model, Region root)
    {
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;
    }

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

    public Region getRoot()
    {
        return root;
    }

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
    @FXML private void addProjectCancelButton()
    {
        viewHandler.openView("projectlist");
    }

}
