package model;

import java.util.ArrayList;

public class TeamModelManager implements TeamModel
{
    private Team team;

    public TeamModelManager()
    {
        this.team = new Team();
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



}
