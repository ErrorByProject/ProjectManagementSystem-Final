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
    private Project project;
    public RequirementListMenuControllerr(){

    }

    public void init(ViewHandler viewHandler, ColourITModel colourITModel, Region root, Project project){
        this.colourITModel = colourITModel;
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewModel = new RequirementListViewModel(colourITModel);
        this.project=project;
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

    public void reset(){
        errorLabel.setText("");
        viewModel.update();
    }

    public Region getRoot() {
        return root;
    }

    @FXML private void addRequirementBPressed(){viewHandler.openView("addRequirement",project);}

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
                colourITModel.removeRequirement(R.getRequirementID());
                viewModel.remove(R);
                RequirementListTable.getSelectionModel().clearSelection();
            }
        }
        catch (Exception e)
        {
            errorLabel.setText("Item not found: " + e.getMessage());
        }

    }

    @FXML private void openRequirementBPressed(){
        errorLabel.setText("");
        try {
            RequirementViewModel selectedItem = RequirementListTable.getSelectionModel()
                    .getSelectedItem();
            if (selectedItem == null)
            {
                throw new IllegalArgumentException("No item selected");
            }

            Requirement R = new Requirement(selectedItem.getProjectID().get(), selectedItem.getRequirementID().get(), selectedItem.getName().get(),
                    selectedItem.getUserStoryObjectProperty().get(), selectedItem.getEstimatedHours().get(), selectedItem.getDeadlineObjectProperty().get(),
                    selectedItem.getOrderNum().get());


            viewHandler.openView("Open",R, project);
            RequirementListTable.getSelectionModel().clearSelection();

        } catch (Exception e){
            errorLabel.setText("Item not found: "+ e.getMessage());
        }
    }

    @FXML private void ManageTeamMemberBPressed(){
        errorLabel.setText("");
        try {
            RequirementViewModel selectedItem = RequirementListTable.getSelectionModel()
                    .getSelectedItem();
            if (selectedItem == null)
            {
                throw new IllegalArgumentException("No item selected");
            }

            Requirement R = new Requirement(selectedItem.getProjectID().get(), selectedItem.getRequirementID().get(), selectedItem.getName().get(),
                    selectedItem.getUserStoryObjectProperty().get(), selectedItem.getEstimatedHours().get(), selectedItem.getDeadlineObjectProperty().get(),
                    selectedItem.getOrderNum().get());

            R.setTeamMembers(selectedItem.getTeamMembers().get() );
            viewHandler.openView("manageTeamMembers",R, project);
            RequirementListTable.getSelectionModel().clearSelection();

        } catch (Exception e){
            errorLabel.setText("Item not found: "+ e.getMessage());
        }

    }

    @FXML private void backBPressed(){
        colourITModel.removeProject(project.getProjectID());
        colourITModel.addProject(project);
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

               Requirement R = new Requirement(selectedItem.getProjectID().get(), selectedItem.getRequirementID().get(), selectedItem.getName().get(),
                       selectedItem.getUserStoryObjectProperty().get(), selectedItem.getEstimatedHours().get(), selectedItem.getDeadlineObjectProperty().get(),
                       selectedItem.getOrderNum().get());


               viewHandler.openView("taskList",R, project);
               RequirementListTable.getSelectionModel().clearSelection();

           } catch (Exception e){
               errorLabel.setText("Item not found: "+ e.getMessage());
           }
       }

    @FXML private void saveBPressed(){
        PrintWriter out = null;
        try {
            String pathname = "Requirement" + project.getProjectID() +".xml"; // instead of 1 should be Project.getId or something to identify the project
            File file = new File(pathname);
            out = new PrintWriter(file);
            String xml = "";
            xml += "<?xml version=\"1.0\" encoding=\"UTF-8\"" + "standalone=\"no\"?>\n";
            xml += "<RequirementList>";
            xml +="\n<NumberOfRequirements>" + colourITModel.getRequirementListSize() + "</NumberOfRequirements>";

            for(int i = 0; i < colourITModel.getRequirementListSize(); i++){


                xml += "\n <Requirement>";
                xml += "\n  <ProjectID> " + colourITModel.getRequirementByIndex(i).getProjectID() + " </ProjectID>";
                xml += "\n  <Name> " + colourITModel.getRequirementByIndex(i).getName() + " </Name>";
                xml += "\n  <RequirementID> " + colourITModel.getRequirementByIndex(i).getRequirementID() + " </RequirementID>";
                xml += "\n  <Description-who> " + colourITModel.getRequirementByIndex(i).getDescription().getWho() + " </Description-who>";
                xml += "\n  <Description-what> " + colourITModel.getRequirementByIndex(i).getDescription().getWho() + " </Description-what>";
                xml += "\n  <Description-how> " + colourITModel.getRequirementByIndex(i).getDescription().getHow() + " </Description-how>";
                xml += "\n  <EstimatedHours> " + colourITModel.getRequirementByIndex(i).getEstimatedHours() + " </EstimatedHours>";
                xml += "\n  <Deadline-Day> " + colourITModel.getRequirementByIndex(i).getDeadline().getDay() + " </Deadline-Day>";
                xml += "\n  <Deadline-Month> " + colourITModel.getRequirementByIndex(i).getDeadline().getMonth() + " </Deadline-Month>";
                xml += "\n  <Deadline-Year> " + colourITModel.getRequirementByIndex(i).getDeadline().getYear() + " </Deadline-Year>";
                xml += "\n  <OrderNum> " +colourITModel.getRequirementByIndex(i).getOrderNum()+ " </OrderNum>";
                xml += "\n <TeamMembers>" + colourITModel.getProjectByIndex(i).getTeamMembers() + " </TeamMembers>";
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

    public void LoadFromMemory(){

        try {
            String pathname = "Requirement" + project.getProjectID() +".xml"; // instead of 1 should be Project.getId or something to identify the project
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
                        colourITModel.addRequirement(R);

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
