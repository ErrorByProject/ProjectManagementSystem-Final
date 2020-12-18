package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.control.Alert.AlertType;

import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author group 3
 * @version 1.0
 */

public class  TaskListViewController {
    @FXML private TableView<TaskViewModel> taskListTable;
    @FXML private TableColumn<TaskViewModel, String > taskIDColumn;
    @FXML private TableColumn<TaskViewModel,String> requirementIDColumn;
    @FXML private TableColumn<TaskViewModel, String> nameColumn;
    @FXML private TableColumn<TaskViewModel, String> descriptionColumn;
    @FXML private TableColumn<TaskViewModel, Date> deadlineColumn;
    @FXML private TableColumn<TaskViewModel, Number> estimatedHoursColumn;
    @FXML private TableColumn<TaskViewModel, Number> hoursSpentColumn;
    @FXML private TableColumn<TaskViewModel, Status> statusColumn;
    @FXML private TableColumn<TaskViewModel, String> teamMembersColumn;
    @FXML private Label errorLabel;
    private Region root;
    private ColourITModel model;
    private ViewHandler viewHandler;
    private TaskListViewModel viewModel;
    private ViewState viewState;
    /**
     * A 0 argument constructor for the window
     */
    public TaskListViewController(){

    }
    /**
     * A initializer method initializing the components
     * @param viewHandler the view handler
     * @param model the model
     * @param root the root
     * @param viewState the view state
     */
    public void init(ViewHandler viewHandler,ColourITModel model, Region root,ViewState viewState){
        this.model = model;
        this.viewState=viewState;
        this.viewHandler=viewHandler;
        this.root=root;
        this.viewModel=new TaskListViewModel(model,viewState);
        taskIDColumn.setCellValueFactory(cellData -> cellData.getValue().getTaskIDProperty());
        requirementIDColumn.setCellValueFactory(cellData -> cellData.getValue().getRequirementIDProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getLabelNameProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescriptionProperty());
        deadlineColumn.setCellValueFactory(cellData -> cellData.getValue().getDeadlineProperty());
        estimatedHoursColumn.setCellValueFactory(cellData -> cellData.getValue().getEstimatedHoursProperty());
        hoursSpentColumn.setCellValueFactory(cellData -> cellData.getValue().getSpentHoursProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());
        teamMembersColumn.setCellValueFactory(cellData -> cellData.getValue().getTeamMembersProperty());
        taskListTable.setItems(viewModel.getList());
        errorLabel.setText("");
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
    public Region getRoot(){
        return root;
    }

    /**
     * A method  fot the add task button
     */
    @FXML private void addTaskButtonPressed(){
        viewHandler.openView("addTask");
    }

    /**
     * A method fot the remove task button
     */
    @FXML private void removeTaskButtonPressed(){
        try
        {
            TaskViewModel selectedItem = taskListTable.getSelectionModel()
                    .getSelectedItem();
            if(selectedItem==null){
                throw new IllegalArgumentException("No item selected");
            }
            boolean remove = confirmationRemove();
            if (remove)
            {
                Task task = new Task(selectedItem.getTaskIDProperty().get(),
                        selectedItem.getRequirementIDProperty().get(),selectedItem.getLabelNameProperty().get(),selectedItem.getDescriptionProperty().get(),selectedItem.getDeadlineProperty().get(),selectedItem.getEstimatedHoursProperty().get(), Status.STARTED);
                task.setTeamMembers(selectedItem.getTeamMembersProperty().get());
                model.removeTask(selectedItem.getTaskIDProperty().get(),viewState.getSelectedRequirement(),viewState.getSelectedProject());
                viewModel.remove(task);
                taskListTable.getSelectionModel().clearSelection();
            }
        }catch(IllegalArgumentException e){
            errorLabel.setText(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * A method for the confirmation
     * @return confirmation
     */
    private boolean confirmationRemove(){
        int index = taskListTable.getSelectionModel().getSelectedIndex();
        TaskViewModel selectedItem = taskListTable.getItems().get(index);
        if (index < 0 || index >= taskListTable.getItems().size())
        {
            return false;
        }
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(
                "Removing task { TaskID: " + selectedItem.getTaskIDProperty().get() +", RequirementID: " +
                        selectedItem.getRequirementIDProperty().get() +", Task Name: " +selectedItem.getLabelNameProperty().get()+ ", Task Description: " + selectedItem.getDescriptionProperty().get()+ ", Deadline: " + selectedItem.getDeadlineProperty().get()+ ", Estimated Hours To Finish: " + selectedItem.getEstimatedHoursProperty().get()+ "Task Status: " + selectedItem.getStatusProperty().get() + "}");
        Optional<ButtonType> result = alert.showAndWait();
        return ((result.isPresent()) && (result.get() == ButtonType.OK));
    }

    /**
     * A method for show task details button
     */
    @FXML private void showTaskDetailsButtonPressed(){
        try
        {
            TaskViewModel selectedItem = taskListTable.getSelectionModel()
                    .getSelectedItem();
            if(selectedItem==null){
                throw new IllegalArgumentException("No item selected");}
            boolean open= confirmationOpen();
            if (open)
            {
                viewState.setSelectedTask(selectedItem.getTaskIDProperty().get());
                viewHandler.openView("taskDetails");
                taskListTable.getSelectionModel().clearSelection();
            }}catch(IllegalArgumentException e){
            errorLabel.setText(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * A method for manage team members button
     */
    @FXML private void manageTeamMembersButtonPressed(){
        try
        {
            TaskViewModel selectedItem = taskListTable.getSelectionModel()
                    .getSelectedItem();
            if(selectedItem==null){
                throw new IllegalArgumentException("No item selected");}
            viewState.setSelectedTask(selectedItem.getTaskIDProperty().get());
            viewHandler.openView("taskManageTeamMembers");
            taskListTable.getSelectionModel().clearSelection();
        }catch(IllegalArgumentException e){
            errorLabel.setText(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * A method for the confirmation open
     * @return confirmation
     */
    private boolean confirmationOpen(){
        int index = taskListTable.getSelectionModel().getSelectedIndex();
        TaskViewModel selectedItem = taskListTable.getItems().get(index);
        if (index < 0 || index >= taskListTable.getItems().size())
        {
            return false;
        }
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(
                "Opening task: { TaskID: " + selectedItem.getTaskIDProperty().get() +", RequirementID: " +
                        selectedItem.getRequirementIDProperty().get() +", Task Name: " +selectedItem.getLabelNameProperty().get()+ ", Task Description: " + selectedItem.getDescriptionProperty().get()+ ", Deadline: " + selectedItem.getDeadlineProperty().get()+ ", Estimated Hours To Finish: " + selectedItem.getEstimatedHoursProperty().get()+ "Task Status: " + selectedItem.getStatusProperty().get() + "}");
        Optional<ButtonType> result = alert.showAndWait();
        return ((result.isPresent()) && (result.get() == ButtonType.OK));
    }

    /**
     * A method for the back button
     */
    @FXML private void backButtonPressed(){

        viewState.setSelectedRequirement("");
        viewHandler.openView("RequirementList");
    }

    /**
     * A method for the save button pressed
     */
    @FXML private void saveBPressed(){
        PrintWriter out = null;
        try {
            String pathname = "TaskP" + viewState.getSelectedProject()+"R"+viewState.getSelectedRequirement() +".xml";
            File file = new File(pathname);
            out = new PrintWriter(file);
            String xml = "";
            xml += "<?xml version=\"1.0\" encoding=\"UTF-8\"" + "standalone=\"no\"?>\n";
            xml += "<TaskList>";
            xml +="\n<NumberOfTasks>" + model.getRequirementByID(viewState.getSelectedRequirement(),viewState.getSelectedProject()).getTasks().getSize() + "</NumberOfTasks>";
            xml +="\n<ProjectID>"+viewState.getSelectedProject()+"</ProjectID>";

            for(int i = 0; i < model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getSize(); i++){


                xml += "\n <Task>";
                xml += "\n  <TaskID> " + model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByIndex(i).getTaskID() + " </TaskID>";
                xml += "\n  <Name> " + model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByIndex(i).getLabelName() + " </Name>";
                xml += "\n  <RequirementID> " + model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByIndex(i).getRequirementID() + " </RequirementID>";
                xml += "\n  <Description> " + model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByIndex(i).getDescription() + " </Description>";
                xml += "\n  <EstimatedHours> " + model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByIndex(i).getEstimatedHours() + " </EstimatedHours>";
                xml += "\n  <Deadline-Day> " + model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByIndex(i).getDeadline().getDay() + " </Deadline-Day>";
                xml += "\n  <Deadline-Month> " + model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByIndex(i).getDeadline().getMonth() + " </Deadline-Month>";
                xml += "\n  <Deadline-Year> " + model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByIndex(i).getDeadline().getYear() + " </Deadline-Year>";
              //  xml += "\n  <Status> " + model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByID(viewState.getSelectedTask()).getStatus().toString() + " </Status>";
                xml += "\n <TeamMembers>" + model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByIndex(i).getTeamMembers() + " </TeamMembers>";
                xml += "\n </Task>";
                System.out.println(model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByID(viewState.getSelectedRequirement()).getTasks().getTaskByIndex(i).getTeamMembers());
            }
            xml += "\n</TaskList>";
            out.println(xml);
            System.out.println(file.getAbsolutePath());


        } catch (FileNotFoundException e){
            errorLabel.setText("Press save button a 2nd time.");
        } finally {
            out.close();
        }


    }

    /**
     * A method that loads from the xml file the data
     */
    public void LoadFromMemory(){

        try {
            String pathname = "TaskP" + viewState.getSelectedProject()+"R"+viewState.getSelectedRequirement() +".xml"; // instead of 1 should be Project.getId or something to identify the project
            File file = new File(pathname);

            Scanner in = new Scanner(file);


            String projectID=null;
            String name = null;
            String RequirementID = null;
            String description = null;
            String TaskId = null;

            Double EstimatedHours = 0.0;
            int day = 0;
            int month  = 0;
            int year = 0;
            String teamMembers = "";
            int count = 0;

            while (in.hasNext()){
                String line2 = in.nextLine();
                if(line2.contains("<NumberOfTasks>")){
                    line2 = line2.replace("<NumberOfTasks>","");
                    line2 = line2.replace("</NumberOfTasks>","");
                    count = Integer.parseInt(line2.trim());
                    break;

                }
            }
            if(in.hasNext()){
                String line=in.nextLine();
                if(line.contains("<ProjectID>")){
                    line=line.replace("<ProjectID>","");
                    line = line.replace("</ProjectID>","");
                    projectID=line;
                }
            }
            for (int i = 0; i < count; i++){

                while(in.hasNext()){
                    String line = in.nextLine();


                    if(line.contains("<TaskID>")){
                        line = line.replace("<TaskID>","");
                        line = line.replace("</TaskID>", "");
                        TaskId = line.trim();
                    } else if(line.contains("<Name>")){
                        line = line.replace("<Name>","");
                        line = line.replace("</Name>", "");
                        name = line.trim();
                    } else if(line.contains("<RequirementID>")){
                        line = line.replace("<RequirementID>","");
                        line = line.replace("</RequirementID>","");
                        RequirementID = line.trim();
                    } else if(line.contains("<Description>")){
                        line = line.replace("<Description>","");
                        line = line.replace("</Description>","");
                        description = line.trim();
                    }  else if(line.contains("<EstimatedHours>")){
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
                        teamMembers = line.trim();
                        break;
                    } else if(line.contains("TeamMembers>")){
                        line = line.replace("<TeamMembers>","");
                            while(!in.nextLine().contains("</Task>")){
                               String los = in.nextLine();
                                line+="\n"+los;
                        }
                        line = line.replace("</TeamMembers>","");
                        teamMembers=line.trim();
                        break;

                    }


            }
                if(TaskId!=null && !TaskId.equals("") && RequirementID!=null && !RequirementID.equals("") &&
                        RequirementID.length()==4 && name!=null && !name.equals("")
                        && description!=null && !description.equals("") && day != 0 && month != 0 && year != 0
                        && EstimatedHours>=0 ){
                    Date D = new Date(day,month,year);


                    Task T = new Task(TaskId, RequirementID,name,description,D,EstimatedHours,Status.NOTSTARTED);
                    T.setTeamMembers(teamMembers);
                    model.addTask(T,RequirementID,projectID);

                    teamMembers = "";
                    name = null;
                    RequirementID = null;
                    EstimatedHours = 0.0;
                    day = 0;
                    month  = 0;
                    year = 0;


                }




        } }catch (FileNotFoundException e){
            errorLabel.setText("There is no file to load from.");
        }

    }


}

