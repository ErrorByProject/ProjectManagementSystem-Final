package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import model.ColourITModel;
import model.Date;
import model.Project;
import model.Status;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

public class ProjectListViewController
{
    @FXML private TableView<ProjectViewModel> projectListTable;
    @FXML private TableColumn<ProjectViewModel, String> nameColumn;
    @FXML private TableColumn<ProjectViewModel, String> projectIDColumn;
    @FXML private TableColumn<ProjectViewModel, String> descriptionColumn;
    @FXML private TableColumn<ProjectViewModel, Date> deadlineColumn;
    @FXML private TableColumn<ProjectViewModel, Number> estimatedHoursColumn;
    @FXML private TableColumn<ProjectViewModel, Status> statusColumn;
    @FXML private TableColumn<ProjectViewModel, Number> hoursSpentColumn;
    @FXML private TableColumn<ProjectViewModel, String> TeamMemberCollum;
    @FXML private TextField searchProjectID;
    @FXML private Label errorLabel;

    private Region root;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private ProjectListViewModel viewModel;
    private ViewState viewState;
    public ProjectListViewController()
    {
        // Called by FXMLLoader
    }

    public void init(ViewHandler viewHandler, ColourITModel model, Region root,ViewState viewState)
    {
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewState=viewState;
        this.errorLabel.setText("");
        this.viewModel = new ProjectListViewModel(model,viewState);
        nameColumn
                .setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        projectIDColumn.setCellValueFactory(
                cellData -> cellData.getValue().getProjectIDProperty());
        descriptionColumn.setCellValueFactory(
                cellData -> cellData.getValue().getDescriptionProperty());
        deadlineColumn.setCellValueFactory(
                cellData -> cellData.getValue().getDeadlineProperty());
        estimatedHoursColumn.setCellValueFactory(
                cellData -> cellData.getValue().getEstimatedHoursProperty());
        statusColumn.setCellValueFactory(
                cellData -> cellData.getValue().getStatusProperty());
        hoursSpentColumn.setCellValueFactory(
                cellData -> cellData.getValue().getHoursSpentProperty());
        TeamMemberCollum.setCellValueFactory(cellData -> cellData.getValue().getTeamMembers());

        projectListTable.setItems(viewModel.getList());
        LoadFromMemory();
        viewModel.update();
    }

    public void reset()
    {
        errorLabel.setText("");
        searchProjectID.setText("");
        viewModel.update();
    }

    public Region getRoot()
    {
        return root;
    }

    @FXML private void addProjectButtonPressed()
    {
        viewHandler.openView("addProject");
    }

