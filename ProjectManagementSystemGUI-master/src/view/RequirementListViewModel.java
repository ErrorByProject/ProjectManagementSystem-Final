package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ColourITModel;
import model.Requirement;
import model.RequirementListModel;

public class RequirementListViewModel {
    private ObservableList<RequirementViewModel> list;
    private ColourITModel model;

    public RequirementListViewModel(ColourITModel model){
        this.model = model;
        this.list = FXCollections.observableArrayList();
        update();
    }

    public void update(){
        list.clear();
        for (int i = 0; i < model.getRequirementListSize(); i++)
        {
            list.add(new RequirementViewModel(model.getRequirementByIndex(i)));
        }
    }

    public ObservableList<RequirementViewModel> getList() {return list;}

    public void remove(Requirement requirement){
        for(int i = 0; i< list.size(); i++){
            if(list.get(i).getProjectID().get().equals(requirement.getProjectID()) && list.get(i).getRequirementID().get().equals(requirement.getRequirementID())&&
                    list.get(i).getName().get().equals(requirement.getName()) && list.get(i).getUserStoryObjectProperty().get().equals(requirement.getDescription()) &&
                    list.get(i).getEstimatedHours().get() == requirement.getEstimatedHours() && list.get(i).getDeadlineObjectProperty().get().equals(requirement.getDeadline()) &&
                    list.get(i).getOrderNum().get() == requirement.getOrderNum()){
                list.remove(i);
                break;
            }
        }
    }

    public void add(Requirement requirement) {list.add(new RequirementViewModel(requirement));}










}
