package model;

import java.util.ArrayList;

public class ColourITModelManager implements ColourITModel {
    private ProjectList projectList;
    private Team team;

    public ColourITModelManager()
    {
        this.team= new Team();
        this.projectList = new ProjectList();
    }

    @Override public void addProject(Project project)
    {
        projectList.addProject(project);
    }

    @Override public void removeProject(String projectID)
    {
        projectList.removeProject(projectID);
    }

    @Override public Project getProjectByID(String projectID)
    {
        return projectList.getProjectByID(projectID);
    }

    @Override public Project[] getAllProjects()
    {
        return projectList.getAllProjects();
    }

    @Override public Project getProjectByIndex(int index)
    {
        return projectList.getProjectByIndex(index);
    }

    @Override public int getNumberOfProjects()
    {
        return projectList.getNumberOfProjects();
    }

    @Override public String getprojectID(Project project)
    {
        return projectList.getprojectID(project);
    }


    @Override public void addRequirement(Requirement requirement,String projectID){ getProjectByID(projectID).getRequirements().addRequirement(requirement);}

    @Override public void removeRequirement(String requirementID,String projectID){
    getProjectByID(projectID).getRequirements().removeRequirement(requirementID);
    }

    @Override public Requirement getRequirementByID(String requirementID,String projectID){return projectList.getProjectByID(projectID).getRequirements().getRequirementByID(requirementID);}
    @Override
    public void addTask(Task task,String requirementID,String projectID) {
        getProjectByID(projectID).getRequirements().getRequirementByID(requirementID).getTasks().addTask(task);
    }


    @Override
    public void removeTask(String taskID,String requirementID,String projectID) {
        getProjectByID(projectID).getRequirements().getRequirementByID(requirementID).getTasks().removeTaskByID(taskID);
    }

    @Override
    public String getTaskID(String taskID,String requirementID,String projectID) {
        return getProjectByID(projectID).getRequirements().getRequirementByID(requirementID).getTasks().getTaskByID(taskID).getTaskID();
    }

    @Override
    public String getRequirementIDOfTheTask(String taskID,String requirementID,String projectID) {
        return getProjectByID(projectID).getRequirements().getRequirementByID(requirementID).getTasks().getTaskByID(taskID).getRequirementID();
    }

    @Override
    public String getLabelNameOfTheTask(String taskID,String requirementID,String projectID) {
        return getProjectByID(projectID).getRequirements().getRequirementByID(requirementID).getTasks().getTaskByID(taskID).getLabelName();
    }

    @Override
    public String getDescriptionOfTheTask(String taskID,String requirementID,String projectID) {
        return getProjectByID(projectID).getRequirements().getRequirementByID(requirementID).getTasks().getTaskByID(taskID).getDescription();
    }


    @Override
    public Date getDeadlineOfTheTask(String taskID,String requirementID,String projectID) {
        return getProjectByID(projectID).getRequirements().getRequirementByID(requirementID).getTasks().getTaskByID(taskID).getDeadline();
    }

    @Override
    public void setDeadline(int day, int month, int year,String taskID,String requirementID,String projectID) {
        getProjectByID(projectID).getRequirements().getRequirementByID(requirementID).getTasks().getTaskByID(taskID).setDeadline(new Date(day,month,year));
    }

    @Override
    public double getSpentHoursOfTheTask(String taskID,String requirementID,String projectID) {
        return getProjectByID(projectID).getRequirements().getRequirementByID(requirementID).getTasks().getTaskByID(taskID).getTimeSpent();
    }

    @Override
    public double getEstimatedHoursOfTheTask(String taskID,String requirementID,String projectID) {
        return getProjectByID(projectID).getRequirements().getRequirementByID(requirementID).getTasks().getTaskByID(taskID).getEstimatedHours();
    }
    @Override public int totalNumberOfTeamMembers()
    {
        return team.totalNumberOfTeamMembers();
    }

    @Override public ArrayList<TeamMember> getAllTeamMembers()
    {
        ArrayList<TeamMember> temp = new ArrayList<>();
        for(int i=0;i<team.totalNumberOfTeamMembers();i++){
            temp.add(team.getTeamMember(i));
        }
        return temp;  }

    @Override public TeamMember getTeamMembersByID(String teamMemberID)
    {
        for (int i = 0; i < team.totalNumberOfTeamMembers(); i++)
        {
            if (team.getTeamMember(i).getTeamMemberID().equals(teamMemberID))
                return team.getTeamMember(i);
        }
        throw new IllegalArgumentException("Invalid ID");
    }

    public void addNewTeamMember(TeamMember teamMember)
    {
        team.addNewTeamMember(teamMember);
    }


    @Override public void removeTeamMemberByID(String teamMemberID)
    {
        if(!teamMemberID.equals("") && teamMemberID!=null){
            team.removeTeamMemberByID(teamMemberID);}
    }


    @Override public TeamMember getTeamMember(int index)
    {
        return team.getTeamMember(index);
    }
}
