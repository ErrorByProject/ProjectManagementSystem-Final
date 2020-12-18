package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequirementTest {

    @Test
    void getName() {
        Requirement requirement = new Requirement("111","1111","name",new UserStory("what","who","why"),2.5,new Date(),1);
        Assertions.assertEquals("name",requirement.getName());
    }

    @Test
    void getProjectID() {
        Requirement requirement = new Requirement("111","1111","name",new UserStory("what","who","why"),2.5,new Date(),1);
        Assertions.assertEquals("111",requirement.getProjectID());
    }

    @Test
    void getOrderNum() {
        Requirement requirement = new Requirement("111","1111","name",new UserStory("what","who","why"),2.5,new Date(),1);
        Assertions.assertEquals(1,requirement.getOrderNum());
    }

    @Test
    void getEstimatedHours() {
        Requirement requirement = new Requirement("111","1111","name",new UserStory("what","who","why"),2.5,new Date(),1);
        Assertions.assertEquals(2.5,requirement.getEstimatedHours());
    }

    @Test
    void setName() {
        Requirement requirement = new Requirement("111","1111","name",new UserStory("what","who","why"),2.5,new Date(),1);
        requirement.setName("YO");
        Assertions.assertEquals("YO",requirement.getName());
    }

    @Test
    void setTeamMembers() {
        Requirement requirement = new Requirement("111","1111","name",new UserStory("what","who","why"),2.5,new Date(),1);
        requirement.setTeamMembers("HEY");
        Assertions.assertEquals("HEY",requirement.getTeamMembers());
    }

    @Test
    void setEstimatedHours() {
        Requirement requirement = new Requirement("111","1111","name",new UserStory("what","who","why"),2.5,new Date(),1);
        requirement.setEstimatedHours(5.5);
        Assertions.assertEquals(5.5,requirement.getEstimatedHours());
        requirement.setEstimatedHours(-1);
        Assertions.assertEquals(5.5,requirement.getEstimatedHours());
    }


    @Test
    void getRequirementID() {
        Requirement requirement = new Requirement("111","1111","name",new UserStory("what","who","why"),2.5,new Date(),1);
        Assertions.assertEquals("1111",requirement.getRequirementID());
    }

    @Test
    void getDescription() {
        Requirement requirement = new Requirement("111","1111","name",new UserStory("what","who","why"),2.5,new Date(),1);
        Assertions.assertEquals(new UserStory("what","who","why"),requirement.getDescription());
    }

    @Test
    void setOrderNum() {
        Requirement requirement = new Requirement("111","1111","name",new UserStory("what","who","why"),2.5,new Date(),1);
        requirement.setOrderNum(6666);
        Assertions.assertEquals(6666,requirement.getOrderNum());
    }

    @Test
    void getSpentHours() {
        Requirement requirement = new Requirement("111","1111","name",new UserStory("what","who","why"),2.5,new Date(),1);
        Assertions.assertEquals(0,requirement.getSpentHours());
    }

    @Test
    void testEquals() {
        Requirement requirement = new Requirement("111","1111","name",new UserStory("what","who","why"),2.5,new Date(),1);
        Requirement requirement1 = new Requirement("111","1111","name",new UserStory("what","who","why"),2.5,new Date(),1);
        Assertions.assertEquals(true,requirement.equals(requirement1));
        Requirement requirement2 = new Requirement("111","1111","name1",new UserStory("what","who","why"),2.5,new Date(),1);
        Assertions.assertEquals(false,requirement.equals(requirement2));
    }
}