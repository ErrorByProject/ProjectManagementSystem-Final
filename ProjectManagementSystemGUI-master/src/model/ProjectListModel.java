package model;

import java.util.ArrayList;

public interface ProjectListModel
{
    public void addProject(Project project);
    public void removeProject(String projectID);
    public Project getProjectByID(String projectID);
    public Project[] getAllProjects();
    public Project getProjectByName(String name);
    public Status getProjectStatus(String projectID);
    public void editDeadlineOfAProject(String projectID, Date newDeadline);
    public void editUserStoryOfTheProject(String projectID, String newDescription);
    public Project[] getAllActiveProjects();
    public RequirementList getRequirementsOfAProject(String projectID);
    public RequirementList getRequirementsByImportance(String projectID);
    public Team getTeamMembersOfAProject(String projectID);
    public Project getProjectByIndex(int index);
    public int getNumberOfProjects();
    public String getprojectID(Project project);
    public String getProjectName(Project project);
    public String getProjectDescription(Project project);
    public void addRequirement(Requirement requirement);

    public void removeRequirement(String requirementID);

    public Requirement getRequirementByID(String requirementID);

    public void editDeadLineOfARequirement(String requirementID, Date newDeadline);

    public void editEstimatedHoursOfARequirement(String requirementID, double estimatedHours);

    public void editDescriptionOfARequirement(String requirementID, UserStory newDescription);

    public Status getRequirementStatus(String requirementID);

    public void assignRequirementOrder(String requirementID, int orderNum);

    public int getRequirementsListTotalHoursOfWork();

    public ArrayList<Requirement> getFinishedRequirements();

    public ArrayList<Requirement> getActiveRequirements();

    public Requirement getRequirementByIndex(int orderNum);

    public Requirement[] getAllRequirements();

    public int getRequirementListSize();

    public RequirementList getRequirementsSortedByOrderNum();

    public String toString ();



}
