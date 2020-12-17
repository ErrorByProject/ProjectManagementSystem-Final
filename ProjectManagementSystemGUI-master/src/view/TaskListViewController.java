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

public class TaskListViewController {
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
    private Requirement requirement;
    private Project project;
    public TaskListViewController(){

    }
    public void init(ViewHandler viewHandler,ColourITModel model, Region root,Requirement requirement,Project project){
        this.model = model;
        this.requirement=requirement;
        this.project=project;
        this.viewHandler=viewHandler;
        this.root=root;
        this.viewModel=new TaskListViewModel(model);
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
    public void reset(){
        errorLabel.setText("");
        viewModel.update();

    }
    public Region getRoot(){
        return root;
    }
    @FXML private void addTaskButtonPressed(){
        viewHandler.openView("addTask",requirement,project);
    }
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
                model.removeTask(task);
                requirement.getTasks().removeTask(task);
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
                Task task = new Task(selectedItem.getTaskIDProperty().get(), selectedItem.getRequirementIDProperty().get(),
                        selectedItem.getLabelNameProperty().get(),selectedItem.getDescriptionProperty().get(),selectedItem.getDeadlineProperty().get(),selectedItem.getEstimatedHoursProperty().get(), Status.STARTED);
                task.setTeamMembers(selectedItem.getTeamMembersProperty().get());
                viewHandler.openView("taskDetails",requirement,project,task);
                taskListTable.getSelectionModel().clearSelection();
            }}catch(IllegalArgumentException e){
            errorLabel.setText(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML private void manageTeamMembersButtonPressed(){
        try
        {
            TaskViewModel selectedItem = taskListTable.getSelectionModel()
                    .getSelectedItem();
            if(selectedItem==null){
                throw new IllegalArgumentException("No item selected");}
            Task task = new Task(selectedItem.getTaskIDProperty().get(), selectedItem.getRequirementIDProperty().get(),
                    selectedItem.getLabelNameProperty().get(),selectedItem.getDescriptionProperty().get(),selectedItem.getDeadlineProperty().get(),selectedItem.getEstimatedHoursProperty().get(), Status.STARTED);
            task.setTeamMembers(selectedItem.getTeamMembersProperty().get());
            viewHandler.openView("manageTeamMembers",requirement,project,task);
            taskListTable.getSelectionModel().clearSelection();
        }catch(IllegalArgumentException e){
            errorLabel.setText(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
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

    @FXML private void backButtonPressed(){
        viewHandler.openView("RequirementList",project);
    }

    @FXML private void saveBPressed(){
        PrintWriter out = null;
        try {
            String pathname = "Task" + project.getProjectID() +".xml"; // instead of 1 should be Project.getId or something to identify the project
            File file = new File(pathname);
            out = new PrintWriter(file);
            String xml = "";
            xml += "<?xml version=\"1.0\" encoding=\"UTF-8\"" + "standalone=\"no\"?>\n";
            xml += "<TaskList>";
            xml +="\n<NumberOfTasks>" + model.getAllTasks().length + "</NumberOfTasks>";

            for(int i = 0; i < model.getAllTasks().length; i++){


                xml += "\n <Task>";
                xml += "\n  <TaskID> " + model.getTask(i).getTaskID() + " </TaskID>";
                xml += "\n  <Name> " + model.getTask(i).getLabelName() + " </Name>";
                xml += "\n  <RequirementID> " + model.getTask(i).getRequirementID() + " </RequirementID>";
                xml += "\n  <Description> " + model.getTask(i).getDescription() + " </Description>";
                xml += "\n  <EstimatedHours> " + model.getTask(i).getEstimatedHours() + " </EstimatedHours>";
                xml += "\n  <Deadline-Day> " + model.getTask(i).getDeadline().getDay() + " </Deadline-Day>";
                xml += "\n  <Deadline-Month> " + model.getTask(i).getDeadline().getMonth() + " </Deadline-Month>";
                xml += "\n  <Deadline-Year> " + model.getTask(i).getDeadline().getYear() + " </Deadline-Year>";
                xml += "\n <TeamMembers>" + model.getTask(i).getTeamMembers() + " </TeamMembers>";
                xml += "\n </Task>";

            }
            xml += "\n</TaskList>";
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
            String pathname = "Task" + project.getProjectID() +".xml"; // instead of 1 should be Project.getId or something to identify the project
            File file = new File(pathname);

            Scanner in = new Scanner(file);



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
                    } else if(line.contains("<TeamMembers>")){
                        line = line.replace("<TeamMembers>","");
                        line = line.replace("</TeamMembers>","");
                        teamMembers = line.trim();
                    }

                    if(TaskId!=null && !TaskId.equals("") && RequirementID!=null && !RequirementID.equals("") &&
                            RequirementID.length()==4 && name!=null && !name.equals("")
                            && description!=null && !description.equals("") && day != 0 && month != 0 && year != 0
                         && EstimatedHours>=0 ){
                        Date D = new Date(day,month,year);


                        Task T = new Task(TaskId, RequirementID,name,description,D,EstimatedHours,Status.STARTED);

                        T.setTeamMembers(teamMembers);
                        model.addTask(T);

                        teamMembers = "";
                        name = null;
                        RequirementID = null;

                        EstimatedHours = 0.0;
                        day = 0;
                        month  = 0;
                        year = 0;


                    }



                }


            }





        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }


}

