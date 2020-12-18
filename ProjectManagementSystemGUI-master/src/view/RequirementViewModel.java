package view;

import javafx.beans.property.*;
import model.Date;
import model.Requirement;
import model.Status;
import model.UserStory;

/**
 * @author group 3
 * @version 1.0
 */
public class RequirementViewModel {
    private StringProperty projectID;
    private StringProperty requirementID;
    private StringProperty name;
    private ObjectProperty <UserStory> userStoryObjectProperty;
    private DoubleProperty estimatedHours;
    private ObjectProperty <Date> deadlineObjectProperty;
    private IntegerProperty orderNum;
    private ObjectProperty <Status> status;
    private StringProperty teamMembers;


    /**
     * A 1 argument constructor creating a requirement view model
     * @param requirement the requirement
     */
    public RequirementViewModel(Requirement requirement){
        projectID = new SimpleStringProperty(requirement.getProjectID());
        requirementID = new SimpleStringProperty(requirement.getRequirementID());
        name = new SimpleStringProperty(requirement.getName());
        userStoryObjectProperty = new SimpleObjectProperty<UserStory>(requirement.getDescription());
        estimatedHours = new SimpleDoubleProperty(requirement.getEstimatedHours());
        deadlineObjectProperty = new SimpleObjectProperty<Date>(requirement.getDeadline());
        orderNum = new SimpleIntegerProperty(requirement.getOrderNum());
        status = new SimpleObjectProperty<Status>(requirement.getStatus());
        teamMembers = new SimpleStringProperty(requirement.getTeamMembers());

    }

    /**
     * A method returning the project id
     * @return the id
     */
    public StringProperty getProjectID(){return projectID;}

    /**
     * A method returning the requirement id
     * @return the id
     */
    public StringProperty getRequirementID(){return requirementID;}

    /**
     * A method returning the name
     * @return the name
     */
    public StringProperty getName(){return name;}

    /**
     * A method returning the user story
     * @return user story
     */
    public ObjectProperty<UserStory> getUserStoryObjectProperty(){return userStoryObjectProperty;}

    /**
     * A method returning the estimated hours
     * @return estimated hours
     */
    public DoubleProperty getEstimatedHours(){return estimatedHours;}

    /**
     * A method returning the deadline
     * @return deadline
     */
    public ObjectProperty<Date> getDeadlineObjectProperty(){return deadlineObjectProperty;}

    /**
     * A method returning the order number
     * @return the order number
     */
    public IntegerProperty getOrderNum(){return orderNum;}

    /**
     * A method returning the status
     * @return the status
     */
    public ObjectProperty<Status> getStatus(){return status;}

    /**
     * A method returning the team members
     * @return team members
     */
    public StringProperty getTeamMembers(){return teamMembers;}








}
