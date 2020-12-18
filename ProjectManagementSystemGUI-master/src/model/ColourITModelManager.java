package model;

import java.util.ArrayList;

public class ColourITModelManager implements ColourITModel {
    private ProjectList projectList;
//    private RequirementList requirementList;
//    private TaskList taskList;
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

    @Override public Project getProjectByName(String name)
    {
        return projectList.getProjectByName(name);
    }

    @Override public Status getProjectStatus(String projectID)
    {
        return projectList.getProjectStatus(projectID);
    }

    public void editDeadlineOfAProject(String projectID, Date newDeadline)
    {
        projectList.editDeadlineOfAProject(projectID, newDeadline);
    }

    public void editUserStoryOfTheProject(String projectID, String newDescription)
    {
        projectList.editUserStoryOfTheProject(projectID, newDescription);
    }

    public Project[] getAllActiveProjects()
    {
        return projectList.getAllActiveProjects();
    }

    public RequirementList getRequirementsOfAProject(String projectID)
    {
        return projectList.getRequirementsOfAProject(projectID);
    }

    public RequirementList getRequirementsByImportance(String projectID)
    {
        return projectList.getRequirementsByImportance(projectID);
    }

    public Team getTeamMembersOfAProject(String projectID)
    {
        return projectList.getTeamMembersOfAProject(projectID);
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

    @Override public String getProjectName(Project project)
    {
        return projectList.getProjectName(project);
    }

    @Override public String getProjectDescription(Project project)
    {
        return projectList.getProjectDescription(project);
    }
    @Override public void addRequirement(Requirement requirement,String projectID){ getProjectByID(projectID).getRequirements().addRequirement(requirement);}

    @Override public void removeRequirement(String requirementID,String projectID){
    getProjectByID(projectID).getRequirements().removeRequirement(requirementID);
    }

    @Override public Requirement getRequirementByID(String requirementID,String projectID){return projectList.getProjectByID(projectID).getRequirements().getRequirementByID(requirementID);}
//
//    @Override public void editDeadLineOfARequirement(String requirementID, Date newDeadline){requirementList.editDeadLineOfARequirement(requirementID,newDeadline);}
//
//    @Override public void editEstimatedHoursOfARequirement(String requirementID, double estimatedHours){requirementList.editEstimatedHoursOfARequirement(requirementID,estimatedHours);}
//
//    @Override public void editDescriptionOfARequirement(String requirementID, UserStory newDescription){requirementList.editDescriptionOfARequirement(requirementID,newDescription);}
//
//    @Override public Status getRequirementStatus(String requirementID){return requirementList.getRequirementStatus(requirementID);}
//
//    @Override public void assignRequirementOrder(String requirementID, int orderNum){requirementList.assignRequirementOrder(requirementID,orderNum);}
//
//    @Override public int getRequirementsListTotalHoursOfWork(){return requirementList.getRequirementsListTotalHoursOfWork();}
//
//    @Override public ArrayList<Requirement> getFinishedRequirements(){return requirementList.getFinishedRequirements();}
//
//    @Override public ArrayList<Requirement> getActiveRequirements(){return requirementList.getActiveRequirements();}
//
//    @Override public Requirement getRequirementByIndex(int orderNum){return requirementList.getRequirementByIndex(orderNum);}
//
//    @Override public Requirement[] getAllRequirements(){return requirementList.getAllRequirements();}
//
//    @Override public RequirementList getRequirementsSortedByOrderNum(){return requirementList.getRequirementsSortedByOrderNum();}
//
//    @Override public String toString (){return requirementList.toString();}
//
//    @Override public int getRequirementListSize(){return requirementList.getRequirementListSize();}
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

//    @Override
//    public int taskListSize() {
//        return taskList.getSize();
//    }
//
//    @Override
//    public Task getTask(int index) {
//        return taskList.getTaskByIndex(index);
//    }

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
    public int getTeamMembersOfTheTask(String taskID,String requirementID,String projectID) {
        return getProjectByID(projectID).getRequirements().getRequirementByID(requirementID).getTasks().getTaskByID(taskID).getResponsibleTeamMembers().getAllTeamMembers().size();
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

    @Override public ArrayList<TeamMember> getTeamMemberByName(String name)
    {
        return null;
    }

    @Override public ArrayList<TeamMember> getTeamMembersByExperience(
            int yearsOfExperience)
    {
        return null;
    }

    @Override public TeamMember getTeamMembersByEmail(Email email)
    {
        return null;
    }

    @Override public ArrayList<TeamMember> getTeamMembersByBirthday(Date date)
    {
        return null;
    }

    @Override public TeamMember getScrumMaster()
    {
        return team.getScrumMaster();
    }

    @Override public TeamMember getProductOwner()
    {
        return team.getProductOwner();
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

    @Override public void removeTeamMember(TeamMember teamMember){
        if(teamMember!=null){
            for(int i=0;i<team.totalNumberOfTeamMembers();i++){
                if(team.getTeamMember(i).equals(teamMember)){
                    team.removeTeamMember(team.getTeamMember(i));
                }
            }}
    }

    @Override public TeamMember getTeamMember(int index)
    {
        return team.getTeamMember(index);
    }

//    @Override public Task [] getAllTasks(){
//        return taskList.getAllTasks();
//    }
}
