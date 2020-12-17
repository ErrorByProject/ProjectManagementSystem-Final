package model;

import java.util.ArrayList;

public interface RequirementListModel {

    public void addRequirement(Requirement requirement);

    public void removeRequirement(String requirementID);

    public Requirement getRequirementByID(String requirementID);

    public void editDeadLineOfARequirement(String requirementID, Date newDeadline);

    public void editEstimatedHoursOfARequirement(String requirementID, double estimatedHours);

    public void editDescriptionOfARequirement(String requirementID, UserStory newDescription);

    public Status getRequirementStatus(String requirementID);

    public void assignRequirementOrder(String requirementID, int orderNum);

    public int getRequirementsListTotalHoursOfWork();

    public ArrayList<Requirement> getFinishedRequirements();

    public ArrayList<Requirement> getActiveRequirements();

    public Requirement getRequirementByIndex(int orderNum);

    public Requirement[] getAllRequirements();

    public int getRequirementListSize();

    public RequirementList getRequirementsSortedByOrderNum();

    public String toString ();



}
