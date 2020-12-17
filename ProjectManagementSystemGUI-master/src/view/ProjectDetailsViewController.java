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
    private Project project;

    public ProjectDetailsViewController()
    {
        // Called by FXMLLoader
    }

    public void init(ViewHandler viewHandler, ColourITModel model, Region root, Project project)
    {
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;
        this.project = project;
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
            this.nameTextField.setText(project.getName());
            this.projectIDTextField.setText(project.getProjectID());
            this.descriptionTextField.setText(project.getDescription());
            this.estimatedHoursTextField.setText(project.getEstimatedHours()+"");
            this.day.setText(project.getDeadline().getDay()+"");
            this.month.setText(project.getDeadline().getMonth()+"");
            this.year.setText(project.getDeadline().getYear()+"");
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
            model.removeProject(project.getProjectID());

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


    @FXML private void backButtonPressed()
    {
        viewHandler.openView("projectlist");
    }


}