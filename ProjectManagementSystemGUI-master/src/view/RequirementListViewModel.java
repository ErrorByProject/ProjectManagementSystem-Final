package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ColourITModel;
import model.Requirement;

/**
 * @author group 3
 * @version 1.0
 */

public class RequirementListViewModel {
    private ObservableList<RequirementViewModel> list;
    private ColourITModel model;
    private ViewState viewState;

    /**
     * A 2 argument constructor creating a requirement list view model
     * @param model this model
     * @param viewState this state
     */
    public RequirementListViewModel(ColourITModel model,ViewState viewState){
        this.model = model;
        this.list = FXCollections.observableArrayList();
        this.viewState=viewState;
        update();
    }

    /**
     * A update method updating the tex fields
     */
    public void update(){
        list.clear();
        for (int i = 0; i < model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementListSize(); i++)
        {
            list.add(new RequirementViewModel(model.getProjectByID(viewState.getSelectedProject()).getRequirements().getRequirementByIndex(i)));
        }
    }

    /**
     * A method getting the requirement view model list
     * @return the list
     */
    public ObservableList<RequirementViewModel> getList() {return list;}

    /**
     * A method removing the requirement
     * @param requirement the requirement
     */
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

    /**
     * A method adding a requirement
     * @param requirement the requirement
     */
    public void add(Requirement requirement) {list.add(new RequirementViewModel(requirement));}










}
