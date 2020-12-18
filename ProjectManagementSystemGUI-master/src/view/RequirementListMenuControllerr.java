package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author group 3
 * @version 1.0
 */

public class RequirementListMenuControllerr {
    @FXML private TableView<RequirementViewModel> RequirementListTable;
    @FXML private TableColumn<RequirementViewModel, Number> orderNumCollum;
    @FXML private TableColumn<RequirementViewModel, String> nameCollum;
    @FXML private TableColumn<RequirementViewModel, String> requirementIDCollum;
    @FXML private TableColumn<RequirementViewModel, String> projectIDCollum;
    @FXML private TableColumn<RequirementViewModel, UserStory> descriptionCollum;
    @FXML private TableColumn<RequirementViewModel, Number> estimatedHoursCollum;
    @FXML private TableColumn<RequirementViewModel, Date> deadlineCollum;
    @FXML private TableColumn<RequirementViewModel, Status> statusTableColumn;
    @FXML private TableColumn<RequirementViewModel, String> TeamMemberCollum;
    @FXML private Label errorLabel;

    private Region root;
    private ViewHandler viewHandler;
    private RequirementListViewModel viewModel;
    private ColourITModel colourITModel;
    private ViewState viewState;

    /**
     * A 0 argument constructor for the window
     */
    public RequirementListMenuControllerr(){

    }

