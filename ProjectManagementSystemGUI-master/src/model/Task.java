package model;

/**
 * model.Task object to describe and share the work of the task
 * @author Rokas Paulauskas
 * @version 1.001 2020-12-03 Rokas
 * @version 1.002 2020-12-10 Rokas
 * Added model.Team class to the task, suggested by Nicolae, changed remove and assign team member methods to use model.Team class
 * tested everything is ok, don't touch it
 */
public class Task {
    private String taskID;
    private String requirementID;
    private String labelName;
    private String description;
    private double estimatedHours;
    private Date deadline;
    private double hoursSpent;
    private Status status;
    private Team team;
    private String teamMembers;

    /**
     * Creates a task object
     * @param taskID - task identification number
     * @param requirementID - requirement identification number
     * @param labelName - task name
     * @param description - what you have to do
     * @param deadline - by when it should be finished
     * @param estimatedHours - in how many hours you should approximately finish it
     * @param status - the state of the task not started/started/ended/approved/disapproved
     */
    public Task(String taskID,String requirementID, String labelName,String description,Date deadline,double estimatedHours, Status status){
        if(taskID!=null && !taskID.equals("") && requirementID!=null && !requirementID.equals("") && requirementID.length()==4 && labelName!=null && !labelName.equals("")
                && description!=null && !description.equals("") && deadline!=null && estimatedHours>=0 && status!=null){
            this.taskID=taskID;
            this.requirementID=requirementID;
            this.labelName=labelName;
            this.deadline=deadline.copy();
            this.description=description;
            this.estimatedHours=estimatedHours;
            team = new Team();
            this.status=status;
            hoursSpent=0;
            this.teamMembers="";
        }
        else
            throw new IllegalArgumentException("Bad input");}

    /**
     * get task identification number
     * @return taskID
     */
    public String getTaskID(){
        return taskID;
    }

    /**
     * get task name
     * @return labelName
     */
    public String getLabelName(){
        return labelName;
    }
    /**
     * get requirement identification number
     * @return requirementID
     */
    public String getRequirementID(){
        return requirementID;
    }

    /**
     * get task description/what should be done
     * @return description
     */
    public String getDescription(){
        return description;
    }

    /**
     * get in how many hours the task should be completed
     * @return estimatedHours
     */
    public double getEstimatedHours(){
        return estimatedHours;
    }
    /**
     * get how many hours are already spent on the task
     * @return hoursSpent
     */
    public double getTimeSpent(){
        return hoursSpent;
    }

    /**
     * Get by when the task should be completed
     * @return deadline
     */
    public Date getDeadline(){
        return deadline;
    }
    /**
     * Get task status
     * @return status
     */
    public Status getStatus(){
        return status;
    }
    public String getTeamMembers(){return teamMembers;
    }
    /**
     * change task identification number to new one
     * @param newID - new task identification number
     */
    public void setTaskID(String newID){
        if(taskID!=null && !taskID.equals("")){
            taskID=newID;
        }}

    /**
     * set new deadline for the task extend it/shorten it
     * @param newDeadline - new date when it should be completed
     */
    public void setDeadline(Date newDeadline){
        if(newDeadline!=null){
            deadline=newDeadline.copy();
        }}
    /**
     * Set task status to any state of the given choices
     * @param status
     */
    public void setStatus(Status status){
        if(status!=null){
            this.status=status;
        }
    }

    /**
     * new estimated hours, the task was changed or the time was reevaluated and you would need more hours to complete it
     * @param newHour - new time how many hours you technically would need
     */
    public void setEstimatedHours(double newHour){
        if(newHour>=0){
            estimatedHours=newHour;
        }}
    /**
     * change task name
     * @param newName - new task name
     */
    public void setLabelName(String newName){
        if(newName!=null && !newName.equals("")){
            labelName=newName;}
    }
    /**
     * set task to finished state
     */
    public void finishTask(){
        setStatus(Status.ENDED);
    }
    /**
     * set new task description, the task description is changed/
     * @param newDescription - new task description, change.
     */
    public void setDescription(String newDescription){
        if(newDescription!=null && !newDescription.equals("")){ description=newDescription;
        }}
    public void setTeamMembers(String teamMembers){
        this.teamMembers=teamMembers;
    }

    /**
     * add new team member working on the task
     * @param teamMember - new team member working on the project
     */
    public void assignTeamMember(TeamMember teamMember){
        if(teamMember!=null){
            team.addNewTeamMember(teamMember);
        }}

    /**
     * Remove team member from the task
     * @param teamMember - team member that gets removed
     */
    public void removeTeamMember(TeamMember teamMember){
        if(teamMember!=null){
            team.removeTeamMember(teamMember);
        }
    }

    /**
     * add more hours spent on the task
     * @param hoursSpent - hours spent today on the task or in the past few days
     */
    public void setHoursSpent(double hoursSpent){
        if(this.hoursSpent+hoursSpent>=0){
            this.hoursSpent+=hoursSpent;
        }
    }

    /**
     * Get a list of team members working on the task.
     * @return team
     */
    public Team getResponsibleTeamMembers(){
        return team;
    }
    /**
     * check if this task and the object given in the arguments are the same
     * @param obj
     * @return true or false(the same or not)
     */
    @Override public boolean equals(Object obj){
        if(!(obj instanceof Task)){
            return false;
        }
        Task other = (Task) obj;
        return (taskID.equals(other.getTaskID()) && requirementID.equals(other.getRequirementID()) && labelName.equals(other.getLabelName())
                && description.equals(other.getDescription()) && estimatedHours==other.getEstimatedHours() && deadline.equals(other.getDeadline())
                && hoursSpent==other.getTimeSpent());

    }

    @Override public String toString()
    {
        return "model.Task:" + "taskID='" + taskID + "', requirementID="
                + requirementID + "', labelName='" + labelName + "', description='"
                + description + "', estimatedHours=" + estimatedHours
                + "', deadline=" + deadline + "', hoursSpent=" + hoursSpent + "', status="
                + status+"'";
    }
}
