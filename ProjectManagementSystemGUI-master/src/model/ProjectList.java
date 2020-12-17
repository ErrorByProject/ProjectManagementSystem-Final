package model;

import java.util.ArrayList;

/* Project list class, the class where all the projects are stored and all data related to the project is stored/updated
 *
 * @author Nicolae Cernean
 * @version 1.1 2020-12-07
 * @version 1.11 2020-12-10
 * Deleted exception at getProjectByID it was ending the for loop prematurely
 * added protections
 */
public class ProjectList
{
    private ArrayList<Project> projects;

    /**
     * Creating a list of projects
     */
    public ProjectList()
    {
        this.projects = new ArrayList<Project>();
    }

    /**
     * Adds a new project to the project list
     *
     * @param project - the project added to the list
     */
    public void addProject(Project project)
    {
        if (project != null)
        {
            projects.add(project);
        }
    }

    /**
     * Returns project by it's index
     *
     * @return project by index
     */
    public Project getProjectByIndex(int index)
    {
        for (int i = 0; i < projects.size(); i++)
        {
            if (i == index)
            {
                return projects.get(i);
            }
        }
        return null;
    }

    /**
     * Returns project ID
     *
     * @return project ID
     * @param project
     */
    public String getprojectID(Project project)
    {
        for (int i = 0; i < projects.size(); i++)
        {
            if (projects.get(i).getProjectID().equals(project.getProjectID()))
            {
                return projects.get(i).getProjectID();
            }
        }
        return null;
    }
    /**
     * Returns project's name
     *
     * @return project name
     * @param project
     */
    public String getProjectName(Project project)
    {
        for (int i = 0; i < projects.size(); i++)
        {
            if (projects.get(i).getName().equals(project.getName()))
            {
                return projects.get(i).getName();
            }
        }
        return null;
    }
    /**
     * Returns project's name
     *
     * @return project name
     * @param project
     */
    public String getProjectDescription(Project project)
    {
        for (int i = 0; i < projects.size(); i++)
        {
            if (projects.get(i).getDescription().equals(project.getDescription()))
            {
                return projects.get(i).getDescription();
            }
        }
        return null;
    }

    /**
     * Returns number of projects added to project list
     *
     * @return number of all projects
     */
    public int getNumberOfProjects()
    {
        return projects.size();
    }

    /**
     * Removes the project from project list
     *
     * @param projectID - the removed project
     */
    public Project getProjectByID(String projectID)
    {
        Project project = null;
        for (int i = 0; i < projects.size(); i++)
        {
            if (projects.get(i).getProjectID().equals(projectID))
            {
                project = projects.get(i);
            }
        }
        return project;
    }

    /**
     * Returns all the projects
     *
     * @return array with all projects
     */
    public Project[] getAllProjects()
    {
        Project[] projectList = new Project[projects.size()];
        for (int i = 0; i < projects.size(); i++)
        {
            projectList[i] = projects.get(i);
        }
        return projectList;
    }

    /**
     * Returns the project by it's name
     *
     * @param name of the project
     */
    public Project getProjectByName(String name)
    {
        for (int i = 0; i < projects.size(); i++)

            if (projects.get(i).getName().equals(name))
            {
                return projects.get(i);
            }
        //throw new IllegalArgumentException("Invalid Project Id");
        return null;
    }

    /**
     * Returns the project by it's ID
     *
     * @param projectID of the project
     * @return the project
     */
    public Status getProjectStatus(String projectID)
    {
        return getProjectByID(projectID).getProjectStatus();
    }

    /**
     * Returns the project from project list
     *
     * @param projectID of the project
     */
    public void removeProject(String projectID)
    {
        if (projectID != null && !projectID.equals("") && projectID.length() == 3)
        {
            projects.remove(getProjectByID(projectID));
        }
    }

    /**
     * Edits/Updates the deadline of the project
     *
     * @param projectID   of the project
     * @param newDeadline new deadline of the project
     */
    public void editDeadlineOfAProject(String projectID, Date newDeadline)
    {
        if (projectID != null && !projectID.equals("") && projectID.length() == 3
                && newDeadline != null)
        {
            getProjectByID(projectID).setDeadline(newDeadline);
        }
    }

    /**
     * Edits/Updates the description of the project
     *
     * @param projectID      of the project
     * @param newDescription new description of the project
     */
    public void editUserStoryOfTheProject(String projectID, String newDescription)
    {
        if (projectID != null && !projectID.equals("") && projectID.length() == 3
                && newDescription != null && !newDescription.equals(""))
        {
            getProjectByID(projectID).setDescription(newDescription);
        }
    }

    /**
     * Edits/Updates the description of the project
     *
     * @return array with all active projects
     */
    public Project[] getAllActiveProjects()
    {
        Project[] projectList = new Project[projects.size()];
        for (int i = 0; i < projects.size(); i++)
        {
    /*  if (projects.get(i).getProjectStatus().equals(Status.ENDED))
      {
        throw new IllegalArgumentException("No active project");
      }
      else
      {
        projectList[i] = projects.get(i);
      }*/
            if (!(projects.get(i).getProjectStatus().equals(Status.ENDED)))
            {
                projectList[i] = projects.get(i);

            }
        }
        return projectList;
    }

    /**
     * Returns requirements of a project
     *
     * @param projectID of the project
     * @return all requirements of the project
     */
    public RequirementList getRequirementsOfAProject(String projectID)
    {
        if (projectID != null && !projectID.equals("") && projectID.length() == 3)
        {
            return getProjectByID(projectID).getRequirements();
        }
        return null;
    }

    /**
     * Returns requirements of a project by importance
     *
     * @param projectID of the project
     * @return array with all requirements by importance
     */
    public RequirementList getRequirementsByImportance(String projectID)
    {
        if (projectID != null && !projectID.equals("") && projectID.length() == 3)
        {
            return getProjectByID(projectID).getRequirementsByImportance();
        }
        return null;
    }

    /**
     * Returns all team members of a project
     *
     * @param projectID of the project
     * @return array with all team members
     */
    public Team getTeamMembersOfAProject(String projectID)
    {
        if (projectID != null && !projectID.equals("") && projectID.length() == 3)
        {
            return getProjectByID(projectID).getAllMembers();
        }
        return null;
    }

}