    /**
     * A initializer initializing the components
     * @param viewHandler the view handler
     * @param model the model
     * @param root the root
     * @param viewState the view state
     */
    public void init(ViewHandler viewHandler, ColourITModel colourITModel, Region root, ViewState viewState){
        this.colourITModel = colourITModel;
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewModel = new RequirementListViewModel(colourITModel,viewState);
        this.viewState=viewState;
        orderNumCollum.setCellValueFactory(cellData -> cellData.getValue().getOrderNum());
        nameCollum.setCellValueFactory(cellData -> cellData.getValue().getName());
        requirementIDCollum.setCellValueFactory(cellData -> cellData.getValue().getRequirementID());
        projectIDCollum.setCellValueFactory(cellData -> cellData.getValue().getProjectID());
        descriptionCollum.setCellValueFactory(cellData -> cellData.getValue().getUserStoryObjectProperty());
        estimatedHoursCollum.setCellValueFactory(cellData -> cellData.getValue().getEstimatedHours());
        deadlineCollum.setCellValueFactory(cellData -> cellData.getValue().getDeadlineObjectProperty());
        statusTableColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus());
        TeamMemberCollum.setCellValueFactory(cellData -> cellData.getValue().getTeamMembers());
        RequirementListTable.setItems(viewModel.getList());
        LoadFromMemory();
        viewModel.update();
    }

    /**
     * A reset method resetting the tex fields
     */
    public void reset(){
        errorLabel.setText("");
        viewModel.update();
    }

    /**
     * A method getting the root
     * @return the root
     */
    public Region getRoot() {
        return root;
    }


    /**
     * A method for add requirement button
     */
    @FXML private void addRequirementBPressed(){viewHandler.openView("addRequirement");}

    /**
     * A method for the remove requirement button
     */
    @FXML private void removeRequirementBPressed(){
        errorLabel.setText("");
        try
        {
            RequirementViewModel selectedItem = RequirementListTable.getSelectionModel()
                    .getSelectedItem();

            boolean remove = confirmation();
            if (remove)
            {
                Requirement R = new Requirement(selectedItem.getProjectID().get(), selectedItem.getRequirementID().get(), selectedItem.getName().get(),
                        selectedItem.getUserStoryObjectProperty().get(), selectedItem.getEstimatedHours().get(), selectedItem.getDeadlineObjectProperty().get(),
                        selectedItem.getOrderNum().get());
                colourITModel.removeRequirement(R.getRequirementID(),viewState.getSelectedProject());
                viewModel.remove(R);
                RequirementListTable.getSelectionModel().clearSelection();
            }
        }
        catch (Exception e)
        {
            errorLabel.setText("Item not found: " + e.getMessage());
        }

    }

    /**
     * A method for open requirement button
     */
    @FXML private void openRequirementBPressed(){
        errorLabel.setText("");
        try {
            RequirementViewModel selectedItem = RequirementListTable.getSelectionModel()
                    .getSelectedItem();
            if (selectedItem == null)
            {
                throw new IllegalArgumentException("No item selected");
            }

            viewState.setSelectedRequirement(selectedItem.getRequirementID().get());
            viewHandler.openView("Open");
            RequirementListTable.getSelectionModel().clearSelection();

        } catch (Exception e){
            errorLabel.setText("Item not found: "+ e.getMessage());
        }
    }

    /**
     * A method for the manage team members button
     */
    @FXML private void ManageTeamMemberBPressed(){
        errorLabel.setText("");
        try {
            RequirementViewModel selectedItem = RequirementListTable.getSelectionModel()
                    .getSelectedItem();
            if (selectedItem == null)
            {
                throw new IllegalArgumentException("No item selected");
            }

            viewState.setSelectedRequirement(selectedItem.getRequirementID().get());
            viewHandler.openView("requirementManageTeamMembers");
            RequirementListTable.getSelectionModel().clearSelection();

        } catch (Exception e){
            errorLabel.setText("Item not found: "+ e.getMessage());
        }

    }

    /**
     * A method for the back button
     */
    @FXML private void backBPressed(){
        viewState.setSelectedProject("");
        viewHandler.openView("projectlist");
        }
       @FXML private void openTaskListButtonPressed(){
           errorLabel.setText("");
           try {
               RequirementViewModel selectedItem = RequirementListTable.getSelectionModel()
                       .getSelectedItem();
               if (selectedItem == null)
               {
                   throw new IllegalArgumentException("No item selected");
               }
               viewState.setSelectedRequirement(selectedItem.getRequirementID().get());
               viewHandler.openView("taskList");
               RequirementListTable.getSelectionModel().clearSelection();

           } catch (Exception e){
               errorLabel.setText("Item not found: "+ e.getMessage());
           }
       }

    /**
     * A method for the save button pressed
     */
    @FXML private void saveBPressed(){
        PrintWriter out = null;
        try {
            String pathname = "Requirement" + viewState.getSelectedProject() +".xml"; // instead of 1 should be Project.getId or something to identify the project
            File file = new File(pathname);
            out = new PrintWriter(file);
            String xml = "";
            xml += "<?xml version=\"1.0\" encoding=\"UTF-8\"" + "standalone=\"no\"?>\n";
            xml += "<RequirementList>";
            xml +="\n<NumberOfRequirements>" + colourITModel.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementListSize()+ "</NumberOfRequirements>";

            for(int i = 0; i < colourITModel.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementListSize(); i++){


                xml += "\n <Requirement>";
                xml += "\n  <ProjectID> " + colourITModel.getProjectByID(viewState.getSelectedProject()).getProjectID() + " </ProjectID>";
                xml += "\n  <Name> " + colourITModel.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByIndex(i).getName() + " </Name>";
                xml += "\n  <RequirementID> " + colourITModel.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByIndex(i).getRequirementID() + " </RequirementID>";
                xml += "\n  <Description-who> " + colourITModel.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByIndex(i).getDescription().getWho() + " </Description-who>";
                xml += "\n  <Description-what> " + colourITModel.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByIndex(i).getDescription().getWho() + " </Description-what>";
                xml += "\n  <Description-how> " + colourITModel.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByIndex(i).getDescription().getHow() + " </Description-how>";
                xml += "\n  <EstimatedHours> " + colourITModel.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByIndex(i).getEstimatedHours() + " </EstimatedHours>";
                xml += "\n  <Deadline-Day> " + colourITModel.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByIndex(i).getDeadline().getDay() + " </Deadline-Day>";
                xml += "\n  <Deadline-Month> " + colourITModel.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByIndex(i).getDeadline().getMonth() + " </Deadline-Month>";
                xml += "\n  <Deadline-Year> " + colourITModel.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByIndex(i).getDeadline().getYear() + " </Deadline-Year>";
                xml += "\n  <OrderNum> " +colourITModel.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByIndex(i).getOrderNum()+ " </OrderNum>";
                xml += "\n <TeamMembers>" + colourITModel.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByIndex(i).getTeamMembers() + " </TeamMembers>";
                xml += "\n </Requirement>";

            }
            xml += "\n</RequirementList>";
            out.println(xml);
            System.out.println(file.getAbsolutePath());


        } catch (FileNotFoundException e){
            e.printStackTrace();
        } finally {
            out.close();
        }


    }

    /**
     * A method that loads from the xml file the data
     */
    public void LoadFromMemory(){

        try {
            String pathname = "Requirement" + viewState.getSelectedProject() +".xml"; // instead of 1 should be Project.getId or something to identify the project
            File file = new File(pathname);

            Scanner in = new Scanner(file);


            String ProjectID = null;
            String name = null;
            String RequirementID = null;
            String who = null;
            String what = null;
            String how = null;
            Double EstimatedHours = 0.0;
            int day = 0;
            int month  = 0;
            int year = 0;
            int orderNum = 0;
            String TeamMembers="";

            int count = 0;


            while (in.hasNext()){
                String line2 = in.nextLine();
                if(line2.contains("<NumberOfRequirements>")){
                    line2 = line2.replace("<NumberOfRequirements>","");
                    line2 = line2.replace("</NumberOfRequirements>","");
                    count = Integer.parseInt(line2.trim());
                    break;

                }
            }

            for (int i = 0; i < count; i++){

                while(in.hasNext()){
                    String line = in.nextLine();


                    if(line.contains("<ProjectID>")){
                        line = line.replace("<ProjectID>","");
                        line = line.replace("</ProjectID>", "");
                        ProjectID = line.trim();
                    } else if(line.contains("<Name>")){
                        line = line.replace("<Name>","");
                        line = line.replace("</Name>", "");
                        name = line.trim();
                    } else if(line.contains("<RequirementID>")){
                        line = line.replace("<RequirementID>","");
                        line = line.replace("</RequirementID>","");
                        RequirementID = line.trim();
                    } else if(line.contains("<Description-who>")){
                        line = line.replace("<Description-who>","");
                        line = line.replace("</Description-who>","");
                        who = line.trim();
                    } else if(line.contains("<Description-what>")){
                        line = line.replace("<Description-what>","");
                        line = line.replace("</Description-what>","");
                        what = line.trim();
                    } else if(line.contains("<Description-how>")){
                        line = line.replace("<Description-how>","");
                        line = line.replace("</Description-how>","");
                        how = line.trim();
                    } else if(line.contains("<EstimatedHours>")){
                        line = line.replace("<EstimatedHours>","");
                        line = line.replace("</EstimatedHours>","");
                        EstimatedHours = Double.parseDouble(line.trim());
                    } else if(line.contains("<Deadline-Day>")){
                        line = line.replace("<Deadline-Day>","");
                        line = line.replace("</Deadline-Day>","");
                        day = Integer.parseInt(line.trim());
                    } else if(line.contains("<Deadline-Month>")){
                        line = line.replace("<Deadline-Month>","");
                        line = line.replace("</Deadline-Month>","");
                        month = Integer.parseInt(line.trim());
                    } else if(line.contains("<Deadline-Year>")){
                        line = line.replace("<Deadline-Year>","");
                        line = line.replace("</Deadline-Year>","");
                        year = Integer.parseInt(line.trim());
                    } else if(line.contains("<OrderNum>")){
                        line = line.replace("<OrderNum>","");
                        line = line.replace("</OrderNum>","");
                        orderNum = Integer.parseInt(line.trim());
                    } else if(line.contains("<TeamMembers>")){
                        line = line.replace("<TeamMembers>","");
                        line = line.replace("</TeamMembers>","");
                        TeamMembers = line.trim();
                    }


                    if(ProjectID != null && !(ProjectID.equals("")) && RequirementID != null && !(RequirementID.equals("")) &&
                            RequirementID.length()==4  && name != null && !(name.equals("")) && who !=null && what != null && how != null && EstimatedHours >= 0 &&
                            day != 0 && month != 0 && year != 0 && orderNum >=0){
                        Date D = new Date(day,month,year);
                        UserStory U = new UserStory( who, what, how);
                        Requirement R = new Requirement(ProjectID, RequirementID, name, U,EstimatedHours, D, orderNum);

                        R.setTeamMembers(TeamMembers);
                        colourITModel.addRequirement(R,ProjectID);
                        TeamMembers = "";
                        ProjectID = null;
                        name = null;
                        RequirementID = null;
                        who = null;
                        what = null;
                        how = null;
                        EstimatedHours = 0.0;
                        day = 0;
                        month  = 0;
                        year = 0;
                        orderNum = 0;

                    }



                }


            }





        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }


    private boolean confirmation(){
        int index = RequirementListTable.getSelectionModel().getSelectedIndex();
        RequirementViewModel selectedItem = RequirementListTable.getItems().get(index);
        if(index < 0 || index >= RequirementListTable.getItems().size()){return false;}
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Removing requirement {" + selectedItem.getRequirementID() + " " + selectedItem.getName()+ "}");
        Optional<ButtonType> result = alert.showAndWait();
        return((result.isPresent()) && (result.get() == ButtonType.OK));
    }
}
