package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.*;

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
    private ViewState viewState;
    public ViewHandler(ColourITModel colourITModel)
    {
        viewState = new ViewState();
        this.colourITModel=colourITModel;
        currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        openView("projectlist");
    }

    public void openView(String id)
    {
        Region root = null;
        switch (id)
        {
            case "projectlist":
                root = loadProjectListView("ProjectListView.fxml");
                break;
            case "addProject":
                root = loadAddProjectView("AddProjectView.fxml");
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

    public void openView(String id, Project project)
    {
        Region root = null;
        switch (id)
        {
            case "ProjectDetails":
                root = loadProjectDetailsView("DetailsProjectView.fxml", project);
                break;
            case "manageTeamMembers":
                root = labelTeamMembers("ProjectTeamMembersView.fxml",project);
                break;
            case "RequirementList":
                root = loadRequirementListView("RequirementListMenu.fxml",project);
                break;
            case "addRequirement":
                root = loadAddRequirement("AddRequirementB.fxml",project);
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
    public void openView(String id, Requirement requirement, Project project){
        Region root = null;

        switch (id){
            case "Open":
                root = labelOpenRequirement("OpenRequirement.fxml",requirement,project);
                break;
            case "manageTeamMembers":
                root = labelTeamMebers("RequirementTeamMembersView.fxml",requirement,project);
                break;
            case "taskList":
                root = loadTaskListView("TaskListView.fxml",requirement,project);
                break;
            case "addTask":
                root = loadAddTaskView("addTaskView.fxml",requirement,project);
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
    public void openView(String id, Requirement requirement, Project project,Task task){
        Region root = null;

        switch (id) {
            case "taskDetails":
                root = loadTaskDetailsView("TaskDetailsView.fxml", task,requirement,project);
                break;
            case "addHoursSpent":
                root = loadAddTimeSpentView("AddTimeSpentView.fxml",task,requirement,project);
                break;
            case "editDeadline":
                root = loadEditDeadlineView("EditDeadlineView.fxml",task,requirement,project);
                break;
            case "setTaskID":
                root = loadSetTaskIDView("SetTaskIDView.fxml",task,requirement,project);
                break;
            case "setTaskName":
                root = loadSetTaskNameView("SetTaskNameView.fxml",task,requirement,project);
                break;
            case "setTaskDescription":
                root = loadSetTaskDescriptionView("SetDescriptionView.fxml",task,requirement,project);
                break;
            case "setTaskEstimatedHours":
                root = loadSetTaskEstimatedHoursView("SetEstimatedHoursView.fxml",task,requirement,project);
                break;
            case "manageTeamMembers":
                root = loadTaskTeamMembersView("TaskTeamMembersView.fxml",task,requirement,project);
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
    public void closeView()
    {
        primaryStage.close();
    }

    private Region loadProjectListView(String fxmlFile)
    {
        if (projectListViewController == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                projectListViewController = loader.getController();
                projectListViewController.init(this, colourITModel, root);
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

    private Region loadProjectDetailsView(String fxmlFile, Project project)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            projectDetailsViewController = loader.getController();
            projectDetailsViewController.init(this, colourITModel, root, project);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return projectDetailsViewController.getRoot();
    }

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

    private Region labelTeamMembers(String fxmlFile, Project project){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            projectTeamMembersViewController = loader.getController();
            projectTeamMembersViewController.init(this,colourITModel,root, project);
        } catch (Exception e){
            e.printStackTrace();
        }

        return projectTeamMembersViewController.getRoot();

    }
    private Region loadRequirementListView(String fxmlFile, Project project){
        if(requirementListMenuControllerr==null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                requirementListMenuControllerr = loader.getController();
                requirementListMenuControllerr.init(this,colourITModel,root,project);
            } catch (Exception e){
                e.printStackTrace();
        }}else {
            requirementListMenuControllerr.reset();
        }

        return requirementListMenuControllerr.getRoot();
    }
    private Region loadAddRequirement(String fxmlFile, Project project){
        if(addRequirementController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                addRequirementController = loader.getController();
                addRequirementController.init(this,colourITModel,root,project);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            addRequirementController.reset();
        }
        return addRequirementController.getRoot();
}
    private Region labelOpenRequirement(String fxmlFile, Requirement requirement,Project project){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            openRequirementController = loader.getController();
            openRequirementController.init(this,colourITModel,root,requirement, project);
        } catch (Exception e){
            e.printStackTrace();
        }

        return openRequirementController.getRoot();

    }

    private Region labelTeamMebers(String fxmlFile, Requirement requirement, Project project){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            requirementTeamMembersViewController = loader.getController();
            requirementTeamMembersViewController.init(this,colourITModel,root,requirement,project);
        } catch (Exception e){
            e.printStackTrace();
        }

        return requirementTeamMembersViewController.getRoot();

    }
    private Region loadTaskListView(String fxmlFile,Requirement requirement,Project project) {
        if(taskListViewController==null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                taskListViewController = loader.getController();
                taskListViewController.init(this, colourITModel, root,requirement,project);
            } catch (Exception e) {
                e.printStackTrace();
            }}else{
            taskListViewController.reset();
        }


        return taskListViewController.getRoot();
    }
    private Region loadAddTaskView(String fxmlFile,Requirement requirement,Project project) {
        if (addTaskViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                addTaskViewController = loader.getController();
                addTaskViewController.init(this, colourITModel, root,requirement,project);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            addTaskViewController.reset();
        }
        return addTaskViewController.getRoot();
    }
    private Region loadTaskDetailsView(String fxmlFile, Task task,Requirement requirement,Project project) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            taskDetailsViewController = loader.getController();
            taskDetailsViewController.init(this, colourITModel, root,task,requirement,project);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskDetailsViewController.getRoot();
    }
    private Region loadAddTimeSpentView(String fxmlFile, Task task,Requirement requirement,Project project) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            addTimeSpentViewController = loader.getController();
            addTimeSpentViewController.init(this, colourITModel, root,task,requirement,project);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addTimeSpentViewController.getRoot();
    }
    private Region loadEditDeadlineView(String fxmlFile, Task task,Requirement requirement,Project project) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            editDeadlineViewController = loader.getController();
            editDeadlineViewController.init(this, colourITModel, root,task,requirement,project);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return editDeadlineViewController.getRoot();
    }
    private Region loadSetTaskIDView(String fxmlFile, Task task,Requirement requirement,Project project) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            setTaskIDViewController = loader.getController();
            setTaskIDViewController.init(this, colourITModel, root,task,requirement,project);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setTaskIDViewController.getRoot();
    }private Region loadSetTaskNameView(String fxmlFile, Task task,Requirement requirement,Project project) {
    try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        setTaskNameViewController = loader.getController();
        setTaskNameViewController.init(this, colourITModel, root,task,requirement,project);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return setTaskNameViewController.getRoot();
}
    private Region loadSetTaskDescriptionView(String fxmlFile, Task task,Requirement requirement,Project project) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            setDescriptionViewController = loader.getController();
            setDescriptionViewController.init(this, colourITModel, root,task,requirement,project);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setDescriptionViewController.getRoot();
    }
    private Region loadSetTaskEstimatedHoursView(String fxmlFile, Task task,Requirement requirement,Project project) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            setEstimatedHoursViewController = loader.getController();
            setEstimatedHoursViewController.init(this, colourITModel, root,task,requirement,project);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setEstimatedHoursViewController.getRoot();
    }
    private Region loadTaskTeamMembersView(String fxmlFile, Task task,Requirement requirement,Project project) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            taskTeamMembersViewController = loader.getController();
            taskTeamMembersViewController.init(this, colourITModel, root,task,requirement,project);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskTeamMembersViewController.getRoot();
    }
}

