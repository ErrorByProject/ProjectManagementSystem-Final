package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;

/**
 * @author group 3
 * @version 1.0
 */
public class AddTeamMemberViewController
{
    @FXML private TextField nameTextField;
    @FXML private TextField idTextField;
    @FXML private TextField xpTextField;

    @FXML private TextField dayTextField;
    @FXML private TextField monthTextField;
    @FXML private TextField yearTextField;

    @FXML private TextField userTextField;
    @FXML private TextField hostTextField;
    @FXML private TextField domainTextField;
    @FXML private Label errorLabel;

    private Region root;
    private ColourITModel model;
    private ViewHandler viewHandler;

    /**
     * A 0 argument constructor for the window
     */
    public AddTeamMemberViewController()
    {
        // Called by FXMLLoader
    }


    /**
     * A initializer method initializing the components
     * @param viewHandler the view handeler
     * @param model the model
     * @param root the root
     */
    public void init(ViewHandler viewHandler, ColourITModel model, Region root)
    {
        this.model = model;
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
        this.nameTextField.setText("");
        this.idTextField.setText("");
        this.xpTextField.setText("");

        this.dayTextField.setText("");
        this.monthTextField.setText("");
        this.yearTextField.setText("");

        this.userTextField.setText("");
        this.hostTextField.setText("");
        this.domainTextField.setText("");

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
     * A method for the button adding the team member
     *
     */
    @FXML private void addTeamMemberSubmitButton()
    {
        errorLabel.setText("");
        try
        {
            Date dob = new Date(Integer.parseInt(dayTextField.getText()),
                    Integer.parseInt(monthTextField.getText()),
                    Integer.parseInt(yearTextField.getText()));

            Email contact = new Email(userTextField.getText(),
                    hostTextField.getText(),
                    domainTextField.getText());

            TeamMember newbie = new TeamMember(nameTextField.getText(),
                    idTextField.getText(),
                    contact,
                    Integer.parseInt(xpTextField.getText()),
                    dob,
                    Role.TEAM_MEMBER);

            model.addNewTeamMember(newbie);
            viewHandler.openView("teamList");
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
    @FXML private void addTeamMemberCancelButton()
    {
        viewHandler.openView("teamList");
    }

}

