package model;

import java.util.ArrayList;

/**
 * @author Nicolae Cernean
 * @version 1.001 Nicolae
 * @version 1.002 Rokas
 * v1.002 notes
 * deleted instance variable model.Project model.Project ID not used.
 * remade method Requirements sorted by orderNum
 * added toString method
 */
public class RequirementList
{
    // private model.Project projectID;
    private ArrayList<Requirement> requirements;

    public RequirementList()
    {
        this.requirements = new ArrayList<>();
    }

    public void addRequirement(Requirement requirement)
    {
        requirements.add(requirement);
    }

    public void removeRequirement(String requirementID)
    {
        requirements.remove(getRequirementByID(requirementID));
    }

    public Requirement getRequirementByID(String requirementID)
    {
        Requirement requirement = null;
        for (int i = 0; i < requirements.size(); i++)
            if (requirements.get(i).getRequirementID().equals(requirementID))
            {
                requirement = requirements.get(i);
            }
        return requirement;
        //throw new IllegalArgumentException("Invalid ID");
    }

    public void editDeadLineOfARequirement(String requirementID, Date newDeadline)
    {
        getRequirementByID(requirementID).setDeadline(newDeadline);
    }

    public void editEstimatedHoursOfARequirement(String requirementID,
                                                 double estimatedHours)
    {
        getRequirementByID(requirementID).setEstimatedHours(estimatedHours);
    }

    public void editDescriptionOfARequirement(String requirementID,
                                              UserStory newDescription)
    {
        getRequirementByID(requirementID).setDescription(newDescription);
    }

    public Status getRequirementStatus(String requirementID)
    {
        return getRequirementByID(requirementID).getStatus();
    }

    public void assignRequirementOrder(String requirementID, int orderNum)
    {
        getRequirementByID(requirementID).setOrderNum(orderNum);
    }

    public int getRequirementsListTotalHoursOfWork()
    {
        int hours = 0;
        for (int i = 0; i < requirements.size(); i++)
        {
            hours += requirements.get(i).getSpentHours();
        }
        return hours;
    }

    public int getRequirementListSize(){return requirements.size();}

    public ArrayList<Requirement> getFinishedRequirements()
    {
        ArrayList<Requirement> finished = new ArrayList<>();
        for (int i = 0; i < requirements.size(); i++)
        {
            if (requirements.get(i).getStatus().equals(Status.ENDED))
            {
                finished.add(requirements.get(i));
            }
        }
        return finished;
    }

    public ArrayList<Requirement> getActiveRequirements()
    {
        ArrayList<Requirement> started = new ArrayList<>();
        for (int i = 0; i < requirements.size(); i++)
        {
            if (requirements.get(i).getStatus().equals(Status.STARTED))
            {
                started.add(requirements.get(i));
            }
        }
        return started;
    }
    public Requirement getRequirementByIndex(int orderNum){
        if(orderNum>=0){
            return requirements.get(orderNum);
        }
        else return null;
    }
    public Requirement[] getAllRequirements()
    {
        Requirement[] requirementsarr = new Requirement[requirements.size()];
        for (int i = 0; i < requirements.size(); i++)
        {
            requirementsarr[i] = requirements.get(i);
        }
        return requirementsarr;
    }

    public Requirement getRequirement(Requirement requirement){
        for(Requirement i: requirements){
            if(i.equals(requirement)){
                return i;
            }
        }
        return null;
    }
    // public boolean isOrderNumUsed(int orderNum)
    // {
    //   for (int i = 0; i < requirements.size(); i++)
    //   {
    //     if (requirements.get(i).getOrderNum() == orderNum)
    //     {
    //       return true;
    //     }
    //   }
    //   return false;
    // }

    /*Focking fabulous */
    public RequirementList getRequirementsSortedByOrderNum()
    {
        Requirement temp = null;
        Requirement[] requirementsarr = new Requirement[requirements.size()];
        RequirementList other = new RequirementList();
        for(int i=0;i<requirements.size();i++){
            requirementsarr[i]=requirements.get(i);
        }
        for (int i = 0; i < requirementsarr.length - 1; i++)
        {
            for(int j=i+1;j<requirementsarr.length;j++){
                if(requirementsarr[i].getOrderNum()>requirementsarr[j].getOrderNum()){
                    temp=requirementsarr[j];
                    requirementsarr[j]=requirementsarr[i];
                    requirementsarr[i]=temp;
                }
            }
        }
        for(int i=0;i<requirementsarr.length;i++){
            other.addRequirement(requirementsarr[i]);
        }
        return other;
    }
    @Override
    public String toString (){
        String text="";
        for (int i=0;i<requirements.size();i++){
            text+= requirements.get(i) + "\n";
        }
        return text;
    }
}
