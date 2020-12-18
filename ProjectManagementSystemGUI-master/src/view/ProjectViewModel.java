package view;

import javafx.beans.property.*;
import model.Date;
import model.Project;
import model.RequirementList;
import model.Status;

/**
 * @author group 3
 * @version 1.0
 */
public class ProjectViewModel
{
    private StringProperty nameProperty;
    private StringProperty projectIDProperty;
    private StringProperty descriptionProperty;
    private ObjectProperty<Date> deadlineProperty;
    private DoubleProperty estimatedHoursProperty;
    private DoubleProperty hoursSpentProperty;
    private ObjectProperty<Status> statusProperty;
    private StringProperty teamMembers;
    private ObjectProperty<RequirementList> requirementListObjectProperty;

    /**
     * A 1 argument constructor creating a project window v
     * @param project the project
     */
    public ProjectViewModel(Project project)
    {
        nameProperty = new SimpleStringProperty(project.getName());
        projectIDProperty = new SimpleStringProperty(project.getProjectID());
        descriptionProperty = new SimpleStringProperty(project.getDescription());
        deadlineProperty = new SimpleObjectProperty<>(project.getDeadline());
        estimatedHoursProperty = new SimpleDoubleProperty(project.getEstimatedHours());
        statusProperty=new SimpleObjectProperty<>(project.getProjectStatus());
        hoursSpentProperty = new SimpleDoubleProperty(project.getHoursSpentOnProject());
        teamMembers = new SimpleStringProperty(project.getTeamMembers());
        requirementListObjectProperty = new SimpleObjectProperty<>(project.getRequirementsByImportance());
    }

    /**
     * A method returning the name
     * @return the name
     */
    public StringProperty getNameProperty()
    {
        return nameProperty;
    }

    /**
     * A method returning the project id
     * @return the project id
     */
    public StringProperty getProjectIDProperty()
    {
        return projectIDProperty;
    }

    /**
     * a method returning the description
     * @return the description
     */
    public StringProperty getDescriptionProperty()
    {
        return descriptionProperty;
    }

    /**
     * A method returning the deadline
     * @return the deadline
     */
    public ObjectProperty<Date> getDeadlineProperty()
    {
        return deadlineProperty;
    }

    /**
     * A method returning the estimated hours
     * @return the estimated hours
     */
    public DoubleProperty getEstimatedHoursProperty()
    {
        return estimatedHoursProperty;
    }

    /**
     * A method returning the hours spent
     * @return the hours
     */
    public DoubleProperty getHoursSpentProperty()
    {
        return hoursSpentProperty;
    }

    /**
     * A method returning the status
     * @return the status
     */
    public ObjectProperty<Status> getStatusProperty()
    {
        return statusProperty;
    }

    /**
     * A method returning the team members
     * @return the team members
     */
    public StringProperty getTeamMembers(){return teamMembers;}

    /**
     * A method returning the requirement list
     * @return the list
     */
    public ObjectProperty<RequirementList> getRequirementListObjectProperty(){return requirementListObjectProperty;};
}

