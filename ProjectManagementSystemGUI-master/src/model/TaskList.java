package model;

import java.util.ArrayList;

/**
 * A class representing a model.Task list
 * @author Nichita Railean
 * @version 1.0 3dec Nichita
 * @version 1.1 10dec Nichita
 *
 * Changed getTaskBy Id bc it was comparing object with number
 * Changed add task, bc u can't create a task outside a task list
 * Removed 2 illegal exeptions that don't work
 * DON'T FACKING TOUCH IT
 * @version 1.11 2020-12-11 Rokas
 * changed name from addTask to createTask
 * added protection to createTask
 * new method addTask
 * added taskList get size
 * added toString method
 * added removeTask(Task task) method
 */

public class TaskList
{
    private ArrayList<Task> tasks;

    /**
     * A 0 argument constructor initializing the model.Task array
     */

    public TaskList (){
        tasks = new ArrayList<Task>();
    }

    /**
     * A method returning a task by index
     *
     * @param index index of a task
     * @return the task by index
     */
    public Task getTaskByIndex(int index){
        return tasks.get(index);
    }
    public int getSize(){
        return tasks.size();
    }
    /**
     * A method to ad tasks to the task array, after creating a task
     * @param taskID the task id
     * @param requirementID the requirement id
     * @param labelName the label name
     * @param description the task description
     * @param deadline the task deadline
     * @param estimatedHours the estimated hours for the task
     */
    public void createTask(String taskID, String requirementID, String labelName, String description, Date deadline, double estimatedHours, Status status){
        if(taskID!=null && !taskID.equals("") && requirementID!=null && !requirementID.equals("") && labelName!=null && !labelName.equals("")
                && description!=null && !description.equals("") && deadline!=null && estimatedHours>=0 && status!=null){
            Task other = new Task(taskID,requirementID,labelName,description,deadline,estimatedHours,status);
            tasks.add(other);             }
    }
    public void addTask(Task task){
        if(task!=null){
            tasks.add(task);
        }
    }
    /**
     * A method returning an array with all requiremnt tasks
     * @param requirementID the requirement id
     * @return the array
     */
    public Task[] getAllRequirementTasks(String requirementID){
        Task[] taskarray = new Task[tasks.size()];
        for (int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getRequirementID().equals(requirementID)){
                taskarray [i] = tasks.get(i);
            }
        }
        return taskarray;
    }

    /**
     * A method setting the deadline of a task by id
     * @param taskID the id of a task
     * @param newdeadline the new deadline
     */
    public void setDeadlineOfATask(String taskID, Date newdeadline){
        if(taskID != null && !(taskID.equals("") && newdeadline != null )){
            for(Task i : tasks){
                if (i.getTaskID().equals(taskID)){
                    i.setDeadline(newdeadline);
                }
            }
        }
    }

    /**
     * A method setting the estimated hours of a task by id
     * @param taskID the id of a task
     * @param estimatedHours the new estimated hours
     */
    public void setEstimatedHoursOfATask(String taskID, int estimatedHours){
        if(taskID != null && !(taskID.equals("") && estimatedHours >=0)){
            for(Task i : tasks){
                if (i.getTaskID().equals(taskID)){
                    i.setEstimatedHours(estimatedHours);
                }
            }
        }
    }

    /**
     * A method setting the description of a task by id
     * @param taskID the id of a task
     * @param newDescription the new description
     */
    public void setDescriptionOfATask(String taskID, String newDescription){
        if(taskID != null && !(taskID.equals("") && newDescription != null && !(newDescription.equals("")))){
            for(Task i : tasks){
                if (i.getTaskID().equals(taskID)){
                    i.setDescription(newDescription);
                }
            }
        }
    }

    /**
     * A method ending a task
     * @param taskID the id of a task
     */
    public void endTask(String taskID){
        if(taskID != null && !(taskID.equals(""))) {
            for(Task i : tasks){
                if (i.getTaskID().equals(taskID)){
                    i.finishTask();
                }
            }
        }
    }

    /**
     * A method getting a model.Task
     * @param taskID the id of a task
     * @return the task
     */
    public Task getTaskByID(String taskID){
        if(taskID != null && !(taskID.equals(""))){
            for(Task i : tasks){
                if (i.getTaskID().equals(taskID)){
                    return i;
                }
            }
        }

        return null;
    }

    /**
     * A method removing a task by id
     * @param taskID the id of a task
     */
    public void removeTaskByID(String taskID){
        if(taskID != null && !(taskID.equals(""))){
            for(Task i : tasks){
                if (i.getTaskID().equals(taskID)){
                    tasks.remove(i);
                }
            }
        }
    }
    public void removeTask(Task task){
        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i).equals(task)){
                tasks.remove(i);
                break;
            }
        }
    }
    /**
     * A method getting the status of a model.Task
     * @param taskID the id of a task
     * @return the status
     */
    public Status getTaskStatus(String taskID){
        if(taskID != null && !(taskID.equals(""))){
            for(Task i : tasks){
                if (i.getTaskID().equals(taskID)){
                    return i.getStatus();
                }
            }
        }
        return null;
    }

    /**
     * A method getting the hours spent on a task
     * @return the hours spent
     */
    public int getHoursSpent(){
        int hours = 0;
        for(Task i : tasks){
            hours += i.getTimeSpent();
        }
        return  hours;
    }

    /**
     * A method getting an array will all the finished tasks
     * @return the array
     */
    public ArrayList<Task> getFinishedTasks(){
        ArrayList<Task> finished = new ArrayList<>();
        for(Task i : tasks){
            if (i.getStatus().equals(Status.ENDED)){
                finished.add(i);
            }
        }
        return finished;
    }

    /**
     * A method getting an array will all the Active tasks
     * @return the array
     */
    public ArrayList<Task> getActiveTasks(){
        ArrayList<Task> Active = new ArrayList<>();
        for(Task i : tasks){
            if (i.getStatus().equals(Status.STARTED) || i.getStatus().equals(Status.NOTSTARTED)){
                Active.add(i);
            }
        }
        return Active;
    }
    @Override
    public String toString (){
        String text="";
        for (int i=0;i<tasks.size();i++){
            text+= tasks.get(i) + "\n";
        }
        return text;
    }

    public Task[] getAllTasks()
    {
        Task[] tasks1 = new Task[tasks.size()];
        for (int i = 0; i < tasks.size(); i++)
        {
            tasks1[i] = tasks.get(i);
        }
        return tasks1;
    }
}










