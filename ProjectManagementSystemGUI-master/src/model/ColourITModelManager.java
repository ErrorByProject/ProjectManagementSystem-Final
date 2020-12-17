package model;

import java.util.ArrayList;

public class ColourITModelManager implements ColourITModel {
    private ProjectList projectList;
    private RequirementList requirementList;
    private TaskList taskList;
    private Team team;

    public ColourITModelManager()
    {
        this.team= new Team();
        this.projectList = new ProjectList();
        this.requirementList=new RequirementList();
        this.taskList=new TaskList();
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
    @Override public void addRequirement(Requirement requirement){ requirementList.addRequirement(requirement);}

    @Override public void removeRequirement(String requirementID){ requirementList.removeRequirement(requirementID);}

    @Override public Requirement getRequirementByID(String requirementID){return requirementList.getRequirementByID(requirementID);}

    @Override public void editDeadLineOfARequirement(String requirementID, Date newDeadline){requirementList.editDeadLineOfARequirement(requirementID,newDeadline);}

    @Override public void editEstimatedHoursOfARequirement(String requirementID, double estimatedHours){requirementList.editEstimatedHoursOfARequirement(requirementID,estimatedHours);}

    @Override public void editDescriptionOfARequirement(String requirementID, UserStory newDescription){requirementList.editDescriptionOfARequirement(requirementID,newDescription);}

    @Override public Status getRequirementStatus(String requirementID){return requirementList.getRequirementStatus(requirementID);}

    @Override public void assignRequirementOrder(String requirementID, int orderNum){requirementList.assignRequirementOrder(requirementID,orderNum);}

    @Override public int getRequirementsListTotalHoursOfWork(){return requirementList.getRequirementsListTotalHoursOfWork();}

    @Override public ArrayList<Requirement> getFinishedRequirements(){return requirementList.getFinishedRequirements();}

    @Override public ArrayList<Requirement> getActiveRequirements(){return requirementList.getActiveRequirements();}

    @Override public Requirement getRequirementByIndex(int orderNum){return requirementList.getRequirementByIndex(orderNum);}

    @Override public Requirement[] getAllRequirements(){return requirementList.getAllRequirements();}

    @Override public RequirementList getRequirementsSortedByOrderNum(){return requirementList.getRequirementsSortedByOrderNum();}

    @Override public String toString (){return requirementList.toString();}

    @Override public int getRequirementListSize(){return requirementList.getRequirementListSize();}
    @Override
    public void addTask(Task task) {
        taskList.addTask(task);
    }

    @Override
    public void removeTask(String ID) {
        taskList.removeTaskByID(ID);
    }

    @Override
    public void removeTask(Task task) {
        taskList.removeTask(task);
    }

    @Override
    public String getTaskID(Task task) {
//        for(int i=0;i<taskListSize();i++){
//            if(taskList.getTaskByIndex(i).equals(task)){
//                return taskList.getTaskByIndex(i).getTaskID();
//            }
//        }
        return task.getTaskID();
    }

    @Override
    public int taskListSize() {
        return taskList.getSize();
    }

    @Override
    public Task getTask(int index) {
        return taskList.getTaskByIndex(index);
    }

    @Override
    public String getRequirementIDOfTheTask(Task task) {
        return task.getRequirementID();
    }

    @Override
    public String getLabelNameOfTheTask(Task task) {
        return task.getLabelName();
    }

    @Override
    public String getDescriptionOfTheTask(Task task) {
        return task.getDescription();
    }

    @Override
    public int getTeamMembersOfTheTask(Task task) {
        return task.getResponsibleTeamMembers().getAllTeamMembers().size();
    }

    @Override
    public Date getDeadlineOfTheTask(Task task) {
        return task.getDeadline();
    }

    @Override
    public void setDeadline(Task task,int day, int month, int year) {
        task.setDeadline(new Date(day,month,year));
    }

    @Override
    public double getSpentHoursOfTheTask(Task task) {
        return task.getTimeSpent();
    }

    @Override
    public double getEstimatedHoursOfTheTask(Task task) {
        return task.getEstimatedHours();
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

    @Override public Task [] getAllTasks(){
        return taskList.getAllTasks();
    }
}
