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


    /**
     * A 0 argument constructor initializing the requirements array
     */
    public RequirementList()
    {
        this.requirements = new ArrayList<>();
    }


    /**
     * A method adding a requirement to the requirement array
     * @param requirement the requirement
     */
    public void addRequirement(Requirement requirement)
    {
        requirements.add(requirement);
    }


    /**
     * A method removing the requirement from the array
     * @param requirementID the requirement id
     */
    public void removeRequirement(String requirementID)
    {
        requirements.remove(getRequirementByID(requirementID));
    }

    /**
     * A method getting the requirement by ID
     * @param requirementID the requirement Id
     * @return the requirement
     */
    public Requirement getRequirementByID(String requirementID)
    {
        Requirement requirement = null;
        for (int i = 0; i < requirements.size(); i++)
            if (requirements.get(i).getRequirementID().equals(requirementID))
            {
                requirement = requirements.get(i);
            }
        return requirement;
    }

    /**
     * A
     * @param requirementID
     * @param newDeadline
     */
    public void editDeadLineOfARequirement(String requirementID, Date newDeadline)
    {
        getRequirementByID(requirementID).setDeadline(newDeadline);
    }

    /**
     * A method editing the estimated hours spent on a requirement
     * @param requirementID the requirement id
     * @param estimatedHours the estimated hours
     */
    public void editEstimatedHoursOfARequirement(String requirementID,
                                                 double estimatedHours)
    {
        getRequirementByID(requirementID).setEstimatedHours(estimatedHours);
    }

    /**
     * A method editing the description of a requirement
     * @param requirementID the requirement id
     * @param newDescription the new description
     */
    public void editDescriptionOfARequirement(String requirementID,
                                              UserStory newDescription)
    {
        getRequirementByID(requirementID).setDescription(newDescription);
    }

    /**
     * A method that gets the requirement status
     * @param requirementID the requirement id
     * @return the status
     */
    public Status getRequirementStatus(String requirementID)
    {
        return getRequirementByID(requirementID).getStatus();
    }

    /**
     * A method assigning the Requirement Order
     * @param requirementID the requirement ID
     * @param orderNum the order num
     */
    public void assignRequirementOrder(String requirementID, int orderNum)
    {
        getRequirementByID(requirementID).setOrderNum(orderNum);
    }


    /**
     * A method getting total hours worked on tasks
     * @return the hours worked
     */
    public int getRequirementsListTotalHoursOfWork()
    {
        int hours = 0;
        for (int i = 0; i < requirements.size(); i++)
        {
            hours += requirements.get(i).getSpentHours();
        }
        return hours;
    }


    /**
     * A method getting the Requirement list size
     * @return the size
     */
    public int getRequirementListSize(){return requirements.size();}

    /**
     * A method returning and arraylist with finished requirements
     * @return the array list
     */
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

    /**
     * A method returning and arraylist with active requirements
     * @return the array list
     */
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

    /**
     * A method getting a requirement by index
     * @param orderNum the index
     * @return the requirement
     */
    public Requirement getRequirementByIndex(int orderNum){
        if(orderNum>=0){
            return requirements.get(orderNum);
        }
        else return null;
    }

    /**
     * A method returning and array with all requirements
     * @return the array
     */
    public Requirement[] getAllRequirements()
    {
        Requirement[] requirementsarr = new Requirement[requirements.size()];
        for (int i = 0; i < requirements.size(); i++)
        {
            requirementsarr[i] = requirements.get(i);
        }
        return requirementsarr;
    }

    /**
     * A method getting the requirement
     * @param requirement the requirement
     * @return requirement
     */
    public Requirement getRequirement(Requirement requirement){
        for(Requirement i: requirements){
            if(i.equals(requirement)){
                return i;
            }
        }
        return null;
    }


    /**
     * A method sorting requirements by order number
     * @return the sorted list
     */
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
    /*
    A method converting the requirement list into string
     */
    @Override
    public String toString (){
        String text="";
        for (int i=0;i<requirements.size();i++){
            text+= requirements.get(i) + "\n";
        }
        return text;
    }
}