    @FXML private void removeProjectButtonPressed()
    {
        errorLabel.setText("");
        try
        {
            ProjectViewModel selectedItem = projectListTable.getSelectionModel()
                    .getSelectedItem();
            if (selectedItem == null)
            {
                throw new IllegalArgumentException("No item selected");
            }
            boolean remove = confirmation();
            if (remove)
            {
                Project project = new Project(selectedItem.getNameProperty().get(),
                        selectedItem.getProjectIDProperty().get(),
                        selectedItem.getDescriptionProperty().get(),
                        selectedItem.getDeadlineProperty().get(),
                        selectedItem.getEstimatedHoursProperty().get(),
                        selectedItem.getStatusProperty().get());
                model.removeProject(selectedItem.getProjectIDProperty().get());
                viewModel.remove(project);
                projectListTable.getSelectionModel().clearSelection();
            }
        }
        catch (Exception e)
        {
            errorLabel.setText("Item not found: " + e.getMessage());
        }
    }
    @FXML private void openRequirementListButtonPressed(){
        try
        {
            ProjectViewModel selectedItem = projectListTable.getSelectionModel()
                    .getSelectedItem();
            if (selectedItem == null)
            {
                throw new IllegalArgumentException("No item selected");
            }
            boolean open = confirmationOpen();
            if (open)
            {
                viewState.setSelectedProject(selectedItem.getProjectIDProperty().get());
                viewHandler.openView("RequirementList");
                projectListTable.getSelectionModel().clearSelection();
            }
        }

        catch (IllegalArgumentException e)
        {
            errorLabel.setText(e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @FXML private void showProjectDetailsButtonPressed()
    {
        try
        {
            ProjectViewModel selectedItem = projectListTable.getSelectionModel()
                    .getSelectedItem();
            if (selectedItem == null)
            {
                throw new IllegalArgumentException("No item selected");
            }
            boolean open = confirmationOpen();
            if (open)
            {
                viewState.setSelectedProject(selectedItem.getProjectIDProperty().get());
                viewHandler.openView("ProjectDetails");
                projectListTable.getSelectionModel().clearSelection();
            }
        }

        catch (IllegalArgumentException e)
        {
            errorLabel.setText(e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    private boolean confirmation()
    {
        int index = projectListTable.getSelectionModel().getSelectedIndex();
        ProjectViewModel selectedItem = projectListTable.getItems().get(index);
        if (index < 0 || index >= projectListTable.getItems().size())
        {
            return false;
        }
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(
                "Removing project  " + selectedItem.getNameProperty().get() + ":"
                        + selectedItem.getProjectIDProperty().get() + ":" + selectedItem
                        .getDescriptionProperty().get() + ":" + selectedItem
                        .getDeadlineProperty().get() + ":" + selectedItem
                        .getEstimatedHoursProperty().get() + ":" + selectedItem
                        .getStatusProperty().get());

        Optional<ButtonType> result = alert.showAndWait();
        return ((result.isPresent()) && (result.get() == ButtonType.OK));
    }



    @FXML public void projectManageTeamMembers(){
        errorLabel.setText("");
        try {
            ProjectViewModel selectedItem = projectListTable.getSelectionModel()
                    .getSelectedItem();
            if (selectedItem == null)
            {
                throw new IllegalArgumentException("No item selected");
            }
            viewState.setSelectedProject(selectedItem.getProjectIDProperty().get());
            viewHandler.openView("projectManageTeamMembers");
            projectListTable.getSelectionModel().clearSelection();

        } catch (Exception e){
            errorLabel.setText("Item not found: "+ e.getMessage());
        }

    }


    private boolean confirmationOpen()
    {
        int index = projectListTable.getSelectionModel().getSelectedIndex();
        ProjectViewModel selectedItem = projectListTable.getItems().get(index);
        if (index < 0 || index >= projectListTable.getItems().size())
        {
            return false;
        }
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(
                "Opening project: { ProjectID: " + selectedItem.getProjectIDProperty().get()
                        + ", Project Name: " + selectedItem.getNameProperty().get()
                        + ", Project Description: " + selectedItem.getDescriptionProperty()
                        .get() + ", Deadline: " + selectedItem.getDeadlineProperty().get()
                        + ", Estimated Hours To Finish: " + selectedItem
                        .getEstimatedHoursProperty().get() + ", Project Status: " + selectedItem
                        .getStatusProperty().get() + "}");
        Optional<ButtonType> result = alert.showAndWait();
        return ((result.isPresent()) && (result.get() == ButtonType.OK));
    }

    @FXML private void backButtonPressed()
    {
        viewHandler.openView("projects");
    }

    @FXML private void openTeamListButtonPressed(){
        viewHandler.openView("teamList");
    }
    @FXML private void searchButtonPressed(){
        int counter = 0;
        for(int i=0;i<model.getAllProjects().length;i++){
            if(model.getProjectByIndex(i).getProjectID().equals(searchProjectID.getText()) && searchProjectID!=null){
                viewState.setSelectedProject(searchProjectID.getText());
                viewHandler.openView("RequirementList");
                break;
            }
            counter++;
        }
        if(counter==model.getAllProjects().length){
            errorLabel.setText("No Project Found");
        }

    }
    @FXML private void saveBPressed(){
        PrintWriter out = null;
        try {
            String pathname = "ProjectList.xml";
            File file = new File(pathname);
            out = new PrintWriter(file);
            String xml = "";
            xml += "<?xml version=\"1.0\" encoding=\"UTF-8\"" + "standalone=\"no\"?>\n";
            xml += "<ProjectList>";
            xml +="\n<NumberOfProjects>" + model.getAllProjects().length+ "</NumberOfProjects>";


            for(int i = 0; i < model.getAllProjects().length; i++){


                xml += "\n <Project>";
                xml += "\n  <ProjectID> " + model.getProjectByIndex(i).getProjectID() + " </ProjectID>";
                xml += "\n  <Name> " + model.getProjectByIndex(i).getName() + " </Name>";
                xml += "\n  <Description> " + model.getProjectByIndex(i).getDescription() + " </Description>";
                xml += "\n  <EstimatedHours> " + model.getProjectByIndex(i).getEstimatedHours() + " </EstimatedHours>";
                xml += "\n  <Deadline-Day> " + model.getProjectByIndex(i).getDeadline().getDay() + " </Deadline-Day>";
                xml += "\n  <Deadline-Month> " + model.getProjectByIndex(i).getDeadline().getMonth() + " </Deadline-Month>";
                xml += "\n  <Deadline-Year> " + model.getProjectByIndex(i).getDeadline().getYear() + " </Deadline-Year>";
                xml += "\n <TeamMembers>" + model.getProjectByIndex(i).getTeamMembers() + " </TeamMembers>";


                xml += "\n </Project>";

            }
            xml += "\n</ProjectList>";
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
            String pathname = "ProjectList.xml";
            File file = new File(pathname);

            Scanner in = new Scanner(file);


            String ProjectID = null;
            String name = null;
            String description = null;
            double EstimatedHours = 0.0;
            int day = 0;
            int month  = 0;
            int year = 0;
            String TeamMembers="";

            int count = 0;


            while (in.hasNext()){
                String line2 = in.nextLine();
                if(line2.contains("<NumberOfProjects>")){
                    line2 = line2.replace("<NumberOfProjects>","");
                    line2 = line2.replace("</NumberOfProjects>","");
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
                    } else if(line.contains("<Description>")){
                        line = line.replace("<Description>","");
                        line = line.replace("</Description>","");
                        description= line.trim();
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
                    } else if(line.contains("<TeamMembers>") && line.contains("</TeamMembers>")){
                        line = line.replace("<TeamMembers>","");
                        line = line.replace("</TeamMembers>","");
                        TeamMembers = line.trim();
                        break;
                    } else if(line.contains("TeamMembers>")){
                        line = line.replace("<TeamMembers>","");
                        while(!line.contains("</TeamMembers>")&& in.hasNext()){
                            String los = in.nextLine();
                            line+="\n"+los;
                        }
                        line = line.replace("</TeamMembers>","");
                        TeamMembers=line.trim();
                        break;

                    }





                }
                if(name!=null && description!=null && !description.equals("") &&
                        ProjectID != null && !ProjectID.equals("") && ProjectID.length() == 3
                        &&  day != 0 && month != 0 && year != 0 && EstimatedHours>=0){
                    Date D = new Date(day,month,year);


                    Project P = new Project(name,ProjectID,description,D,EstimatedHours,Status.STARTED);
                    P.setTeamMembers(TeamMembers);
                    model.addProject(P);
                    ProjectID = null;
                    name = null;
                    TeamMembers = "";

                    EstimatedHours = 0.0;
                    day = 0;
                    month  = 0;
                    year = 0;


                }


            }





        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }


}















