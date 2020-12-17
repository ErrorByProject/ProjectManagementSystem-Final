package model;

public class TaskListModelManager implements TaskListModel {
    private TaskList taskList;

    public TaskListModelManager(){
        this.taskList= new TaskList();
        createDummyData();
    }
    private void createDummyData(){
        try{
            String[] strings = {"ASDA","TASK","REQU","ADAS","1234","4321","5555","6666"};
            for(int i=0;i<4;i++){
                int randomIndex1=(int) (Math.random()*7);
                int randomIndex2=(int) (Math.random()*7);
                int randomIndex3=(int) (Math.random()*7);
                int randomIndex4=(int) (Math.random()*7);
                int randomIndex5=(int) (Math.random()*7);
                taskList.addTask(new Task(strings[randomIndex1],strings[randomIndex2],strings[randomIndex3],strings[randomIndex4],new Date(),randomIndex5,Status.STARTED));

            }
        }catch (Exception e){
            //ok
        }
    }
    @Override
    public void addTask(Task task) {
        taskList.addTask(task);
    }

    @Override
    public void removeTask(String ID) {
        taskList.removeTaskByID(ID);
    }

    @Override
    public void removeTask(Task task) {
        taskList.removeTask(task);
    }

    @Override
    public String getTaskID(Task task) {
//        for(int i=0;i<taskListSize();i++){
//            if(taskList.getTaskByIndex(i).equals(task)){
//                return taskList.getTaskByIndex(i).getTaskID();
//            }
//        }
        return task.getTaskID();
    }

    @Override
    public int taskListSize() {
        return taskList.getSize();
    }

    @Override
    public Task getTask(int index) {
        return taskList.getTaskByIndex(index);
    }

    @Override
    public String getRequirementIDOfTheTask(Task task) {
        return task.getRequirementID();
    }

    @Override
    public String getLabelNameOfTheTask(Task task) {
        return task.getLabelName();
    }

    @Override
    public String getDescriptionOfTheTask(Task task) {
        return task.getDescription();
    }

    @Override
    public int getTeamMembersOfTheTask(Task task) {
        return task.getResponsibleTeamMembers().getAllTeamMembers().size();
    }

    @Override
    public Date getDeadlineOfTheTask(Task task) {
        return task.getDeadline();
    }

    @Override
    public void setDeadline(Task task,int day, int month, int year) {
        task.setDeadline(new Date(day,month,year));
    }

    @Override
    public double getSpentHoursOfTheTask(Task task) {
        return task.getTimeSpent();
    }

    @Override
    public double getEstimatedHoursOfTheTask(Task task) {
        return task.getEstimatedHours();
    }
}
