package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.*;
/**
 * @author group 3
 * @version 1.0
 */

public class ViewHandler
{
    private Scene currentScene;
    private Stage primaryStage;
    private ColourITModel colourITModel;
    private RequirementListMenuControllerr requirementListMenuControllerr;
    private ProjectListViewController projectListViewController;
    private ProjectDetailsViewController projectDetailsViewController;
    private AddProjectViewController addProjectViewController;
    private ProjectTeamMembersViewController projectTeamMembersViewController;
    private AddRequirementController addRequirementController;
    private OpenRequirementController openRequirementController;
    private RequirementTeamMembersViewController requirementTeamMembersViewController;
    private TaskListViewController taskListViewController;
    private AddTaskViewController addTaskViewController;
    private TaskDetailsViewController taskDetailsViewController;
    private AddTimeSpentViewController addTimeSpentViewController;
    private EditDeadlineViewController editDeadlineViewController;
    private SetTaskIDViewController setTaskIDViewController;
    private SetTaskNameViewController setTaskNameViewController;
    private SetDescriptionViewController setDescriptionViewController;
    private SetEstimatedHoursViewController setEstimatedHoursViewController;
    private TaskTeamMembersViewController taskTeamMembersViewController;
    private TeamViewController teamViewController;
    private AddTeamMemberViewController addTeamMemberViewController;
    private ViewState viewState;

    /**
     * A 1 argument constructor creating the view handler
     * @param colourITModel the model
     */
    public ViewHandler(ColourITModel colourITModel)
    {

        this.colourITModel=colourITModel;
        this.currentScene = new Scene(new Region());
        this.viewState = new ViewState();
    }


