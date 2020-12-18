package view;
/**
 * @author group 3
 * @version 1.0
 */
public class ViewState {


    private String selectedProject;
    private String selectedRequirement;
    private String selectedTask;

    /**
     * A 0 argument constructor for the window
     */
    public ViewState(){
        selectedProject="";
        selectedRequirement="";
        selectedTask="";
    }

    /**
     * A method returning selected project
     * @return project
     */
    public String getSelectedProject(){
        return selectedProject;
    }

    /**
     * A method returning selected requirement
     * @return selected requirement
     */
    public String getSelectedRequirement(){
        return selectedRequirement;
    }

    /**
     * A method returning selected task
     * @return selected task
     */
    public String getSelectedTask(){
        return selectedTask;
    }

    /**
     * A method returning selected project
     * @param id
     */
    public void setSelectedProject(String id){
        selectedProject=id;
    }

    /**
     * A method getting selected requirement
     * @param id the id
     */
    public void setSelectedRequirement(String id){
        selectedRequirement=id;
    }

    /**
     * A method getting the selected task
     * @param id the id
     */
    public void setSelectedTask(String id){
        selectedTask=id;
    }
}
