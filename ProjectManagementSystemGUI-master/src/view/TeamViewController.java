package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import model.*;

import java.util.ArrayList;
import java.util.Optional;
/**
 * @author group 3
 * @version 1.0
 */

public class TeamViewController
{
    @FXML private TableView<TeamMemberViewModel> teamTable;
    @FXML private TableColumn<TeamMemberViewModel, String> nameColumn;
    @FXML private TableColumn<TeamMemberViewModel, String> idColumn;

    @FXML private Label errorLabel;

    private Region root;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private TeamViewModel viewModel;

    /**
     * A 0 argument constructor for the window
     */
    public TeamViewController()
    {
        // Called by FXMLLoader
    }

    /**
     * A initializer initializing the components
     * @param viewHandler the view handler
     * @param model the model
     * @param root the root
     *
     */
    public void init(ViewHandler viewHandler, ColourITModel model, Region root)
    {
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewModel = new TeamViewModel(model);
        this.errorLabel.setText("");
        nameColumn
                .setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        idColumn
                .setCellValueFactory(cellData -> cellData.getValue().getIdProperty());


        teamTable.setItems(viewModel.getList());
    }

    /**
     * A reset method resetting the tex fields
     */
    public void reset()
    {
        errorLabel.setText("");

        viewModel.update();
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
     * A method for add team members button
     */
    @FXML private void addTeamMemberButtonPressed()
    {
        viewHandler.openView("addTeamMember");
    }

    /**
     * A method for remove team members button
     */
    @FXML private void removeTeamMemberButtonPressed()
    {
        errorLabel.setText("");
        try
        {
            TeamMemberViewModel selectedItem = teamTable.getSelectionModel()
                    .getSelectedItem();

            boolean remove = confirmation();
            if (remove)
            {
                model.removeTeamMemberByID(selectedItem.getIdProperty().get());
                teamTable.getSelectionModel().clearSelection();
                viewModel.remove((selectedItem.getIdProperty().get()));
            }
        }
        catch (Exception e)
        {
            errorLabel.setText("Item not found: " + e.getMessage());
        }
    }



    /**
     * A method for show team members details button
     */
    @FXML private void showTeamMemberDetailsButtonPressed()
    {
        errorLabel.setText("");
        try
        {
            details();
        }
        catch (Exception e)
        {
            errorLabel.setText("Item not found: " + e.getMessage());
        }
    }

    /**
     * A method showing team members
     * @return the result
     */
    private boolean details()
    {
        int index = teamTable.getSelectionModel().getSelectedIndex();
        TeamMemberViewModel selectedItem = teamTable.getItems().get(index);
        if (index < 0 || index >= teamTable.getItems().size())
        {
            return false;
        }
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Details for "+ selectedItem.getNameProperty().get());
        alert.setHeaderText(
                "\nName: " + model.getTeamMember(index).getName() +
                        "\nID: " + model.getTeamMember(index).getTeamMemberID()+
                        "\nYears of Experience: " + model.getTeamMember(index).getYearsOfExperience() +
                        "\nRole: " + model.getTeamMember(index).getRole() +
                        "\nDate of Birth: " + model.getTeamMember(index).getBirthdate() +
                        "\nEmail: " +model.getTeamMember(index).getEmail() );
        Optional<ButtonType> result = alert.showAndWait();
        return ((result.isPresent()) && (result.get() == ButtonType.OK));
    }


    /**
     * A confirmation window
     * @return confirmation
     */
    private boolean confirmation()
    {
        int index = teamTable.getSelectionModel().getSelectedIndex();
        TeamMemberViewModel selectedItem = teamTable.getItems().get(index);
        if (index < 0 || index >= teamTable.getItems().size())
        {
            return false;
        }
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(
                "Removing {" + selectedItem.getIdProperty().get() + ": " + selectedItem
                        .getNameProperty().get() + "}");
        Optional<ButtonType> result = alert.showAndWait();
        return ((result.isPresent()) && (result.get() == ButtonType.OK));
    }
    @FXML private void backButtonPressed(){
        viewHandler.openView("projectlist");
    }
}

