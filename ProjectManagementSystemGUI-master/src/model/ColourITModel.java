package model;

import java.util.ArrayList;

public interface ColourITModel {
    void addProject(Project project);
    void removeProject(String projectID);
    Project getProjectByID(String projectID);
    Project[] getAllProjects();
    Project getProjectByIndex(int index);
    int getNumberOfProjects();
    String getprojectID(Project project);
    void addRequirement(Requirement requirement,String projectID);
    void removeRequirement(String requirementID,String projectID);
    Requirement getRequirementByID(String requirementID,String projectID);
    String toString ();
    void addTask(Task task,String requirementID,String projectID);
    void removeTask(String taskID,String requirementID,String projectID);
    String getTaskID(String taskID,String requirementID,String projectID);
    String getRequirementIDOfTheTask(String taskID,String requirementID,String projectID);
    String getLabelNameOfTheTask(String taskID,String requirementID,String projectID);
    String getDescriptionOfTheTask(String taskID,String requirementID,String projectID);
    Date getDeadlineOfTheTask(String taskID,String requirementID,String projectID);
    void setDeadline(int day,int month,int year,String taskID,String requirementID,String projectID);
    double getSpentHoursOfTheTask(String taskID,String requirementID,String projectID);
    double getEstimatedHoursOfTheTask(String taskID,String requirementID,String projectID);
    void addNewTeamMember(TeamMember teamMember);
    void removeTeamMemberByID(String teamMemberID);
    TeamMember getTeamMember(int index);
    int totalNumberOfTeamMembers();
    ArrayList<TeamMember> getAllTeamMembers();
    TeamMember getTeamMembersByID(String teamMemberID);
}
