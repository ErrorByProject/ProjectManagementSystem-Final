package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ColourITModel;
import model.Team;
import model.TeamMember;
import model.TeamModel;

import java.util.ArrayList;

public class TeamViewModel
{
    private ObservableList<TeamMemberViewModel> list;
    private ColourITModel model;

    public TeamViewModel(ColourITModel model)
    {
        this.model = model;
        this.list = FXCollections.observableArrayList();
        update();
    }

    public void update()
    {
        list.clear();
        for (int i = 0; i < model.totalNumberOfTeamMembers(); i++)
        {
            list.add(new TeamMemberViewModel(model.getTeamMember(i)));
        }
    }

    public ObservableList<TeamMemberViewModel> getList()
    {
        return list;
    }

    /**
     * Removes based on ID as that should be unique
     * @param teamMemberId
     */
    public void remove(String teamMemberId)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getIdProperty().get()
                    .equals(teamMemberId))
            {
                list.remove(i);
                break;
            }
        }
    }

    public void add(TeamMember teamMember)
    {
        list.add(new TeamMemberViewModel(teamMember));
    }

}
