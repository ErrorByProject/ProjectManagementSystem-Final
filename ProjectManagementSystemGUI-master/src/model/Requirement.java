package model;

import javafx.beans.property.ObjectProperty;

/**
 * A class representing a model.Requirement
 * @author Nichita Railean
 * @version 1.0 3dec
 * removed arraylist in arraylist task, and task list array
 * removed model.Status
 * removed add tasklist
 * removed some illegal exeptions
 * DON'T FACKING TOUCH IT
 */

public class Requirement
{

    private String projectID;
    private String name;
    private double estimatedHours;
    private Date deadline;
    private int orderNum;
    private String requirementID;
    private UserStory description;
    private Team team;
    private TaskList taskList;
    private String teamMembers;




    /**
     * A 7 argument constructor creating a model.Requirement
     * @param projectID project id
     * @param requirementID requirement id
     * @param name requirement name
     * @param description user story description
     * @param estimatedHours estimated hours for the requirement
     * @param deadline requirement deadline
     * @param orderNum index of requirement
     */
    public Requirement(String projectID, String requirementID, String name, UserStory description, double estimatedHours, Date deadline, int orderNum){
        if(projectID != null && !(projectID.equals("")) && requirementID != null && !(requirementID.equals("")) && requirementID.length()==4  && name != null && !(name.equals("")) && description != null && estimatedHours >= 0 && deadline != null && orderNum >=0){
            this.projectID = projectID;
            this.requirementID = requirementID;
            this.name = name;
            this.description = description;
            this.estimatedHours = estimatedHours;
            this.deadline = deadline;
            this.orderNum = orderNum;
            this.teamMembers = "";
            team = new Team();
            taskList = new TaskList();

        }else{
            throw new IllegalArgumentException("Wrong Input");
        }

    }

    /**
     * A method getting the name or the requirement
     * @return name of the requirement
     */
    public String getName(){return name;}

    /**
     * A method getting the project ID
     * @return the project id
     */
    public String getProjectID(){return projectID;}

    /**
     * A method returning team member sting for the table
     * @return teamMembers
     */
    public String getTeamMembers(){return teamMembers;}

    /**
     * A method getting the orderNumber
     * @return the order number
     */
    public int getOrderNum(){return orderNum;}

    /**
     * A method getting the Estimated hours for the model.Requirement
     * @return estimated hours of the requirement
     */
    public double getEstimatedHours(){return estimatedHours;}

    /**
     * A method setting the name of the requirement
     * @param newName the new name
     */
    public void setName(String newName){
        if (newName != null && !(newName.equals("")))
        {
            this.name = newName;
        }
    }

    /**
     * A method setting the team members for the tables
     * @param teamMembers the new team members
     */
    public void setTeamMembers(String teamMembers){this.teamMembers = teamMembers;}

    /**
     * A method setting the estimated hours of the requirement;
     * @param newHours the new hours
     */
    public void setEstimatedHours(double newHours){
        if(newHours >= 0){
            this.estimatedHours = newHours;
        }
    }

    /**
     * A method setting the description of the requirement
     * @param newDescription the new description
     */
    public void setDescription(UserStory newDescription){
        if(newDescription != null){
            this.description = newDescription;
        }
    }

    /**
     * A method returning the requirement ID
     *
     * @return the requirement ID
     */
    public String getRequirementID(){return requirementID;}

    /**
     * A method getting the description
     * @return description of the requirement
     */
    public UserStory getDescription(){return description;}

    /**
     * A method adding a task to requirement
     *
     * task  added to the requirement
     */
    public void addTask (String taskID, String requirementID, String labelName, String description, Date deadline, double estimatedHours, Status status){
        taskList.createTask(taskID, requirementID, labelName, description, deadline, estimatedHours, status);
    }

    /**
     * A method getting the Tasklist
     * @return task list
     */
    public TaskList getTasks(){return taskList;}

    /**
     * A method assiging a team member
     * @param teamMember the member being assigned
     */
    public void assignTeamMember (TeamMember teamMember){
        team.addNewTeamMember(teamMember);
    }

    /**
     * A method getting an array with responsible team members
     * @return array with responsible team members
     */
    public Team GetResponsibleTeam(){
        return team;
    }


    /**
     * A method setting the dead line for the requirement
     * @param newDeadline the new deadline of the requirement
     */
    public void setDeadline(Date newDeadline){
        if(newDeadline != null){
            this.deadline = newDeadline;
        }
    }

    /**
     * A method setting the order number
     * @param orderNum the new order num of the requirement
     */
    public void setOrderNum(int orderNum){
        if(orderNum >= 0){
            this.orderNum = orderNum;
        }
    }

    /**
     * A method setting the requirement ID
     * @param newID the new requirement ID
     */
    public void setRequirementID(String newID){
        if(newID != null && !(newID.equals("")) && newID.length() == 5){
            requirementID = newID;
        }
    }

    /**
     * A method setting the project ID
     * @param newID the new Project ID
     */
    public void setProjectID(String newID){
        if(newID != null && !(newID.equals(""))){
            projectID = newID;
        }
    }

    /**
     * A method getting the status of the requirement
     * @return the status of the requirement
     */
    public Status getStatus(){
        if(taskList.getActiveTasks().size()==0){
            return Status.ENDED;
        } else {
            return Status.STARTED;
        }

    }

    /**
     * A method getting the deadline for the requirement
     * @return the deadline of the requirement
     */
    public Date getDeadline(){return deadline;}

    /**
     * A method getting the hours spent on the requirement
     * @return the hours spent on the requirement
     */
    public int getSpentHours(){
        return taskList.getHoursSpent();
    }


    /**
     * A equals method
     * @param obj a dummy oject
     * @return if it equals
     */
    @Override public boolean equals(Object obj){
        if(!(obj instanceof Requirement)){return false;}
        Requirement other = (Requirement) obj;
        return (projectID.equals(other.projectID) && requirementID.equals(other.requirementID) && name.equals(other.name) &&
                description.equals(other.description) && estimatedHours == other.estimatedHours && deadline.equals(other.deadline) &&
                orderNum == other.orderNum);
    }

    @Override public String toString()
    {
        return "model.Requirement{" + "projectID='" + projectID + "', name='"
                + name + "', estimatedHours=" + estimatedHours + ", deadline="
                + deadline + ", orderNum=" + orderNum + ", requirementID='"
                + requirementID  + "', description=" + description + ", team="
                + team + "', taskList=" + taskList + '}';
    }



}
