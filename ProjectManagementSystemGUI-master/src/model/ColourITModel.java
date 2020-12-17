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
    public void addTask(Task task);
    public void removeTask(String ID);
    public void removeTask(Task task);
    public String getTaskID(Task task);
    public int taskListSize();
    public Task getTask(int index);
    String getRequirementIDOfTheTask(Task task);
    String getLabelNameOfTheTask(Task task);
    String getDescriptionOfTheTask(Task task);
    int getTeamMembersOfTheTask(Task task);
    Date getDeadlineOfTheTask(Task task);
    void setDeadline(Task task,int day,int month,int year);
    double getSpentHoursOfTheTask(Task task);
    double getEstimatedHoursOfTheTask(Task task);
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
