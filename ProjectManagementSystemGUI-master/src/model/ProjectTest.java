package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    void getName() {
        Project project = new Project("name","111","description",new Date(),2.5,Status.STARTED);
        Assertions.assertEquals("name",project.getName());

    }

    @Test
    void getTeamMembers() {
        Project project = new Project("name","111","description",new Date(),2.5,Status.STARTED);
        project.setTeamMembers("HEYY");
        Assertions.assertEquals("HEYY",project.getTeamMembers());
    }

    @Test
    void setName() {
        Project project = new Project("name","111","description",new Date(),2.5,Status.STARTED);
        project.setName("1111");
        Assertions.assertEquals("1111",project.getName());
    }

    @Test
    void getDescription() {
        Project project = new Project("name","111","description",new Date(),2.5,Status.STARTED);
        Assertions.assertEquals("description",project.getDescription());
    }

    @Test
    void setDescription() {
        Project project = new Project("name","111","description",new Date(),2.5,Status.STARTED);
        project.setDescription("testest");
        Assertions.assertEquals("testest",project.getDescription());
    }

    @Test
    void getProjectID() {
        Project project = new Project("name","111","description",new Date(),2.5,Status.STARTED);
        Assertions.assertEquals("111",project.getProjectID());
    }

    @Test
    void setProjectID() {
        Project project = new Project("name","111","description",new Date(),2.5,Status.STARTED);
       project.setProjectID("333");
        Assertions.assertEquals("333",project.getProjectID());
        project.setProjectID("5555");
        Assertions.assertEquals("333",project.getProjectID());
        project.setProjectID("55");
        Assertions.assertEquals("333",project.getProjectID());
    }

    @Test
    void setEstimatedHours() {
        Project project = new Project("name","111","description",new Date(),2.5,Status.STARTED);
        Assertions.assertEquals(2.5,project.getEstimatedHours());
        project.setEstimatedHours(-1);
        Assertions.assertEquals(2.5,project.getEstimatedHours());
        project.setEstimatedHours(9.5);
        Assertions.assertEquals(9.5,project.getEstimatedHours());
    }






    @Test
    void getProjectStatus() {
        Project project = new Project("name","111","description",new Date(),2.5,Status.STARTED);
        Assertions.assertEquals(Status.ENDED,project.getProjectStatus()); // because has no active requirements.
    }

    @Test
    void getHoursSpentOnProject() {
        Project project = new Project("name","111","description",new Date(),2.5,Status.STARTED);
        Assertions.assertEquals(0,project.getHoursSpentOnProject());
    }
}