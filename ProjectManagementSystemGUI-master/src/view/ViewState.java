package view;

public class ViewState {
    private String selectedProject;
    private String selectedRequirement;
    private String selectedTask;
    public ViewState(){
        selectedProject="";
        selectedRequirement="";
        selectedTask="";
    }
    public String getSelectedProject(){
        return selectedProject;
    }
    public String getSelectedRequirement(){
        return selectedRequirement;
    }
    public String getSelectedTask(){
        return selectedTask;
    }
    public void setSelectedProject(String id){
        selectedProject=id;
    }
    public void setSelectedRequirement(String id){
        selectedRequirement=id;
    }
    public void setSelectedTask(String id){
        selectedTask=id;
    }
}
