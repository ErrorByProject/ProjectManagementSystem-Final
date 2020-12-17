package model;

/**
 * TeamMember class to store data about Team Members
 *
 * @author Rokas Paulauskas
 * @author Nicolae Cernean(edited)
 * @version 1.001 2020-12-03 Rokas
 * @version 1.002 2020-12-07 Nicolae
 * @version 1.003 2020-12-10 Rokas
 * version 1.003 notes
 * constructor role setter from version 2 was always setting Team_Member role and was not taking the input from the constructor fixed
 * deleted teamMemberTaskList added Team class, changed methods add tasks/remove tasks
 * v1.002 reverse(fix) changed the team member constructor to not use single setmethods, cause it can create empty spaces etc
 * added protections to setters which were probably deleted in v1.002
 * took out exceptions will add them later no way to handle them now
 */
public class TeamMember
{
    private String name;
    private String teamMemberID;
    private int yearsOfExperience;
    private Email email;
    private Date birthdate;
    private Role role;
    private TaskList taskList;

    /**
     * Creating a team member to the files
     *
     * @param name              - name of the team member
     * @param yearsOfExperience - how many years of experience team member has in the software developer field
     * @param email             - team members email
     * @param birthDate         - the birth date of the team member
     * @param role              - the role of a team member
     */
    public TeamMember(String name, String teamMemberID, Email email,
                      int yearsOfExperience, Date birthDate, Role role)
    {
        if (name != null && !name.equals("") && teamMemberID != null
                && !teamMemberID.equals("") && teamMemberID.length()==4 && isEmailValid(email) && yearsOfExperience >= 0 && isEmailValid(email)
                && birthDate != null && role!=null)
        {
            this.name=name;
            this.teamMemberID=teamMemberID;
            this.yearsOfExperience=yearsOfExperience;
            this.email=email;
            this.birthdate = birthDate.copy();
            this.role=role;
            taskList = new TaskList();
        }else{
            throw new IllegalArgumentException("Wrong input");
        }
    }

    /**
     * Returns employees name
     *
     * @return employees name
     */
    public String getName()
    {
        return name;
    } /* tested*/

    /**
     * Sets a new team member name
     *
     * @param name -edit name of a team member
     */
    public void setName(String name)
    {
        if(name!=null && !name.equals("")){
            this.name = name;}
    } /* tested*/

    /**
     * Returns team member ID
     *
     * @return teamMemberID
     */
    public String getTeamMemberID()
    {
        return teamMemberID;
    } /* tested*/

    /**
     * Sets a new ID of a team member
     *
     * @param teamMemberID -edit ID of a team member
     */
    public void setTeamMemberID(String teamMemberID)  /* tested*/
    {
        if (teamMemberID.length() == 4)
        {
            this.teamMemberID = teamMemberID;
        }
  /*  else
    {
      throw new IllegalArgumentException("The team member ID must contain 4 characters");
    } */ }

    /**
     * Gets an email of the employee
     *
     * @return returns email object
     */
    public Email getEmail()
    {
        return email;
    }  /* tested*/

    /**
     * Sets/edits a new email of a team member
     *
     * @param newEmail -change  the email of a team member
     */
    public void setEmail(Email newEmail)  /* tested*/
    {
        if (isEmailValid(newEmail))
            this.email = newEmail;
   /* else
      throw new IllegalArgumentException("Illegal email input, try again");*/
    }

    /**
     * Gets a role for the team member
     *
     * @return returns the role of a team member
     */

    public Role getRole()
    {
        return role;
    }  /* tested*/

    /**
     * Sets a role for a team member
     *
     * @param role set a new role for a team member
     */

    public void setRole(Role role)
    {
        if(role!=null){
            this.role = role;}
    }  /* tested*/

    /**
     * Returns how long the employee has worked in this field
     *
     * @return years of experience in software developer field
     */
    public int getYearsOfExperience()
    {
        return yearsOfExperience;
    }  /* tested*/

    /**
     * Updates/Sets a new year of a team member worked in this field
     *
     * @param newYears -sets/updates years of experience
     */
    public void setYearsOfExperience(int newYears)  /* tested*/
    {
        if(newYears>=0){
            this.yearsOfExperience = newYears;}
    }

    /**
     * Returns the birth date of the employee
     *
     * @return employees birth date
     */
    public Date getBirthdate()
    {
        return birthdate.copy();
    }  /* tested*/

    /**
     * Checks if the given email is valid
     *
     * @param email - email you give to check if it is valid
     * @return true or false which equals to valid or not valid
     */
    public boolean isEmailValid(Email email)  /* tested*/
    {
        if (email.toString().equals("Wrong format"))
        {
            return false;
        }
        return true;
    }

    /**
     * Compares employees checks if it is the same employee if it
     * is another object automatically says that they are not the same
     *
     * @param obj any object
     * @return same object or not
     */
    @Override public boolean equals(Object obj)  /* tested*/
    {
        if (!(obj instanceof TeamMember))
        {
            return false;
        }
        TeamMember other = (TeamMember) obj;
        return name.equals(other.getName()) && yearsOfExperience == other
                .getYearsOfExperience() && email.equals(other.getEmail()) && birthdate
                .equals(other.getBirthdate());
    }
    /**
     * Add a task to a team member task list
     *
     * @param  task-is added to team members task list
     */
    public void addTaskToList(Task task)
    {
        if(task!=null){
            taskList.addTask(task);}
        else{
            throw new IllegalArgumentException("Task is empty");
        }
    }
    /**
     * Returns all the information about a team member
     *
     * @return all tasks of a team member
     */
    public TaskList getTeamMemberTaskList()
    {
        return taskList;
    }

    /**
     * Returns all the information about a team member
     *
     * @return all information about a team member
     */
    @Override public String toString()  /* tested*/
    {
        return "TeamMember:" + "name='" + name + "', teamMemberID='"
                + teamMemberID + "', yearsOfExperience=" + yearsOfExperience
                + "', email='" + email + "', birthdate='" + birthdate + "', role='" + role + "'";
    }
}
