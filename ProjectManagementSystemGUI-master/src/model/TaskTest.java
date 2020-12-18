package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getTaskID() {
        Task task = new Task("111","1111","label","description",new Date(),5.5,Status.NOTSTARTED);
        Assertions.assertEquals("111",task.getTaskID());
    }

    @Test
    void getLabelName() {
        Task task = new Task("111","1111","label","description",new Date(),5.5,Status.NOTSTARTED);
        Assertions.assertEquals("label",task.getLabelName());
    }

    @Test
    void getRequirementID() {
        Task task = new Task("111","1111","label","description",new Date(),5.5,Status.NOTSTARTED);
        Assertions.assertEquals("1111",task.getRequirementID());
    }

    @Test
    void getDescription() {
        Task task = new Task("111","1111","label","description",new Date(),5.5,Status.NOTSTARTED);
    Assertions.assertEquals("description",task.getDescription());
    }

    @Test
    void getEstimatedHours() {
        Task task = new Task("111","1111","label","description",new Date(),5.5,Status.NOTSTARTED);
        Assertions.assertEquals(5.5,task.getEstimatedHours());
    }

    @Test
    void getTimeSpent() {
        Task task = new Task("111","1111","label","description",new Date(),5.5,Status.NOTSTARTED);
        Assertions.assertEquals(0,task.getTimeSpent());
        task.setHoursSpent(5.5);
        Assertions.assertEquals(5.5,task.getTimeSpent());
        task.setHoursSpent(-1);
        Assertions.assertEquals(4.5,task.getTimeSpent());
        task.setHoursSpent(-5);
        Assertions.assertEquals(4.5,task.getTimeSpent()); // because 4.5-5<0 has to be hoursSpent>0
    }

    @Test
    void getDeadline() {
        Task task = new Task("111","1111","label","description",new Date(),5.5,Status.NOTSTARTED);
        Assertions.assertEquals(new Date(),task.getDeadline());
    }

    @Test
    void getStatus() {
        Task task = new Task("111","1111","label","description",new Date(),5.5,Status.NOTSTARTED);
        Assertions.assertEquals(Status.NOTSTARTED,task.getStatus());
    }

    @Test
    void getTeamMembers() {
        Task task = new Task("111","1111","label","description",new Date(),5.5,Status.NOTSTARTED);
        task.setTeamMembers("HEY");
        Assertions.assertEquals("HEY",task.getTeamMembers());
        Assertions.assertEquals(Status.STARTED,task.getStatus());
    }

    @Test
    void setTaskID() {
        Task task = new Task("111","1111","label","description",new Date(),5.5,Status.NOTSTARTED);
        task.setTaskID("212");
        Assertions.assertEquals("212",task.getTaskID());
    }


    @Test
    void setEstimatedHours() {
        Task task = new Task("111","1111","label","description",new Date(),5.5,Status.NOTSTARTED);
        task.setEstimatedHours(5.5);
        Assertions.assertEquals(5.5,task.getEstimatedHours());
    }


    @Test
    void finishTask() {
        Task task = new Task("111","1111","label","description",new Date(),5.5,Status.NOTSTARTED);
        task.finishTask();
        Assertions.assertEquals(Status.ENDED,task.getStatus());
    }


    @Test
    void testEquals() {
        Task task = new Task("111","1111","label","description",new Date(),5.5,Status.NOTSTARTED);
        Task task1 = new Task("111","1111","label","description",new Date(),5.5,Status.NOTSTARTED);
        Task task2 = new Task("1511","1111","label","description",new Date(),5.5,Status.NOTSTARTED);
        Assertions.assertEquals(true,task.equals(task1));
        Assertions.assertEquals(false,task.equals(task2));
    }
}