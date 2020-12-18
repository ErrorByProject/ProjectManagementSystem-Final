package view;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import model.Date;
import model.Email;
import model.TeamMember;

import java.util.List;

/**
 * @author group 3
 * @version 1.0
 */

public class TeamMemberViewModel
{
    private StringProperty nameProperty;
    private StringProperty idProperty;


    /**
     * A 1 argument constructor creating a team member view model
     * @param teamMember the team member
     */
    public TeamMemberViewModel(TeamMember teamMember)
    {
        nameProperty = new SimpleStringProperty(teamMember.getName());
        idProperty = new SimpleStringProperty(teamMember.getTeamMemberID());
    }

    /**
     * A method returning the name
     * @return name
     */
    public StringProperty getNameProperty()
    {
        return nameProperty;
    }

    /**
     * A method returning the id property
     * @return id
     */
    public StringProperty getIdProperty()
    {
        return idProperty;
    }


}
