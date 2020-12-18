package model;

import java.util.ArrayList;

public interface ColourITModel {
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
    public void addRequirement(Requirement requirement,String projectID);

    public void removeRequirement(String requirementID,String projectID);

    public Requirement getRequirementByID(String requirementID,String projectID);

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
    public void addTask(Task task,String requirementID,String projectID);
    public void removeTask(String taskID,String requirementID,String projectID);
    public String getTaskID(String taskID,String requirementID,String projectID);
    public int taskListSize();
    public Task getTask(int index);
    String getRequirementIDOfTheTask(String taskID,String requirementID,String projectID);
    String getLabelNameOfTheTask(String taskID,String requirementID,String projectID);
    String getDescriptionOfTheTask(String taskID,String requirementID,String projectID);
    int getTeamMembersOfTheTask(String taskID,String requirementID,String projectID);
    Date getDeadlineOfTheTask(String taskID,String requirementID,String projectID);
    void setDeadline(int day,int month,int year,String taskID,String requirementID,String projectID);
    double getSpentHoursOfTheTask(String taskID,String requirementID,String projectID);
    double getEstimatedHoursOfTheTask(String taskID,String requirementID,String projectID);
    public void addNewTeamMember(TeamMember teamMember);
    public void removeTeamMemberByID(String teamMemberID);
    public void removeTeamMember(TeamMember teamMember);
    public TeamMember getTeamMember(int index);
    public int totalNumberOfTeamMembers();
    public ArrayList<TeamMember> getAllTeamMembers();
    public TeamMember getTeamMembersByID(String teamMemberID);
    public ArrayList<TeamMember> getTeamMemberByName(String name);
    public ArrayList<TeamMember> getTeamMembersByExperience(int yearsOfExperience);
    public TeamMember getTeamMembersByEmail(Email email);
    public ArrayList<TeamMember> getTeamMembersByBirthday(Date date);
    public TeamMember getScrumMaster();
    public TeamMember getProductOwner();
    public Task[] getAllTasks();
}