    /**
     * A method staring the stage
     */
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        openView("projectlist");
    }

    /**
     * A method opening the view
     * @param id
     */
    public void openView(String id)
    {
        Region root = null;
        switch (id)
        {
            case "projectlist":
                root = loadProjectListView("ProjectListView.fxml",viewState);
                break;
            case "addProject":
                root = loadAddProjectView("AddProjectView.fxml");
                break;
            case "ProjectDetails":
                root = loadProjectDetailsView("DetailsProjectView.fxml", viewState);
                break;
            case "projectManageTeamMembers":
                root = labelTeamMembers("ProjectTeamMembersView.fxml",viewState);
                break;
            case "RequirementList":
                root = loadRequirementListView("RequirementListMenu.fxml",viewState);
                break;
            case "addRequirement":
                root = loadAddRequirement("AddRequirementB.fxml",viewState);
                break;
            case "Open":
                root = labelOpenRequirement("OpenRequirement.fxml",viewState);
                break;
            case "requirementManageTeamMembers":
                root = labelTeamMebers("RequirementTeamMembersView.fxml",viewState);
                break;
            case "taskList":
                root = loadTaskListView("TaskListView.fxml",viewState);
                break;
            case "addTask":
                root = loadAddTaskView("addTaskView.fxml",viewState);
                break;
            case "taskDetails":
                root = loadTaskDetailsView("TaskDetailsView.fxml", viewState);
                break;
            case "addHoursSpent":
                root = loadAddTimeSpentView("AddTimeSpentView.fxml",viewState);
                break;
            case "editDeadline":
                root = loadEditDeadlineView("EditDeadlineView.fxml",viewState);
                break;
            case "setTaskID":
                root = loadSetTaskIDView("SetTaskIDView.fxml",viewState);
                break;
            case "setTaskName":
                root = loadSetTaskNameView("SetTaskNameView.fxml",viewState);
                break;
            case "setTaskDescription":
                root = loadSetTaskDescriptionView("SetDescriptionView.fxml",viewState);
                break;
            case "setTaskEstimatedHours":
                root = loadSetTaskEstimatedHoursView("SetEstimatedHoursView.fxml",viewState);
                break;
            case "taskManageTeamMembers":
                root = loadTaskTeamMembersView("TaskTeamMembersView.fxml",viewState);
                break;
            case "teamList":
                root = loadTeamView("TeamView.fxml");
                break;
            case "addTeamMember":
                root = loadAddTeamMemberView("AddTeamMemberView.fxml");
                break;

        }
        currentScene.setRoot(root);
        String title = "";
        if (root.getUserData() != null)
        {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }
    /**
     * A method closing the view
     *
     */
    public void closeView()
    {
        primaryStage.close();
    }

    /**
     * A method loading the project list view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region loadProjectListView(String fxmlFile,ViewState state)
    {
        if (projectListViewController == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                projectListViewController = loader.getController();
                projectListViewController.init(this, colourITModel, root, state);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            projectListViewController.reset();
        }
        return projectListViewController.getRoot();
    }

    /**
     * A method loading the project details list view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region loadProjectDetailsView(String fxmlFile, ViewState state)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            projectDetailsViewController = loader.getController();
            projectDetailsViewController.init(this, colourITModel, root, state);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return projectDetailsViewController.getRoot();
    }

    /**
     * A method loading the project list view
     * @param fxmlFile the file
     *
     * @return list
     */
    private Region loadAddProjectView(String fxmlFile)
    {
        if (addProjectViewController == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                addProjectViewController = loader.getController();
                addProjectViewController.init(this, colourITModel, root);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            addProjectViewController.reset();
        }
        return addProjectViewController.getRoot();
    }

    /**
     * A method loading the team members view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region labelTeamMembers(String fxmlFile, ViewState state){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            projectTeamMembersViewController = loader.getController();
            projectTeamMembersViewController.init(this,colourITModel,root, state);
        } catch (Exception e){
            e.printStackTrace();
        }

        return projectTeamMembersViewController.getRoot();

    }
    /**
     * A method loading the requirement list view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region loadRequirementListView(String fxmlFile, ViewState state){
        if(requirementListMenuControllerr==null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                requirementListMenuControllerr = loader.getController();
                requirementListMenuControllerr.init(this,colourITModel,root, state);
            } catch (Exception e){
                e.printStackTrace();
        }}else {
            requirementListMenuControllerr.reset();
        }

        return requirementListMenuControllerr.getRoot();
    }
    /**
     * A method loading the requirement view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region loadAddRequirement(String fxmlFile, ViewState state){
        if(addRequirementController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                addRequirementController = loader.getController();
                addRequirementController.init(this,colourITModel,root,state);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            addRequirementController.reset();
        }
        return addRequirementController.getRoot();
}
    /**
     * A method loading the open requirement view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region labelOpenRequirement(String fxmlFile, ViewState state){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            openRequirementController = loader.getController();
            openRequirementController.init(this,colourITModel,root,state);
        } catch (Exception e){
            e.printStackTrace();
        }

        return openRequirementController.getRoot();

    }
    /**
     * A method loading the team members  view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */

    private Region labelTeamMebers(String fxmlFile, ViewState state){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            requirementTeamMembersViewController = loader.getController();
            requirementTeamMembersViewController.init(this,colourITModel,root, state);
        } catch (Exception e){
            e.printStackTrace();
        }

        return requirementTeamMembersViewController.getRoot();

    }
    /**
     * A method loading the task list view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region loadTaskListView(String fxmlFile,ViewState state) {
        if(taskListViewController==null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                taskListViewController = loader.getController();
                taskListViewController.init(this, colourITModel, root, state);
            } catch (Exception e) {
                e.printStackTrace();
            }}else{
            taskListViewController.reset();
        }


        return taskListViewController.getRoot();
    }
    /**
     * A method loading the add task view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region loadAddTaskView(String fxmlFile,ViewState state) {
        if (addTaskViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                addTaskViewController = loader.getController();
                addTaskViewController.init(this, colourITModel, root,state);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            addTaskViewController.reset();
        }
        return addTaskViewController.getRoot();
    }
    /**
     * A method loading the load details view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region loadTaskDetailsView(String fxmlFile, ViewState state) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            taskDetailsViewController = loader.getController();
            taskDetailsViewController.init(this, colourITModel, root,state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskDetailsViewController.getRoot();
    }
    /**
     * A method loading the add time spent view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region loadAddTimeSpentView(String fxmlFile, ViewState state) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            addTimeSpentViewController = loader.getController();
            addTimeSpentViewController.init(this, colourITModel, root,state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addTimeSpentViewController.getRoot();
    }
    /**
     * A method loading the edit deadline view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region loadEditDeadlineView(String fxmlFile, ViewState state) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            editDeadlineViewController = loader.getController();
            editDeadlineViewController.init(this, colourITModel, root,state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return editDeadlineViewController.getRoot();
    }
    /**
     * A method loading the set task id view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region loadSetTaskIDView(String fxmlFile, ViewState state) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            setTaskIDViewController = loader.getController();
            setTaskIDViewController.init(this, colourITModel, root, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setTaskIDViewController.getRoot();
    }
    /**
     * A method loading set task name view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region loadSetTaskNameView(String fxmlFile, ViewState state) {
    try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        setTaskNameViewController = loader.getController();
        setTaskNameViewController.init(this, colourITModel, root, state);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return setTaskNameViewController.getRoot();
}
    /**
     * A method loading set task description view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region loadSetTaskDescriptionView(String fxmlFile, ViewState state) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            setDescriptionViewController = loader.getController();
            setDescriptionViewController.init(this, colourITModel, root,state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setDescriptionViewController.getRoot();
    }
    /**
     * A method loading set estimated hours view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region loadSetTaskEstimatedHoursView(String fxmlFile, ViewState state) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            setEstimatedHoursViewController = loader.getController();
            setEstimatedHoursViewController.init(this, colourITModel, root,state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setEstimatedHoursViewController.getRoot();
    }
    /**
     * A method loading set team members view
     * @param fxmlFile the file
     * @param state the state
     * @return list
     */
    private Region loadTaskTeamMembersView(String fxmlFile, ViewState state) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            taskTeamMembersViewController = loader.getController();
            taskTeamMembersViewController.init(this, colourITModel, root,state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskTeamMembersViewController.getRoot();
    }
    /**
     * A method loading set team view
     * @param fxmlFile the file
     *
     * @return list
     */
    private Region loadTeamView(String fxmlFile)
    {
        if (teamViewController == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                teamViewController = loader.getController();
                teamViewController.init(this, colourITModel, root);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            teamViewController.reset();
        }
        return teamViewController.getRoot();
    }
    /**
     * A method loading the add team member view
     * @param fxmlFile the file
     *
     * @return list
     */
    private Region loadAddTeamMemberView(String fxmlFile)
    {
        if (addTeamMemberViewController == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                addTeamMemberViewController = loader.getController();
                addTeamMemberViewController.init(this, colourITModel, root);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            addTeamMemberViewController.reset();
        }
        return addTeamMemberViewController.getRoot();
    }
}

