package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ColourITModel;
import model.TeamMember;

/**
 * @author group 3
 * @version 1.0
 */

public class TeamViewModel
{
    private ObservableList<TeamMemberViewModel> list;
    private ColourITModel model;

    /**
     * A one argument constructor creating a team view model
     * @param model the model
     */
    public TeamViewModel(ColourITModel model)
    {
        this.model = model;
        this.list = FXCollections.observableArrayList();
        update();
    }

    /**
     * A update  method updating the tex fields
     */
    public void update()
    {
        list.clear();
        for (int i = 0; i < model.totalNumberOfTeamMembers(); i++)
        {
            list.add(new TeamMemberViewModel(model.getTeamMember(i)));
        }
    }

    /**
     * A method getting the team member list
     * @return list
     */
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

    /**
     * A method adding a team member
     * @param teamMember
     */
    public void add(TeamMember teamMember)
    {
        list.add(new TeamMemberViewModel(teamMember));
    }

}
