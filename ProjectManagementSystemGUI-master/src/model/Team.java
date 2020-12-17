package model;/*
 * Team class to store a list of Team Members
 *
 * @author Joseph Carroll
 * @author Nicolae Cernean(edited)
 * @version 1.1 2020-12-07
 * @version 1.11 2020-12-10 Rokas
 * version 1.11 notes
 * added toString method,
 * renamed removeTeamMember(String teamMemberID)  to removeTeamMemberByID(String teamMemberID)
 * Created new removeTeamMember(TeamMember teamMember) method
 * added protections to some methods
 * changed method getAllTeamMembers to return Team not the Array
 * changed getTeamMembers by name to return ArrayList, cause Array was throwing exceptions and was creating empty spots
 * changed getTeamMembers by experience to return ArrayList, cause Array was throwing exceptions and was creating empty spots
 * changed getTeamMembers by email to return one team member cause emails are unique
 * assign a task to a team member is redundant cause taskList is empty you can't find anything in it.
 */
import java.util.ArrayList;

public class Team
{

    private ArrayList<TeamMember> team;
    // private TaskList taskList;

    /**
     * Creating a list of team member
     */
    public Team()
    {
        this.team = new ArrayList<TeamMember>();
        //    this.teamMemberTaskList = new ArrayList<>();
        // this.taskList =
    }

    /**
     * Adds a new team member to a team
     *
     * @param teamMember - new team member to add to team
     */
    public void addNewTeamMember(TeamMember teamMember)
    {
        if(teamMember!=null){
            team.add(teamMember);}
    }

    /**
     * Removes a team member from a team given by id
     *
     * @param teamMemberID - team member to be removed
     */
    public void removeTeamMemberByID(String teamMemberID)
    {
        if(!teamMemberID.equals("") && teamMemberID!=null){
            team.remove(getTeamMembersByID(teamMemberID));}
    }
    /**
     * Gets a team member from the arrayList
     * @param index - location in the list
     * @return desired team member object
     */
    public TeamMember getTeamMember(int index)
    {
        return team.get(index);
    }

    /**
     * Removes a given team member by selected team member
     * @param teamMember - selected team member
     */
    public void removeTeamMember(TeamMember teamMember){
        if(teamMember!=null){
            for(int i=0;i<team.size();i++){
                if(team.get(i).equals(teamMember)){
                    team.remove(i);
                }
            }}
    }
    /**
     * Gets the number of team members in a team
     *
     * @return total number of team members
     */
    public int totalNumberOfTeamMembers()
    {
        return team.size();
    }

    /**
     * Gets a list of all the team members
     *
     * @return an array of team members
     */
    public ArrayList<TeamMember> getAllTeamMembers()
    {
        if (team.size() == 0){
            // throw new NullPointerException("No list available");
            return null;}
        else
            return team;
    }

    /**
     * Gets a team member with a specific ID
     *
     * @param teamMemberID - team members ID to be checked
     * @return a team member
     */
    public TeamMember getTeamMembersByID(String teamMemberID)
    {
        for (int i = 0; i <= team.size(); i++)
        {
            if (team.get(i).getTeamMemberID().equals(teamMemberID))
                return team.get(i);
        }
        throw new IllegalArgumentException("Invalid ID");
    }

    /**
     * Gets a list of team members by name
     *
     * @param name - name to be searched for
     * @return an arrayList of team members
     */
    public ArrayList<TeamMember> getTeamMemberByName(String name)
    {
        ArrayList<TeamMember> nameList = new ArrayList<TeamMember>();
        for (int i = 0; i < team.size(); i++)
        {
            if (team.get(i).getName().equals(name))
                nameList.add(team.get(i));
        }
        if (nameList.size() == 0){
            //throw new IndexOutOfBoundsException("No list available");} //if no list stops the program if you can't handle it
            return null;} // choice between them
        else{
            return nameList;}
    }

    /**
     * Gets a list of team members with specific number of years of experience
     *
     * @param yearsOfExperience - desired number of years of experience
     * @return an arrayList of team members
     */
    public ArrayList<TeamMember> getTeamMembersByExperience(int yearsOfExperience)
    {
        ArrayList<TeamMember> xpList = new ArrayList<TeamMember>();
        for (int i = 0; i < team.size(); i++)
        {
            if (team.get(i).getYearsOfExperience() == yearsOfExperience)
                xpList.add(team.get(i));
        }
        if (xpList.size() == 0){
            // throw new IndexOutOfBoundsException("No list available");} //if no list stops the program if you can't handle it
            return null;} // choice between them
        else{
            return xpList;}
    }

    /**
     * Gets a list of team members by email
     *
     * @param - email to be searched for
     * @return an array of team members
     */
    public TeamMember getTeamMembersByEmail(Email email)
    {
        for (int i = 0; i < team.size(); i++)
        {
            if (team.get(i).getEmail().equals(email))
                return team.get(i);
        }
        return null;
    }

    /**
     * Gets a list of team members by birthday
     *
     * @param date - birthday to be searched for
     * @return an array of team members
     */
    public ArrayList<TeamMember> getTeamMembersByBirthday(Date date)
    {
        ArrayList<TeamMember> birthdayList = new ArrayList<TeamMember>();
        for (int i = 0; i < team.size(); i++)
        {
            if (team.get(i).getBirthdate().equals(date))
                birthdayList.add(team.get(i));
        }
        if (birthdayList.size() == 0)
            //throw new NullPointerException("No list available"); stops the program
            return null;
        else
            return birthdayList;
    }

    /**
     * Returns the scrum master from team
     *
     * @return returns the scrum master
     */
    public TeamMember getScrumMaster()
    {
        for (int i = 0; i < team.size(); i++)

            if (team.get(i).getRole().equals(Role.SCRUM_MASTER))
            {
                return team.get(i);
            }
   /* throw new IllegalArgumentException(
        "Please assign a scrum master to this project");*/
        return null;
    }
    /**
     * Returns the product owner from team
     *
     * @return returns the product owner
     */
    public TeamMember getProductOwner()
    {
        for (int i = 0; i < team.size(); i++)

            if (team.get(i).getRole().equals(Role.PRODUCT_OWNER))
            {
                return team.get(i);
            }
        throw new IllegalArgumentException(
                "Please assign a product owner to this project");
    }

    /**
     * Assigns a task to a team member by his teamMemberID
     *
     * @param taskID the task ID
     * @param teamMemberID the team member ID
     */
    // public void assignTaskToATeamMember(String taskID, String teamMemberID)
    // {
    //   getTeamMembersByID(teamMemberID).addTaskToList(taskList.getTaskByID(taskID));
    // }

    /**
     * Returns all the tasks of a team member
     *
     * @param teamMemberID the team member ID
     */
    // public TaskList getTeamMemberTasks(String teamMemberID)
    // {
    //  return getTeamMembersByID(teamMemberID).getTeamMemberTaskList();
    // }
    /**
     * Prints out team members
     */
    @Override
    public String toString (){
        String text="";
        for (int i=0;i<team.size();i++){
            text+= team.get(i) + "\n";
        }
        return text;
    }
}
