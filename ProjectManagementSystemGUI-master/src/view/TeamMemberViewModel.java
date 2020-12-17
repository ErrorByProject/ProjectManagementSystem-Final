package view;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import model.Date;
import model.Email;
import model.TeamMember;

import java.util.List;

public class TeamMemberViewModel
{
    private StringProperty nameProperty;
    private StringProperty idProperty;


    public TeamMemberViewModel(TeamMember teamMember)
    {
        nameProperty = new SimpleStringProperty(teamMember.getName());
        idProperty = new SimpleStringProperty(teamMember.getTeamMemberID());
    }

    public StringProperty getNameProperty()
    {
        return nameProperty;
    }

    public StringProperty getIdProperty()
    {
        return idProperty;
    }


}
