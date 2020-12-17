package model;

import java.util.ArrayList;

public interface TeamModel
{

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
}
