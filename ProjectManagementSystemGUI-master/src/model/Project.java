package model;

import java.util.ArrayList;
/*
 * Project class, is the class with all the methods needed to edit/update the project and store all the data related to projects
 *
 * @author Nicolae Cernean
 * @version 1.1 2020-12-07
 * @version 1.2 2020-12-10
 * version 1.2
 * turned off instance variable teamProject cause it's an aray list of aray list of teams?
 * deleted addATeam
 * added protections to setters took out exceptions to not crash the project
 * added remove member
 * changed getall members to return team
 * changed constructor to initialise if it meets all conditions not by one.
 */
public class Project
{
    private String name;
    private String projectID;
    private String description;
    private Date deadline;
    private double estimatedHours;
    private RequirementList requirementList;
    private Team team;
    private Status status;
    private String teamMembers;

    // private ArrayList <Team>teamProject;

    /**
     * Creating a project with all
     *
     * @param name           - name of the project
     * @param projectID-     project ID
     * @param description    - description of the project
     * @param deadline       - the deadline of the project
     * @param estimatedHours - estimated hours spent
     * @param status         -status of the project
     */

    public Project(String name, String projectID, String description,
                   Date deadline, double estimatedHours, Status status)
    {
        if(!name.equals("") && name!=null && description!=null && !description.equals("") && projectID != null && !projectID.equals("") && projectID.length() == 3
                && deadline!=null && estimatedHours>=0){
            this.name=name;
            this.description=description;
            this.projectID=projectID;
            this.deadline=deadline;
            this.estimatedHours=estimatedHours;
            this.team = new Team();
            this.status=status;
            this.requirementList = new RequirementList();
            this.status=Status.STARTED;
        }else {
            throw new IllegalArgumentException("wrong input");
        }
        //this.teamProject = new ArrayList();}
    }

    /**
     * Returns projects name
     *
     * @return projects name
     */
    public String getName()
    {
        return name;
    }

    public String getTeamMembers(){return teamMembers;}

    public void setTeamMembers(String teamMembers){this.teamMembers = teamMembers;}
    /**
     * The method is adding a team to the project
     *
     * @param team added to the project
     */
 /* public void addATeam(Team team)
  {
     teamProject.add(team);
  }*/

    public void setRequirementList (RequirementList requirementList){
        this.requirementList=requirementList;
    }
    /**
     * Edit/Updates projects name
     *
     * @param name of the project
     */
    public void setName(String name)
    {
        if(!name.equals("") && name!=null){
            this.name = name;}
    }
    /**
     * Returns project description
     *
     * @return projects description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets/Updates projects description
     *
     * @param description of the project
     */
    public void setDescription(String description)
    {
        if(description!=null && description.equals("")){
            this.description = description;}
    }

    /**
     * Returns project ID
     *
     * @return projects ID
     */
    public String getProjectID()
    {
        return projectID;
    }

    /**
     * Sets/Updates projects ID
     *
     * @param projectID is updating
     */
    public void setProjectID(String projectID)
    {
        if (projectID != null && !projectID.equals("") && projectID.length() == 3)
        {
            this.projectID = projectID;
        }
    /*else
    {
      throw new IllegalArgumentException("The ID must contain 3 characters");
    }*/
    }

    /**
     * Returns deadline of the project
     *
     * @return project deadline
     */
    public Date getDeadline()
    {
        return deadline;
    }

    /**
     * Sets/Updates projects ID
     *
     * @param deadline is updated
     */
    public void setDeadline(Date deadline)
    {
        if(deadline!=null){
            this.deadline = deadline;}
    }

    /**
     * Returns estimated working hours on project
     *
     * @return project estimated hours for work in project
     */
    public double getEstimatedHours()
    {
        return estimatedHours;
    }

    /**
     * Sets/updates estimated hours of work in the project
     *
     * @param estimatedHours newHours of work in project
     */
    public void setEstimatedHours(int estimatedHours)
    {
        if(estimatedHours>=0){
            this.estimatedHours = estimatedHours;}
    }

    /**
     * Returns arequirements added in the project
     *
     * @return requirements of the project
     */
    public RequirementList getRequirements()
    {
        return requirementList;
    }

    /**
     * Sets/updates the scrum master of the project
     *
     * @param teamMemberID the team member that will be the scrum master
     */
    public void setScrumMaster(String teamMemberID)
    {
        if(teamMemberID!=null && teamMemberID.length()==4){
            team.getTeamMembersByID(teamMemberID).setRole(Role.SCRUM_MASTER);}
    }

    /**
     * Sets/updates the scrum master of the project
     *
     * @param teamMemberID the team member that will be the product owner
     */
    public void setProductOwner(String teamMemberID)
    {
        if(teamMemberID!=null && teamMemberID.length()==4){
            team.getTeamMembersByID(teamMemberID).setRole(Role.PRODUCT_OWNER);}
    }

    /**
     * Adds a team member in the project
     *
     * @param teamMemberID the team member that will be added in the project
     */
    public void addTeamMemberInAProject(String teamMemberID)
    {
        if(teamMemberID!=null && teamMemberID.length()==4){
            team.addNewTeamMember(team.getTeamMembersByID(teamMemberID));}
    }
    public void removeTeamMemberInAProject(String teamMemberID){
        if(teamMemberID!=null && teamMemberID.length()==4){
            team.removeTeamMember(team.getTeamMembersByID(teamMemberID));}
    }
    /**
     * Returns all the team members working on the project
     *
     * @return the team member that will be added in the project
     */
    public Team getAllMembers()
    {
        return team;
    }

    /**
     * Returns the project status
     *
     * @param status the new status of the project
     */
    // public void updateProjectStatus(Status status)
    // {
    //   if(status!=null){
    //   this.status = status;}
    // }

    /**
     * Returns the project status
     *
     * @return the project status
     */
    public Status getProjectStatus()
    {
        if(status.equals(Status.NOTSTARTED) && requirementList.getAllRequirements().length>0){
            status=Status.STARTED;
        }
        if(!status.equals(Status.ENDED) && requirementList.getActiveRequirements().isEmpty()){
            status=Status.ENDED;
        }
        return status;
    }

    /**
     * Returns the total hours that are/were spent on the project
     *
     * @return the project total hours spent
     */
    public double getHoursSpentOnProject()
    {
        return requirementList.getRequirementsListTotalHoursOfWork();
    }

    /**
     * Returns all the tasks of the project that weren't assigned to team members
     *
     * @return all the unassigned tasks between the team members
     */
    public ArrayList<Task> getUnassignedTasks()
    {
        ArrayList<Task> tasks=new ArrayList<>();
        for (int i = 0; i < getRequirementsByImportance().getAllRequirements().length;i++)
        {
            for(int j=0;j<getRequirementsByImportance().getRequirementByIndex(i).getTasks().getSize();j++){
                if(getRequirementsByImportance().getRequirementByIndex(i).getTasks().getTaskByIndex(j).getResponsibleTeamMembers().totalNumberOfTeamMembers()==0){
                    tasks.add(getRequirementsByImportance().getRequirementByIndex(i).getTasks().getTaskByIndex(j));
                }
            }
        }
        return tasks;
    }

    /**
     * Returns the requirements by importance
     *
     * @return all the the requirements by importance
     */
    public RequirementList getRequirementsByImportance()
    {
        return requirementList.getRequirementsSortedByOrderNum();
    }
    public void addRequirement(Requirement requirement){
        requirementList.addRequirement(requirement);
    }
}

