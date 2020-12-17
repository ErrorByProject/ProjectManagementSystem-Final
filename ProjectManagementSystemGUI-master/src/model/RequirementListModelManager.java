package model;

import java.util.ArrayList;

public class RequirementListModelManager implements RequirementListModel{
    private RequirementList requirementList;


    public RequirementListModelManager(){
        this.requirementList = new RequirementList();
    }

    @Override public void addRequirement(Requirement requirement){ requirementList.addRequirement(requirement);}

    @Override public void removeRequirement(String requirementID){ requirementList.removeRequirement(requirementID);}

    @Override public Requirement getRequirementByID(String requirementID){return requirementList.getRequirementByID(requirementID);}

    @Override public void editDeadLineOfARequirement(String requirementID, Date newDeadline){requirementList.editDeadLineOfARequirement(requirementID,newDeadline);}

    @Override public void editEstimatedHoursOfARequirement(String requirementID, double estimatedHours){requirementList.editEstimatedHoursOfARequirement(requirementID,estimatedHours);}

    @Override public void editDescriptionOfARequirement(String requirementID, UserStory newDescription){requirementList.editDescriptionOfARequirement(requirementID,newDescription);}

    @Override public Status getRequirementStatus(String requirementID){return requirementList.getRequirementStatus(requirementID);}

    @Override public void assignRequirementOrder(String requirementID, int orderNum){requirementList.assignRequirementOrder(requirementID,orderNum);}

    @Override public int getRequirementsListTotalHoursOfWork(){return requirementList.getRequirementsListTotalHoursOfWork();}

    @Override public ArrayList<Requirement> getFinishedRequirements(){return requirementList.getFinishedRequirements();}

    @Override public ArrayList<Requirement> getActiveRequirements(){return requirementList.getActiveRequirements();}

    @Override public Requirement getRequirementByIndex(int orderNum){return requirementList.getRequirementByIndex(orderNum);}

    @Override public Requirement[] getAllRequirements(){return requirementList.getAllRequirements();}

    @Override public RequirementList getRequirementsSortedByOrderNum(){return requirementList.getRequirementsSortedByOrderNum();}

    @Override public String toString (){return requirementList.toString();}

    @Override public int getRequirementListSize(){return requirementList.getRequirementListSize();}


}
